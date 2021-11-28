import Vue from 'vue'
import store from '../store'
import storeApp from '../store/modules/settings'
import Router from 'vue-router'
import staticRouter from './staticRouter'
import defaultIndex from './defaultIndex'
import componentsRouter from './componentsRouter'
import asyncRouter from './asyncRouter'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import getPageTitle from '@/utils/get-page-title'
import { addRouterMap } from '../utils/addRouter'
import { getInfo } from '@/api/user/user'

Vue.use(Router)

const router = new Router({
  scrollBehavior: () => ({ y: 0 }),
  routes: storeApp.state.isDebugger ? [...defaultIndex, ...asyncRouter, ...staticRouter, ...componentsRouter] : staticRouter
})
NProgress.configure({ showSpinner: false }) // NProgress Configuration
router.beforeEach(async(to, from, next) => {
  // start progress bar
  NProgress.start()
  // set page title
  document.title = getPageTitle(to.meta.title)
  // determine whether the user has logged in
  if (to.path !== '/login') {
    if (store.getters.getRouterStatus) {
      next()
    } else {
      if (storeApp.state.isDebugger) {
        next()
      } else {
        console.log('重新加载路由,并更新用户信息~~~~~~~~~~~')
        gotoRouter(to, next)
      }
    }
  } else {
    next()
  }
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})

// 设置动态路由
const gotoRouter = (to, next) => {
  getInfo({}).then(res => {
    store.dispatch('user/setName', res.data.loginUser.user.username) // 存储用户信息到vuex
    store.dispatch('user/setRoles', res.data.loginUser.roles) // 存储用户信息到vuex
    store.dispatch('user/setOrg', res.data.loginUser.org) // 存储用户信息到vuex
    const asyncRouter = addRouterMap(res.data.loginUser.menus) // 递归解析路由
    // 一定不能写在静态路由里面,否则会出现,访问动态路由404的情况.所以在这列添加
    asyncRouter.push({ path: '*', redirect: '/404' })
    router.addRoutes(asyncRouter) // 动态添加路由
    store.dispatch('user/setStatus', true) // 改变状态，重新获取用户权限信息
    store.dispatch('user/setRouterList', asyncRouter) // 存储到vuex

    next({ ...to, replace: true })
  })
    .catch(error => {
      console.log(error)
    })
}

export default router
