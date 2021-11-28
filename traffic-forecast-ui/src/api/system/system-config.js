import request from '@/utils/request'

// 查询平台基本配置,不需要权限
export const configInfo = () => {
  return request('get', '/v1/api/platform/config/info', {}, {})
}
