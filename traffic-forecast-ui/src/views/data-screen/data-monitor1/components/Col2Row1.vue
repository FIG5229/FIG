<template>
  <div id="Col2Row1">
    <p class="col2-row1-title">
      <img :src="require('@/assets/data-screen/car_icon.png')">&nbsp;
      <span>今日运行车次/辆</span>
    </p>
    <div class="col2-row1-content">
      <template v-for="(v, i) in carVal">
        <div v-if="i !== 5" :key="i" class="content-value">
          {{ v }}
        </div>
      </template>
      <template v-for="(v, i) in carVal">
        <div v-if="i === 5" :key="i" style="color: #ebd500;border: 2px solid #ebd500;" class="content-value">
          {{ v }}
        </div>
      </template>

    </div>
  </div>
</template>
<script>
export default {
  props: {},
  data() {
    return {
      val: '222666'
    }
  },
  computed: {
    carVal() {
      return this.val.split('')
    }
  }
}
</script>
<style lang="scss" scoped>
#Col2Row1 {
  text-align: center;
  color: #88beff;
  .col2-row1-title {
    font-size: 24px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .col2-row1-content {
    .content-value {
      display: inline-block;
      padding: 18px 12px;
      margin: 0 10px;
      color: #88beff;
      font-size: 41px;
      font-weight: bold;
      border: 2px solid #5a9cff;
    }
  }
}
</style>
