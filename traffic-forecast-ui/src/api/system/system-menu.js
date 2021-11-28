import request from '@/utils/request'

// 菜单列表
export const menuList = bId => {
  if (bId === undefined || bId === null) {
    bId = 0
  }
  return request({
    url: '/v1/api/platform/menu/select/tree/' + bId,
    method: 'get',
    params: {}
  })
}
// 增加菜单
export const menuAdd = data => {
  return request({
    url: '/v1/api/platform/menu/create',
    method: 'post',
    data
  })
}
// 修改菜单
export const menuEdit = data => {
  return request({
    url: '/v1/api/platform/menu/update',
    method: 'post',
    data
  })
}
// 删除菜单
export const menuDel = bIds => {
  return request({
    url: '/v1/api/platform/menu/delete',
    method: 'post',
    data: bIds
  })
}
export const menuRoleList = rId => {
  return request({
    url: '/v1/api/platform/menu/list/role/' + rId,
    method: 'get',
    params: {}
  })
}
