import request from '@/utils/request'



// 查询所有 参数
export function listGoods(query) {
  return request({
    url: '/test/drpGoods/select',
    method: 'get',
    params: query
  })
}

// 查询商品
export function getOneGoods(id) {
  return request({
    url: '/test/drpGoods/' + id,
    method: 'get'
  })
}


// 新增商品
export function addGoods(data) {
  return request({
    url: '/test/drpGoods/',
    method: 'post',
    data: data
  })
}

// 修改商品
export function updateGoods(data) {

  return request({
    url: '/test/drpGoods/',
    method: 'put',
    data: data
  })
}

// 删除商品
export function delGoods(id) {
  return request({
    url: '/test/drpGoods/delete/' + id,
    method: 'delete'
  })
}



