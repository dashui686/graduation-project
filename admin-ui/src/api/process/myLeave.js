import request from '@/utils/request'


// 新增岗位
export function addLeave(data) {
    return request({
      url: '/test/PleaseLeave',
      method: 'post',
      data: data
    })
  }