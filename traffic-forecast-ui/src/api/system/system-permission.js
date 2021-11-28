import request from '@/utils/request'

// 权限列表
export const permissionList = params => {
  return request({
    method: 'get',
    url: '/v1/api/platform/permission/list',
    params
  })
}
// 创建权限
export const permissionAdd = data => {
  return request({
    method: 'post',
    url: '/v1/api/platform/permission/create',
    data
  })
}
// 修改权限基本信息
export const permissionEdit = data => {
  return request({
    method: 'post',
    url: '/v1/api/platform/permission/update',
    data
  })
}
// 删除权限
export const permissionDelete = bId => {
  return request({
    method: 'post',
    url: '/v1/api/platform/permission/delete/' + bId,
    params: {}
  })
}
// 角色权限
export const permissionRoleList = rId => {
  return request({
    url: '/v1/api/platform/permission/list/role/' + rId,
    method: 'get',
    params: {}
  })
}
