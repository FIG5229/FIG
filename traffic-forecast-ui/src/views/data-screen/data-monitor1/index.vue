<template>
  <div class="dashboard-container">
    <dv-full-screen-container>
      <div
        class="nav-right"
        @click="$router.push('/data-screen/data-monitor2')"
      >
        <i class="el-icon-arrow-right" />
      </div>
      <div class="top">
        <top title="货运车流预测" />
      </div>
      <div class="main">
        <div class="main-col main-col1">
          <item title="路径测试" class="main-row main-col1-row1">
            <col1-row1 />
          </item>
          <item title="路局管内车流" class="main-row main-col1-row2">
            <col1-row2 />
          </item>
          <item title="路局预测命中率" class="main-row main-col1-row3">
            <col1-row3 />
          </item>
          <item title="路局预测准确率" class="main-row main-col1-row4">
            <col1-row4 />
          </item>
        </div>
        <div class="main-col main-col2">
          <div class="main-row main-col2-row1">
            <col2-row1 />
          </div>
          <div class="main-row main-col2-row2">
            <col2-row2 />
          </div>
          <div class="main-row main-col2-row3">
            <item title="准确率统计-月" class="main-row main-col2-row3-col1">
              <col2-row3-col1 />
            </item>
            <item title="小时统计预测数" class="main-row main-col2-row3-col2">
              <col2-row3-col2 />
            </item>
          </div>
        </div>
        <div class="main-col main-col3">
          <div class="main-row main-col3-row1">
            <item
              title="输入数据总量/错误量"
              class="main-row main-col3-row1-col1"
            >
              <col3-row1-col1 />
            </item>
            <item title="异常预测统计/天" class="main-row main-col3-row1-col2">
              <col3-row1-col2 />
            </item>
          </div>
          <item
            title="异常预测站点分布（TOP10）"
            class="main-row main-col3-row2"
          >
            <col3-row2 />
          </item>
          <item title="异常数据稽核统计" class="main-row main-col3-row3">
            <col3-row3 />
          </item>
        </div>
      </div>
    </dv-full-screen-container>
  </div>
</template>

<script>
import Item from '../components/Item'
import Top from '../components/Top'
import Col1Row1 from './components/Col1Row1'
import Col1Row2 from './components/Col1Row2'
import Col1Row3 from './components/Col1Row3'
import Col1Row4 from './components/Col1Row4'
import Col2Row3Col1 from './components/Col2Row3Col1'
import Col2Row3Col2 from './components/Col2Row3Col2'
import Col2Row1 from './components/Col2Row1'
import Col2Row2 from './components/Col2Row2'
import Col3Row1Col1 from './components/Col3Row1Col1'
import Col3Row1Col2 from './components/Col3Row1Col2'
import Col3Row2 from './components/Col3Row2'
import Col3Row3 from './components/Col3Row3'
export default {
  name: 'DataMonitor',
  components: {
    Top,
    Item,
    Col1Row1,
    Col1Row2,
    Col2Row1,
    Col1Row3,
    Col1Row4,
    Col2Row3Col1,
    Col2Row3Col2,
    Col3Row1Col1,
    Col3Row1Col2,
    Col3Row3,
    Col2Row2,
    Col3Row2
  },
  data() {
    return {}
  },
  mounted() {},
  methods: {}
}
</script>

<style lang="scss" scoped>
@import "../styles/index";
.common-title {
  display: flex;
  width: 100%;
  height: 56px;
  img {
    display: block;
    height: 100%;
  }
}
.dashboard-container {
  position: relative;
  width: 100%;
  height: 100%;
  background: url("../../../assets/data-screen/home_bg.png") no-repeat center
    center;
  background-size: cover;
  .main {
    display: flex;
    overflow: hidden;
    position: absolute;
    top: 130px;
    left: 30px;
    right: 30px;
    bottom: 30px;
    box-sizing: border-box;
    justify-content: space-between;
    .main-col {
      height: 100%;
      display: flex;
      justify-content: space-between;
      flex-direction: column;
    }
    .main-col1 {
      width: 439px;
    }
    .main-col2 {
      margin: 0 30px;
      width: 790px;
    }
    .main-col3 {
      width: 590px;
    }
    .main-col1-row1 {
      height: 183px;
    }
    .main-col1-row2 {
      height: 241px;
    }
    .main-col1-row3 {
      height: 242px;
    }
    .main-col1-row4 {
      height: 242px;
    }
    .main-col2-row1 {
      height: 155px;
    }
    .main-col2-row2 {
      height: 500px;
    }
    .main-col2-row3 {
      height: 242px;
      display: flex;
      flex-direction: row;
      justify-content: space-between;
    }
    .main-col2-row3-col1 {
      width: 49%;
    }
    .main-col2-row3-col2 {
      width: 49%;
    }
    .main-col3-row1 {
      height: 306px;
      display: flex;
      flex-direction: row;
      justify-content: space-between;
    }
    .main-col3-row1-col1 {
      width: 99%;
    }
    .main-col3-row1-col2 {
      width: 99%;
    }
    .main-col3-row2 {
      height: 274px;
    }
    .main-col3-row3 {
      height: 274px;
    }
  }
}
</style>
