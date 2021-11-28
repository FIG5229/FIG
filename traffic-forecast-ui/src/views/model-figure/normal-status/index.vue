<template>
  <div class="normal-status-path">
    <dimension-query @onParameterChange="onParameterChange" @onQuery="onQuery" />
    <component :is="currentCpt" />
  </div>
</template>
<script>
import dimensionQuery from '../components/dimensionQuery'
import pathModel from './src/path-model'
import timeModel from './src/time-model'
export default {
  components: {
    dimensionQuery,
    pathModel,
    timeModel
  },
  data() {
    return {
      currentCpt: pathModel
    }
  },
  methods: {
    onParameterChange(ev) {
      ev && ev === 'path' ? this.currentCpt = pathModel : this.currentCpt = timeModel
    },
    onQuery(str) {
      alert('查询数据(' + str + ')')
    }
  }
}
</script>
<style lang="scss" scoped>
$prefix:'normal-status';
.#{$prefix}-path{
  width: 100%;
  color: #fff;
  height: calc(100% - 108px);
}
</style>
