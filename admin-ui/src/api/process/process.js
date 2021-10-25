import request from '@/utils/request'



// 登录方法
export function list() {
    return request({
      url: '/login',
      method: 'post',
      data: data
    })
  }