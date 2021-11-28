<template>
  <div id="formatDataPicker" class="format-data-picker">
    <el-date-picker
      v-model="startTime"
      class="start-time mg-rh-20"
      type="date"
      :value-format="valueFormat"
      :placeholder="placeholder[0]"
      :picker-options="{
        disabledDate(time){
          return new Date(time).getTime() > new Date(endTime) ? true : false
        }
      }"
      @change="changeTime('startTime')"
    />
    —
    <el-date-picker
      v-model="endTime"
      class="end-time mg-lf-20"
      type="date"
      :picker-options="{
        disabledDate(time){
          return new Date(time).getTime() < new Date(startTime) ? true : false
        }
      }"
      :value-format="valueFormat"
      :placeholder="placeholder[1]"
      @change="changeTime('endTime')"
    />

  </div>
</template>
<script>
export default {
  name: 'FormatDataPicker',
  props: {
    // 日期格式
    valueFormat: {
      type: String,
      default: 'yyyy/MM/dd'
    },
    refresh: {
      type: Boolean,
      default: false
    },
    value: {
      type: Array,
      default: () => []
    },
    placeholder: {
      type: Array,
      default() {
        return ['请选择开始时间', '请选择结束时间']
      }
    }
  },
  data() {
    return {
      startTime: '',
      endTime: ''
    }
  },
  computed: {
    // middle值
    middleValue: {
      get(oldVal) {
        return [this.startTime, this.endTime]
      },
      set(newVal) {
        this.startTime = newVal[0]
        this.endTime = newVal[1]
        return newVal
      }
    }
  },
  watch: {
    // entry
    value: {
      handler(newVal, oldVal) {
        this.middleValue = newVal
      },
      immediate: true
    },
    refresh(newVal, oldVal) {
      Object.assign(this.$data.ordetPicker, this.$options.data().ordetPicker)
    }
  },
  methods: {
    // output
    changeTime(time) {
      time && this.$emit('input', [this.startTime || '', this.endTime || ''])
    }
  }
}
</script>
<style lang="scss">
  $prefix:'format-data-picker';
  .#{$prefix}{
    display: inline-block;
    .mg-lf-20{
      margin-left: 10px;
    }
    .mg-rh-20{
      margin-right: 10px;
    }
    .el-date-editor{
      width: 110px;
    }
    .el-input__prefix{
      display: none;
    }
    .el-input__inner{
      padding-left: 10px;
      padding-right: 0;
      background-color: #1D2230;
      color: #E3E7FF;
    }
  }
</style>
