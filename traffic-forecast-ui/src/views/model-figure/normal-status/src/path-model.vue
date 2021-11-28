<template>
  <div class="path-model">
    <path-map-table />
    <path-map />
  </div>
</template>
<script>
import pathMap from './components/path-map'
import pathMapTable from './components/path-map-table'
export default {
  components: {
    pathMap,
    pathMapTable
  }
}
</script>
<style lang="scss" scoped>
  $prefix:'path-model';
  .#{$prefix}{
    position: relative;
    padding: 10px;
    height: calc(100% - 60px);
    background-color: #000;
  }
</style>
