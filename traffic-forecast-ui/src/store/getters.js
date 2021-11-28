const getters = {
  sidebar: state => state.app.sidebar,
  size: state => state.app.size,
  device: state => state.app.device,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  getRouterStatus: state => state.user.status,
  routerList: state => state.user.RouterList,
  roles: state => state.user.roles,
  org: state => state.user.org
}
export default getters
