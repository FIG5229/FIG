import request from '@/utils/request'

// 登陆
export const login = data => {
  return request({
    url: '/v1/api/platform/login',
    method: 'post',
    data
  })
}
// 注销登陆
export const logout = params => {
  return request({
    url: '/v1/api/platform/logout',
    method: 'get',
    params
  })
}
// 获取用户信息
export const getInfo = () => {
  return request({
    url: '/v1/api/platform/login/user',
    method: 'get',
    params: {}
  })
}
