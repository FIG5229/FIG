<template>
  <div id="eId" class="echarts-wrap" style="width: 100%; height: 100%" />
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
      self.myChart = self.echarts.init(document.getElementById('eId'))
      const colors = [
        '#025BFF',
        '#066FFF',
        '#0084FF',
        '#0086E4',
        '#0096FF',
        '#00B4FF',
        '#6FDDF3',
        '#FFB14D',
        '#FF9540',
        '#FF583C'
      ]
      const lineargroup = [
        {
          value: 1000,
          name: '武汉北'
        },
        {
          value: 900,
          name: '长沙'
        },
        {
          value: 800,
          name: '成都'
        },
        {
          value: 700,
          name: '庙岭'
        },
        {
          value: 600,
          name: '温春'
        },
        {
          value: 500,
          name: '鹿道'
        },
        {
          value: 400,
          name: '上虞'
        },
        {
          value: 300,
          name: '临湘'
        },
        {
          value: 200,
          name: '北碚'
        },
        {
          value: 100,
          name: '楚雄西'
        }
      ]
      const data1 = lineargroup.map((v, i) => {
        return {
          value: lineargroup[i].value,
          name: lineargroup[i].name
        }
      })
      const option = {
        color: colors,
        series: [
          {
            top: 0,
            type: 'funnel',
            gap: 0,
            minSize: 150,
            left: '10%',
            width: '60%',
            height: '80%',
            label: {
              show: true,
              position: 'inside',
              fontSize: '14',
              formatter: function(d) {
                return d.percent + '%'
              }
            },
            data: data1
          },
          {
            top: 0,
            type: 'funnel',
            gap: -1,
            minSize: 150,
            left: '10%',
            width: '60%',
            z: 2,
            height: '80%',
            label: {
              normal: {
                color: '#fff',
                position: 'right',
                formatter: function(d) {
                  return d.name + d.data.value
                }
              }
            },
            labelLine: {
              show: false
            },
            data: data1
          }
        ]
      }
      self.myChart.setOption(option)
    }
  }
}
</script>

<style lang="scss" scoped>
.item-wrap {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  .title {
    margin-bottom: 8px;
    height: 42px;
    line-height: 42px;
    background: url("../../../../assets/data-screen/title-bg.png") no-repeat
      center center;
    background-size: 100% 100%;
    font-size: 22px;
    font-weight: 500;
    color: #0efcff;
    text-indent: 30px;
  }
  .border-content {
    height: 100%;
    background: url("../../../../assets/data-screen/cheek_se.png") no-repeat
      center center;
    background-size: 100% 100%;
  }
}
</style>
