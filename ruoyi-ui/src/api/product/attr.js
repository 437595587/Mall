import request from '@/utils/request'

// 查询规格参数列表
export function listAttr(query) {
  return request({
    url: '/product/attr/list',
    method: 'get',
    params: query
  })
}

// 查询规格参数详细
export function getAttr(attrId) {
  return request({
    url: '/product/attr/' + attrId,
    method: 'get'
  })
}

// 新增规格参数
export function addAttr(data) {
  return request({
    url: '/product/attr',
    method: 'post',
    data: data
  })
}

// 修改规格参数
export function updateAttr(data) {
  return request({
    url: '/product/attr',
    method: 'put',
    data: data
  })
}

// 删除规格参数
export function delAttr(attrId) {
  return request({
    url: '/product/attr/' + attrId,
    method: 'delete'
  })
}
