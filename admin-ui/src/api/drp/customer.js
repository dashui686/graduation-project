import request from '@/utils/request'



// 查询所有 参数
export function listRelation(query) {
  return request({
    url: '/test/relation/customer/select',
    method: 'get',
    params: query
  })
}

// 查询客户
export function getOne(id) {
  return request({
    url: '/test/relation/customer/' + id,
    method: 'get'
  })
}


// 新增客户
export function addRelation(data) {
  return request({
    url: '/test/relation/customer/',
    method: 'post',
    data: data
  })
}

// 修改客户
export function updateRelation(data) {

  return request({
    url: '/test/relation/customer/',
    method: 'put',
    data: data
  })
}

// 删除客户
export function delRelation(id) {
  return request({
    url: '/test/relation/customer/delete/' + id,
    method: 'delete'
  })
}



