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
      <el-table-column label="操作" align="center" width="400" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <el-button type="primary" size="mini" @click="openUpdate(row)">
            修改权限
          </el-button>
          <el-button type="primary" size="mini" @click="openRoleUsers(row)">
            用户关联
          </el-button>
          <el-popconfirm @onConfirm="handleDel(row.bId)" title="确定删除吗？">
            <el-button slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit "
      @pagination="getList"
    />

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
        <el-form-item label="角色名称" prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>
        <el-form-item label="菜单权限" prop="menuIds">
          <!-- <el-input v-model="temp.menuIds" /> -->
          <el-scrollbar>
            <el-tree
              ref="tree"
              v-loading="treeLoading"
              :show-checkbox="true"
              style="height:300px;"
              :highlight-current="true"
              :expand-on-click-node="false"
              :data="menuListData"
              :props="{
                children: 'children',
                label: 'name'
              }"
              node-key="bId"
              default-expand-all
              @check="checkMenu"
            />

          </el-scrollbar>
        </el-form-item>
        <el-form-item label="接口权限" prop="permissionIds">
          <el-select v-model="temp.permissionIds" multiple style="width: 320px;" placeholder="请选择">
            <el-option
              v-for="item in permissionList"
              :key="item.bId"
              :label="item.name"
              :value="item.bId"
            />
          </el-select>
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

    <el-dialog v-el-drag-dialog :close-on-click-modal="false" title="绑定用户角色" :visible.sync="dialogTransferVisible" width="70%">
      <el-form ref="roleUserForm" label-position="left" label-width="80px" style="width: 100%">
        <el-transfer
          v-model="roleUsers"
          class="role-transfer"
          filterable
          :filter-method="userFilterMethod"
          filter-placeholder="请输入用户"
          :data="allUsers"
          :titles="['用户列表','已绑定用户']"
        />
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogTransferVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="updateRoleUsers()">
          确定
        </el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import {
  roleList,
  roleAdd,
  roleEdit,
  roleDelete,
  bindRoleToUser,
  findUsersByRole,
  allUserList
} from '@/api/system/system-role'
import { menuList, menuRoleList } from '@/api/system/system-menu'
import { permissionList, permissionRoleList } from '@/api/system/system-permission'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import waves from '@/directive/waves' // waves directive

export default {
  name: 'SystemRole',
  components: { Pagination },
  directives: { waves },
  data() {
    return {
      list: [],
      permissionList: [],
      userId: '',
      role_id: '',
      menuListData: [],
      treeLoading: false,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10
      },
      dialogFormVisible: false,
      dialogTransferVisible: false,
      // 模态框类型
      dialogStatus: '',
      textMap: {
        update: '修改',
        create: '创建'
      },
      temp: {
        id: undefined,
        name: '',
        menuIds: [],
        permissionIds: []
      },
      roleUsers: [],
      allUsers: [],
      // 表单校验规则
      rules: {
        name: [{ required: true, message: '请输入角色名称！', trigger: 'change' }]
      }
    }
  },
  created() {
    this.getList()
    // 获取所有用户数据，封装成 label key 格式
    this.getAllUsers()
  },
  methods: {
    getList() {
      this.listLoading = true
      roleList().then(response => {
        this.list = response.data.roles
        this.total = response.data.roles.length
        this.listLoading = false
      }).catch(error => {
        // this.listLoading = false
        console.log(error)
      })
      this.getPermissionList()
    },
    getPermissionList() {
      permissionList().then(response => {
        this.permissionList = response.data.permissions
      }).catch(error => {
        console.log(error)
      })
    },
    getAllUsers() {
      allUserList().then(response => {
        const userlist = response.data.users
        userlist.forEach(e => {
          this.allUsers.push({
            label: e.username,
            key: e.bId,
            account: e.account
          })
        })
      }).catch(error => {
        console.log(error)
      })
    },
    // 打开创建角色模态框
    openCreate() {
      this.resetForm()
      this.getMenuList()
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
          roleAdd(this.temp).then(res => {
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
      this.getMenuList()
      this.getRoleMenuList(row.bId)
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
          roleEdit(this.temp).then(res => {
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
    // 打开角色用户模态框
    openRoleUsers(row) {
      this.dialogTransferVisible = true
      this.roleUsers = []
      console.log(row.bId)
      this.role_id = row.bId
      findUsersByRole(row.bId).then(res => {
        const list = res.data.users
        list.forEach(item => {
          this.roleUsers.push(item.bId)
        })
      })
    },
    updateRoleUsers() {
      const data = {}
      data.rId = this.role_id
      const array = []
      this.roleUsers.forEach((item) => {
        array.push(item)
      })
      data.uIds = array
      bindRoleToUser(data).then(res => {
        this.dialogTransferVisible = false
      })
    },
    // 穿梭框搜索
    userFilterMethod(query, item) {
      return item.label.indexOf(query) > -1 || item.account.indexOf(query) > -1
    },
    handleDel(ids) {
      roleDelete(new Array(ids)).then(res => {
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
    // 获取菜单列表
    getMenuList() {
      this.treeLoading = true
      menuList().then(response => {
        this.treeLoading = false
        this.menuListData = response.data.menu
      }).catch(error => {
        this.treeLoading = true
        console.log(error)
      })
    },
    // 角色对应的菜单权限
    getRoleMenuList(rId) {
      menuRoleList(rId).then(res => {
        this.temp.menuIds = res.data.menuIds
        this.$refs.tree.setCheckedKeys(this.temp.menuIds)
      }).catch(error => {
        console.log(error)
      })
      permissionRoleList(rId).then(res => {
        this.temp.permissionIds = res.data.permissionIds
      }).catch(error => {
        console.log(error)
      })
    },
    // 点击菜单复选框
    checkMenu(node, checkNode) {
      console.log(node, checkNode)
      this.temp.menuIds = checkNode.checkedKeys
    },
    // 清空表单
    resetForm() {
      this.temp = {
        id: undefined,
        name: '',
        menuIds: []
      }
    }
  }
}
</script>

<style>
  .role-transfer .el-transfer-panel {
    width: 40%;
  }
</style>
