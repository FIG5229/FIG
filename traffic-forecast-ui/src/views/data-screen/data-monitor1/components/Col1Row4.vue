<template>
  <div id="Col1Row4" class="echarts-wrap" style="width: 100%; height: 100%" />
</template>

<script>
import 'echarts-liquidfill/src/liquidFill.js'
export default {
  name: 'Col1Row4',
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
      self.myChart = self.echarts.init(document.getElementById('Col1Row4'))
      const option = {
        backgroundColor: '',
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          top: '3%',
          left: '3%',
          right: '4%',
          bottom: '6%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            data: [
              '哈局',
              '沈局',
              '京局',
              '太局',
              '呼局',
              '郑局',
              '武局',
              '西局',
              '济局',
              '上局',
              '广局',
              '宁局',
              '成局'
            ],
            axisTick: {
              show: false,
              alignWithLabel: true
            },
            axisLine: {
              show: false
            },
            axisLabel: {
              color: '#fff',
              interval: 0,
              formatter: function(value) {
                var str = value.split('')
                return str.join('\n')
              }
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            axisLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            axisLabel: {
              color: '#fff'
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: ['#121313'],
                width: 1,
                type: 'solid'
              }
            }
          }
        ],
        series: [
          {
            name: '路局预测准确率',
            type: 'bar',
            barWidth: '30%',
            itemStyle: {
              normal: {
                barBorderRadius: 3,
                color: new self.echarts.graphic.LinearGradient(0, 1, 0, 0, [
                  { offset: 0, color: '#186ED5' },
                  { offset: 1, color: '#EBD500' }
                ])
              }
            },
            data: [
              100,
              52,
              20,
              33,
              39,
              33,
              22,
              11,
              22,
              11,
              12,
              11,
              50,
              60,
              20,
              15,
              13
            ]
          }
        ]
      }
      self.myChart.setOption(option)
    }
  }
}
</script>

<style lang='scss' scoped>
</style>
