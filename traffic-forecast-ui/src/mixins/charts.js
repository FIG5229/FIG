const charts = {
  mounted() {
    window.addEventListener('resize', this.chartResize)
  },
  methods: {
    chartResize() {
      this.chart && this.chart.resize()
    }
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.chartResize)
  }
}
export default charts
