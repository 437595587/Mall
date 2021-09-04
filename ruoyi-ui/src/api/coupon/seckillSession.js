import request from '@/utils/request'

// 查询秒杀活动场次列表
export function listSeckillSession(query) {
  return request({
    url: '/coupon/seckillSession/list',
    method: 'get',
    params: query
  })
}

// 查询秒杀活动场次详细
export function getSeckillSession(id) {
  return request({
    url: '/coupon/seckillSession/' + id,
    method: 'get'
  })
}

// 新增秒杀活动场次
export function addSeckillSession(data) {
  return request({
    url: '/coupon/seckillSession',
    method: 'post',
    data: data
  })
}

// 修改秒杀活动场次
export function updateSeckillSession(data) {
  return request({
    url: '/coupon/seckillSession',
    method: 'put',
    data: data
  })
}

// 删除秒杀活动场次
export function delSeckillSession(id) {
  return request({
    url: '/coupon/seckillSession/' + id,
    method: 'delete'
  })
}
