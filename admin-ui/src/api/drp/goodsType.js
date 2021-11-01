import request from '@/utils/request'



// 查询所有 参数
export function listGoodsType(query) {
  return request({
    url: '/test/goodsType/select',
    method: 'get',
    params: query
  })
}

// 查询供应商
export function getOne(id) {
  return request({
    url: '/test/goodsType/' + id,
    method: 'get'
  })
}


// 新增供应商
export function addGoodsType(data) {
  return request({
    url: '/test/goodsType/',
    method: 'post',
    data: data
  })
}

// 修改供应商
export function updateGoodsType(data) {

  return request({
    url: '/test/goodsType/',
    method: 'put',
    data: data
  })
}

// 删除供应商
export function delGoodsType(id) {
  return request({
    url: '/test/goodsType/delete/' + id,
    method: 'delete'
  })
}



