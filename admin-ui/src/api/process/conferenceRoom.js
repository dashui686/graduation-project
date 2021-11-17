import request from '@/utils/request'

export function listConferenceRoom(query) {
  return request({
    url: '/test/conference/list',
    method: 'get',
    params: query
  })
}

export function addConference(query) {
  return request({
    url: '/test/conference',
    method: 'post',
    date: query
  })
}
