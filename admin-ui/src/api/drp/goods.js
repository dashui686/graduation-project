import request from '@/utils/request'



// 查询所有 参数
export function listGoods(query) {
  return request({
    url: '/test/drpGoods/select',
    method: 'get',
    params: query
  })
}

// 查询所有 参数
export function queryByWarehouseId(id) {
  return request({
    url: '/test/drpGoods/treeSelectByWarehouseId/'+id,
    method: 'get',
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
  console.log(data,123)
  return request({
    url: '/test/drpGoods',
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


// 查询树形值
export function treeselect2() {
  return request({
    url: '/test/goodsType/treeselect',
    method: 'get'
  })
}


