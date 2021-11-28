环境和依赖
----
- node
- webpack
- scss
- @vue/cli
- [element-ui](https://element.eleme.cn/#/zh-CN/component/installation) - 前端UI框架
- [vue](https://cn.vuejs.org/)  - 前端MVVM框架
- [开源项目参考](https://github.com/PanJiaChen/vue-element-admin)

项目构建及基本配置
----
- ##### 安装依赖
```
npm install
```
- ##### 运行项目
```
npm run dev
```
-  ##### 项目打包命令
```
npm run build:prod
```

- ##### 服务相关配置
> 接口服务地址

- 开发环境: `.env.development > VUE_APP_BASE_API`
- 测试环境: `.env.test > VUE_APP_BASE_API`
- 预发布环境: `.env.staging > VUE_APP_BASE_API`
- 生产环境: `.env.production > VUE_APP_BASE_API`
- 如设置反向代理，解决开发环境的跨域问题，请修改以下位置，并将开发环境配置文件的VUE_APP_BASE_API设置为空 ""
```
  vue.config.js >  devServer > proxy > target: '实际接口请求地址'
```
目录结构
----

```
├── .env.development                //开发环境配置
├── .env.production                 //生产环境配置
├── .env.staging                    //预发布环境配置
├── .env.staging                    //测试环境配置
├── .eslintignore
├── .eslintrc.js
├── README.md
├── babel.config.js                 //Babel配置文件
├── node_modules                    //前端依赖包
├── package-lock.json               //记录当前状态下实际安装得各个包版本
├── package.json                    //项目所需要得各种模块及项目的配置信息
├── postcss.config.js               //PostCSS配置文件
├── public                          //项目入口html
├── src                             //项目根目录
|    ├── App.vue                    //vue项目根组件
|    ├── api                        //后台接口统一管理
|    ├── assets                     //静态文件目录，经过webpack处理
|    ├── components                 //公共组件
|    ├── directive                  //自定义指令
|    ├── mixins                     //混入组件
|    ├── icons                      //icon
|    ├── layout                     //页面布局
|    ├── main.js                    //入口文件
|    ├── router                     //路由目录
|    ├── settings.js                //全局配置参数
|    ├── store                      //vuex数据仓库
|    ├── styles                     //公共scss文件
|    ├── utils                      //工具库
|    └── views                      //业务组件和页面
├── tests
└── vue.config.js
```

开发步骤
----
#### 开发一个新的页面 需要以下操作
> 注意：src/settings.js > isDebugger ,当为true时开启本地调试模式，直接获取所有本地写死的路由，不必请求后端路由权限
##### 1. 在 src/views 中创建对应的业务目录，目录、非组件、类，使用(kebab-case)命名规范，组件和类使用(PascalCase)命名规范
##### 2. 在 步骤1创建的目录下，新建index.vue文件，作为业务组件的主组件，进行开发

- 组件模板格式
```
<template>
  <!-- 每个template标签内只能有一个父元素 -->
  <div>
    <!-- 使用组件时，大写字母处断开，转换为小写，并用'-'连接 -->
    <svg-icon icon-class="fullscreen" />
    <span>{{text}}</span>
    <button @click="ceshi">点我触发click事件</buttom>
  </div>
</template>
<script>
// 引入需要使用的组件
import SvgIcon from '@/components/SvgIcon'
export default {
  // 定义组件名
  name: 'SystemMenu',
  // 需要使用的组件
  components: {
    IconBtn
  },
  // 数据改变，视图层自动做出相应变化
  data () {
    return {
      text: 'ceshi'
    }
  },
  // 函数方法
  methods: {
    ceshi () {
      this.text = '我改变了'
    }
  }
}
</script>

<style lang="scss" scoped>
/* css区域，项目默认使用scss预处理器，也可以直接使用css */

</style>

```

##### 3. 在以下文件中创建该页面的路由
```
   动态路由：src/router/asyncRouter 全部从后端获取，此文件只用于开发过程中开启isDebugger的调试
   静态路由：src/router/staticRouter 全部前端写死，用类似登陆，404等不需要权限的路由配置
   scr/utils/addRouter.js中 增加新建组件的component定义(key必须和此页面的url相等)
   部署生产环境之前，请在菜单管理中配置好新创建的路由菜单
```

- 路由格式
```
//asyncRouter.js，注：路由中的 所有url，name 都必须唯一
// path 所有一级路由都以 '/'开始，以 / 开头的嵌套路径会被当作根路径。 这让你充分的使用嵌套组件而无须设置嵌套的路径。其余路由不可以 '/'开始
// 异步动态路由
[
  {
    path: '/system',
    name: '/system',
    meta: { title: '系统管理', icon: 'home' },
    component: () => import('@/layout'),
    children: [
      {
        path: 'system-menu',
        name: 'system-menu',
        meta: { title: '菜单管理', icon: 'home' },
        component: () => import('@/views/system/system-menu')
      },
      {
        path: 'system-role',
        name: 'system-role',
        meta: { title: '角色管理', icon: 'home' },
        component: () => import('@/views/system/system-role')
      },
      {
        path: 'system-org',
        name: 'system-org',
        meta: { title: '组织机构', icon: 'home' },
        component: () => import('@/views/system/system-org')
      },
      {
        path: 'system-user',
        name: 'system-user',
        meta: { title: '用户管理', icon: 'home' },
        component: () => import('@/views/system/system-user')
      }
    ]
  }
]

```
```
(1).当设置 true 的时候该路由不会再侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1
hidden: true // (默认 false)

(2).当设置 noredirect 的时候该路由在面包屑导航中不可被点击
redirect: 'noredirect'

(3).当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
若你想不管路由下面的 children 声明的个数都显示你的根路由
你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由,此属性是能再根路由中设置
alwaysShow: true
```