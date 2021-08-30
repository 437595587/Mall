import request from '@/utils/request'

// 查询sku信息列表
export function listSkuInfo(query) {
  return request({
    url: '/product/skuInfo/list',
    method: 'get',
    params: query
  })
}

// 查询sku信息详细
export function getSkuInfo(skuId) {
  return request({
    url: '/product/skuInfo/' + skuId,
    method: 'get'
  })
}

// 新增sku信息
export function addSkuInfo(data) {
  return request({
    url: '/product/skuInfo',
    method: 'post',
    data: data
  })
}

// 修改sku信息
export function updateSkuInfo(data) {
  return request({
    url: '/product/skuInfo',
    method: 'put',
    data: data
  })
}

// 删除sku信息
export function delSkuInfo(skuId) {
  return request({
    url: '/product/skuInfo/' + skuId,
    method: 'delete'
  })
}
