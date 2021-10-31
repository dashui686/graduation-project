<template>
  <div class="continer">
    <div class="inner">
    <el-form ref="leaveForm" :model="leaveFormData" :rules="rules" size="medium" label-width="100px">
      <el-form-item label="请假类型" prop="type">
        <el-select v-model="leaveFormData.type" placeholder="请选择请假类型" clearable :style="{width: '100%'}">
          <el-option v-for="(item, index) in typeOptions" :key="index" :label="item.label" :value="item.value"
            :disabled="item.disabled"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="开始时间" prop="beginDate">
        <el-date-picker v-model="leaveFormData.beginDate"  type="datetime" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss"
          :style="{width: '100%'}" placeholder="请选择开始时间" clearable></el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="endDate">
        <el-date-picker v-model="leaveFormData.endDate"  type="datetime" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss"
          :style="{width: '100%'}" placeholder="请选择结束时间" clearable></el-date-picker>
      </el-form-item>
      <el-form-item label="时长(小时)" prop="countDay">
        <el-input-number v-model="leaveFormData.countDay" disabled placeholder="时长(小时)" :step='1' :precision='2'>
        </el-input-number>
      </el-form-item>
      <el-form-item label="请假理由" prop="reason">
        <el-input v-model="leaveFormData.reason" type="textarea" placeholder="请输入请假理由"
          :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item label="审批人" prop="assignee">
        <el-select v-model="leaveFormData.assignee" placeholder="请选择审批人" clearable :style="{width: '100%'}">
          <el-option v-for="(item, index) in assigneeOptions" :key="index" :label="item.label"
            :value="item.value" :disabled="item.disabled"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
    </div>
  </div>
</template>
<script>
export default {
  name:"myLeave",
  components: {},
  props: [],
  data() {
    return {
      leaveFormData: {
        type: undefined,
        beginDate: null,
        endDate: null,
        countDay: 0,
        reason: undefined,
        assignee: undefined,
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
        countDay: [{
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
  watch: {},
  created() {},
  mounted() {},
  methods: {
    submitForm() {
      this.$refs['leaveForm'].validate(valid => {
        if (!valid) return
        // TODO 提交表单
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
