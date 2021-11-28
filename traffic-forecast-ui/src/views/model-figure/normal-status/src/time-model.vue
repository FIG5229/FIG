<template>
  <div class="time-model">
    <div class="time-model-inner">
      <div class="time-model-item mg-right-40">
        <page-card>
          <div slot="header">
            <header-col1-row1 @onSearch="onSearchCol1Row1" />
          </div>
          <col1-row1 />
        </page-card>
      </div>
      <div class="time-model-item">
        <page-card>
          <div slot="header">
            <header-col2-row1 @onSearch="onSearchCol2Row1" />
          </div>
          <co2-row1 />
        </page-card>
      </div>
      <div class="time-model-item mg-top-40 mg-right-40">
        <page-card>
          <div slot="header">
            区间时间TOP10
          </div>
          <col1-row2 />
        </page-card>
      </div>
      <div class="time-model-item mg-top-40">
        <page-card>
          <div slot="header">
            停站时间TOP10
          </div>
          <co2-row2 />
        </page-card>
      </div>
    </div>
  </div>
</template>
<script>
import headerCol1Row1 from './components/headerCol1Row1'
import headerCol2Row1 from './components/headerCol2Row1'
import Col1Row1 from './charts/Col1row1'
import Co2Row1 from './charts/Co2Row1'
import Col1Row2 from './charts/Col1Row2'
import Co2Row2 from './charts/Co2Row2'
export default {
  components: { headerCol1Row1, Col1Row1, headerCol2Row1, Co2Row1, Col1Row2, Co2Row2 },
  data() {
    return {
    }
  },
  methods: {
    onSearchCol1Row1(acceptObj) {
      alert('查询数据' + JSON.stringify(acceptObj))
    },
    onSearchCol2Row1(acceptObj) {
      alert('查询数据' + JSON.stringify(acceptObj))
    }
  }
}
</script>
<style lang="scss" scoped>
  $prefix:'time-model';
  .#{$prefix}{
    width: 100%;
    padding: 0 40px 40px 40px;
    height: calc(100% - 120px);
    overflow: auto;
    &-inner{
      // width: 100%;
      height: 100%;
      min-width: 1300px;
      overflow: auto;
    }
    &-item{
      display: inline-block;
      width: 48%;
      // flex-basis: 48%;
    }
    .mg-top-40{
      margin-top: 40px;
    }
    .mg-right-40{
      margin-right: 40px;
    }
  }
</style>
