import request from '@/utils/request'

// 查询秒杀商品通知订阅列表
export function listSeckillSkuNotice(query) {
  return request({
    url: '/coupon/seckillSkuNotice/list',
    method: 'get',
    params: query
  })
}

// 查询秒杀商品通知订阅详细
export function getSeckillSkuNotice(id) {
  return request({
    url: '/coupon/seckillSkuNotice/' + id,
    method: 'get'
  })
}

// 新增秒杀商品通知订阅
export function addSeckillSkuNotice(data) {
  return request({
    url: '/coupon/seckillSkuNotice',
    method: 'post',
    data: data
  })
}

// 修改秒杀商品通知订阅
export function updateSeckillSkuNotice(data) {
  return request({
    url: '/coupon/seckillSkuNotice',
    method: 'put',
    data: data
  })
}

// 删除秒杀商品通知订阅
export function delSeckillSkuNotice(id) {
  return request({
    url: '/coupon/seckillSkuNotice/' + id,
    method: 'delete'
  })
}
