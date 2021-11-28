// settings中的isDebugger=true时，跳过后台权限获取操作并直接使用此文件的路由配置
// 异步动态路由
const asyncRouter = [
  {
    path: '/system',
    name: '/system',
    meta: { title: '系统管理', icon: 'example' },
    component: () => import('@/layout'),
    alwaysShow: true,
    children: [
      {
        path: 'system-menu',
        name: 'system-menu',
        meta: { title: '菜单管理', icon: 'list' },
        component: () => import('@/views/system/system-menu')
      },
      {
        path: 'system-role',
        name: 'system-role',
        meta: { title: '角色管理', icon: 'people' },
        component: () => import('@/views/system/system-role')
      },
      {
        path: 'system-org',
        name: 'system-org',
        meta: { title: '组织机构', icon: 'tree' },
        component: () => import('@/views/system/system-org')
      },
      {
        path: 'system-user',
        name: 'system-user',
        meta: { title: '用户管理', icon: 'peoples' },
        component: () => import('@/views/system/system-user')
      },
      {
        path: 'system-permission',
        name: 'system-permission',
        meta: { title: '权限管理', icon: 'peoples' },
        component: () => import('@/views/system/system-permission')
      }
    ]
  },
  {
    path: '/data-screen',
    name: '/data-screen',
    meta: { title: '可视化大屏', icon: 'example' },
    component: () => import('@/layout/AppsLayout'),
    alwaysShow: true,
    children: [
      {
        path: 'data-monitor1',
        name: 'data-monitor1',
        meta: { title: '车流预测', icon: 'list' },
        component: () => import('@/views/data-screen/data-monitor1')
      },
      {
        path: 'data-monitor2',
        name: 'data-monitor2',
        meta: { title: '车流预测', icon: 'list' },
        component: () => import('@/views/data-screen/data-monitor2')
      }
    ]
  },
  {
    path: '/model-figure',
    name: '/model-figure',
    meta: { title: '模型推算', icon: 'example' },
    component: () => import('@/views/model-figure'),
    alwaysShow: true,
    children: [
      {
        path: 'normal-status',
        name: 'normal-status',
        meta: { title: '正常状态查询', icon: 'list' },
        component: () => import('@/views/model-figure/normal-status')
      }
    ]
  }
]

export default asyncRouter
