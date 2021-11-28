<template>
  <el-popover
    placement="bottom"
    width="540"
    trigger="click"
    popper-class="popper-class"
    @show="$refs.icons.reset()"
  >
    <svg-icons-ui ref="icons" :height="$props.height" @selected="selectedIcon" v-model="form.icon" />
    <el-input slot="reference" v-model="form.icon" placeholder="请输入内容" readonly style="cursor: pointer;">
      <template slot="prepend" style="width: 40px;"><svg-icon :icon-class="form.icon" /></template>
    </el-input>
  </el-popover>
</template>
<script>
import SvgIconsUi from './svg-icons-ui'
export default {
  name: 'SelectIcon',
  components: { SvgIconsUi },
  props: {
    // icon弹出框盖度
    height: {
      type: String
    },
    valueKey: {
      type: String,
      default: 'value'
    },
    value: String
  },
  data: function() {
    return {
      form: {
        icon: this.value
      }
    }
  },
  watch: {
    value(v) {
      this.form.icon = v
    }
  },
  methods: {
    selectedIcon(name) {
      this.form.icon = name
      document.body.click()
      this.$emit('input', name)
    }
  }

}
</script>
<style lang="scss" scoped>

</style>
