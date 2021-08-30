import request from '@/utils/request'

// 查询会员等级列表
export function listMemberLevel(query) {
  return request({
    url: '/member/memberLevel/list',
    method: 'get',
    params: query
  })
}

// 查询会员等级详细
export function getMemberLevel(id) {
  return request({
    url: '/member/memberLevel/' + id,
    method: 'get'
  })
}

// 新增会员等级
export function addMemberLevel(data) {
  return request({
    url: '/member/memberLevel',
    method: 'post',
    data: data
  })
}

// 修改会员等级
export function updateMemberLevel(data) {
  return request({
    url: '/member/memberLevel',
    method: 'put',
    data: data
  })
}

// 删除会员等级
export function delMemberLevel(id) {
  return request({
    url: '/member/memberLevel/' + id,
    method: 'delete'
  })
}
