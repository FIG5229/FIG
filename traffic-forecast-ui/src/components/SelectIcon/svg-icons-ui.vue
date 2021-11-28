<template>
  <div class="ui-fas">
    <el-input v-model="name" suffix-icon="el-icon-search" placeholder="请输入图标名称" @input.native="filterIcons" />
    <el-scrollbar class="el-scrollbar-custom" :style="{'height': height}">
      <ul class="fas-icon-list">
        <li v-for="(item, index) in iconList" :key="index" @click="selectedIcon(item)">
          <svg-icon :icon-class="item" />
          <span class="icon-name line2Hidden">{{ item }}</span>
        </li>
      </ul>
    </el-scrollbar>
  </div>
</template>

<script>
import SvgIconsList from './svg-icons-list'
export default {
  name: 'SvgIconsUi',
  props: {
    height: {
      default: () => {
        return '100%'
      },
      type: String
    },
    value: String
  },
  data() {
    return {
      name: this.value,
      iconList: SvgIconsList
    }
  },
  methods: {
    filterIcons() {
      this.iconList = SvgIconsList
      if (this.name) {
        this.iconList = this.iconList.filter(item => item.includes(this.name))
      }
    },
    selectedIcon(name) {
      this.name = name
      this.$emit('selected', name)
    },
    reset() {
      this.name = ''
      this.iconList = SvgIconsList
    }
  }
}
</script>
<style lang="scss" scoped>
  .ui-fas{
    width: 100%;
    .fas-icon-list{
      overflow: hidden;
      padding-left: 15px;
      li{
        list-style: none;
        float: left;
        width: 80px;
        text-align: center;
        height: 90px;
        color: #666;
        font-size: 13px;
        border-right: 1px solid #eee;
        border-bottom: 1px solid #eee;
        margin-right: -1px;
        margin-bottom: -1px;
        cursor: pointer;
        svg{
          display: block;
          font-size: 32px;
          margin: 10px auto;
          color: #606266;
          transition: color .15s linear;
        }
        .icon-name{
          padding: 0 3px;
          height: 28px;
          word-wrap:break-word;
          width: 100%;
        }
      }
    }
  }
</style>
