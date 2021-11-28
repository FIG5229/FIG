<template>
  <div class="page-card has-shadow">
    <div class="page-card_header">
      <slot name="header" />
    </div>
    <div class="page-card_body" :style="contentStyle">
      <slot />
    </div>
  </div>
</template>
<script>
export default {
  name: 'PageCard',
  props: {
    contentStyle: {
      type: Object,
      default() {
        return {
          height: '260px'
        }
      }
    }
  }
}
</script>
<style lang="scss" scoped>
  $prefix:'page-card';
  .#{$prefix}{
    background: #1D2230;
    border-radius: 4px;
    overflow: hidden;
    // min-width: 600px;
    // color: #303133;
    transition: .3s;
    &_header{
      padding: 30px;
      box-sizing:border-box;
      font-size: 18px;
      font-family: Source Han Sans CN;
      font-weight: 400;
      color: #E3E7FF;
    }
    &_body{
      padding: 0 30px 30px 30px;
    }
    &.has-shadow{
      // box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
    }
  }
</style>
