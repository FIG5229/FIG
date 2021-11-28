<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button
        class="filter-item"
        type="primary"
        icon="el-icon-plus"
        @click="openCreate('0')"
      >创建</el-button>
      <el-button
        v-waves
        :disabled="selectBId.length === 0"
        class="filter-item"
        type="primary"
        icon="el-icon-delete"
        @click="handleDel(selectBId)"
      >删除</el-button>
    </div>

    <el-table
      ref="tableMixin"
      v-loading="listLoading"
      height="height: calc(100% - 50px);"
      style="width: 100%;height:calc(100% - 50px);"
      :data="list"
      row-key="bId"
      :tree-props="{children: 'children'}"
      border
      fit
      default-expand-all
      highlight-current-row
      @selection-change="handleChangeBId"
    >
      <el-table-column type="selection" width="50" align="center" />
      <el-table-column fixed="left" label="菜单名称" min-width="150px" show-overflow-tooltip prop="name" align="left" header-align="center" />
      <el-table-column label="菜单地址" min-width="140" show-overflow-tooltip prop="routing" align="center" />
      <el-table-column label="组件路径" min-width="200" show-overflow-tooltip prop="component" align="center" />
      <el-table-column label="菜单类型" width="100" prop="type" align="center">
        <template slot-scope="{row}">
          <template v-if="row.type === '1'">目录</template>
          <template v-if="row.type === '2'">菜单</template>
          <template v-if="row.type === '3'">按钮</template>
        </template>
      </el-table-column>
      <el-table-column label="菜单ICON" width="80" prop="icon" align="center">
        <template slot-scope="{row}">
          <svg-icon :icon-class="row.icon" />
        </template>
      </el-table-column>
      <el-table-column label="是否启用" width="80" prop="status" align="center">
        <template slot-scope="{row}">
          <el-tag
            :type="row.status === 0 ? 'danger' : 'success'"
          >{{ row.status === 0 ? '禁用' : '启用' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="排序" width="100" prop="sort" align="center" />
      <!--      <el-table-column label="接口权限" prop="permissions" align="center">-->
      <!--        <template slot-scope="{row}">-->
      <!--          <el-tag v-for="item in row.permissions" :key="item.bId" type="info"> {{ item.name }}-->
      <!--          </el-tag>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <el-table-column fixed="right" label="操作" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <el-button type="primary" size="mini" @click="openCreate(row.type, row.bId)">创建</el-button>
          <el-button type="primary" size="mini" @click="openUpdate(row)">修改</el-button>
          <el-popconfirm @onConfirm="handleDel(new Array(row.bId))" title="确定删除吗？">
            <el-button slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
      v-el-drag-dialog
      :close-on-click-modal="false"
      :title="textMap[dialogStatus]"
      :visible.sync="dialogFormVisible"
      width="500px"
    >
      <el-form
        ref="dataForm"
        :rules="rules"
        :model="temp"
        label-position="left"
        label-width="80px"
        style="width: 400px; margin-left:30px;"
      >
        <el-input v-model="temp.pId" style="display:none;" />
        <el-form-item label="菜单名称" prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>
        <el-form-item label="菜单地址" prop="routing">
          <el-input v-model="temp.routing" />
        </el-form-item>
        <el-form-item label="组件路径" prop="component">
          <el-input v-model="temp.component" />
        </el-form-item>
        <el-form-item label="菜单图标" prop="icon">
          <select-icons v-model="temp.icon" height="300px" />
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model="temp.sort" />
        </el-form-item>
        <el-form-item label="菜单类型" prop="type">
          <el-radio-group v-model="temp.type">
            <!-- <el-radio-button label="0" :disabled="['1','2','3'].includes(menuType)">系统</el-radio-button> -->
            <el-radio-button label="1" :disabled="['0','3'].includes(menuType)">目录</el-radio-button>
            <el-radio-button label="2" :disabled="['0','3'].includes(menuType)">菜单</el-radio-button>
            <el-radio-button label="3" :disabled="['0','1','2'].includes(menuType)">按钮</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否启用" prop="status">
          <el-radio-group v-model="temp.status">
            <el-radio-button label="1">启用</el-radio-button>
            <el-radio-button label="0">禁用</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="新开窗口" prop="status">
          <el-radio-group v-model="temp.extend.blank">
            <el-radio-button :label="1">是</el-radio-button>
            <el-radio-button :label="0">否</el-radio-button>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { menuAdd, menuDel, menuEdit, menuList } from '@/api/system/system-menu'
import waves from '@/directive/waves' // waves directive
import SelectIcons from '@/components/SelectIcon/index.vue'
export default {
  name: 'SystemMenu',
  directives: { waves },
  components: { SelectIcons },
  data() {
    return {
      list: [],
      listLoading: false,
      dialogFormVisible: false,
      // 选中的行id
      selectBId: [],
      // 菜单类型判断
      menuType: '0',
      // 模态框类型
      dialogStatus: '',
      textMap: {
        update: '修改',
        create: '创建'
      },
      temp: {
        bId: '',
        pId: '',
        icon: '',
        name: '',
        permissionIds: [],
        sort: '',
        status: '1',
        type: '0',
        routing: '',
        component: '',
        extend: {
          blank: 0
        }
      },
      permissionNames: new Set(),
      // 表单校验规则
      rules: {
        name: [{ required: true, message: '请输入菜单名称！', trigger: 'change' }],
        routing: [{ required: true, message: '请输入地址！', trigger: 'change' }],
        component: [{ required: true, message: '请输入组件路径！', trigger: 'change' }]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    initExtendDeep(data) {
      data.forEach(item => {
        if (!item.extend) {
          item.extend = { blank: 0 }
        }
        if (item.children && item.children.length > 0) {
          this.initExtendDeep(item.children)
        }
      })
      return data
    },
    getList() {
      this.listLoading = true
      menuList().then(response => {
        this.list = this.initExtendDeep(response.data.menu)
        this.list = response.data.menu
        this.listLoading = false
      }).catch(error => {
        // this.listLoading = false
        console.log(error)
      })
    },
    // 打开创建用户模态框
    openCreate(type, bId) {
      this.resetForm()
      delete this.temp.bId
      // type:  1目录，2菜单和按钮
      this.menuType = (Number(type) + 1).toString()
      this.temp.type = this.menuType
      if (type === '0') {
        this.temp.pId = '0'
      } else {
        this.temp.pId = bId
      }
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 创建用户
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          let component = this.temp.component
          if (component.charAt(0) === '/') {
            component = component.substr(1)
          }
          menuAdd(Object.assign({}, this.temp, { component })).then(res => {
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
    // 打开修改用户模态框
    openUpdate(row) {
      this.resetForm()
      if (row.permissions) {
        row.permissionIds = []
        row.permissions.forEach(e => {
          row.permissionIds.push(e.bId)
        })
      }
      this.temp = Object.assign({}, row) // copy obj
      delete this.temp.children
      delete this.temp.gmtCreate
      delete this.temp.gmtModified
      this.menuType = (Number(row.type)).toString()
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 更新菜单
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          let component = this.temp.component
          if (component.charAt(0) === '/') {
            component = component.substr(1)
          }
          menuEdit(Object.assign({}, this.temp, { component })).then(res => {
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
      menuDel(ids).then(res => {
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
    // 多选变化时
    handleChangeBId(select) {
      this.selectBId = []
      if (select.length > 0) {
        select.map(item => {
          this.selectBId.push(item.bId)
        })
      }
    },
    // 清空表单
    resetForm() {
      this.temp = {
        bId: '',
        pId: '',
        icon: '',
        name: '',
        permissionIds: [],
        sort: '',
        status: '1',
        type: '0',
        routing: '',
        component: '',
        extend: {
          blank: 0
        }
      }
    }
  }
}
</script>
