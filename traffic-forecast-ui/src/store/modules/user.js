import { login, logout } from '@/api/user/user'
import staticRouter from '../../router/staticRouter'

const state = {
  status: false, // 是否已经获取了动态路由
  name: '', // 名字
  avatar: '', // 头像
  org: '', // 所属组织机构
  roles: [], // 角色
  RouterList: [] // 动态路由
}

const mutations = {
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_STATUS: (state, status) => {
    state.status = status
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  SET_ORG: (state, org) => {
    state.org = org
  },
  set_router: (state, RouterList) => {
    state.RouterList = RouterList
  }
}

const actions = {
  // 设置用户名
  setName: ({ commit }, name) => {
    commit('SET_NAME', name)
  },
  // 设置角色
  setRoles: ({ commit }, roles) => {
    commit('SET_ROLES', roles)
  },
  // 设置组织机构
  setOrg: ({ commit }, org) => {
    commit('SET_ORG', org)
  },
  // 设置是否已经获取了动态路由
  setStatus: ({ commit }, status) => {
    commit('SET_STATUS', status)
  },
  // 设置路由
  setRouterList: ({ commit }, routerList) => {
    commit('set_router', staticRouter.concat(routerList))
  },
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ account: username.trim(), password: password }).then(response => {
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout().then(() => {
        window.location.reload()
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  }

}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

