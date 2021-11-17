<template>
  <div id="container">

    <div class="div1">
          <el-form :model="Params" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
            <el-form-item label="用户名称" prop="nickName">
              <el-input
                v-model="Params.nickName"
                placeholder="请输入用户名称"
                clearable
                size="small"
                style="width: 240px"
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="手机号码" prop="phonenumber">
              <el-input
                v-model="Params.phonenumber"
                placeholder="请输入手机号码"
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
    </div>
    <div class="div2">
      <div id="userInfo" v-for="item in userList" :key="item.id">
        <!--        头像-->
        <div id="avatar" >
          <el-row class="demo-avatar demo-basic">
            <el-col :span="12">
              <div class="demo-basic--circle">
                <div class="block">
                  <img :src="'/dev-api'+item.avatar" class="user-avatar">
                  <!-- <el-avatar :size="50" :src="item.avatar"></el-avatar> -->
                </div>
              </div>
            </el-col>
          </el-row>
        </div>
        <!-- 信息-->
        <div >
          <el-row>
            <el-col><h1>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;{{item.nickName}}</h1></el-col>
          </el-row>
          <br>
          <br>
          <el-row>
            <el-col v-if="item.sex == 0">性别:<b>男</b></el-col>
            <el-col v-if="item.sex == 1">性别:<b>女</b></el-col>
          </el-row>
          <br>
          <el-row>
            <el-col>手机号:{{item.phonenumber}}</el-col>
          </el-row>
          <br>
          <el-row>
            <el-col>邮箱:{{item.email}}</el-col>
          </el-row>
        </div>
      </div>
    </div>


    </div>
</template>
<script>
  import {queryUserInfo } from "@/api/system/user";

  export default {
    data() {
      return {
        // 显示搜索条件
        showSearch: true,
        // 用户表格数据
        userList: null,
        //搜索
        Params: {
          nikeName: undefined,
          phonenumber: undefined,
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
        this.handleQuery();
      },
      /** 查询用户列表 */
      getList(params) {

        queryUserInfo(params).then(response => {
            this.userList = response
          }
        );
      },
    }
  }
</script>
<style scoped>
  #userInfo {
    display: flex;
    margin-right: 10px;
    margin-top: 30px;
    width: 350px;
    height: 250px;
    background-color: white;
    border-radius: 15px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.5), 0 0 6px rgba(0, 0, 0, 0.04);
  }

  #avatar{
    margin:10px 10px 0 10px;
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

  
  .user-avatar {
    
    cursor: pointer;
    width: 40px;
    height: 40px;
    border-radius: 50%;
  }
</style>
