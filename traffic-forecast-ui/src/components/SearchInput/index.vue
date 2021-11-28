<template>
  <div class="search-input">
    <el-input
      ref="searchInput"
      v-model="valueInner"
      :placeholder="placeholder"
      suffix-icon="el-icon-search"
      @keyup.enter.native="onSearch"
    />
  </div>
</template>
<script>
export default {
  name: 'SearchInput',
  model: {
    prop: 'value',
    event: 'change'
  },
  props: {
    value: {
      type: [String, Number],
      default: ''
    },
    size: {
      type: String,
      default: 'small'
    },
    placeholder: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      valueInner: ''
    }
  },
  watch: {
    size: {
      handler: 'getSize',
      immediate: true
    },
    value(nVal){
      this.valueInner = nVal
    },
    valueInner(nVal) {
      this.$emit('change', nVal)
    }
  },
  mounted() {
    // element-ui input 暂时不支持suffix-icon点击事件
    this.inputIcon = this.$refs.searchInput.$el.querySelector('.el-input__suffix')
    this.inputIcon.addEventListener('click', this.iconClick, false)
  },
  beforeDestroy() {
    this.inputIcon.removeEventListener('click', this.iconClick)
  },
  methods: {
    iconClick(e) {
      this.onSearch()
    },
    onSearch() {
      this.$emit('change', this.valueInner)
      this.$listeners.onSearch()
    },
    getSize(size) {
      const sizeArr = ['small', 'large']
      const styleArr = [
        { width: '180px', height: '30px' },
        { width: '250px', height: '40px' }
      ]
      this.$nextTick().then(res => {
        const elIput = this.$el.querySelector('.el-input__inner')
        const elDomw = this.$el.querySelector('.el-input')
        const elSufix = this.$el.querySelector('.el-icon-search')
        elIput.style.height = styleArr[sizeArr.findIndex(v => v === size)].height
        elIput.style.lineHeight = styleArr[sizeArr.findIndex(v => v === size)].height
        elSufix.style.lineHeight = styleArr[sizeArr.findIndex(v => v === size)].height
        elDomw.style.width = styleArr[sizeArr.findIndex(v => v === size)].width
      })
    }
  }
}
</script>
<style lang="scss">
  $prefix:'search-input';
  .#{$prefix}{
    display: inline-block;
    .el-input__inner{
      height: 40px;
      line-height: 40px;
      background-color: #1D2230;
      border-radius: 20px;
      font-size: 16px;
      color: #fff;
      &::placeholder{
        color: #fff;
        font-size: 16px;
      }
    }
    .el-input__icon{
      line-height: 40px;
      font-size: 20px;
    }
    .el-input__suffix{
      right: 10px;
    }
  }
</style>
