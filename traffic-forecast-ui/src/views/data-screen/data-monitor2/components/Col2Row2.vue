<template>
  <div id="Col2Row2" class="echarts-wrap" style="width: 100%; height: 100%" />
</template>

<script>
export default {
  name: 'Col1Row2',
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
      self.myChart = self.echarts.init(document.getElementById('Col2Row2'))
      const option = {
        backgroundColor: '',
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        visualMap: {
          show: false,
          min: 80,
          max: 600,
          inRange: {
            colorLightness: [0, 1]
          }
        },
        series: [
          {
            name: '站点异常统计',
            type: 'pie',
            radius: '55%',
            center: ['50%', '50%'],
            data: [
              { value: 335, name: '道岔故障' },
              { value: 310, name: '机车故障' },
              { value: 274, name: '车载故障' },
              { value: 235, name: '列尾故障' },
              { value: 400, name: '轨道故障及线路' }
            ].sort(function(a, b) {
              return a.value - b.value
            }),
            roseType: 'radius',
            label: {
              color: '#ECECEC',
              formatter: function(params) {
                const { name, percent } = params
                return `${name}:${percent}%`
              }
            },
            labelLine: {
              lineStyle: {
                color: '#C9C9C9'
              },
              smooth: 0.2,
              length: 10,
              length2: 20
            },
            color: ['#4E7DFF', '#53E041', '#70FAFF', '#EBD500', '#1DA7FF'],
            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function(idx) {
              return Math.random() * 200
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
