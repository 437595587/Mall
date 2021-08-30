import request from '@/utils/request'

// 查询spu信息列表
export function listSpu(query) {
  return request({
    url: '/product/spuInfo/list',
    method: 'get',
    params: query
  })
}

// 查询spu信息详细
export function getSpu(id) {
  return request({
    url: '/product/spuInfo/' + id,
    method: 'get'
  })
}

// 新增spu信息
export function addSpu(data) {
  return request({
    url: '/product/spuInfo',
    method: 'post',
    data: data
  })
}

// 修改spu信息
export function updateSpu(data) {
  return request({
    url: '/product/spuInfo',
    method: 'put',
    data: data
  })
}

// 删除spu信息
export function delSpu(id) {
  return request({
    url: '/product/spuInfo/' + id,
    method: 'delete'
  })
}
