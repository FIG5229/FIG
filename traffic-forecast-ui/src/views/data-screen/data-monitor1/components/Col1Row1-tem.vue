<template>
  <div id="col3row3" class="echarts-wrap" style="width: 100%;height: 100%;" />
</template>

<script>
import 'echarts-liquidfill/src/liquidFill.js'
export default {
  name: 'Col3Row3',
  data() {
    return {}
  },
  mounted() {
    const opData = {}
    this.loadEcharts(opData)
  },
  methods: {
    loadEcharts(opData) {
      const self = this
      self.myChart = self.echarts.init(document.getElementById('col3row3'))
      const option = {}
      self.myChart.setOption(option)
    }
  }
}

</script>

<style lang="scss" scoped>
.item-wrap{
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  .title{
    margin-bottom: 8px;
    height: 42px;
    line-height: 42px;
    background: url("../../../../assets/data-screen/title-bg.png") no-repeat center center;
    background-size: 100% 100%;
    font-size: 22px;
    font-weight: 500;
    color: #0EFCFF;
    text-indent: 30px;
  }
  .border-content{
    height: 100%;
    background: url("../../../../assets/data-screen/cheek_se.png") no-repeat center center;
    background-size: 100% 100%;
  }
}
</style>
