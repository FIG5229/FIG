import request from '@/utils/request'
// 模糊查询站点
export const searchSite = key => {
  return request({
    url: '/selectKey',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
    },
    transformRequest: [function(data) {
      let ret = ''
      for (const it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    data: { key: key }
  })
}
