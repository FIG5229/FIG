<template>
  <div class="app-container">
    <el-row :gutter="20" style="height: 100%;">
      <el-col :span="4" style="height: 100%;">
        <el-input
          v-model="filterText"
          placeholder="过滤组织机构"
        />
        <el-tree
          ref="treeMixin"
          style="width: 100%;height:calc(100% - 100px);overflow-y: auto;"
          :highlight-current="true"
          class="filter-tree"
          :expand-on-click-node="false"
          :data="orgList"
          :props="{
            children: 'children',
            label: 'name'
          }"
          node-key="bId"
          default-expand-all
          :filter-node-method="filterNode"
          @node-click="clickNode"
        />
      </el-col>
      <el-col :span="20" style="height: 100%;">
        <div class="filter-container">
          <el-input v-model="listQuery.account" placeholder="账号" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
          <el-input v-model="listQuery.username" placeholder="用户名" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
          <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="getList">
            搜索
          </el-button>
          <el-button v-waves :disabled="listQuery.account === '' && listQuery.username === ''" class="filter-item" type="primary" icon="el-icon-search" @click="resetSearch">
            重置
          </el-button>
          <el-button class="filter-item" style="margin-right: 10px;" type="primary" icon="el-icon-plus" @click="openCreate">
            创建
          </el-button>
        </div>

        <el-table
          ref="tableMixin"
          v-loading="listLoading"
          :data="list"
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
          <el-table-column label="所属机构" prop="org.name" align="center" />
          <el-table-column label="账号" prop="account" align="center" />
          <el-table-column label="用户名" prop="username" align="center" />
          <el-table-column label="状态" prop="status" align="center">
            <template slot-scope="{row}">
              <el-tag :type="row.status === 0 ? 'danger' : 'success'">
                {{ row.status === 0 ? '禁用' : '启用' }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="性别" width="100" prop="extend.gender" align="center" />
          <el-table-column label="描述" width="100" prop="extend.description" align="center" />
          <el-table-column label="操作" align="center" width="270" class-name="small-padding fixed-width">
            <template slot-scope="{row}">
              <el-button type="primary" size="mini" @click="openNewPwd(row)">
                重置密码
              </el-button>
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
      </el-col>
    </el-row>
    <el-dialog v-el-drag-dialog :close-on-click-modal="false" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="500px">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:30px;">
        <el-form-item label="账号" prop="account">
          <el-input v-model="temp.account" />
        </el-form-item>
        <el-form-item v-if="dialogStatus === 'create'" label="密码" prop="password">
          <el-input v-model="temp.password" />
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="temp.username" />
        </el-form-item>
        <el-form-item label="角色" prop="rIds">
          <el-select v-model="temp.rIds" multiple placeholder="请选择角色">
            <el-option
              v-for="item in rolesList"
              :key="item.value"
              :label="item.name"
              :value="item.bId"
            />
          </el-select>
        </el-form-item>
        <el-form-item v-if="dialogStatus === 'update'" label="组织" prop="oId">
          <select-tree
            :data="orgList"
            :default-props="{
              children: 'children',
              label: 'name'
            }"
            node-key="bId"
            :checked-key="temp.oId"
            @popoverHide="popoverHide"
          />
        </el-form-item>
        <el-form-item label="是否启用" prop="status">
          <el-radio-group v-model="temp.status">
            <el-radio-button label="1">启用</el-radio-button>
            <el-radio-button label="0">禁用</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="性别" prop="extend.gender">
          <el-input v-model="temp.extend.gender" />
        </el-form-item>
        <el-form-item label="邮箱" prop="extend.email">
          <el-input v-model="temp.extend.email" />
        </el-form-item>
        <el-form-item label="联系电话" prop="extend.mobile_no">
          <el-input v-model="temp.extend.mobile_no" />
        </el-form-item>
        <el-form-item label="描述" prop="extend.description">
          <el-input v-model="temp.extend.description" />
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

    <el-dialog
      v-el-drag-dialog
      :close-on-click-modal="false"
      :title="textMap['updatepwd']"
      :visible.sync="dialogNewPsdFormVisible"
      width="500px"
    >
      <el-form
        ref="newpsdForm"
        :rules="newpsdRules"
        :model="newpsd"
        label-position="left"
        label-width="90px"
        style="width: 400px; margin-left:30px;"
      >
        <el-form-item label="新密码" prop="newpassword">
          <el-input v-model="newpsd.newpassword" type="password" />
        </el-form-item>
        <el-form-item label="确认密码" prop="repassword">
          <el-input v-model="newpsd.repassword" type="password" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogNewPsdFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="updatePsd()">
          确定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { userList, userAdd, userEdit, userDelete } from '@/api/system/system-user'
import { roleList, userRoleList } from '@/api/system/system-role'
import { orgTree } from '@/api/system/system-org'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import SelectTree from '@/components/SelectTree'
import waves from '@/directive/waves' // waves directive
// 局部混入，动态控制表格和树的高度

export default {
  name: 'SystemUser',
  components: { Pagination, SelectTree },
  directives: { waves },
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码!'))
      } else if (value !== this.newpsd.newpassword) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      list: [],
      rolesList: [],
      selectRolesList: [],
      orgList: [
        {
          bId: '0',
          name: '全部',
          children: []
        }
      ],
      filterText: '',
      // 选中的组织
      selectOrgList: '0',
      total: 0,
      listLoading: false,
      listQuery: {
        page: 1,
        limit: 10,
        account: '',
        username: ''
      },
      dialogFormVisible: false,
      dialogNewPsdFormVisible: false,
      // 模态框类型
      dialogStatus: '',
      textMap: {
        update: '修改',
        create: '创建',
        updatepwd: '重置密码'
      },
      temp: {
        bId: '',
        account: '',
        oId: '',
        password: '',
        rIds: [],
        status: '1',
        username: '',
        extend: {
          gender: '',
          email: '',
          mobile_no: '',
          description: ''
        }
      },
      // 表单校验规则
      rules: {
        account: [{ required: true, message: '请输入账号！', trigger: 'change' }],
        password: [{ required: true, message: '请输入密码！', trigger: 'change' }],
        rIds: [{ required: true, message: '请选择角色！', trigger: 'change' }],
        username: [{ required: true, message: '请输入用户名！', trigger: 'change' }]
      },
      newpsd: {
        bId: '',
        newpassword: '',
        repassword: ''
      },
      newpsdRules: {
        newpassword: [{ required: true, message: '请输入密码！', trigger: 'change' }],
        repassword: [{ required: true, message: '请输入密码！', trigger: 'change' }, { validator: validatePass, trigger: 'blur' }]
      }
    }
  },
  watch: {
    filterText(val) {
      this.$refs.treeMixin.filter(val)
    }
  },
  created() {
    this.getOrgList()
    this.getList()
    this.getRolesList()
  },
  methods: {
    initExtend(data) {
      data.forEach(item => {
        if (!item.extend) {
          item.extend = {}
        }
      })
      return data
    },
    getList() {
      this.listLoading = true
      userList(this.selectOrgList, this.listQuery).then(response => {
        this.listLoading = false
        this.list = this.initExtend(response.data.users)
        this.total = response.data.total
      }).catch(error => {
        // this.listLoading = false
        console.log(error)
      })
    },
    getRolesList() {
      roleList().then(response => {
        this.rolesList = response.data.roles
      }).catch(error => {
        // this.listLoading = false
        console.log(error)
      })
    },
    getSelectRolesList(uId) {
      userRoleList(uId).then(response => {
        this.temp.rIds = response.data.roles
      }).catch(error => {
        // this.listLoading = false
        console.log(error)
      })
    },
    getOrgList() {
      orgTree().then(response => {
        this.orgList[0].children = response.data.orgTree
        this.$nextTick(() => {
          this.$refs.treeMixin.setCurrentKey(0)
        })
      }).catch(error => {
        // this.listLoading = false
        console.log(error)
      })
    },
    // 重置搜索
    resetSearch() {
      this.listQuery.account = ''
      this.listQuery.username = ''
      this.getList()
    },
    // 打开创建用户模态框
    openCreate() {
      if (this.selectOrgList === '' || this.selectOrgList === undefined || this.selectOrgList === null) {
        this.$message({
          message: '请先选择组织机构！',
          type: 'warning'
        })
        return false
      }
      this.resetForm()
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
          userAdd(this.temp).then(res => {
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
    // 打开重置密码窗口
    openNewPwd(row) {
      this.dialogNewPsdFormVisible = true
      this.newpsd = {
        bId: row.bId,
        newpassword: '',
        repassword: ''
      }
      this.$nextTick(() => {
        this.$refs['newpsdForm'].clearValidate()
      })
    },
    // 重置密码
    updatePsd() {
      this.$refs['newpsdForm'].validate((valid) => {
        if (valid) {
          const params = {
            bId: this.newpsd.bId,
            password: this.newpsd.newpassword
          }
          userEdit(params).then(res => {
            if (res.code === 200) {
              this.$notify({
                message: '重置成功！',
                type: 'success',
                duration: 2000
              })
            }
            this.dialogNewPsdFormVisible = false
            this.getList()
          }).catch(() => {
            this.$notify({
              message: '重置失败！',
              type: 'error',
              duration: 2000
            })
            this.dialogNewPsdFormVisible = false
          })
        }
      })
    },
    // 打开修改用户模态框
    openUpdate(row) {
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.getSelectRolesList(row.bId)
      console.log(row)
      this.temp = Object.assign(this.temp, row) // copy obj
      this.temp.oId = row.org ? row.org.bId : '0'
      console.log(this.temp)
      delete this.temp.password
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 更新用户
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          userEdit(this.temp).then(res => {
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
    handleDel(id) {
      userDelete(new Array(id)).then(res => {
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
    // 事件有两个参数：第一个是所有选中的节点ID，第二个是所有选中的节点数据
    popoverHide(checkedIds, checkedData) {
      this.temp.oId = checkedIds
    },
    // 清空表单
    resetForm() {
      this.temp = {
        bId: '',
        oId: this.selectOrgList,
        appIds: [],
        account: '',
        password: '',
        rIds: [],
        status: '1',
        username: '',
        extend: {
          gender: '',
          email: '',
          mobile_no: '',
          description: ''
        }
      }
    },
    // 过滤方法
    filterNode(value, data) {
      if (!value) return true
      return data.name.indexOf(value) !== -1
    },
    // 点击机构显示用户
    clickNode(obj, node, self) {
      this.selectOrgList = obj.bId
      this.getList()
    }
  }
}
</script>
