<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="流程名称" prop="processName">
        <el-input
          v-model="queryParams.processName"
          placeholder="请输入流程名称"
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

    <el-table v-loading="loading" :data="postList" >
      <el-table-column label="流程名称" align="center" prop="processName" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.processState"/>
        </template>
      </el-table-column>
      
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <router-link class="sidebar-logo-link" :to="'/process/processes/'+scope.row.processZip">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
            >查看</el-button>
          </router-link>
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
  </div>
</template>

<script>
import { getMyProcess } from "@/api/process/process.js";
import AppLink from '../../layout/components/Sidebar/Link.vue'

export default {
  name: "backlogProcess",
  components:{AppLink},
  dicts: ['sys_normal_disable'],
  data() {
    return {
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 岗位表格数据
      postList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        processName: undefined,
        processState: 0,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        processName: [
          { required: true, message: "流程名称不能为空", trigger: "blur" }
        ],
        processZip: [
          { required: true, message: "流程路径不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询岗位列表 */
    getList() {
      this.loading = true;
      getMyProcess(this.queryParams).then(res =>{
        console.log(res)
        this.postList = res.data;
        // this.total = res.data.total;
        this.loading = false;
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        processName: undefined
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

   handleLink(row){
     console.log(row)
   }
  }
};
</script>
<style scoped>
</style>
