import request from '@/utils/request'



// 查询所有 参数
export function listGoodsType(query) {
  return request({
    url: '/test/goodsType/select',
    method: 'get',
    params: query
  })
}

// 查询商品类型
export function getOne(id) {
  return request({
    url: '/test/goodsType/' + id,
    method: 'get'
  })
}


// 新增商品类型
export function addGoodsType(data) {
  return request({
    url: '/test/goodsType/',
    method: 'post',
    data: data
  })
}

// 修改商品类型
export function updateGoodsType(data) {

  return request({
    url: '/test/goodsType/',
    method: 'put',
    data: data
  })
}

// 删除商品类型
export function delGoodsType(id) {
  return request({
    url: '/test/goodsType/delete/' + id,
    method: 'delete'
  })
}



