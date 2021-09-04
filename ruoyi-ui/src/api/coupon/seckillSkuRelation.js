import request from '@/utils/request'

// 查询秒杀活动商品关联列表
export function listSeckillSkuRelation(query) {
  return request({
    url: '/coupon/seckillSkuRelation/list',
    method: 'get',
    params: query
  })
}

// 查询秒杀活动商品关联详细
export function getSeckillSkuRelation(id) {
  return request({
    url: '/coupon/seckillSkuRelation/' + id,
    method: 'get'
  })
}

// 新增秒杀活动商品关联
export function addSeckillSkuRelation(data) {
  return request({
    url: '/coupon/seckillSkuRelation',
    method: 'post',
    data: data
  })
}

// 修改秒杀活动商品关联
export function updateSeckillSkuRelation(data) {
  return request({
    url: '/coupon/seckillSkuRelation',
    method: 'put',
    data: data
  })
}

// 删除秒杀活动商品关联
export function delSeckillSkuRelation(id) {
  return request({
    url: '/coupon/seckillSkuRelation/' + id,
    method: 'delete'
  })
}
