<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button
        class="filter-item"
        style="margin-right: 10px;"
        type="primary"
        icon="el-icon-printer"
        @click="$refs.printTable.toImage()"
      >打印
      </el-button>
      <print-table ref="printTable" />
    </div>

    <el-table
      ref="tableMixin"
      :data="list.slice((listQuery.page-1)*listQuery.limit,listQuery.page*listQuery.limit)"
      border
      fit
      highlight-current-row
      height="height: calc(100% - 100px);"
      style="width: 100%;height:calc(100% - 100px);"
    >
      <el-table-column label="序号" width="60" align="center">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="角色名称" prop="name" align="center" />
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit "
    />
  </div>
</template>

<script>
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import waves from '@/directive/waves' // waves directive
import PrintTable from '@/components/PrintTable'

export default {
  name: 'SystemRole',
  components: { Pagination, PrintTable },
  directives: { waves },
  data() {
    return {
      list: [{ 'bId': '0', 'code': 'default', 'extend': null, 'gmtCreate': '2019-12-12 15:09:05', 'gmtModified': '2019-12-12 15:09:05', 'id': 1, 'name': '默认角色', 'sort': 1, 'status': 1 }, { 'bId': 'e0da9f42810443f9b092ab97c0f8d83b', 'code': '', 'extend': null, 'gmtCreate': '2019-12-12 15:42:18', 'gmtModified': '2019-12-12 15:42:18', 'id': 2, 'name': '测试', 'sort': 10000, 'status': 1 }, { 'bId': 'eb20ef14c7e244d5943da687c51414be', 'code': '', 'extend': null, 'gmtCreate': '2019-12-17 15:08:14', 'gmtModified': '2019-12-17 15:08:14', 'id': 3, 'name': 'ceshiheight', 'sort': 10000, 'status': 1 }, { 'bId': 'e314811b4a04404eb4f8dd4ae7a87f05', 'code': '', 'extend': null, 'gmtCreate': '2019-12-17 15:08:20', 'gmtModified': '2019-12-17 15:08:20', 'id': 4, 'name': 'ceshiheight1', 'sort': 10000, 'status': 1 }, { 'bId': 'dfcfabba58ad424190359c6ba5c8845a', 'code': '', 'extend': null, 'gmtCreate': '2019-12-17 15:08:24', 'gmtModified': '2019-12-17 15:08:24', 'id': 5, 'name': 'ceshiheight2', 'sort': 10000, 'status': 1 }, { 'bId': 'ef8a186129534850aafd9d331d3d37be', 'code': '', 'extend': null, 'gmtCreate': '2019-12-17 15:08:49', 'gmtModified': '2019-12-17 15:08:49', 'id': 6, 'name': 'ceshiheight3', 'sort': 10000, 'status': 1 }],
      total: 100,
      listQuery: {
        page: 1,
        limit: 10
      }
    }
  }
}
</script>
