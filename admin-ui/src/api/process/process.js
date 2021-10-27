import request from '@/utils/request'



// 查询所有 参数
export function listProcess(query) {
    return request({
      url: '/test/processDeploy/select',
      method: 'get',
      params: query
    })
}

// 查询岗位详细
export function getProcessDeploy(processId) {
  return request({
    url: '/test/processDeploy/' + processId,
    method: 'get'
  })
}
