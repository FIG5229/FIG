<template>
  <section class="app-main">
    <transition name="fade-transform" mode="out-in">
      <router-view :key="key" />
    </transition>
  </section>
</template>

<script>
export default {
  name: 'AppMain',
  computed: {
    key() {
      return this.$route.path
    }
  }
}
</script>

<style scoped>
.app-main {
  width: 100%;
  overflow: hidden;
  position: absolute;
  top: 0;
  bottom: 0;
}
.fixed-header+.app-main {
  position: absolute;
  top: 50px;
  bottom: 0;
}
</style>

<style lang="scss">
// fix css style bug in open el-dialog
/*.el-popup-parent--hidden {*/
/*  .fixed-header {*/
/*    padding-right: 15px;*/
/*  }*/
/*}*/
</style>
