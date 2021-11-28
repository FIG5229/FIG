import { home } from './../settings'

export const addRouterMap = routerList => {
  let redirectIndex = '/defalut-layout'
  const router = []
  routerList.forEach(e => {
    if (e.type === '3') {
      return
    }
    // 组件路径
    let component = e.component
    if (component.charAt(0) === '/') {
      component = component.substr(1)
    }
    let e_new = {
      path: e.routing,
      hidden: e.sort < 0,
      name: e.routing,
      alwaysShow: e.type === '1' || e.type === '0',
      meta: {
        title: e.name,
        icon: e.icon,
        extend: e.extend || {}
      },
      // component: constantRouterComponents[e.routing]
      // component: resolve => require(['@/' + e.icon], resolve)
      // component: () => import(`@/${component}`)
      component: resolve => require([`@/${component}`], resolve)
    }
    if (e.children && e.children.length > 0) {
      e_new = Object.assign({}, e_new, { children: addRouterMap(e.children) })
    }
    if (e.redirect) {
      e_new = Object.assign({}, e_new, { redirect: e.redirect })
    }
    router.push(e_new)
  })

  if (home === '') {
    // 把第一个菜单作为首页
    if (routerList.length > 0) {
      redirectIndex = routerList[0].children > 0 ? routerList[0].routing + routerList[0].children[0].routing : routerList[0].routing
    }
  } else {
    redirectIndex = home
  }
  const def_index = {
    path: '/',
    hidden: true,
    name: 'index',
    redirect: redirectIndex,
    alwaysShow: true,
    meta: {
      title: '首页',
      icon: 'home'
    }
  }
  router.push(def_index)
  return router
}
