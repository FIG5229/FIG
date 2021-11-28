<template>
  <div class="dashboard-container">
    <div class="dashboard-wrap">
      <div class="title-top">
        <img src="../../assets/login_logo.png" class="sidebar-logo">
        <h3 class="title">{{ title }}</h3>
      </div>
      <div class="app-wrap">
        <div v-for="(item, index) in list" :key="index" class="item" @click="item.url && $router.push(item.url)">
          <img :src="item.img" alt="">
          <div class="app-name">{{ item.name }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import defaultSettings from '@/settings'
export default {
  name: 'Dashboard',
  data() {
    return {
      title: defaultSettings.title,
      list: [
        {
          url: '/data-screen/data-monitor1',
          name: '大屏展示',
          img: require('../../assets/data-screen/dashboard/menu_icons_one.png')
        },
        {
          url: '',
          name: '数据处理',
          img: require('../../assets/data-screen/dashboard/menu_icons_two.png')
        },
        {
          url: '/model-figure/normal-status',
          name: '模型推算',
          img: require('../../assets/data-screen/dashboard/menu_icons_three.png')
        },
        {
          url: '',
          name: '服务器',
          img: require('../../assets/data-screen/dashboard/menu_icons_four.png')
        },
        {
          url: '',
          name: '实时消费',
          img: require('../../assets/data-screen/dashboard/menu_icons_five.png')
        },
        {
          url: '',
          name: '系统管理',
          img: require('../../assets/data-screen/dashboard/menu_icons_six.png')
        },
        {
          url: '',
          name: '应用扩展',
          img: require('../../assets/data-screen/dashboard/menu_extend.png')
        }
      ]
    }
  },
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard-container{
  min-height: 100%;
  width: 100%;
  background: url(../../assets/menu_bg.png) no-repeat center center;
  background-size: cover;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  .dashboard-wrap{
    position: relative;
    text-align: center;
    .title-top{
      margin-bottom: 50px;
    }
    .title {
      display: inline-block;
      color: #eee;
      text-align: center;
      font-weight: bold;
      vertical-align: 12px;
      font-size: 42px;
      letter-spacing: 3px;
    }
  }
  .app-wrap{
    width: 1073px;
    min-height: 430px;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    align-content: space-around;
    .item{
      display: flex;
      flex-direction: column;
      align-content: center;
      width: 244px;
      height: 196px;
      background: rgba(255,255,255,0.08);
      box-shadow: 0px 0px 19px 3px rgba(19, 23, 50, 0.31);
      border-radius: 10px;
      cursor: pointer;
      img{
        margin: 30px auto 0;
        display: block;
        width: 100px;
        height: 100px;
      }
      .app-name{
        height: 40px;
        font-size: 20px;
        font-weight: 400;
        color: #FFFFFF;
        line-height: 40px;
        text-align: center;
      }
    }
  }
}
</style>
