<template>
    <iframe-inset :src="src"></iframe-inset>
</template>

<script>
import IframeInset from '@/components/IframeInset'
export default {
  name: 'IframeDemo',
  components: { IframeInset },
  data() {
    return {
      src: ''
    }
  },
  mounted() {
    this.src = this.$route.meta.extend.url
  }
}
</script>
