<template>
  <div id="Col2Row1" class="echarts-wrap" style="width: 100%; height: 100%" />
</template>

<script>
export default {
  name: 'Col1Row1',
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
      self.myChart = self.echarts.init(document.getElementById('Col2Row1'))
      const testData = [10, 20, 30, 40, 50, 60, 70, 80, 92, 100, 110, 120]
      const provRank = [
        'jan',
        'feb',
        'mar',
        'apr',
        'may',
        'jun',
        'jul',
        'aug',
        'sep',
        'oct',
        'nov',
        'dec'
      ]
      const datX = testData.map((v, i) => {
        return {
          value: v,
          itemStyle: {
            color: i === testData.length - 1 ? '#EBD500' : '#11E9FF'
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
