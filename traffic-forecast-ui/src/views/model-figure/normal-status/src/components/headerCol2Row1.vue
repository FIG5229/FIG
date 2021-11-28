<template>
  <div class="header-col2-row1">
    <div class="header-col2-row1_left">
      <div class="title">{{ title }}</div>
    </div>
    <div class="header-col2-row1_right">
      <search-input v-model="value" size="small" @onSearch="searchActive" />
    </div>
  </div>
</template>
<script>
export default {
  name: 'HeaderCol2Row1',
  props: {
    title: {
      type: String,
      default: '停站时间'
    }
  },
  data() {
    return {
      value: ''
    }
  },
  watch: {
    value(n) {
      // console.log(n, 'small11')
    }
  },
  methods: {
    searchActive() {
      const senData = {
        searchValue: this.value
      }
      this.$emit('onSearch', senData)
    }
  }
}
</script>
<style lang="scss" scoped>
  $prefix:'header-col2-row1';
  .#{$prefix}{
    display: flex;
    align-items: center;
    justify-content: space-between;
    &_left{
      .title{
        margin-right: 17px;
        font-weight: 400;
      }
    }
    &_right{

    }
  }
</style>
