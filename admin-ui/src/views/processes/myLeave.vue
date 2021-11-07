<template>
  <div class="continer">
    <div class="inner">
    <el-form ref="leaveForm" :model="leaveFormData" :rules="isApprove?rules1:rules" size="medium" label-width="100px">
      <el-form-item label="请假类型" prop="type" >
        <el-select v-model="leaveFormData.type" placeholder="请选择请假类型" clearable :style="{width: '100%'}" :disabled="this.isApprove">
          <el-option v-for="(item, index) in typeOptions" :key="index" :label="item.label" :value="item.value"
            :disabled="item.disabled"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="开始时间" prop="beginDate">
        <el-date-picker v-model="leaveFormData.beginDate" :disabled="this.isApprove"  type="datetime" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss"
          :style="{width: '100%'}" placeholder="请选择开始时间" clearable></el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="endDate">
        <el-date-picker v-model="leaveFormData.endDate" :disabled="this.isApprove"  type="datetime" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss"
          :style="{width: '100%'}" placeholder="请选择结束时间" clearable></el-date-picker>
      </el-form-item>
      <el-form-item label="时长(小时)" prop="duration">
        <el-input-number v-model="leaveFormData.duration" disabled placeholder="时长(小时)" :step='1' :precision='2'>
        </el-input-number>
      </el-form-item>
      <el-form-item label="请假理由" prop="reason">
        <el-input :disabled="this.isApprove" v-model="leaveFormData.reason" type="textarea" placeholder="请输入请假理由"
          :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item label="审批人" prop="assignee">
        <el-select v-model="leaveFormData.assignee" placeholder="请选择审批人" clearable :style="{width: '100%'}">
          <el-option v-for="(item, index) in assigneeOptions" :key="index" :label="item.label"
            :value="item.value" :disabled="item.disabled"></el-option>
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="审批理由" prop="approveReason" v-if="this.isApprove"> -->
      <el-form-item label="审批理由" prop="approveReason" v-if="this.isApprove">
        <el-input v-model="leaveFormData.approveReason" type="textarea" placeholder="请输入审批理由"
          :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item size="large" v-if="!this.isApprove">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
      <el-form-item size="large" v-else>
        <el-button type="primary" @click="Approve(true)">通过</el-button>
        <el-button @click="Approve(false)">拒绝</el-button>
      </el-form-item>
    </el-form>
    </div>
  </div>
</template>
<script>
import { listByOption } from "@/api/system/user";
import { addLeave,getBusiness,approveProcess } from "@/api/process/myLeave";

export default {
  name:"myLeave",
  components: {},
  props: [],
  data() {
    return {
      isApprove:false,
      leaveFormData: {
        type: undefined,
        beginDate: null,
        endDate: null,
        duration: undefined,
        reason: undefined,
        assignee: undefined,
      },
      rules1: {
        approveReason: [{
          required: true,
          message: '请选择请假类型',
          trigger: 'change'
        }],
      },
      rules: {
        type: [{
          required: true,
          message: '请选择请假类型',
          trigger: 'change'
        }],
        beginDate: [{
          required: true,
          message: '请选择开始时间',
          trigger: 'change'
        }],
        endDate: [{
          required: true,
          message: '请选择结束时间',
          trigger: 'change'
        }],
        duration: [{
          required: true,
          message: '时长(小时)',
          trigger: 'blur'
        }],
        reason: [{
          required: true,
          message: '请输入请假理由',
          trigger: 'blur'
        }],
        assignee: [{
          required: true,
          message: '请选择审批人',
          trigger: 'change'
        }],
      },
      typeOptions: [{
        "label": "事假",
        "value": "事假"
      }, {
        "label": "病假",
        "value": "病假"
      }, {
        "label": "公干",
        "value": "公干"
      }],
      assigneeOptions: [],
    }
  },
  computed: {
  },
  watch: {
    'leaveFormData.endDate':function(newValue,oldValue){
      let a = this.leaveFormData.beginDate;
      var endDate = new Date(newValue.replace(/-/g, "/"));
      if(a == undefined){return}
      var beginDate = new Date(a.replace(/-/g, "/"));
      var usedTime = this.getIntervalHour(beginDate,endDate);
      this.leaveFormData.duration = usedTime
    },
    'leaveFormData.beginDate':function(newValue,oldValue){
      let a = this.leaveFormData.endDate;
      var beginDate = new Date(newValue.replace(/-/g, "/"));
      if(a == undefined){return}
      var endDate = new Date(a.replace(/-/g, "/"));
      var usedTime = this.getIntervalHour(beginDate,endDate);
      this.leaveFormData.duration = usedTime

    },
  },
  created() {
    this.getOption();
    console.log(this.$route)
    this.$nextTick(()=>{
    this.getBusi(this.$route.params.id);

    });
    
  },
  mounted() {
    
  },
  methods: {
    getBusi(id){
      if(id == 0){
        return ;
      }else{
        getBusiness(id).then(response => {
          this.leaveFormData = response.data;
          this.isApprove = true;

        });
      }
      
    },
    getOption(){
      listByOption().then(response => {
        this.assigneeOptions = response.data
      });
    },
    Approve(bool){
      this.leaveFormData.processInstanceId = this.$route.params.id;
      this.leaveFormData.state = this.leaveFormData.assignee?2:1;
      this.leaveFormData.taskId = this.$route.query.taskId;
      if(bool){
      this.$refs['leaveForm'].validate(valid => {
        if (!valid) return
        // 提交同意表单
        approveProcess(this.leaveFormData).then(res=>{
          console.log(res);
        })
      })
      }else{
        console.log(2)
      }
    },
    getIntervalHour(startDate, endDate) {
            var ms = endDate.getTime() - startDate.getTime();
            if (ms < 0) return 0;
            return Math.floor(ms/1000/60/60);
        },
    submitForm() {
      console.log(this)
      this.$refs['leaveForm'].validate(valid => {
        if (!valid) return
        // TODO 提交表单
        addLeave(this.leaveFormData).then(res=>{
          console.log(res);
        })
      })
    },
    resetForm() {
      this.$refs['leaveForm'].resetFields()
    },
  }
}

</script>
<style scoped>
.continer{
  width: 100%;
}
.inner{

  width: 50%;
  margin: 100px auto 0 auto;
}
</style>
