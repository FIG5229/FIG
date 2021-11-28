
<template>
  <!-- eslint-disable vue/require-component-is -->
  <component v-bind="linkProps(to,blank)">
    <slot />
  </component>
</template>

<script>
import { isExternal } from '@/utils/validate'

export default {
  props: {
    to: {
      type: String,
      required: true
    },
    blank: {
      type: [String, Number],
      default: 0
    }
  },
  methods: {
    linkProps(url, blank) {
      if (blank || isExternal(url)) {
        return {
          is: 'a',
          href: isExternal(url) ? url : '/#' + url,
          target: '_blank',
          rel: 'noopener'
        }
      }
      return {
        is: 'router-link',
        to: url
      }
    }
  }
}
</script>
