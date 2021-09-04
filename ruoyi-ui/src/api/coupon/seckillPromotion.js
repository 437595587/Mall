import request from '@/utils/request'

// 查询秒杀活动列表
export function listSeckillPromotion(query) {
  return request({
    url: '/coupon/seckillPromotion/list',
    method: 'get',
    params: query
  })
}

// 查询秒杀活动详细
export function getSeckillPromotion(id) {
  return request({
    url: '/coupon/seckillPromotion/' + id,
    method: 'get'
  })
}

// 新增秒杀活动
export function addSeckillPromotion(data) {
  return request({
    url: '/coupon/seckillPromotion',
    method: 'post',
    data: data
  })
}

// 修改秒杀活动
export function updateSeckillPromotion(data) {
  return request({
    url: '/coupon/seckillPromotion',
    method: 'put',
    data: data
  })
}

// 删除秒杀活动
export function delSeckillPromotion(id) {
  return request({
    url: '/coupon/seckillPromotion/' + id,
    method: 'delete'
  })
}
