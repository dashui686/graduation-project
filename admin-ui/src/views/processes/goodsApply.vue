
<template>
  <div>
    <el-form ref="goodsForm" :model="goodsFormData" :rules="goods" size="medium" label-width="100px">
      <el-form-item label="仓库选择" prop="warehouseId">
        <el-select v-model="goodsFormData.warehouseId" placeholder="请选择仓库选择" clearable
          :style="{width: '100%'}">
          <el-option v-for="(item, index) in warehouseIdOptions" :key="index" :label="item.label"
            :value="item.id" :disabled="item.disabled"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="物品选择" prop="goodsId">
        <el-select v-model="goodsFormData.goodsId" placeholder="请选择物品" clearable :style="{width: '100%'}">
          <el-option v-for="(item, index) in goodsIdOptions" :key="index" :label="item.goodsName"
            :value="item.goodsId" :disabled="item.disabled"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="数量" prop="count">
        <el-input-number v-model="goodsFormData.count" :max="goodsMax" :min="0" placeholder="数量" :step='1'></el-input-number> （最大数{{this.goodsMax}}）
      </el-form-item>
      <el-form-item label="申请理由" prop="reason">
        <el-input v-model="goodsFormData.reason" type="textarea" placeholder="请输入申请理由"
          :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item label="审批人" prop="assigness">
        <el-select v-model="goodsFormData.assigness" placeholder="请选择审批人" clearable :style="{width: '100%'}">
          <el-option v-for="(item, index) in assignessOptions" :key="index" :label="item.label"
            :value="item.value" :disabled="item.disabled"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import {  treeselect as warehouseTreeselect } from "@/api/drp/warehouse";
  import {  queryByWarehouseId } from "@/api/drp/goods";
export default {
  components: {},
  props: [],
  data() {
    return {
      goodsMax:0,
      goodsFormData: {
        warehouseId: undefined,
        goodsId: undefined,
        count: undefined,
        reason: undefined,
        assigness: undefined,
      },
      goods: {
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
        reason: [{
          required: true,
          message: '请输入申请理由',
          trigger: 'blur'
        }],
        assigness: [{
          required: true,
          message: '请选择审批人',
          trigger: 'change'
        }],
      },
      warehouseIdOptions: [],
      goodsIdOptions: [],
      assignessOptions: [{
        "label": "选项一",
        "value": 1
      }, {
        "label": "选项二",
        "value": 2
      }],
    }
  },
  computed: {},
  watch: {
      'goodsFormData.warehouseId':{
      handler (newVal) {
        console.log(newVal)
        if(newVal != undefined){
          this.goodsFormData.goodsId = undefined;
          this.goodsIdOptions = [];
          queryByWarehouseId(newVal).then(res=>{
            this.goodsIdOptions = res.data; 
          })
        }
	      
      },
      deep: true,
      immediate: true
    },
    'goodsFormData.goodsId':function(newVal){
      if(newVal ==undefined) return ;
      let a = this.goodsIdOptions.find(obj=>{
        return obj.goodsId == newVal;
      })
      console.log(a);
      this.goodsMax = a.goodsCount;
    }
  },
  created() {
    this.warehouseTreeselectList();
  },
  mounted() {},
  methods: {
    warehouseTreeselectList(){
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