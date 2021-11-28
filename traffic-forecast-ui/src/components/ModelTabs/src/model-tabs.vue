<template>
  <div class="ly-tabs">
    <div class="ly-tabs_header">
      <div class="ly-tabs_nav_wrap">
        <div
          v-for="(item, index) in tabsList"
          :key="index"
          ref="tablists"
          :class="itemClass(index)"
          :style="itemStyle"
          @click="tabsChange(index,item.name)"
        >
          {{ item.label }}
        </div>
        <div class="ly-tabs-line" :style="lineStyle" />
      </div>
    </div>
    <div v-show="disabedContent" class="ly-tabs-content">
      <slot />
    </div>
  </div>
</template>
<script>
export default {
  name: 'ModelTabs',
  model: {
    prop: 'value',
    event: 'change'
  },
  props: {
    value: {
      type: String,
      default: ''
    },
    lineColor: {
      type: String,
      default: '#409eff'
    },
    // 标签item样式
    itemStyle: {
      type: Object,
      default() {
        return {
        }
      }
    },
    // 是否禁用content
    disabedContent: {
      type: Boolean,
      default: true
    }
  },
  provide() {
    return {
      itemStyle: this.itemStyle
    }
  },
  data() {
    return {
      tabsList: [],
      lineWidth: 0,
      currentTabIndex: '',
      translateX: 0
    }
  },
  computed: {
    // 底部线条样式
    lineStyle() {
      return {
        width: `${this.lineWidth}px`,
        transform: `translateX(${this.translateX}px)`,
        backgroundColor: `${this.lineColor}`
      }
    }
  },
  watch: {
    value(n) {
      this.currentTabIndex = this.tabsList.findIndex(v => v.name === n)
    },
    tabsList() {
      this.currentTabIndex = this.tabsList.findIndex(v => v.name === this.value)
      this.showContent()
    },
    currentTabIndex(n) {
      if (n < 0) return
      this.$nextTick().then(res => {
        const tabRefs = this.$refs.tablists[n]
        this.lineWidth = tabRefs.offsetWidth - 40
        this.translateX = tabRefs.offsetLeft + 20
        this.showContent(n)
      })
    }
  },
  mounted() {
    this.tabsList = this.$children.map(v => {
      return {
        label: v.label,
        name: v.name
      }
    })
  },
  methods: {
    // tab切换时间
    tabsChange(index, name) {
      this.currentTabIndex = index
      this.$emit('change', name)
    },
    // content的显示和隐藏
    showContent(n = this.currentTabIndex) {
      this.$children.forEach((v, i) => {
        v.show = false
        n === i && (this.$children[i].show = true)
      })
    },
    // 头部样式
    itemClass(i) {
      return [
        'ly-tabs-item',
        {
          'ly-tabs-item_frist': i === 0,
          'ly-tabs-item_last': i === (this.tabsList.length - 1),
          'ly-tabs-item_active': i === this.currentTabIndex
        }
      ]
    }
  }
}
</script>
<style lang="scss">
$prefixCls:'ly-tabs';
.#{$prefixCls}{
  text-align: left;
  &-line{
    position: relative;
    height: 2px;
    transition: transform .3s cubic-bezier(.645,.045,.355,1);
    &::after{
        content: '';
        position: absolute;
        left: 50%;
        transform: translateX(-50%);
        bottom: 0;
        width: 0;
        height: 0;
        border-left: 10px solid transparent;
        border-right: 10px solid transparent;
        border-bottom: 10px solid #1D2230;
    }
  }
  &_header{
    padding: 10px;
    .#{$prefixCls}_nav_wrap{
      position: relative;
      width: 100%;
      .#{$prefixCls}-item{
        &_frist{
          // padding-left: 0;
          vertical-align: top;
        }
        .#{$prefixCls}-item_last{
          // padding-right: 0;
        }
        &_active{
          background-color: #2A7BFF;
        }
        display: inline-block;
        height: 40px;
        line-height: 40px;
        padding: 0 20px;
        cursor: pointer;
      }
    }
  }
}
</style>
