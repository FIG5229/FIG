<template>
  <div>
    <el-button type="text" style="font-size: 18px;margin: 20px" @click="dialogVisible = true">点击打开可拖拽模态框</el-button>

    <el-dialog
      v-el-drag-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
      @dragDialog="handleDrag"
    >
      <span>正在拖拽{{ num }}</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible: false,
      num: 0
    }
  },
  methods: {
    // 监听拖拽事件
    handleDrag() {
      this.num++
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    }
  }
}
</script>
