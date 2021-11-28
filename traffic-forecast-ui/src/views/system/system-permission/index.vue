<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" style="margin-right: 10px;" type="primary" icon="el-icon-edit" @click="openCreate">
        创建
      </el-button>
    </div>

    <el-table
      ref="tableMixin"
      v-loading="listLoading"
      height="height: calc(100% - 100px);"
      style="width: 100%;height:calc(100% - 100px);"
      :data="list.slice((listQuery.page-1)*listQuery.limit,listQuery.page*listQuery.limit)"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="序号" width="60" align="center">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="权限名称" prop="name" align="center" />
      <el-table-column label="权限编码" prop="code" align="center" />
      <el-table-column label="接口地址" prop="url" align="center" />
      <el-table-column label="权限值" prop="val" align="center" />
      <el-table-column label="权限说明" prop="summary" align="center" />
      <el-table-column label="操作" align="center" width="180" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <el-button type="primary" size="mini" @click="openUpdate(row)">
            修改
          </el-button>
          <el-popconfirm @onConfirm="handleDel(row.bId)" title="确定删除吗？">
            <el-button slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit " @pagination="getList" />

    <el-dialog v-el-drag-dialog :close-on-click-modal="false" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="500px">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="80px" style="width: 400px; margin-left:30px;">
        <el-form-item label="权限名称" prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>
        <el-form-item label="权限编码" prop="code">
          <el-input v-model="temp.code" />
        </el-form-item>
        <el-form-item label="接口地址" prop="url">
          <el-input v-model="temp.url" />
        </el-form-item>
        <el-form-item label="权限值" prop="val">
          <el-input v-model="temp.val" />
        </el-form-item>
        <el-form-item label="权限说明" prop="summary">
          <el-input v-model="temp.summary" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          确定
        </el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { permissionList, permissionAdd, permissionEdit, permissionDelete } from '@/api/system/system-permission'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import waves from '@/directive/waves' // waves directive

export default {
  name: 'SystemPermission',
  components: { Pagination },
  directives: { waves },
  data() {
    return {
      list: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10
      },
      dialogFormVisible: false,
      // 模态框类型
      dialogStatus: '',
      textMap: {
        update: '修改',
        create: '创建'
      },
      temp: {
        bId: undefined,
        code: '',
        name: '',
        summary: '',
        url: '',
        val: ''
      },
      // 表单校验规则
      rules: {
        name: [{ required: true, message: '请输入权限名称！', trigger: 'change' }],
        code: [{ required: true, message: '请输入权限编码！', trigger: 'change' }]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      permissionList().then(response => {
        this.listLoading = false
        this.list = response.data.permissions
        this.total = response.data.permissions.length
      }).catch(error => {
        // this.listLoading = false
        console.log(error)
      })
    },
    // 打开创建角色模态框
    openCreate() {
      this.resetForm()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 创建角色
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          permissionAdd(this.temp).then(res => {
            if (res.code === 200) {
              this.$notify({
                message: '创建成功！',
                type: 'success',
                duration: 2000
              })
            }
            this.dialogFormVisible = false
            this.getList()
          }).catch(() => {
            this.$notify({
              message: '创建失败！',
              type: 'error',
              duration: 2000
            })
            this.dialogFormVisible = false
          })
        }
      })
    },
    // 打开修改角色模态框
    openUpdate(row) {
      this.temp.bId = row.bId
      this.temp.name = row.name
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 更新角色
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          permissionEdit(this.temp).then(res => {
            if (res.code === 200) {
              this.$notify({
                message: '修改成功！',
                type: 'success',
                duration: 2000
              })
            }
            this.dialogFormVisible = false
            this.getList()
          }).catch(() => {
            this.$notify({
              message: '修改失败！',
              type: 'error',
              duration: 2000
            })
            this.dialogFormVisible = false
          })
        }
      })
    },
    handleDel(ids) {
      permissionDelete(new Array(ids)).then(res => {
        if (res.code === 200) {
          this.$notify({
            message: '删除成功！',
            type: 'success',
            duration: 2000
          })
          this.getList()
        }
      })
        .catch(() => {
          this.$notify({
            message: '删除失败！',
            type: 'error',
            duration: 2000
          })
        })
    },
    // 清空表单
    resetForm() {
      this.temp = {
        bId: undefined,
        code: '',
        name: '',
        summary: '',
        url: '',
        val: ''
      }
    }
  }
}
</script>
