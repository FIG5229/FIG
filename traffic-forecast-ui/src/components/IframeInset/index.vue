<template>
    <iframe :src="src" height="100%" width="100%" frameborder="0" />
</template>

<script>
export default {
  name: 'IframeInset',
  props: {
    src: {
      required: true,
      type: String
    }
  },
  data() {
    return {}
  }
}
</script>
