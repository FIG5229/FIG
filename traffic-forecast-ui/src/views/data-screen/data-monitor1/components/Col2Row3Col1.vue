<template>
  <div id="col2row3col1" class="echarts-wrap" style="width: 100%;height: 100%;" />
</template>

<script>
export default {
  name: 'Col2Row3Col1',
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
      self.myChart = self.echarts.init(document.getElementById('col2row3col1'))
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
          data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24'],
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
            name: '文案1',
            type: 'line',
            itemStyle: {
              normal: {
                color: '#FED701',
                lineStyle: {
                  color: '#FED701',
                  width: 3
                },
                areaStyle: {
                  color: new self.echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                    offset: 0,
                    color: 'rgba(254, 215, 1, 0)'
                  }, {
                    offset: 1,
                    color: 'rgba(254, 215, 1, 0.7)'
                  }])
                }
              }
            },
            data: [5, 13, 8, 7, 12, 8, 8, 3, 9, 9, 9, 9, 20, 20, 20, 20, 24, 18, 27, 20, 15, 15, 15, 11]
          }
        ]
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
