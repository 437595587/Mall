import request from '@/utils/request'

// 查询商品库存列表
export function listWareSku(query) {
  return request({
    url: '/ware/wareSku/list',
    method: 'get',
    params: query
  })
}

// 查询商品库存详细
export function getWareSku(id) {
  return request({
    url: '/ware/wareSku/' + id,
    method: 'get'
  })
}

// 新增商品库存
export function addWareSku(data) {
  return request({
    url: '/ware/wareSku',
    method: 'post',
    data: data
  })
}

// 修改商品库存
export function updateWareSku(data) {
  return request({
    url: '/ware/wareSku',
    method: 'put',
    data: data
  })
}

// 删除商品库存
export function delWareSku(id) {
  return request({
    url: '/ware/wareSku/' + id,
    method: 'delete'
  })
}
