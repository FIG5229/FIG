<template>
  <div class="page-top">
    <div class="page-top-left">
      <img src="../../../assets/login_logo.png" class="title-logo">
      <span class="title">{{ title }}</span>
      <model-tabs v-model="activeVal" line-color="#1D2230" :item-style="itemStyle" :disabed-content="false">
        <model-tabs-item v-for="(item, index) in tabLists" :key="index" :label="item.label" :name="item.name" />
      </model-tabs>
    </div>
    <div class="page-top-right">
      <i class="el-icon-user-solid" />
      <span>用户信息</span>
    </div>
  </div>
</template>
<script>
export default {
  name: 'PageTop',
  props: {
    title: {
      default: '标题',
      type: [String, Number]
    },
    activeName: {
      type: String,
      default: ''
    },
    tabLists: {
      type: Array,
      default() {
        return [
          { label: 'tabLabel', name: 'tabName', path: '/target route of path' }
        ]
      }
    }
  },
  data() {
    return {
      activeVal: this.activeName || this.tabLists[0].name || '',
      itemStyle: {
        height: '90px',
        lineHeight: '90px',
        fontSize: '16px',
        color: '#E3E7FF'
      }
    }
  },
  watch: {
    activeVal(n, o) {
      if (n !== o) {
        this.tabLists.length && this.$router.push({ path: this.tabLists.find(v => v.name === n).path })
      }
    }
  },
  mounted() {

  },
  methods: {

  }
}

</script>
<style lang="scss" scoped>
$top:'page-top';
.#{$top}{
  display: flex;
  align-items: center;
  width: 100%;
  height: 90px;
  background: #1D2230;
  padding: 0 25px;
  // background-size: 1920px 114px;
  &-left{
    flex: 1;
    display: flex;
    align-items: center;
    .title-logo{
      width: 30px;
      height: 30px;
    }
    .title{
      margin:0 60px 0 20px;
      font-size: 30px;
      font-family: AliHYAiHei;
      font-weight: bold;
      color: #FFFFFF;
      text-shadow: 0px 4px -1px #000000;
      background: linear-gradient(0deg, rgba(255, 255, 255, 1) 0%, rgba(120, 120, 120, 1) 39.35546875%, rgba(255, 255, 255, 1) 77.05078125%, rgba(255, 255, 255, 0.35) 100%);
      -webkit-background-clip: text;
      -webkit-text-fill-color: transparent;
    }
  }
  &-right{
    color: #CCCCCC;
    font-size: 20px;
  }
}
</style>
