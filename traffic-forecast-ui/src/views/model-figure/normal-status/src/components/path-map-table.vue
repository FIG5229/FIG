<template>
  <div class="path-map-table">
    <div class="path-map-table_wrap">
      <div class="path-map-table_query">
        <div class="search-item">
          <el-form ref="form" :model="queryLists" label-position="right" label-width="68px">
            <el-row :gutter="30">
              <el-col :span="12">
                <el-form-item label="始发站">
                  <el-select
                    v-model.trim="queryLists.start_station"
                    allow-create
                    clearable
                    class="search-item-inner"
                    filterable
                    remote
                    reserve-keyword
                    placeholder="请输入关键词"
                    :remote-method="remoteSta"
                    :loading="loading"
                  >
                    <el-option
                      v-for="(item, index) in optionsSta"
                      :key="index"
                      :label="item"
                      :value="item"
                    />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="终点站">
                  <el-select
                    v-model.trim="queryLists.end_station"
                    allow-create
                    clearable
                    class="search-item-inner"
                    filterable
                    remote
                    reserve-keyword
                    placeholder="请输入关键词"
                    :remote-method="remoteEnd"
                    :loading="loading"
                  >
                    <el-option
                      v-for="(item, index) in optionsEnd"
                      :key="index"
                      :label="item"
                      :value="item"
                    />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="当前站">
                  <el-select
                    v-model.trim="queryLists.current_station"
                    allow-create
                    clearable
                    class="search-item-inner"
                    filterable
                    remote
                    reserve-keyword
                    placeholder="请输入关键词"
                    :remote-method="remoteCur"
                    :loading="loading"
                  >
                    <el-option
                      v-for="(item, index) in optionsCur"
                      :key="index"
                      :label="item"
                      :value="item"
                    />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="当前路局">
                  <el-input v-model.trim="queryLists.area4" class="search-item-inner" />
                </el-form-item>
              </el-col>
              <el-col :span="24" class="align-right">
                <el-button type="primary" style="width:110px" @click="onQueryList">查询</el-button>
              </el-col>
            </el-row>
          </el-form>
        </div>
      </div>
      <div class="path-map-table_content">
        <el-table :data="tableData" style="width: 100%" max-height="260">
          <el-table-column align="center" prop="city" label="站名" />
          <el-table-column align="center" prop="percentage" label="占比">
            <template slot-scope="{row}">
              {{ (row.percentage*100).toFixed(2) +'%' }}
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>
<script>
import pathMathEvnet from './PathMathEvent'
import { searchSite } from '@/api/api'
export default {
  data() {
    return {
      tableData: [],
      optionsSta: [],
      optionsCur: [],
      optionsEnd: [],
      loading: false,
      queryLists: {
        start_station: '',
        current_station: '',
        end_station: '',
        area4: ''
      }
    }
  },
  mounted() {
    pathMathEvnet.$on('getTbData', res => {
      console.log(res)
      this.tableData = res.data.routes
    })
  },
  methods: {
    onQueryList() {
      const self = this
      pathMathEvnet.$emit('getMapQuery', self.queryLists)
    },
    remoteMethod(query, type) {
      const self = this
      if (query !== '') {
        this.loading = true
        searchSite(query).then(res => {
          if (type === 'sta') {
            self.optionsSta = res.data
          } else if (type === 'end') {
            self.optionsEnd = res.data
          } else if (type === 'cur') {
            self.optionsCur = res.data
          }
        }).catch(e => {
          console.log(e)
        }).finally(e => {
          this.loading = false
        })
      } else {
        this.options = []
      }
    },
    remoteSta(query) {
      this.remoteMethod(query, 'sta')
    },
    remoteCur(query) {
      this.remoteMethod(query, 'cur')
    },
    remoteEnd(query) {
      this.remoteMethod(query, 'end')
    }
  }
}
</script>
<style lang="scss" scoped>
$prefix:'path-map-table';
.#{$prefix}{
  position: absolute;
  width: 500px;
  top: 60px;
  left: 60px;
  // bottom: 200px;
  .align-right{
    text-align: right;
    /deep/ .el-button--primary{
      background-color: #2A7BFF;
      border-color: #2A7BFF;
    }
  }
  &_wrap{
    height: 100%;
    display: flex;
    flex-direction: column;
  }
  &_query{
    background: rgba(0,0,0,0.6);
    // opacity: 0.6;
    padding: 24px 30px;
    border: 4px solid #1D2230;
    .search-item-inner{
      width: 130px;
      /deep/ .el-input__inner{
        background-color: #000;
        border-radius:0;
        color: #fff;
      }
    }
    /deep/ .el-form-item__label{
      color: #fff;
    }
  }
  &_content{
    flex: 1;
    margin-top: 24px;
    padding: 30px 20px;
    background: rgba(0,0,0,0.6);
    // opacity: 0.6;
    border: 4px solid #1D2230;
    /deep/ .el-table__header-wrapper{
      border-radius: 4px;
    }
    /deep/ .el-table{
     background-color: rgba(0,0,0,0.1);
     &::before{
       height: 0;
     }
    }
    /deep/ .el-table__expanded-cell {
      background-color: transparent;
    }

    /deep/ tr {
        background-color: transparent;
        &:hover{
          // background-color: red;
        }
    }
    // ::v-deep .el-table tbody tr:hover > td {
    //   background-color: transparent;
    // }
    /deep/ td {
        border-bottom: none;
        font-size: 14px;
        font-weight: 400;
        color: #7CA0FE;
        padding: 10px 0;
        &:hover{
          // background-color: red;
        }
    }
    /deep/ th {
        background-color: #1D2230;
        text-align: center;
        border-bottom: none;
        font-size: 14px;
        font-weight: 400;
        color: #E3E7FF;
    }
  }
}
</style>
