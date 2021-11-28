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
          <el-input v-model="listQuery.name" placeholder="机构名" style="width: 200px;" class="filter-item" @keyup.enter.native="getList" />
          <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="getList">
            搜索
          </el-button>
          <el-button v-waves :disabled="listQuery.name === ''" class="filter-item" type="primary" icon="el-icon-search" @click="resetSearch">
            重置
          </el-button>
          <el-button class="filter-item" style="margin-right: 10px;" type="primary" icon="el-icon-plus" @click="openCreate">
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
          <el-table-column label="机构名称" prop="name" align="center" />
          <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
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
      </el-col>
    </el-row>
    <el-dialog v-el-drag-dialog :close-on-click-modal="false" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="500px">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="80px" style="width: 400px; margin-left:30px;">
        <el-form-item label="机构名称" prop="name">
          <el-input v-model="temp.name" />
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
import { orgTree, orgAdd, orgEdit, orgDelete } from '@/api/system/system-org'
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

export default {
  name: 'SystemUser',
  components: { Pagination },
  directives: { waves },
  data() {
    return {
      list: [],
      orgList: [
        {
          bId: '0',
          name: '全部',
          children: []
        }
      ],
      filterText: '',
      // 选中的组织
      selectOrg: '0',
      total: 0,
      listLoading: false,
      listQuery: {
        page: 1,
        limit: 10,
        name: ''
      },
      dialogFormVisible: false,
      // 模态框类型
      dialogStatus: '',
      textMap: {
        update: '修改',
        create: '创建'
      },
      temp: {
        bId: '',
        pId: '',
        name: ''
      },
      // 表单校验规则
      rules: {
        name: [{ required: true, message: '请输入机构名称！', trigger: 'change' }]
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
  },
  methods: {
    getList() {
      this.listLoading = true
      orgTree(this.selectOrg).then(response => {
        this.listLoading = false
        if (this.selectOrg !== '0') {
          this.list = response.data.orgTree[0].children
          this.total = response.data.orgTree[0].children.length
        } else {
          this.list = response.data.orgTree
          this.total = response.data.orgTree.length
        }
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
      this.listQuery.name = ''
      this.getList()
    },
    // 打开创建用户模态框
    openCreate() {
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
          this.temp.pId = this.selectOrg
          delete this.temp.bId
          console.log(this.temp)
          orgAdd(this.temp).then(res => {
            if (res.code === 200) {
              this.$notify({
                message: '创建成功！',
                type: 'success',
                duration: 2000
              })
              this.getOrgList()
              this.getList()
            }
            this.dialogFormVisible = false
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
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.temp = {
        bId: row.bId,
        pId: row.pId,
        name: row.name
      }
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 更新用户
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          orgEdit(this.temp).then(res => {
            if (res.code === 200) {
              this.$notify({
                message: '修改成功！',
                type: 'success',
                duration: 2000
              })
              this.getList()
              this.getOrgList()
            }
            this.dialogFormVisible = false
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
      orgDelete(id).then(res => {
        if (res.code === 200) {
          this.$notify({
            message: '删除成功！',
            type: 'success',
            duration: 2000
          })
          this.getOrgList()
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
        bId: '',
        pId: '',
        name: ''
      }
    },
    // 过滤方法
    filterNode(value, data) {
      if (!value) return true
      return data.name.indexOf(value) !== -1
    },
    // 点击机构显示用户
    clickNode(obj, node, self) {
      this.selectOrg = obj.bId
      this.getList()
    }
  }
}
</script>
