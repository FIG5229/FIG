import request from '@/utils/request'

// 角色列表
export const roleList = params => {
  return request({
    method: 'get',
    url: '/v1/api/platform/role/list',
    params
  })
}
// 创建角色
export const roleAdd = data => {
  return request({
    method: 'post',
    url: '/v1/api/platform/role/create',
    data
  })
}
// 修改角色基本信息
export const roleEdit = data => {
  return request({
    method: 'post',
    url: '/v1/api/platform/role/update',
    data
  })
}
// 删除角色
export const roleDelete = bIds => {
  return request({
    method: 'post',
    url: '/v1/api/platform/role/delete',
    data: bIds
  })
}
// 查询指定用户拥有的角色
export const userRoleList = uId => {
  return request({
    url: '/v1/api/platform/role/list/user/' + uId,
    method: 'get',
    params: {}
  })
}

// 给角色绑定用户
export const bindRoleToUser = data => {
  return request({
    method: 'post',
    url: '/v1/api/platform/role/bindRoleToUser',
    data
  })
}
// 查询角色下所有用户
export const findUsersByRole = bId => {
  return request({
    url: `/v1/api/platform/role/list/findUsersByRole/${bId}`,
    method: 'get'
  })
}

// 查询角色下所有用户
export const allUserList = () => {
  return request({
    url: `/v1/api/platform/user/allUsers/`,
    method: 'get'
  })
}
