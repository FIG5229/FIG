// settings中的isDebugger=true时，跳过后台权限获取操作并直接使用此文件的路由配置
// 异步动态路由
const componentsRouter = [
  // debugger下设置路由-组件
  {
    path: '/components',
    name: '/components',
    meta: { title: '组件库', icon: 'home' },
    component: () => import('@/layout'),
    alwaysShow: true,
    children: [
      // 多级目录演示
      {
        path: 'multilevel',
        name: 'multilevel',
        meta: { title: '多级路由', icon: 'home' },
        component: () => import('@/views/components/multilevel'),
        alwaysShow: true,
        children: [
          {
            path: 'multilevel-1',
            name: 'multilevel-1',
            meta: { title: '3级路由', icon: 'home' },
            component: () => import('@/views/components/multilevel/multilevel-1')
          }
        ]
      },
      // 表格打印组件
      {
        path: 'print-table',
        name: 'print-table',
        meta: { title: '表格打印', icon: 'home' },
        component: () => import('@/views/components/print-table')
      },
      // 模态框拖拽组件
      {
        path: 'drag-dialog',
        name: 'drag-dialog',
        meta: { title: '拖拽模态框', icon: 'home' },
        component: () => import('@/views/components/drag-dialog')
      },
      // 外链
      {
        path: 'https://github.com/',
        name: 'https://github.com/',
        meta: { title: '外链', icon: 'home' }
      },
      // 内部iframe
      {
        path: 'iframe-demo',
        name: './pages/iframe.html?id=1',
        meta: { title: 'iframe访问内部html', icon: 'home' },
        component: () => import('@/views/components/iframe-demo')
      },
      // 外部iframe
      {
        path: 'iframe-demo2',
        name: 'https://www.baidu.com',
        meta: { title: 'iframe访问外部链接', icon: 'home' },
        component: () => import('@/views/components/iframe-demo')
      }
    ]
  }
]

export default componentsRouter
