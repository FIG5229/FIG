<template>
  <div id="col1row1" class="echarts-wrap" style="width: 100%;height: 100%;" />
</template>

<script>
import 'echarts-liquidfill/src/liquidFill.js'
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
      self.myChart = self.echarts.init(document.getElementById('col1row1'))
      var attrs = [{
        'name': '命中率',
        'center_l': '25%'
      }, {
        'name': '准确率',
        'center_l': '75%'
      }]
      var datas = [
        [0.9, 0.9, 0.9],
        [0.9, 0.9, 0.9]
      ]

      var series = []
      for (var i = 0; i < datas.length; i++) {
        var item = {
          type: 'liquidFill',
          radius: '90%',
          center: [attrs[i].center_l, '50%'],
          //  shape: 'roundRect',
          data: datas[i],
          backgroundStyle: {
            color: {
              type: 'linear',
              x: 1,
              y: 0,
              x2: 0.5,
              y2: 1,
              colorStops: [{
                offset: 1,
                color: 'rgba(4,33,77, 0)'
              }, {
                offset: 0.5,
                color: 'rgba(4,33,77, .5)'
              }, {
                offset: 0,
                color: 'rgba(4,33,77, 1)'
              }],
              globalCoord: false
            }
          },
          outline: {
            borderDistance: 5,
            itemStyle: {
              borderWidth: 5,
              borderColor: {
                type: 'linear',
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [{
                  offset: 0,
                  color: 'rgba(50,115,233, 1)'
                }, {
                  offset: 0.5,
                  color: 'rgba(50,115,233, .5)'
                }, {
                  offset: 1,
                  color: 'rgba(50,115,233, 1)'
                }],
                globalCoord: false
              },
              shadowBlur: 10,
              shadowColor: '#000'
            }
          },
          label: {
            normal: { // 此处没有生效，本地生效
              textStyle: {
                fontSize: 10,
                color: '#e6e6e6'
              },
              formatter: ''

            }
          }
        }
        series.push(item)
      }
      const option = {
        graphic: [{
          type: 'group',
          left: '17.5%',
          top: '30%',
          children: [{
            type: 'text',
            z: 100,
            left: 'center',
            top: '25',
            style: {
              fill: '#fff',
              text: datas[0][0] * 100 + '%',
              font: '20px Microsoft YaHei'
            }
          }, {
            type: 'text',
            z: 100,
            left: 'center',
            top: '60',
            style: {
              fill: '#fff',
              text: attrs[0].name,
              font: '20px Microsoft YaHei'
            }
          }]
        }, {
          type: 'group',
          left: '68%',
          top: '30%',
          children: [{
            type: 'text',
            z: 100,
            left: 'center',
            top: '25',
            style: {
              fill: '#fff',
              text: datas[1][0] * 100 + '%',
              font: '20px Microsoft YaHei'
            }
          }, {
            type: 'text',
            z: 100,
            left: 'center',
            top: '60',
            style: {
              fill: '#fff',
              text: attrs[1].name,
              font: '20px Microsoft YaHei'
            }
          }]
        }],
        series: series
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
