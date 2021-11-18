import request from '@/utils/request'


// 新增业务
export function addLeave(data) {
    return request({
      url: '/test/goodsApply',
      method: 'post',
      data: data
    })
  }



  // 查询业务
  export function getBusiness(id){
    return request({
      url:"/test/goodsApply/getBusiness/"+id,
      method:"get",
    })
  }
  
  // 查询业务
  export function getById(id){
    return request({
      url:"/test/goodsApply/"+id,
      method:"get",

    })
  }


    // 查询业务
    export function approveProcess(data){
      return request({
        url:"/test/goodsApply/approveProcess",
        method:"post",
        data
      })
    }