<template>
  <div id="Col3Row3" class="echarts-wrap" style="width: 100%;height: 100%;" />
</template>

<script>
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
      self.myChart = self.echarts.init(document.getElementById('Col3Row3'))
      const option = {
        backgroundColor: '#000',
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          top: '5%',
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          boundaryGap: false, // 坐标轴两边留白
          data: ['10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00'],
          axisLabel: { // 坐标轴刻度标签的相关设置。
            //	interval: 0,//设置为 1，表示『隔一个标签显示一个标签』
            //	margin:15,
            textStyle: {
              color: '#fff',
              fontStyle: 'bold',
              fontFamily: '微软雅黑',
              fontSize: 14
            }
            // rotate:50,
          },
          axisTick: {// 坐标轴刻度相关设置。
            show: false
          },
          axisLine: {// 坐标轴轴线相关设置
            show: false
          },
          splitLine: { // 坐标轴在 grid 区域中的分隔线。
            show: false
          }
        },
        yAxis: [
          {
            type: 'value',
            splitNumber: 5,
            axisLabel: {
              textStyle: {
                color: '#ffffff',
                fontStyle: 'normal',
                fontFamily: '微软雅黑',
                fontSize: 14
              }
            },
            axisLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: 'rgba(255,255,255,0.09)'
                // 	opacity:0.1
              }
            }

          }
        ],
        series: [
          {
            name: '文案2',
            type: 'line',
            itemStyle: {
              normal: {
                color: '#63DDE2',
                lineStyle: {
                  color: '#63DDE2',
                  width: 3
                },
                areaStyle: {
                  color: new self.echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                    offset: 0,
                    color: 'rgba(99, 221, 226, 0)'
                  }, {
                    offset: 1,
                    color: 'rgba(99, 221, 226, 0.7)'
                  }])
                }
              }
            },
            data: [5, 13, 8, 7, 12, 14, 20]
          }
        ]
      }
      self.myChart.setOption(option)
    }
  }
}

</script>

<style lang="scss" scoped>

</style>
