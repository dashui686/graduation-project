import request from '@/utils/request'



// 查询所有 参数
export function listRelation(query) {
  return request({
    url: '/test/relation/select',
    method: 'get',
    params: query
  })
}

// 查询供应商
export function getOne(id) {
  return request({
    url: '/test/relation/' + id,
    method: 'get'
  })
}


// 新增供应商
export function addRelation(data) {
  return request({
    url: '/test/relation/',
    method: 'post',
    data: data
  })
}

// 修改供应商
export function updateRelation(data) {

  return request({
    url: '/test/relation/',
    method: 'put',
    data: data
  })
}

// 删除供应商
export function delRelation(id) {
  return request({
    url: '/test/relation/delete/' + id,
    method: 'delete'
  })
}



