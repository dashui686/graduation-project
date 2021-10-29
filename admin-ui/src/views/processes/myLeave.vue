<template>
  <div class="main">
    <el-form ref="leaveForm" :model="leaveFormData" :rules="rules" size="medium" label-width="100px">
      <el-form-item label="申请人" prop="userId">
        <el-input v-model="leaveFormData.userId" placeholder="请输入申请人申请人" :maxlength="11" show-word-limit
          clearable prefix-icon='el-icon-user-solid' :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item label="开始时间" prop="beginDateTime">
        <el-time-picker v-model="leaveFormData.beginDateTime" format="HH:mm:ss" value-format="HH:mm:ss"
          :picker-options='{"selectableRange":"00:00:00-23:59:59"}' :style="{width: '100%'}"
          placeholder="请选择开始时间" clearable></el-time-picker>
      </el-form-item>
      <el-form-item label="开始时间" prop="endDateTime">
        <el-time-picker v-model="leaveFormData.endDateTime" format="HH:mm:ss" value-format="HH:mm:ss"
          :picker-options='{"selectableRange":"00:00:00-23:59:59"}' :style="{width: '100%'}"
          placeholder="请选择开始时间" clearable></el-time-picker>
      </el-form-item>
      <el-form-item label="天数" prop="dayCount">
        <el-input-number v-model="leaveFormData.dayCount" placeholder="天数" :step='1' :precision='2'
          :disabled='true'></el-input-number>
      </el-form-item>
      <el-form-item label="审批人" prop="field107">
        <el-select v-model="leaveFormData.field107" placeholder="请选择审批人" filterable clearable
          :style="{width: '100%'}">
          <el-option v-for="(item, index) in field107Options" :key="index" :label="item.label"
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
export default {
  name: "myLeave",
  components: {},
  props: [],
  data() {
    return {
      leaveFormData: {
        userId: undefined,
        beginDateTime: "09:14:13",
        endDateTime: "09:14:03",
        dayCount: 0,
        field107: undefined,
      },
      rules: {
        userId: [{
          required: true,
          message: '请输入申请人申请人',
          trigger: 'blur'
        }, {
          pattern: /^1(3|4|5|7|8|9)\d{9}$/,
          message: '手机号格式错误',
          trigger: 'blur'
        }],
        beginDateTime: [{
          required: true,
          message: '请选择开始时间',
          trigger: 'change'
        }],
        endDateTime: [{
          required: true,
          message: '请选择开始时间',
          trigger: 'change'
        }],
        dayCount: [{
          required: true,
          message: '天数',
          trigger: 'blur'
        }],
        field107: [{
          required: true,
          message: '请选择审批人',
          trigger: 'change'
        }],
      },
      field107Options: [{
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
.main{
  width: 100%;
  margin:  0 auto ;
  border: 1px solid red;
}
</style>
