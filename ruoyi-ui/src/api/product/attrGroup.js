import request from '@/utils/request'

// 查询属性分组列表
export function listAttrGroup(query) {
  return request({
    url: '/product/attrGroup/list',
    method: 'get',
    params: query
  })
}

// 查询属性分组详细
export function getAttrGroup(attrGroupId) {
  return request({
    url: '/product/attrGroup/' + attrGroupId,
    method: 'get'
  })
}

// 新增属性分组
export function addAttrGroup(data) {
  return request({
    url: '/product/attrGroup',
    method: 'post',
    data: data
  })
}

// 修改属性分组
export function updateAttrGroup(data) {
  return request({
    url: '/product/attrGroup',
    method: 'put',
    data: data
  })
}

// 删除属性分组
export function delAttrGroup(attrGroupId) {
  return request({
    url: '/product/attrGroup/' + attrGroupId,
    method: 'delete'
  })
}

// 获取属性分组的关联属性
export function getAttr(attrGroupId, query) {
  return request({
    url: '/product/attrGroup/attr/' + attrGroupId,
    method: 'get',
    params: query
  })
}

//删除属性分组的关联属性
export function delAttr(attrIds) {
  return request({
    url: `/product/attrGroup/attr/${ attrIds }`,
    method: 'delete'
  });
}

// 查询未被分组的属性
export function getNoRelationAttr(query) {
  return request({
    url: '/product/attrGroup/noRelationAttr',
    method: 'get',
    params: query
  });
}

// 新增属性分组和属性的关联信息
export function addNoRelationAttr(attrGroupId, attrIds) {
  return request({
    url: `product/attrGroup/noRelationAttr/${ attrGroupId }/${ attrIds }`,
    method: 'post'
  });
}

//根据分类id查询商品分组属性列表
export function listAttrGroupAttrs(catalogId) {
  return request({
    url: 'product/attrGroup/listAttrGroupAttrs/' + catalogId,
    method: 'get'
  })
}
