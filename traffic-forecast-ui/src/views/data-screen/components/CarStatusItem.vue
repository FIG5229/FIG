<template>
  <div class="car-status-item">
    <div class="car-status-item-top">
      <span class="left">{{ carTimeInfo.destination }}</span>
      <span class="right">{{ carTimeInfo.status }}</span>
    </div>
    <div class="car-status-time els-text">
      <label>预计出发：</label><span>{{ carTimeInfo.sTime }}</span>
    </div>
    <div class="car-status-time els-text">
      <label>预计到达：</label><span>{{ carTimeInfo.eTime }}</span>
    </div>
    <el-progress style="marginTop:10px" :percentage="carTimeInfo.percent" :show-text="false" />
  </div>
</template>
<script>
export default {
  name: 'CarStatusItem',
  props: {
    carTimeInfo: {
      type: Object,
      default: () => {
        return {
          destination: '千岛湖南',
          status: '在途中',
          sTime: '2020/10/20 12：00',
          eTime: '2020/10/20 12：00',
          percent: 89
        }
      }
    }
  },
  data() {
    return {

    }
  }
}
</script>
<style lang="scss">
.car-status-item{
    padding: 10px;
    border-bottom: 1px solid #3097F6;
    .els-text{
        overflow: hidden;
        text-overflow:ellipsis;
        white-space: nowrap;
    }
    .car-status-item-top{
        display: flex;
        align-items: center;
        justify-content: space-between;
        .left{
            color: #71E6FF;
            font-size: 18px;
            font-weight: bold;
        }
        .right{
            font-size: 14px;
            font-weight: 400;
            color: #EBD500;
        }
    }
    .car-status-time{
        color: #fff;
        margin-top: 10px;
        font-size: 12px;
    }
    .el-progress-bar__inner{
        background-image: linear-gradient(to right, #186ED5 , #71E7FF);
    }
}

</style>
