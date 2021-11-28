<template>
  <div id="Co2Row1" class="echarts-wrap" style="width: 100%; height: 100%" />
</template>

<script>
import 'echarts-liquidfill/src/liquidFill.js'
export default {
  name: 'Co2Row1',
  data() {
    return {
      chart: null
    }
  },
  mounted() {
    const opData = {}
    this.loadEcharts(opData)
  },
  methods: {
    loadEcharts(opData) {
      const self = this
      self.myChart = this.chart = self.echarts.init(document.getElementById('Co2Row1'))
      self.myChart.on('click', (bar) => {
        this.$bus.emit('onClickBarCol1Row2', bar)
      })
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
              'Mon',
              'Tue',
              'Wed',
              'Thu',
              'Fri',
              'Sat',
              'Sun'
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
              interval: 0
              // formatter: function(value) {
              //   var str = value.split('')
              //   return str.join('\n')
              // }
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
            name: '停站时间',
            type: 'bar',
            barWidth: '20%',
            itemStyle: {
              normal: {
                barBorderRadius: 3,
                color: '#02CE7F'
              }
            },
            data: [
              100,
              52,
              200,
              334,
              390,
              330,
              220,
              110,
              220,
              111,
              120
            ]
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
