<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--部门数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
            v-model="deptName"
            placeholder="请输入部门名称"
            clearable
            size="small"
            prefix-icon="el-icon-search"
            style="margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree
            :data="deptOptions"
            :props="defaultProps"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            ref="tree"
            default-expand-all
            @node-click="handleNodeClick"
          />
        </div>
      </el-col>
      <!--商品数据-->
      <el-col :span="20" :xs="24">
        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="商品ID" prop="goodsId">
            <el-input
              v-model="queryParams.goodsId"
              placeholder="请输入商品ID"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="商品名称" prop="goodsName">
            <el-input
              v-model="queryParams.goodsName"
              placeholder="请输入商品名称"
              clearable
              size="small"
              style="width: 240px"
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
              v-hasPermi="['system:user:add']"
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
              v-hasPermi="['system:user:edit']"
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
              v-hasPermi="['system:user:remove']"
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
              v-hasPermi="['system:user:export']"
            >导出</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="商品ID" align="center" key="goodsId" prop="goodsId" v-if="columns[0].visible" />
          <el-table-column label="商品名称" align="center" key="goodsName" prop="goodsName" v-if="columns[1].visible" :show-overflow-tooltip="true" />
          <el-table-column label="商品分类" align="center" key="goodsTypeName" prop="goodsTypeName" v-if="columns[2].visible" :show-overflow-tooltip="true" />
          <el-table-column label="所属仓库" align="center" key="warehouseName" prop="warehouseName" v-if="columns[2].visible" :show-overflow-tooltip="true" />
          <el-table-column label="商品数量" align="center" key="goodsCount" prop="goodsCount" v-if="columns[3].visible" :show-overflow-tooltip="true" />
          <el-table-column label="商品产地" align="center" key="goodsLocality" prop="goodsLocality" v-if="columns[4].visible" width="120" />
          <el-table-column label="商品单位" align="center" key="goodsUnit" prop="goodsUnit" v-if="columns[5].visible" width="120" />

          <el-table-column label="创建时间" align="center" prop="createDate" v-if="columns[6].visible" width="160">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.createDate) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="更新时间" align="center" prop="updateDate" v-if="columns[6].visible" width="160">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.updateDate) }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
            align="center"
            width="160"
            class-name="small-padding fixed-width"
          >
            <template slot-scope="scope" v-if="scope.row.userId !== 1">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['system:user:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['system:user:remove']"
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
      </el-col>
    </el-row>

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="商品名称" prop="goodsName">
              <el-input v-model="form.goodsName" placeholder="请输入商品名称" maxlength="30" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="归属仓库" prop="warehouseId">
              <treeselect v-model="form.warehouseId" :options="deptOptions" :show-count="true" placeholder="请选择归属仓库" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="商品类型" prop="goodsTypeId">
              <treeselect v-model="form.goodsTypeId" :options="goodsTypeOptions" :show-count="true" placeholder="请选择商品类型" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="商品数量" prop="goodsCount">
              <el-input v-model.number="form.goodsCount" type="number" placeholder="要跟商品名称一样" maxlength="11" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="商品生产地" prop="goodsLocality">
              <el-input v-model="form.goodsLocality" placeholder="请输入商品生产地" maxlength="50" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="商品单位" prop="goodsUnit">
              <el-input v-model="form.goodsUnit" placeholder="请输入商品单位" maxlength="11" />
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
  import {  treeselect } from "@/api/drp/warehouse";
  import {  listGoods ,addGoods, getOneGoods, treeselect2, updateGoods, delGoods} from "@/api/drp/goods";
  import { getToken } from "@/utils/auth";
  import Treeselect from "@riophae/vue-treeselect";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";

  export default {
    name: "User",
    dicts: ['sys_normal_disable', 'sys_user_sex'],
    components: { Treeselect },
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
        // 商品表格数据
        userList: null,
        // 弹出层标题
        title: "",
        // 部门树选项
        deptOptions: undefined,
        goodsTypeOptions: undefined,
        // 是否显示弹出层
        open: false,
        // 部门名称
        deptName: undefined,

        // 表单参数
        form: {},
        defaultProps: {
          children: "children",
          label: "label"
        },

        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          goodsId: undefined,
          goodsName: undefined,
          warehouseId: undefined
        },
        // 列信息
        columns: [
          { key: 0, label: `商品数量`, visible: true },
          { key: 1, label: `商品名称`, visible: true },
          { key: 2, label: `商品昵称`, visible: true },
          { key: 3, label: `部门`, visible: true },
          { key: 4, label: `手机号码`, visible: true },
          { key: 5, label: `状态`, visible: true },
          { key: 6, label: `创建时间`, visible: true }
        ],
        // 表单校验
        rules: {
          userName: [
            { required: true, message: "商品名称不能为空", trigger: "blur" },
            { min: 2, max: 20, message: '商品名称长度必须介于 2 和 20 之间', trigger: 'blur' }
          ],
          goodsCount: [
            {
              required: true, message: "商品数量只能是数字", trigger: "blur" }
          ],


        }
      };
    },
    watch: {
      // 根据名称筛选部门树
      deptName(val) {
        this.$refs.tree.filter(val);
      }
    },
    created() {
      this.getTreeselect();
      this.getTreeselect2();
      this.getList();

    },
    methods: {
      /** 查询商品列表 */
      getList() {
        this.loading = true;
        listGoods(this.queryParams).then(response => {
          console.log(response)
            this.userList = response.data;
            // this.total = response.total;
            this.loading = false;
          }
        );
      },
      /** 查询部门下拉树结构 */
      getTreeselect() {
        treeselect().then(response => {
          this.deptOptions = response.data;
        });
      },
      /** 查询部门下拉树结构 */
      getTreeselect2() {
        treeselect2().then(response => {
          this.goodsTypeOptions = response.data;
        });
      },
      // 筛选节点
      filterNode(value, data) {
        if (!value) return true;
        return data.label.indexOf(value) !== -1;
      },
      // 节点单击事件
      handleNodeClick(data) {
        this.queryParams.warehouseId = data.id;
        this.getList();
      },

      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {};
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.dateRange = [];
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.goodsId);
        this.single = selection.length != 1;
        this.multiple = !selection.length;
      },
      // 更多操作触发
      handleCommand(command, row) {
        switch (command) {
          case "handleResetPwd":
            this.handleResetPwd(row);
            break;
          case "handleAuthRole":
            this.handleAuthRole(row);
            break;
          default:
            break;
        }
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加用户";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const userId = row.goodsId || this.ids;
        getOneGoods(userId).then(response => {
          console.log(response)
           this.form = response.data;
          this.open = true;
          this.title = "修改商品";
        });
      },

      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.goodsId != undefined) {
              updateGoods(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addGoods(this.form).then(response => {
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
        const userIds = row.goodsId || this.ids;
        this.$modal.confirm('是否确认删除商品编号为"' + userIds + '"的数据项？').then(function() {
          return delGoods(userIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$modal.confirm('是否确认导出所有商品数据项？').then(() => {
          this.exportLoading = true;
          return exportUser(queryParams);
        }).then(response => {
          this.$download.name(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
      },

    }
  };
</script>
