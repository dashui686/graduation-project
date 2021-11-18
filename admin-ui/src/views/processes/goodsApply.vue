<template>
  <div>
    <el-form ref="goodsForm" :model="goodsFormData" :rules="this.Approve?goods1:goods" size="medium" label-width="100px">
      <el-form-item label="仓库选择" prop="warehouseId">
        <el-select v-model="goodsFormData.warehouseId" :disabled="this.isApprove" placeholder="请选择仓库选择" clearable
          :style="{width: '100%'}">
          <el-option v-for="(item, index) in warehouseIdOptions" :key="index" :label="item.label"
            :value="item.id" :disabled="item.disabled"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="物品选择" prop="goodsId">
        <el-select v-model="goodsFormData.goodsId" placeholder="请选择物品" :disabled="this.isApprove" clearable :style="{width: '100%'}">
          <el-option v-for="(item, index) in goodsIdOptions" :key="index"  :label="item.goodsName"
            :value="item.goodsId" :disabled="item.disabled"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="数量" prop="count">
        <el-input-number v-model="goodsFormData.count" :max="goodsMax" :disabled="this.isApprove" :min="0" placeholder="数量" :step='1'></el-input-number> （最大数{{this.goodsMax}}）
      </el-form-item>
      <el-form-item label="申请理由" prop="reason">
        <el-input v-model="goodsFormData.reason" :disabled="this.isApprove" type="textarea" placeholder="请输入申请理由"
          :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item label="审批人" prop="assignee">
        <el-select v-model="goodsFormData.assignee" placeholder="请选择审批人" clearable :style="{width: '100%'}">
          <el-option v-for="(item, index) in assigneeOptions" :key="index" :label="item.label"
            :value="item.value" :disabled="item.disabled"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item size="large"  v-if="!this.isApprove">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>

      <el-form-item size="large" v-else>
        <el-button type="primary" @click="Approve(true)" :disabled="this.view">通过</el-button>
        <el-button @click="Approve(false)" :disabled="this.view">拒绝</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  import {  treeselect as warehouseTreeselect } from "@/api/drp/warehouse";
  import {  queryByWarehouseId } from "@/api/drp/goods";
  import { listByOption } from "@/api/system/user";

  import { addLeave,getBusiness,approveProcess,getById } from "@/api/process/goodsApply";
export default {
  components: {},
  props: [],
  data() {
    return {
      isApprove:false,
      view:false,
      goodsMax:0,
      goodsFormData: {
        warehouseId: undefined,
        goodsId: undefined,
        count: undefined,
        reason: undefined,
        assignee: undefined,
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
        assignee: [{
          required: true,
          message: '请选择审批人',
          trigger: 'change'
        }],
      },
      goods1: {
      },
      warehouseIdOptions: [],
      goodsIdOptions: [],
      assigneeOptions: [{
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
        console.log(newVal,111)
        if(newVal != undefined){
          if(this.isApprove == false) this.goodsFormData.goodsId = undefined;
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
      console.log(a,1111);
      if(this.isApprove == false) this.goodsMax = a.goodsCount;
    }
  },
  created() {
    this.getOption();
    this.warehouseTreeselectList();
    this.$nextTick(()=>{
      let aa = this.$route.query.bskey;
      if(aa ==undefined){
        this.getBusi(this.$route.params.id);
      console.log(this.$route)

      }else{
        this.getBusiness(aa);
      }
    });
    
  },
  mounted() {},
  methods: {
    Approve(bool){
      this.goodsFormData.processInstanceId = this.$route.params.id;
      this.goodsFormData.state = this.goodsFormData.assignee==undefined||this.goodsFormData.assignee==''?2:1;
      console.log(this.goodsFormData.state)
      
      this.goodsFormData.taskId = this.$route.query.taskId;
      this.goodsFormData.approveReason = "无";
      if(bool){
      this.$refs['goodsForm'].validate(valid => {
        if (!valid) return
        // 提交同意表单
        approveProcess(this.goodsFormData).then(res=>{
          this.$modal.msgSuccess("审批成功，请关闭页面");
          console.log(res);
        })
      })
      }else{
        console.log(2)
      }
    },

    getBusiness(id){
       if(id == 0) return ;
        getById(id).then(response => {
          this.goodsFormData = response.data;
          this.isApprove = true;
          this.view = true;
          this.$nextTick(()=>{
            this.goodsFormData.goodsId = response.data.goodsId;
          })

        });
    },
    getBusi(id){
      if(id == 0){
        return ;
      }else{
        getBusiness(id).then(response => {
          console.log(response)
          this.goodsMax = response.data.count;
          this.goodsFormData = response.data;
          this.isApprove = true;
          this.$nextTick(()=>{
            this.goodsFormData.goodsId = response.data.goodsId;
          })
        });
      }
    },
    getOption(){
      listByOption().then(response => {
        this.assigneeOptions = response.data
      });
    },
    warehouseTreeselectList(){
      warehouseTreeselect().then(res=>{
        this.warehouseIdOptions = res.data;
      })
    },
    submitForm() {
      this.$refs['goodsForm'].validate(valid => {
        if (!valid) return
        // TODO 提交表单
        if (this.goodsFormData.count <= 0 ) return
        // TODO 提交表单
        addLeave(this.goodsFormData).then(res=>{
           this.$modal.msgSuccess("申请成功");
        })
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