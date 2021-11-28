<template>
  <div id="col3row1col2" class="echarts-wrap" style="width: 100%;height: 100%;" />
</template>

<script>
import 'echarts-liquidfill/src/liquidFill.js'
export default {
  name: 'Col3Row1Col2',
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
      self.myChart = self.echarts.init(document.getElementById('col3row1col2'))
      const option = {
        tooltip: {
          trigger: 'item',
          formatter: function(data) {
            return data.name + ': ' + data.value + '%'
          }
        },
        series: [{
          type: 'pie',
          radius: '55%',
          center: ['50%', '50%'],
          data: [{
            value: 30,
            name: '不正确率'
          },
          {
            value: 70,
            name: '正确率'
          }
          ],
          roseType: 'radius',
          label: {
            color: '#fff',
            fontWeight: 'bold',
            formatter: function(data) {
              return data.name + '\n' + data.value + '%'
            }
          },
          labelLine: {
            length: 1,
            lineStyle: {
              width: 2,
              color: '#888'
            }
          },
          itemStyle: {
            normal: {
              color: function(params) {
                var colorList = ['#5DBACD', '#0A8BF9']
                return colorList[params.dataIndex % colorList.length]
              }
            }
          },
          animationType: 'scale',
          animationEasing: 'elasticOut',
          animationDelay: function(idx) {
            return Math.random() * 200
          }
        }]
      }
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
