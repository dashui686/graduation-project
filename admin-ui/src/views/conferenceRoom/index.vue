<template>
  <div id="container">
    <div class="div1">
<!--      条件搜索-->
      <el-form :model="Params" :inline="true" v-show="showSearch" label-width="85px">
        <el-form-item label="会议室名称" prop="name">
          <el-input
            v-model="Params.name"
            placeholder="请输入会议室名称"
            clearable
            size="small"
            style="width: 240px"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="会议室状态" prop="state">
          <el-select v-model="Params.state" placeholder="请选择">
            <el-option
              v-for="item in stateOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="div2">
<!--      会议室信息-->
      <div id="conferenceInfo" v-for="item in conferenceRoom" @click="onOpen(item)">
        <div class="demo-image">
          <div class="block">
            <span class="demonstration"></span>
            <el-image
              style="width: 300px; height: 300px"
              :src="item.image"
            ></el-image>
          </div>
        </div>
        <div class="info">
<!--          是否使用中图标-->
          <h1 class="conferenceName">{{item.name}}</h1>
          <br>
          <p>容量：<b>{{item.capacity}}</b>人</p>
          <p>位置：<b>{{item.position}}</b></p>
          <p v-if="item.state ==0" style="color: #75BA27"><span class="el-icon-circle-check"></span>正常</p>
          <p v-if="item.state ==1" style="color: red"><span class="el-icon-circle-close"></span>使用中</p>
        </div>
      </div>
    </div>
<!--    弹框-->
    <div>
      <el-dialog v-bind="$attrs" v-on="$listeners" :visible.sync="dialogFormVisible" @close="onClose" title="预定会议室">
        <el-form ref="elForm" :model="conferenceFormData" :rules="rules" size="medium" label-width="100px">
          <el-form-item label="会议室" prop="conferenceRoomName">
            <el-input v-model="conferenceFormData.conferenceRoomName" disabled placeholder="请输入会议室" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
          <el-form-item label="开始时间" prop="beginDate">
            <el-date-picker type="datetime" v-model="conferenceFormData.beginDate" format="yyyy-MM-dd HH:mm:ss"
                            value-format="yyyy-MM-dd HH:mm:ss" :style="{width: '100%'}" placeholder="请选择开始时间" clearable>
            </el-date-picker>
          </el-form-item>
          <el-form-item label="结束时间" prop="endDate">
            <el-date-picker type="datetime" v-model="conferenceFormData.endDate" format="yyyy-MM-dd HH:mm:ss"
                            value-format="yyyy-MM-dd HH:mm:ss" :style="{width: '100%'}" placeholder="请选择结束时间" clearable>
            </el-date-picker>
          </el-form-item>
          <el-form-item label="使用事由" prop="reason">
            <el-input v-model="conferenceFormData.reason" type="textarea" placeholder="请输入使用事由"
                      :autosize="{minRows: 3, maxRows: 4}" :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="handelConfirm">确定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>



</template>

<script>

  import { listConferenceRoom,addConference } from "@/api/process/conferenceRoom";

    export default {
      name: "conferenceRoom",
      data() {
        return {
          stateOptions: [{
            value: 0,
            label: '未使用'
          },
          {
            value: 1,
            label: '使用中',
          }],
          value:'',
          form: {
          },
          //
          dialogFormVisible: false,
          // 显示搜索条件
          showSearch: true,
          // 用户数据
          conferenceRoom: null,
          //搜索
          Params: {
            name:undefined,
            state:undefined
          },
          conferenceFormData: {
            conferenceRoomName: undefined,
            conferenceRoom: undefined,
            beginDate: null,
            endDate: null,
            reason: undefined,
            remarks: undefined,
          },
          rules: {
            conferenceRoom: [{
              required: true,
              message: '请输入会议室',
              trigger: 'blur'
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
            reason: [{
              required: true,
              message: '请输入使用事由',
              trigger: 'blur'
            }],
          },
        }
      },
      created() {
        this.getList()
      },
      methods: {
        /** 搜索按钮操作 */
        handleQuery() {
          this.getList(this.Params);
        },
        /** 重置按钮操作 */
        resetQuery() {
          this.Params = {
            name:undefined,
            state:undefined
          }
          this.handleQuery();
        },
        //查询数据
        getList(Params){
          listConferenceRoom(Params).then(response =>{
            console.log(Params)
            this.conferenceRoom = response.data
          })
        },
        onOpen(item) {
          if(item.state == 1){
            return;
          }else{
            this.dialogFormVisible = true;
            this.conferenceFormData.conferenceRoomName = item.name;
            this.conferenceFormData.conferenceRoom = item.id;
          }
        },
        onClose() {
          this.$refs['elForm'].resetFields()
          this.conferenceFormData.conferenceRoom= undefined;
          this.conferenceFormData.conferenceRoomName= undefined;
        },
        close() {
          this.$emit('update:visible', false)
        },
        /**
         * 提交表单
         */
        handelConfirm() {
          this.$refs['elForm'].validate(valid => {
            if (!valid) return
            addConference(this.conferenceFormData).then(res =>{
              this.$modal.msgSuccess('申请成功')
            })
          })
        },
      }
    }
</script>

<style scoped>
  #conferenceInfo{
    width: 800px;
    height: 300px;
    background-color: #f3f6f8;
    border-radius: 15px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.5), 0 0 6px rgba(0, 0, 0, 0.04);
    margin:20px 20px;
    display: flex;
  }
  .demo-image{
    width: 300px;
    height: 300px;
  }
  .info{
    width: 500px;
    height: 300px;
    text-align: center;
  }
  #container {
    height: 100%;
    width: 100%;
    display: flex;
    flex-direction: column;
  }

  .div1, .div2 {
    justify-content: center;
    display: flex;
  }
  .div1{
    margin: 20px 0;
  }
  .div2{
    flex-wrap: wrap;
  }
  .conferenceName{
    color: #00afff;
  }
</style>
