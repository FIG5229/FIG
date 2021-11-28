<template>
  <div id="Col4Row1">
    <div class="col-row-warp">
      <div v-for="(item, index) in lists" :key="index" class="col4-row1-item">
        <div class="item-title els-text">{{ item.title }}</div>
        <div class="item-text els-text">{{ item.value }}</div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {},
  data() {
    return {
      lists: [
        { title: '数据总量', value: '199GB' },
        { title: '数据总条数', value: '199万' },
        { title: '日增量大小', value: '10G' },
        { title: '日增量条数', value: '100万' },
        { title: '存储库数量', value: '16' },
        { title: '表数量', value: '10' },
        { title: '字段数量', value: '101' },
        { title: '模型/新频次', value: '1000次' },
        { title: '模型数量', value: '3个' },
        { title: '预测次数', value: '1011次' },
        { title: '训练数据量', value: '1000次' },
        { title: '模型/新周期', value: '1个月' }
      ]
    }
  }
}
</script>
<style lang="scss" scoped>
#Col4Row1 {
  padding: 0 10px;
  .els-text {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  .col-row-warp{
    width: 100%;
    display: flex;
    justify-content: space-between;
    flex-direction: row;
    flex-wrap: wrap;
  }
  .col4-row1-item {
    width: 30.33%;
    margin-top: 10px;
    text-align: center;
    background: #113d65;
    box-sizing: border-box;
    line-height: 1;
    &:nth-child(3n-1){
      margin-right:10px;
      margin-left: 10px;
    }
    .item-title {
      padding: 10px 0;
      color: #fff;
      font-size: 12px;
      font-weight: 400;
    }
    .item-text {
      padding: 5px 0 10px 0;
      font-size: 16px;
      font-weight: bold;
      color: #5db9cd;
    }
  }
}
</style>
