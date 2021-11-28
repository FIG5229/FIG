<template>
  <div v-show="show" class="ly-tab-item">
    <slot />
  </div>
</template>
<script>
export default {
  name: 'ModelTabsItem',
  inject: ['itemStyle'],
  props: {
    label: {
      type: String,
      default: ''
    },
    name: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      show: false
    }
  }
}
</script>
<style lang="scss">
</style>
