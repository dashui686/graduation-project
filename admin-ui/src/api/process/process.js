import request from '@/utils/request'



// 查询所有 参数
export function listProcess(query) {
    return request({
      url: '/test/processDeploy/select',
      method: 'get',
      params: query
    })
}

// 查询流程详细
export function getProcessDeploy(processId) {
  return request({
    url: '/test/processDeploy/' + processId,
    method: 'get'
  })
}

// 查询待办任务，根据用户ID
export function getMyProcess(query) {
  return request({
    url: '/test/process/myProcess',
    method: 'get',
    params: query
  })
}


// 查询 个人发起任务
export function myProcessStart(query) {
  return request({
    url: '/test/process/myProcessStart',
    method: 'get',
    params: query
  })
}




// 新增岗位
export function addProcessDeploy(data) {
  return request({
    url: '/test/processDeploy/',
    method: 'post',
    data: data
  })
}

// 修改流程
export function updateProcessDeploy(data) {
  return request({
    url: '/test/processDeploy/',
    method: 'put',
    data: data
  })
}

// 删除流程
export function delProcessDeploy(postId) {
  return request({
    url: '/test/processDeploy/' + postId,
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



