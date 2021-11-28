import ModelTabs from './ModelTabs'
import PageCard from './PageCard'
import FormatDataPicker from './FormatDataPicker'
import SearchInput from './SearchInput'
const components = {
  ...ModelTabs,
  PageCard,
  FormatDataPicker,
  SearchInput
}
const install = (Vue) => {
  const bus = new Vue({
    methods: {
      emit(event, ...args) {
        this.$emit(event, ...args)
      },
      on(event, ...args) {
        this.$on(event, ...args)
      },
      off(event, cb) {
        this.$off(event, cb)
      }
    }
  })
  Object.keys(components).forEach(cpt => {
    Vue.component(components[cpt].name, components[cpt])
  })
  Vue.prototype.$bus = bus
}
export default {
  install
}
