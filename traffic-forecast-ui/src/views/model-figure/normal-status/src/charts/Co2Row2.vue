<template>
  <div id="Co2Row2" class="echarts-wrap" style="width: 100%; height: 100%" />
</template>

<script>
export default {
  name: 'Co2Row2',
  data() {
    return {
      chart: ''
    }
  },
  mounted() {
    const opData = {}
    this.loadEcharts(opData)
    this.$bus.on('onClickBarCol1Row2', (params) => {
      var ramdomArr = []
      for (var i = 0; i < 10; i++) {
        ramdomArr.push(Math.floor(Math.random() * (500 - 1 + 1) + 1))
      }
      this.loadEcharts(opData, ramdomArr)
    })
  },
  methods: {
    loadEcharts(opData, ramdomD = [10, 22, 133, 44, 150, 60, 72, 89, 190, 200]) {
      const self = this
      self.myChart = this.chart = self.echarts.init(document.getElementById('Co2Row2'))
      const provRank = [
        '杭州',
        '北京',
        '南京',
        '青岛',
        '厦门',
        '宁波',
        '武汉',
        '广州',
        '上海',
        '济南'
      ]
      const datX = ramdomD.sort((a, b) => a - b).map((v, i) => {
        return {
          value: v,
          itemStyle: {
            color: i === 9 ? '#EBD500' : '#11E9FF'
          }
        }
      })
      const option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          },
          formatter: function(objs, index) {
            const obj = objs[0]
            return `${obj.name}<br/>${obj.marker}${obj.seriesName} : ${obj.value}`
          }
        },
        grid: {
          top: '3%',
          left: '3%',
          right: '10%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'value',
          axisTick: {
            show: false
          },
          axisLine: {
            show: false
          },
          axisLabel: {
            show: true,
            textStyle: {
              color: '#fff'
            }
          },
          splitLine: {
            show: false
          }
        },
        yAxis: {
          type: 'category',
          boundaryGap: true,
          inverse: true,
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            margin: 18,
            color: '#fff'
          },
          data: provRank
        },
        series: [
          {
            name: '停留时间',
            barMaxWidth: '40%',
            type: 'bar',
            itemStyle: {
              normal: {
                barBorderRadius: 2
              }
            },
            label: {
              normal: {
                show: true,
                position: 'right',
                formatter: '{c}',
                textStyle: {
                  color: '#fff'
                }
              }
            },
            data: datX
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
