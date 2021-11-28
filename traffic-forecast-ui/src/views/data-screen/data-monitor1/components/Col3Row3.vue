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
      const seriesData = [{
        name: '文案内容',
        value: '40000'
      }, {
        name: '受理号为空',
        value: '53000'
      }, {
        name: '当前站为空',
        value: '40000'
      }, {
        name: '时间异常',
        value: '10000'
      }, {
        name: '报文为空',
        value: '40000'
      }]
      const colorList = ['#7B91FF', '#53E041', '#89CDFF', '#1BC0FF', '#12EAFF', '#E60000', '#92368D', '#BF9926']
      const option = {
        tooltip: {
          trigger: 'item',
          borderColor: 'rgba(255,255,255,.3)',
          backgroundColor: 'rgba(13,5,30,.6)',
          borderWidth: 1,
          padding: 5,
          formatter: function(parms) {
            var str = parms.marker + '' + parms.data.name + '</br>' +
              '数量：' + parms.data.value + '</br>' +
              '占比：' + parms.percent + '%'
            return str
          }
        },
        legend: [{
          type: 'scroll',
          icon: 'circle',
          bottom: '4%',
          itemGap: 10,
          itemWidth: 8,
          // bottom:'50%',
          textStyle: {
            fontSize: 14,
            color: '#ffffff'
          }}],
        series: [{
          type: 'pie',
          z: 3,
          center: ['50%', '45%'],
          radius: ['50%', '70%'],
          clockwise: true,
          avoidLabelOverlap: true,
          hoverOffset: 15,
          itemStyle: {
            normal: {
              color: function(params) {
                return colorList[params.dataIndex]
              }
            }
          },
          label: {
            show: true,
            position: 'outside',
            formatter: '{a|{b}：{d}%}\n{hr|}',
            rich: {
              a: {
                color: '#fff',
                fontWeight: 'bold',
                padding: [20, -80, 0, -80]
              }
            }
          },
          labelLine: {
            normal: {
              length: 10,
              length2: 90,
              lineStyle: {
                width: 1
              }
            }
          },
          data: seriesData
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
