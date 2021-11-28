<template>
  <div class="dimension-query">
    <div class="dimension-query-search">
      <el-dropdown @command="onCommand">
        <div class="dimension-query-select-btn">
          {{ parameter === 'path' ? '路径参数' : '时间参数' }}<i class="el-icon-caret-bottom el-icon--right" />
        </div>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item style="width:140px" command="path">路径参数</el-dropdown-item>
          <el-dropdown-item style="width:140px" command="time">时间参数</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <search-input
        v-model="valO"
        style="marginLeft:30px"
        size="large"
        placeholder="请输入搜索内容..."
        @onSearch="onQueryStr"
      />
    </div>
    <el-breadcrumb separator="|" class="dimension-query_jump_list">
      <el-breadcrumb-item :to="{ path: '/' }">
        <i class="el-icon-s-home" />
        首页
      </el-breadcrumb-item>
      <el-breadcrumb-item @click.native="$router.go(-1)"><i class="el-icon-back" />返回</el-breadcrumb-item>
      <el-breadcrumb-item><i class="el-icon-chat-line-square" /> 系统消息</el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>
<script>
export default {
  name: 'DimensionQuery',
  data() {
    return {
      valO: '',
      inputIcon: null,
      parameter: 'path'
    }
  },
  methods: {
    onQueryStr() {
      this.$listeners.onQuery(this.valO)
    },
    onCommand(ev) {
      this.parameter = ev
      this.valO = ''
      this.$listeners.onParameterChange(ev)
    }
  }
}
</script>
<style lang="scss" scoped>
  $prefix:'dimension-query';
  .#{$prefix}{
    padding: 10px 40px;
    display: flex;
    align-items: center;
    /deep/ .el-breadcrumb{
      font-size: 16px;
      line-height: 1;
      .el-breadcrumb__inner{
        color: #CCC;
        cursor: pointer;
      }
    }
    // justify-content: space-between;
    &-search{
      flex: 1;
    }
    &-select-btn{
      width: 140px;
      height: 40px;
      background: #2A7BFF;
      font-size: 16px;
      text-align: center;
      line-height: 40px;
      font-weight: 400;
      color: #E3E7FF;
      border: 1px solid #2A7BFF;
    }
    &_jump_list{
      /deep/ .el-breadcrumb__separator{
        padding: 0 20px;
      }
    }
  }
</style>
