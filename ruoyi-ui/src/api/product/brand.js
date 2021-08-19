import request from '@/utils/request'

// 查询品牌维护列表
export function listBrand(query) {
  return request({
    url: '/product/brand/list',
    method: 'get',
    params: query
  })
}

// 查询品牌维护详细
export function getBrand(brandId) {
  return request({
    url: '/product/brand/' + brandId,
    method: 'get'
  })
}

// 新增品牌维护
export function addBrand(data) {
  return request({
    url: '/product/brand',
    method: 'post',
    data: data
  })
}

// 修改品牌维护
export function updateBrand(data) {
  return request({
    url: '/product/brand',
    method: 'put',
    data: data
  })
}

// 删除品牌维护
export function delBrand(brandId) {
  return request({
    url: '/product/brand/' + brandId,
    method: 'delete'
  })
}
