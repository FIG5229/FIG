<template>
  <div class="top">
    <div class="title top-font-style">{{ title }}</div>
    <screenfull id="screenfull" style="position: absolute;z-index:500;right: 10px;top: 10px;color: #fff;" />
  </div>
</template>

<script>
import Screenfull from '@/components/Screenfull'
export default {
  name: 'Top',
  components: { Screenfull },
  props: {
    title: {
      default: '标题',
      type: [String, Number]
    }
  },
  data() {
    return {

    }
  },
  mounted() {

  },
  methods: {

  }
}

</script>

<style lang="scss" scoped>
.top{
  position: relative;
  width: 100%;
  height: 108px;
  background: url("../../../assets/data-screen/home_head_img.png") no-repeat center center;
  background-size: 1920px 114px;
  .title{
    height: 100px;
    line-height: 94px;
    font-size: 48px;
    text-align: center;
  }
  .top-font-style{
    font-size: 52px;
    font-weight: 500;
    color: #0EFCFF;
    text-shadow: 0px 3px 5px rgba(0, 0, 0, 0.3);
    background: linear-gradient(0deg, #666666 10.107421875%, #FFFFFF 59.0087890625%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
  }
}
</style>
