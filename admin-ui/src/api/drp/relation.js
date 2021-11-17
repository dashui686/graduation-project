import request from '@/utils/request'



// 查询所有 参数
export function listRelation(query) {
  return request({
    url: '/test/relation/supplier/select',
    method: 'get',
    params: query
  })
}

// 查询供应商
export function getOne(id) {
  return request({
    url: '/test/relation/supplier/' + id,
    method: 'get'
  })
}


// 新增供应商
export function addRelation(data) {
  return request({
    url: '/test/relation/supplier/',
    method: 'post',
    data: data
  })
}

// 修改供应商
export function updateRelation(data) {

  return request({
    url: '/test/relation/supplier/',
    method: 'put',
    data: data
  })
}

// 删除供应商
export function delRelation(id) {
  return request({
    url: '/test/relation/supplier/delete/' + id,
    method: 'delete'
  })
}

// 查询树形值
export function treeSelect2() {
  return request({
    url: '/test/relation/supplier/treeSelect',
    method: 'get'
  })
}


