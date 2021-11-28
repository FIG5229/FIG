<template>
  <div id="pathMap" style="width: 100%; height: 100%">
    <iframe ref="mapIframe" :src="url" class="iframe-style" height="100%" width="100%" frameborder="0" />
  </div>
</template>

<script>
import pathMathEvnet from './PathMathEvent'
export default {
  name: 'PathMap',
  data() {
    return {
      url: './forJavaScript/examples/classic/map.html'
    }
  },
  mounted() {
    const self = this
    const iframeWin = this.$refs.mapIframe.contentWindow
    // 判断iframe是否加载完成并做兼容性处理
    if (iframeWin.attachEvent) {
      iframeWin.attachEvent('onload', function() {
        iframeWin.postMessage({
          cmd: 'getConfig',
          params: {
            mapUrl: vueGlobal.MAP_URL,
            api: process.env.VUE_APP_BASE_API
          }
        }, '*')
      })
    } else {
      iframeWin.onload = function() {
        iframeWin.postMessage({
          cmd: 'getConfig',
          params: {
            mapUrl: vueGlobal.MAP_URL,
            api: process.env.VUE_APP_BASE_API
          }
        }, '*')
      }
    }
    pathMathEvnet.$on('getMapQuery', query => {
      iframeWin.postMessage({
        cmd: 'getMapData',
        params: query
      }, '*')
    })
    window.addEventListener('message', event => {
      var data = event.data
      switch (data.cmd) {
        case 'tbData':
          // 处理业务逻辑
          self.loadTb(data.params)
      }
    })
  },
  methods: {
    loadTb(res) {
      pathMathEvnet.$emit('getTbData', res)
    },
    receiveTbData(res) {
      this.loadTb(res)
    }
  }
}
</script>

<style lang="scss" scoped>
#pathMap{
  //background-image: url('../../../../../assets/testmap_bg.png');
  //background-repeat: no-repeat;
  //text-align: center;
  //background-size: 100% 100%;
  .iframe-style{
    width: 100%;
    height: 100%;
  }
}
</style>
