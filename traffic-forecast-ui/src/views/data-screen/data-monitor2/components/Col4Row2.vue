<template>
  <div id="ColRow2" style="overflow: hidden;">
    <vueSeamlessScroll class="seamless-warp" :class-option="classOption" :data="lists">
      <CarStatusItem
        v-for="(obj, index) in lists"
        :key="index"
        :car-time-info="obj"
      />
    </vueSeamlessScroll>
  </div>
</template>
<script>
import vueSeamlessScroll from 'vue-seamless-scroll'
import CarStatusItem from '../../components/CarStatusItem'
export default {
  name: 'ColRow2',
  components: { CarStatusItem, vueSeamlessScroll },
  data() {
    return {
      lists: [
        {
          destination: '千岛湖南',
          status: '在途中',
          sTime: '2020-10-20 12：00',
          eTime: '2020-10-20 12：00',
          percent: 89
        },
        {
          destination: '新安江',
          status: '在途中',
          sTime: '2020-10-10 12：00',
          eTime: '2020-10-20 12：00',
          percent: 20
        },
        {
          destination: '功塘',
          status: '在途中',
          sTime: '2020-10-10 12：00',
          eTime: '2020-10-20 12：00',
          percent: 60
        }
      ]
    }
  },
  computed: {
    classOption() {
      return {
        step: 0.2, // 数值越大速度滚动越快
        limitMoveNum: 2, // 开始无缝滚动的数据量 this.dataList.length
        hoverStop: true, // 是否开启鼠标悬停stop
        direction: 1, // 0向下 1向上 2向左 3向右
        openWatch: true, // 开启数据实时监控刷新dom
        singleHeight: 0, // 单步运动停止的高度(默认值0是无缝不停止的滚动) direction => 0/1
        singleWidth: 0, // 单步运动停止的宽度(默认值0是无缝不停止的滚动) direction => 2/3
        waitTime: 1000 // 单步运动停止的时间(默认值1000ms)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
#ColRow2 {
  padding: 0 10px
}
</style>
