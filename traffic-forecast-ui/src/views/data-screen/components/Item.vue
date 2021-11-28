<template>
  <div class="item-wrap">
    <div class="title">{{ title }}</div>
    <div class="border-content">
      <slot />
    </div>
  </div>
</template>

<script>
export default {
  name: 'Item',
  props: {
    title: {
      default: '标题',
      type: [String, Number]
    }
  },
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
.item-wrap{
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  background: url("../../../assets/data-screen/cheek_se.png") no-repeat center center;
  background-size: 100% 100%;
  .title{
    margin-bottom: 8px;
    height: 42px;
    line-height: 48px;
    background: url("../../../assets/data-screen/title-bg.png") no-repeat 4px 5px;
    background-size: 100% 100%;
    font-size: 18px;
    font-weight: 500;
    color: #0EFCFF;
    text-indent: 30px;
  }
  .border-content{
    padding: 6px;
    height: 100%;
  }
}
</style>
