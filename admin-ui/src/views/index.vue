<template>
  <div class="app-container home">
    <div class="title">
<!--      <el-image :src="logo"></el-image><span style="font-size: 35px;font-weight: bolder;color: #00afff">我的桌面</span>-->
      <h1><b style="font-size: 40px;color: #00afff">首页</b></h1>
    </div>
    <div class="body">
      <div class="div1">
<!--          待处理的事务-->
        <div class="transaction">
          <h3>待处理的事务...</h3>
<!--          <template slot-scope="scope">-->
<!--            <router-link class="sidebar-logo-link" :to="'/process/processes/'+scope.row.processInstanceDefinitionKey+'/'+scope.row.processInstanceId+'?taskId='+scope.row.taskId">-->
              <ul>
                <li v-for="item in postList">{{item.processInstanceDefinitionName}}</li>
              </ul>
<!--            </router-link>-->
<!--          </template>-->
        </div>
<!--        信息公告-->
        <div class="notice">
          <h3>信息公告...</h3>
          <el-carousel height="460px">
            <el-carousel-item v-for="(item, index) in list_img" :key="index">
              <h3 class="small">
                <img :src="item.url" alt />
              </h3>
            </el-carousel-item>
          </el-carousel>
          <ul>
            <li v-for="(item,index) in sysNotice">
              <ul>
                <li v-for="item2 in item.rows">{{item2.noticeTitle}}</li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
      <div class="div2">
            <!--        事务统计-->
            <div class="transactionStatistics">
              <h3>我的事务统计...</h3>
            </div>
            <!--        我的项目-->
            <div class="myProject">
              <h3>我的项目...</h3>

              <ul>
                <p>仓库管理</p>
                <li v-for="item in warehouse">
                    {{item.warehouseName}}
                </li>
              </ul>
              <ul>
                <p>仓库管理</p>
                <li v-for="item in warehouse">
                  {{item.warehouseName}}
                </li>
              </ul>
            </div>
        <div class="div2-1" style="float:left;">
            <!--        未读消息-->
            <div class="unreadMessage">
              <h3>未读消息...</h3>
            </div>
            <!--        工作日程-->
            <div class="workSchedule">
              <h3>我的工作日程...</h3>
            </div>
            <!--        内部通讯录-->
            <div class="addressBook">
              <h3>内部通讯录...</h3>
            </div>
        </div>
          <div class="div2-2" style="float:left;">
<!--            关注的知识-->
            <div class="knowledge">
              <h3>我关注的知识...</h3>
            </div>
<!--            友情链接-->
            <div class="links">
              <h3>友情链接</h3>
            </div>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
  import { getMyProcess } from "@/api/process/process";
  import { queryAll } from "@/api/drp/warehouse";
  import { listNotice } from "@/api/system/notice";

export default {
  name: "Index",
  data() {
    return {

      list_img: [
        { url: 'http://cn.colloa.com/trial/mvc/file?name=customerBeko.jpg&contentType=image/jpeg&file=2016%5c4%5cd5f9d1cd-f1c9-44bc-9f03-460e4d6827eb.jpg' },
        { url: 'http://cn.colloa.com/trial/mvc/file?name=012.png&contentType=image/png&file=2016%5c4%5cd4470f54-358c-4026-a208-76cd71028b6a.png' },
        { url: 'http://cn.colloa.com/trial/mvc/file?name=file01.jpg&contentType=image/jpeg&file=2016%5c4%5c298b6f78-cc6b-4c0a-80d1-6564ce997b58.jpg' },
        { url: 'http://cn.colloa.com/trial/mvc/file?name=czyc.png&contentType=image/png&file=2016%5c4%5c05690c91-4bf8-4fd9-810e-91332b470901.png' },
        { url: 'http://cn.colloa.com/trial/mvc/file?name=22.png&contentType=image/png&file=2016%5c4%5cb5a50e97-84e1-4b6f-9001-43de9d97f34a.png' },
      ],

      // 版本号
      version: "3.7.0",
      //代办事务数据
      postList: [],
      //仓库数据
      warehouse: [],
      sysNotice: [],
      form:{},
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        processName: undefined,
        processState: 0,
      },
    };
  },
  created() {
    this.getList();
    this.warehouseList();
    this.noticeList();
  },
  methods: {
    //待办事务数据
    getList() {
      this.loading = true;
      getMyProcess(this.queryParams).then(res =>{
        console.log(res)
        this.postList = res.data.records;
        this.total = res.data.total;
        this.loading = false;
      })
    },
    //仓库数据
    warehouseList(){
      queryAll().then(res =>{
        this.warehouse = res
      })
    },
    //公告数据
    noticeList(){
      listNotice().then(res =>{
        console.log(res)
        this.sysNotice = res
      })
    }
  },
};
</script>

<style scoped lang="scss">
  .app-container{
    background-color: #f3f6f8;
    height: 1350px;
  }

  .body{
    display: flex;
  }

  .transaction{
    width: 520px;
    height: 350px;
    background-color: #ffffff;
    margin: 0 50px 50px 0;
  }
  .transactionStatistics{
    width: 95%;
    height: 150px;
    background-color: #ffffff;
    margin-bottom: 50px;
  }
  .myProject{
    width: 95%;
    height: 380px;
    background-color: #ffffff;
  }
  .div1{
    width: 30%;
    height: 852px;
    margin-right: 50px;
    float: left;
  }
  .div2{
    width: 70%;
    height: 852px;
    float: right;
  }
  .notice{
    width: 520px;
    height: 95%;
    background-color: #ffffff;
  }
  .unreadMessage{
    width: 500px;
    height: 120px;
    background-color: #ffffff;
    margin: 50px 50px 0 0;
  }
  .workSchedule{
    width: 500px;
    height: 260px;
    margin: 50px 50px 0 0;
    background-color: #ffffff;
  }
  .addressBook{
    width: 500px;
    height: 100px;
    margin: 50px 50px 0 0;
    background-color: #ffffff;
  }
  .knowledge{
    width: 500px;
    height: 380px;
    background-color: #ffffff;
    margin-top: 50px;
  }
  .links{
    width: 500px;
    height: 150px;
    background-color: #ffffff;
    margin-top: 50px;
  }
  h3{
    padding: 15px 0 0 10px;
    font-weight: bolder;
  }
  li{
    color: fuchsia;
    list-style: none;
    margin-top: 5px;
  }

  ul{
    display: inline-block;
    margin-right: 20px;
  }
  p{
    font-size: 18px;
  }
</style>
