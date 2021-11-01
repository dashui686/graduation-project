import request from '@/utils/request'



// 查询所有 参数
export function listWarehouse(query) {
  return request({
    url: '/test/drpWarehouse/select',
    method: 'get',
    params: query
  })
}

// 查询树形值
export function treeselect() {
  return request({
    url: '/test/drpWarehouse/treeselect',
    method: 'get'
  })
}

// 查询流程详细
export function getWarehouse(warehouseId) {
  return request({
    url: '/test/drpWarehouse/' + warehouseId,
    method: 'get'
  })
}


// 新增岗位
export function addWarehouse(data) {
  return request({
    url: '/test/drpWarehouse/',
    method: 'post',
    data: data
  })
}

// 修改流程
export function updateWarehouse(data) {

  return request({
    url: '/test/drpWarehouse/',
    method: 'put',
    data: data
  })
}

// 删除流程
export function delWarehouse(warehouseId) {
  return request({
    url: '/test/drpWarehouse/delete/' + warehouseId,
    method: 'delete'
  })
}

// 部署流程
export function deploy(postId) {
  return request({
    url: '/test/processDeploy/deploy',
    method: 'post',
    data: {processId:postId}
  })
}

// 取消部署流程
export function unDeploy(postId) {
  return request({
    url: '/test/processDeploy/unDeploy',
    method: 'post',
    data: {processId:postId}
  })
}



