import request from '@/utils/request'

// 查询采购单列表
export function listPurchase(query) {
  return request({
    url: '/ware/purchase/list',
    method: 'get',
    params: query
  })
}

// 查询采购单详细
export function getPurchase(id) {
  return request({
    url: '/ware/purchase/' + id,
    method: 'get'
  })
}

// 新增采购单
export function addPurchase(data) {
  return request({
    url: '/ware/purchase',
    method: 'post',
    data: data
  })
}

// 修改采购单
export function updatePurchase(data) {
  return request({
    url: '/ware/purchase',
    method: 'put',
    data: data
  })
}

// 删除采购单
export function delPurchase(id) {
  return request({
    url: '/ware/purchase/' + id,
    method: 'delete'
  })
}

export function listUnReceivePurchase() {
  return request({
    url: '/ware/purchase/listUnReceivePurchase',
    method: 'get'
  })
}
