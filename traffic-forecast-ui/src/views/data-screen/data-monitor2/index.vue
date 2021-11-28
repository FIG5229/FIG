<template>
  <div class="dashboard-container">
    <dv-full-screen-container>
      <div class="nav-left" @click="$router.push('/data-screen/data-monitor1')"><i class="el-icon-arrow-left" /></div>
      <top title="货运车流预测" />
      <div class="main">
        <div class="main-col main-col1">
          <item title="运输货品停留时间（TOP10）" class="main-row main-col1-row1" >
            <col1-row1/>
          </item>
          <item title="站点通行能力（TOP10）" class="main-row main-col1-row2">
            <col1-row2 />
          </item>
        </div>
        <div class="main-col main-col2">
          <item title="异常预测数据量统计" class="main-row main-col2-row1" >
            <col2-row1 />
          </item>
          <item title="站点异常统计" class="main-row main-col2-row2" >
            <col2-row2 />
          </item>
        </div>
        <div class="main-col main-col3">
          <item title="实时模型输入数量" class="main-row main-col3-row1" >
            <col3-row1/>
          </item>
          <item title="实时模型预测数量" class="main-row main-col3-row2" >
            <col3-row2 />
          </item>
          <item title="实时模型预测异常数量" class="main-row main-col3-row3" >
            <col3-row3 />
          </item>
        </div>
        <div class="main-col main-col4">
          <item title="总量统计" class="main-row main-col4-row1" >
            <col4-row1 />
          </item>
          <item title="车辆运行情况" class="main-row main-col4-row2" >
            <col4-row2 />
          </item>
        </div>
      </div>
    </dv-full-screen-container>
  </div>
</template>

<script>
import Item from '../components/Item'
import Top from '../components/Top'
import Col1Row2 from './components/Col1Row2'
import Col1Row1 from './components/Col1Row1'
import Col2Row1 from './components/Col2Row1'
import Col2Row2 from './components/Col2Row2'
import Col3Row1 from './components/Col3Row1'
import Col3Row2 from './components/Col3Row2'
import Col3Row3 from './components/Col3Row3'
import Col4Row1 from './components/Col4Row1'
import Col4Row2 from './components/Col4Row2'
export default {
  name: 'DataMonitor',
  components: { Top, Item, Col1Row2, Col1Row1, Col2Row1, Col2Row2, Col3Row1, Col3Row2, Col3Row3, Col4Row1, Col4Row2},
  data() {
    return {

    }
  },
  mounted() {

  },
  methods: {

  }
}

</script>

<style lang="scss" scoped>
@import "../styles/index";
  .common-title{
    display: flex;
    width: 100%;
    height: 56px;
    img{
      display: block;
      height: 100%;
    }
  }
  .dashboard-container{
    position: relative;
    width: 100%;
    height: 100%;
    background: url("../../../assets/data-screen/home_bg.png") no-repeat center center;
    background-size: cover;
    .main{
      display: flex;
      overflow: hidden;
      position: absolute;
      top: 130px;
      left: 30px;
      right: 30px;
      bottom: 30px;
      box-sizing: border-box;
      justify-content: space-between;
      .main-col{
        height: 100%;
        display: flex;
        justify-content: space-between;
        flex-direction: column;
      }
      .main-col1{
        width: 500px;
      }
      .main-col2{
        margin: 0 30px;
        width: 500px;
      }
      .main-col3{
        width: 483px;
      }
      .main-col4{
        margin-left: 30px;
        width: 375px;
      }
      .main-col1-row1{
        height: 491px;
      }
      .main-col1-row2{
        height: 398px;
      }
      .main-col2-row1{
        height: 491px;
      }
      .main-col2-row2{
        height: 398px;
      }
      .main-col3-row1{
        height: 282px;
      }
      .main-col3-row2{
        height: 287px;
      }
      .main-col3-row3{
        height: 287px;
      }
      .main-col4-row1{
        height: 383px;
      }
      .main-col4-row2{
        height: 481px;
      }
    }
  }
</style>
