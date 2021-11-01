<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="供应商ID" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入供应商ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入供应商名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:post:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:post:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:post:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['system:post:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="postList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="供应商ID" align="center" prop="id" />
      <el-table-column label="供应商名称" align="center" prop="name" />
      <el-table-column label="联系人" align="center" prop="contacts" />
      <el-table-column label="联系人职务" align="center" prop="contactsPoition" />
      <el-table-column label="联系人电话" align="center" prop="contactsPhone" />
      <el-table-column label="地址" align="center" prop="address" />
      <el-table-column label="银行卡号" align="center" prop="bankAccount" />
      <el-table-column label="开户名称" align="center" prop="bankName" />
      <el-table-column label="最后交易日期" align="center" prop="lasttradingDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lasttradingDate) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后交易金额" align="center" prop="lasttradingMoney" />
      <el-table-column label="创建时间" align="center" prop="createDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createDate) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建人" align="center" prop="createUsers" />
      <el-table-column label="更新时间" align="center" prop="updateDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateDate) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" align="center" prop="updateUsers" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="联系人部门" align="center" prop="contactsDepartment" />
      <el-table-column label="关系类型" align="center" prop="type" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:post:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:post:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改供应商对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="供应商名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入供应商名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系人" prop="contacts">
              <el-input v-model="form.contacts" placeholder="请输入联系人" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="联系人职务" prop="contactsPoition">
              <el-input v-model="form.contactsPoition" placeholder="请输入联系人职务" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系人电话" prop="contactsPhone">
              <el-input v-model="form.contactsPhone" placeholder="请输入联系人电话" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="地址" prop="address">
              <el-input v-model="form.address" placeholder="请输入地址" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="银行卡号" prop="bankAccount">
              <el-input v-model="form.bankAccount" placeholder="请输入银行卡号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="开户名称" prop="bankName">
              <el-input v-model="form.bankName" placeholder="请输入开户名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="最后交易金额" prop="lasttradingMoney">
              <el-input v-model="form.lasttradingMoney" placeholder="请输入最后交易金额" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="创建人" prop="createUsers">
              <el-input v-model="form.createUsers" placeholder="请输入创建人" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="更新人" prop="updateUsers">
              <el-input v-model="form.updateUsers" placeholder="请输入更新人" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="联系人部门" prop="contactsDepartment">
              <el-input v-model="form.contactsDepartment" placeholder="请输入联系人部门" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="关系类型" prop="type">
              <el-input v-model="form.type" placeholder="请输入关系类型" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  //import { listWarehouse,addWarehouse,updateWarehouse,getWarehouse,delWarehouse} from "@/api/drp/warehouse";
  import { listRelation, getOne ,addRelation, updateRelation ,delRelation} from "@/api/drp/relation";

  export default {
    name: "Post",
    dicts: ['sys_normal_disable'],
    data() {
      return {
        // 遮罩层
        loading: true,
        // 导出遮罩层
        exportLoading: false,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 供应商表格数据
        postList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          id: undefined,
          name: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          postName: [
            { required: true, message: "供应商名称不能为空", trigger: "blur" }
          ],
          postCode: [
            { required: true, message: "供应商编码不能为空", trigger: "blur" }
          ]
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询供应商列表 */
      getList() {
        this.loading = true;
        listRelation(this.queryParams).then(res => {
          console.log(res)
          this.postList = res.data.records;
          this.total = res.data.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          postId: undefined,
          postCode: undefined,
          postName: undefined,
          postSort: 0,
          status: "0",
          remark: undefined
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length!=1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加供应商";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const postId = row.id || this.ids
        getOne(postId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改供应商";
        });
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != undefined) {
              updateRelation(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addRelation(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        console.log(row)
        const postIds = row.id || this.ids;
        this.$modal.confirm('是否确认删除供应商编号为"' + postIds + '"的数据项？').then(function() {
          return delRelation(postIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$modal.confirm('是否确认导出所有供应商数据项？').then(() => {
          this.exportLoading = true;
          return exportPost(queryParams);
        }).then(response => {
          this.$download.name(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
      }
    }
  };
</script>
