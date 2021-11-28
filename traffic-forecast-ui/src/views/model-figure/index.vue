<template>
  <div class="model-figure-container">
    <page-top title="铁总预测分析展示系统" :tab-lists="tabLists" active-name="normalStatus" />
    <router-view />
  </div>
</template>

<script>
import PageTop from '../components/page-top/index.vue'
export default {
  name: 'NormalStatus',
  components: { PageTop },
  data() {
    return {
      tabLists: [
        { label: '正常状态查询', name: 'normalStatus', path: '/model-figure/normal-status' },
        { label: '异常状态查询', name: 'abnormalStatus', path: '/model-figure/normal-status' },
        { label: '去首页', name: 'goHome', path: '/' }
      ]
    }
  },
  mounted() {
  },
  methods: {
  }
}

</script>

<style lang="scss" scoped>
  .model-figure-container{
    position: relative;
    width: 100%;
    height: 100%;
    min-height: 860px;
    background-color: #000;
  }
</style>
