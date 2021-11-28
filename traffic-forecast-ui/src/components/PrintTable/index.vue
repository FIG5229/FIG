<template>
  <div>
    <div id="printRes" class="print-res" style="position: absolute;top: -9999px;z-index: -999;">
      <img class="real_pic" style="display: block;" :src="dataURL1">
      <img class="real_pic" style="display: block;" :src="dataURL2">
    </div>
    <img id="printTb" class="real_pic page-hidden" :src="dataURL3">
  </div>
</template>
<script>
import html2canvas from 'html2canvas'
import printJS from 'print-js'
export default {
  name: 'PrintTable',
  data() {
    return {
      dataURL1: '',
      dataURL2: '',
      dataURL3: ''
    }
  },
  methods: {
    toImage() {
      const self = this
      html2canvas(document.getElementsByClassName('el-table__header')[0], {
        backgroundColor: null,
        imageTimeout: 0
      }).then((canvas) => {
        const dataURL1 = canvas.toDataURL('image/png')
        self.dataURL1 = dataURL1
        html2canvas(document.getElementsByClassName('el-table__body')[0], {
          backgroundColor: null,
          imageTimeout: 0
        }).then((canvas) => {
          const dataURL2 = canvas.toDataURL('image/png')
          self.dataURL2 = dataURL2
          setTimeout(function() {
            html2canvas(document.getElementById('printRes'), {
              backgroundColor: null,
              imageTimeout: 0
            }).then((canvas) => {
              const dataURL3 = canvas.toDataURL('image/png')
              self.dataURL3 = dataURL3
              setTimeout(function() {
                self.printHtmlCustomStyle()
              }, 100)
            })
          }, 100)
        })
      })
    },
    printHtmlCustomStyle() {
      printJS({
        printable: 'printTb',
        type: 'html',
        imageStyle: 'width:100%;',
        scanStyles: false
      })
    }
  }
}
</script>
<style>
  .page-hidden{
    position: absolute;top:0px;z-index: -999;display: none;
  }
  @media print {#printTb{display: block; }}
</style>
