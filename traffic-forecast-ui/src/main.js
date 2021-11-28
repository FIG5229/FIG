import Vue from 'vue'
import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n

import '@/styles/index.scss' // global css
// import './styles/element-variables.scss'

import App from './App'
import store from './store'
import router from './router'
import Cookies from 'js-cookie'
import '@/icons' // icon
import elDragDialog from '@/directive/el-drag-dialog'
import echarts from 'echarts'
import dataV from '@jiaminghi/data-view'
import 'echarts/map/js/china.js'
import globalComponets from '@/components'
Vue.use(globalComponets)
Vue.use(dataV)
Vue.prototype.echarts = echarts
// set ElementUI lang to EN
// Vue.use(ElementUI, { locale })
Vue.config.productionTip = false
Vue.use(ElementUI, {
  locale,
  size: Cookies.get('size') || 'mini' // set element-ui default size
})

// 全局注册自定义指令
Vue.use(elDragDialog)

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})

// node_modules/@jiaminghi/data-view/lib/components/fullScreenContainer/src/main.vue
// initConfig () {
//       const { dom } = this
//       // const { width, height } = screen
//       const width = 1920
//       const height = 1080
//       this.allWidth = width
//
//       dom.style.width = `${width}px`
//       dom.style.height = `${height}px`
//     },
