import request from '@/utils/request'

// 查询采购需求列表
export function listPurchaseDetail(query) {
  return request({
    url: '/ware/purchaseDetail/list',
    method: 'get',
    params: query
  })
}

// 查询采购需求详细
export function getPurchaseDetail(id) {
  return request({
    url: '/ware/purchaseDetail/' + id,
    method: 'get'
  })
}

// 新增采购需求
export function addPurchaseDetail(data) {
  return request({
    url: '/ware/purchaseDetail',
    method: 'post',
    data: data
  })
}

// 修改采购需求
export function updatePurchaseDetail(data) {
  return request({
    url: '/ware/purchaseDetail',
    method: 'put',
    data: data
  })
}

// 删除采购需求
export function delPurchaseDetail(id) {
  return request({
    url: '/ware/purchaseDetail/' + id,
    method: 'delete'
  })
}
