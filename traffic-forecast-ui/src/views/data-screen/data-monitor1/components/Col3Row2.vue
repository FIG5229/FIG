<template>
  <div id="Col3Row2" class="echarts-wrap" style="width: 100%; height: 100%" />
</template>

<script>
import 'echarts-liquidfill/src/liquidFill.js'
export default {
  name: 'Col3Row2',
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
      self.myChart = self.echarts.init(document.getElementById('Col3Row2'))
      var salvProName = [
        '临湘',
        '路口铺',
        '鹿道',
        '温春',
        '武汉北',
        '长沙南',
        '新乡',
        '昆明东',
        '拉萨',
        '成都'
      ]
      var salvProValue = [239, 181, 154, 144, 135, 117, 74, 72, 67, 55]
      var salvProMax = []
      for (let i = 0; i < salvProValue.length; i++) {
        salvProMax.push(salvProValue[0])
      }
      const option = {
        backgroundColor: '',
        grid: {
          left: '2%',
          right: '2%',
          bottom: '2%',
          top: '2%',
          containLabel: true
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'none'
          },
          formatter: function(params) {
            return params[0].name + ' : ' + params[0].value
          }
        },
        yAxis: {
          show: true,
          type: 'value',
          axisLabel: {
            show: true,
            textStyle: {
              color: '#fff'
            }
          },
          axisTick: {
            show: false
          },
          axisLine: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: ['#121313'],
              width: 1,
              type: 'solid'
            }
          }
        },
        xAxis: [
          {
            type: 'category',
            inverse: true,
            axisLabel: {
              show: true,
              interval: 0,
              // rotate:40,
              textStyle: {
                color: '#fff'
              }
            },
            splitLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            axisLine: {
              show: false
            },
            data: salvProName
          },
          {
            type: 'category',
            inverse: true,
            axisTick: 'none',
            axisLine: 'none',
            show: true,
            data: salvProValue
          }
        ],
        series: [
          {
            name: '值',
            type: 'bar',
            zlevel: 1,
            itemStyle: {
              normal: {
                barBorderRadius: 30,
                color: new self.echarts.graphic.LinearGradient(0, 1, 0, 0, [
                  {
                    offset: 0,
                    color: '#007EFF'
                  },
                  {
                    offset: 1,
                    color: '#EBD500'
                  }
                ])
              }
            },
            barWidth: '20',
            data: salvProValue
          },
          {
            name: '背景',
            type: 'bar',
            barWidth: 20,
            barGap: '-100%',
            data: salvProMax,
            itemStyle: {
              normal: {
                color: 'rgba(24,31,68,1)',
                barBorderRadius: 30
              }
            }
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
