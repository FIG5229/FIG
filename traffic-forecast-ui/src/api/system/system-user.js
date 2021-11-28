import request from '@/utils/request'

// 用户管理-用户列表
export const userList = (oId, params) => {
  return request({
    url: '/v1/api/platform/user/list/' + oId,
    method: 'post',
    params: params
  })
}
// 用户管理-创建
export const userAdd = data => {
  return request({
    url: '/v1/api/platform/user/create',
    method: 'post',
    data
  })
}
// 用户管理-修改
export const userEdit = data => {
  return request({
    url: '/v1/api/platform/user/update',
    method: 'post',
    data
  })
}
// 用户管理-删除
export const userDelete = bIds => {
  return request({
    url: '/v1/api/platform/user/delete',
    method: 'post',
    data: bIds
  })
}
