module.exports = {

  title: '铁总货运车流预测分析系统',
  /**
   * @Description: 版本号
   * @author Gongkaikai
   * @date 2019/12/12
  */
  v: '/v1/api',
  /**
   * @type {boolean} true | false
   * @description Whether fix the header
   */
  fixedHeader: true,

  /**
   * @type {boolean} true | false
   * @description Whether show the logo in sidebar
   */
  sidebarLogo: true,

  /**
   * @Description: 首页地址，如果为空则按照路由规则(utils/addRouter.js)设置首页
   * @author Gongkaikai
   * @value system/system-user
   * @date 2019/10/8
  */
  home: '/data-screen/data-monitor',

  /**
   * @description: 是否开启调试模式，调试模式下，路由权限不请求服务端
   * @param {boolean}
   */
  isDebugger: true
}
