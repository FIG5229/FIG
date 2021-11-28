import request from '@/utils/request'

// 组织机构列表-树形
export const orgTree = bId => {
  if (bId === undefined || bId === null) {
    bId = 0
  }
  return request({
    method: 'get',
    url: '/v1/api/platform/org/select/tree/' + bId,
    params: {}
  })
}
// 组织机构-获取单个组织机构详情
export const getOrgInfo = bId => {
  return request({
    method: 'get',
    url: '/v1/api/platform/org/select/' + bId,
    params: {}
  })
}
// export const orgTree = params => {
//   return request('get', 'https://easy-mock.com/mock/5c9dc6993b462b35e7854d43/gzky/v1/api/platform/org/list', params, {})
// }
// 创建机构
export const orgAdd = data => {
  return request({
    method: 'post',
    url: '/v1/api/platform/org/create',
    data: data
  })
}
// 修改机构
export const orgEdit = data => {
  return request({
    method: 'post',
    url: '/v1/api/platform/org/update',
    data: data
  })
}
// 删除机构
export const orgDelete = bId => {
  return request({
    method: 'post',
    url: '/v1/api/platform/org/delete/' + bId,
    data: {}
  })
}
