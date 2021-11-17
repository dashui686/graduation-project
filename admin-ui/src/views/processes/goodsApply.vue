<template>
  <div class="contain" style="width: 1100px;margin: 20px auto;">
    <el-form ref="goodsForm" :model="goodsFormData" :rules="goods" size="medium" label-width="100px">
      <el-form-item label="申请物品" prop="goodsList">
        <el-button type="primary"  @click="dialogFormVisible = true">添加商品</el-button>
        <el-table
          :data="goodsFormData.tableData"
          style="width:1100px"
          max-height="250">
          <el-table-column
            prop="date"
            label="日期"
            width="150">
          </el-table-column>
          <el-table-column
            prop="name"
            label="姓名"
            width="120">
          </el-table-column>
          <el-table-column
            prop="province"
            label="省份"
            width="120">
          </el-table-column>
          <el-table-column
            prop="city"
            label="市区"
            width="120">
          </el-table-column>
          <el-table-column
            prop="address"
            label="地址"
            width="300">
          </el-table-column>
          <el-table-column
            prop="zip"
            label="邮编"
            width="120">
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            width="120">
            <template slot-scope="scope">
              <el-button
                @click.native.prevent="deleteRow(scope.$index, tableData)"
                type="text"
                size="small">
                移除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-form-item>
      <el-form-item label="申请理由" prop="reason">
        <el-input v-model="goodsFormData.reason" type="textarea" placeholder="请输入申请理由"
          :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item label="审批人" prop="assignee">
        <el-select v-model="goodsFormData.assignee" placeholder="请选择审批人" clearable :style="{width: '100%'}">
          <el-option v-for="(item, index) in assigneeOptions" :key="index" :label="item.label"
            :value="item.value" :disabled="item.disabled"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>


          <el-dialog :visible.sync="dialogFormVisible" @close="onClose" title="添加物品">
      <el-form ref="goodsForms" :model="goodsFormDataDialog" :rules="goodsDialog" size="medium" label-width="100px">
        <el-form-item label="仓库选择" prop="warehouseId">
          <el-select v-model="goodsFormDataDialog.warehouseId" placeholder="请选择仓库选择" clearable
            :style="{width: '100%'}">
            <el-option v-for="(item, index) in warehouseIdOptions" :key="index" :label="item.label"
              :value="item.id" :disabled="item.disabled"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="物品选择" prop="goodsId">
          <el-select v-model="goodsFormDataDialog.goodsId" placeholder="请选择物品" clearable :style="{width: '100%'}">
            <el-option v-for="(item, index) in goodsIdOptions" :key="index" :label="item.goodsName"
              :value="item.goodsId" :disabled="item.disabled"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="数量" prop="count">
          <el-input-number v-model="goodsFormDataDialog.count" :min="0" :max="goodsMax" placeholder="数量" :step='1'></el-input-number>
        </el-form-item>
        <el-form-item label="单位" prop="goodsUnit">
          <el-input v-model="goodsFormDataDialog.goodsUnit" placeholder="请选择物品" :disabled='true' clearable
            :style="{width: '100%'}"></el-input>
        </el-form-item>
        <el-form-item label="物品分类" prop="goodsTypeName">
          <el-input v-model="goodsFormDataDialog.goodsTypeName" placeholder="请选择物品" :disabled='true' clearable
            :style="{width: '100%'}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {  treeselect as warehouseTreeselect } from "@/api/drp/warehouse";
  import {  queryByWarehouseId } from "@/api/drp/goods";
export default {
  name:"goodsApply",
  components: {},
  props: [],

  data() {


    return {

       goodsMax:0,
       goodsFormDataDialog: {
        warehouseId: undefined,
        goodsId: undefined,
        count: 0,
        goodsUnit: undefined,
        goodsTypeName: undefined,
      },
      goodsDialog: {
        warehouseId: [{
          required: true,
          message: '请选择仓库选择',
          trigger: 'change'
        }],
        goodsId: [{
          required: true,
          message: '请选择物品',
          trigger: 'change'
        }],
        count: [{
          required: true,
          message: '数量',
          trigger: 'blur'
        }],
        goodsUnit: [{
          required: true,
          message: '请选择物品',
          trigger: 'blur'
        }],
        goodsTypeId: [{
          required: true,
          message: '请选择物品',
          trigger: 'blur'
        }],
      },
      warehouseIdOptions: [],
      goodsIdOptions: [],

      dialogFormVisible:false,
      formLabelWidth:'120px',
      goods:{

      },
      goodsFormData: {
        reason: undefined,
        assignee: undefined,
        tableData:undefined,
      },
      goods: {
        reason: [{
          required: true,
          message: '请输入申请理由',
          trigger: 'blur'
        }],
        assignee: [{
          required: true,
          message: '请选择审批人',
          trigger: 'blur'
        }],
        goodsList:[
          {
          required: true,
          message: '请选择物品',
          trigger: 'blur'
        }
        ]
      },
      assigneeOptions: [],
    }
  },
  computed: {},
    watch:{
    'goodsFormDataDialog.warehouseId':{
      handler (newVal) {
        if(newVal != undefined){
          this.goodsIdOptions = [];
          queryByWarehouseId(newVal).then(res=>{
          this.goodsIdOptions = res.data; 
          })
        }
	      
      },
      deep: true,
      immediate: true
    },
    'goodsFormDataDialog.goodsId':function(newVal){
      if(newVal ==undefined) return ;
      this.goodsFormDataDialog.count = 0;
      console.log(newVal);
      let a = this.goodsIdOptions.find(obj=>{
        return obj.goodsId == newVal;
      })
      console.log(a);
      this.goodsMax = a.goodsCount;
      this.goodsFormDataDialog.goodsUnit = a.goodsUnit;
      this.goodsFormDataDialog.goodsTypeName = a.goodsTypeName;

    }
  },
  created() {
    this.warehouseTreeselectList();
  },
  mounted() {},
  methods: {
    onClose() {
      this.$refs['goodsForms'].resetFields()
    },
    close() {
      this.$refs['goodsForms'].resetFields()
      this.dialogFormVisible=false;
    },
    handelConfirm() {
      this.$refs['goodsForms'].validate(valid => {
        if (!valid) return
        this.close()
      })
    },
    deleteRow(index, rows) {
      rows.splice(index, 1);
    },
    warehouseTreeselectList(){
      warehouseTreeselect().then(res=>{
        this.warehouseIdOptions = res.data;
      })
    },
    goodsTreeselectList(){
      warehouseTreeselect().then(res=>{
        this.warehouseIdOptions = res.data;
      })
    },
    submitForm() {
      this.$refs['goodsForm'].validate(valid => {
        if (!valid) return
        // TODO 提交表单
      })
    },
    resetForm() {
      this.$refs['goodsForm'].resetFields()
    },
  }
}

</script>
<style>
</style>
