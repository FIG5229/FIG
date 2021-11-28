打印组件用于打印element表格
----
- [print-js.js](https://github.com/crabbly/Print.js) - 前端打印插件
- [html2canvas](https://github.com/niklasvh/html2canvas)  - 前端html转canvas

demo
----
```
import PrintTable from'@/components/PrintTable'
<print-table ref="printTable"></print-table>
<el-button class="filter-item" style="margin-right: 10px;" type="primary" icon="el-icon-printer" @click="$refs.printTable.toImage()">打印</el-button>
```