<template>
  <div class="header-col1-row1">
    <div class="header-col1-row1_left">
      <div class="title">{{ title }}</div>
      <format-data-picker v-model="dateSection" :placeholder="['', '']" />
    </div>
    <div class="header-col1-row1_right">
      <search-input v-model="value" size="small" @onSearch="searchActive" />
    </div>
  </div>
</template>
<script>
export default {
  name: 'HeaderCol1Row1',
  props: {
    title: {
      type: String,
      default: '区间时间'
    }
  },
  data() {
    return {
      value: '',
      dateSection: []
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
        searchDate: this.dateSection,
        searchValue: this.value
      }
      this.$emit('onSearch', senData)
    }
  }
}
</script>
<style lang="scss" scoped>
  $prefix:'header-col1-row1';
  .#{$prefix}{
    min-width: 560px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    flex-wrap: wrap;
    &_left{
      .title{
        display: inline-block;
        margin-right: 17px;
        font-weight: 400;
      }
    }
    &_right{

    }
  }
</style>
