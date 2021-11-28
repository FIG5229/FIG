// 静态动态路由，无权限限制
const staticRouter = [
  {
    path: '/redirect',
    component: () => import('@/layout'),
    hidden: true,
    children: [
      {
        path: '/redirect/:path*',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/dashboard',
    component: () => import('@/views/dashboard/index'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  {
    path: '/defalut-layout',
    component: () => import('@/layout'),
    hidden: true
  }
  // 404 page must be placed at the end !!!
  // { path: '*', redirect: '/404', hidden: true }
]
export default staticRouter
