
const autoHeight = {
  data() {
    return {
      mixinsTbHeight: 500,
      mixinsToBottom: 0,
      mixinsTreeStyle: { 'height': '500px', 'overflow-y': 'auto' }
    }
  },
  mounted() {
    if (this.$refs.tableMixin || this.$refs.tree) {
      const self = this
      self.$nextTick(() => {
        if (self.$refs.tableMixin) {
          self.mixinsTbHeight = window.innerHeight - self.getOffsetTopByBody(self.$refs.tableMixin.$el) - self.mixinsToBottom
        }
        if (self.$refs.treeMixin) {
          self.mixinsTreeStyle.height = window.innerHeight - self.getOffsetTopByBody(self.$refs.treeMixin.$el) + 'px'
        }
      })
      window.onresize = function() {
        if (self.$refs.tableMixin) {
          self.mixinsTbHeight = window.innerHeight - self.getOffsetTopByBody(self.$refs.tableMixin.$el) - self.mixinsToBottom
        }
        if (self.$refs.treeMixin) {
          self.mixinsTreeStyle.height = window.innerHeight - self.getOffsetTopByBody(self.$refs.treeMixin.$el) + 'px'
        }
      }
    }
  },
  methods: {
    // 元素相对于body的offsetTop
    getOffsetTopByBody(el) {
      let offsetTop = 0
      while (el && el.tagName !== 'BODY') {
        offsetTop += el.offsetTop
        el = el.offsetParent
      }
      return offsetTop
    }
  }
}
export default autoHeight
