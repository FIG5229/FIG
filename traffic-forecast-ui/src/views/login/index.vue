<template>
  <div class="login-container">
    <div class="login-wrap">
      <div class="title-container">
        <img src="../../assets/login_logo.png" class="sidebar-logo">
        <h3 class="title">{{ title }}</h3>
      </div>
      <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on" label-position="left">
        <div class="title-top">用户登录</div>
        <el-form-item prop="username">
          <span class="svg-container">
            <svg-icon icon-class="user" />
          </span>
          <el-input
            ref="username"
            v-model="loginForm.username"
            placeholder="Username"
            name="username"
            type="text"
            tabindex="1"
            auto-complete="on"
          />
        </el-form-item>

        <el-form-item prop="password">
          <span class="svg-container">
            <svg-icon icon-class="password" />
          </span>
          <el-input
            :key="passwordType"
            ref="password"
            v-model="loginForm.password"
            :type="passwordType"
            placeholder="Password"
            name="password"
            tabindex="2"
            auto-complete="on"
            @keyup.enter.native="handleLogin"
          />
          <span class="show-pwd" @click="showPwd">
            <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
          </span>
        </el-form-item>

        <el-button
          :loading="loading"
          type="primary"
          style="width:100%;height: 44px;margin-top:30px;font-size: 18px;"
          @click.native.prevent="handleLogin"
        >登录</el-button>

        <!--        <div class="tips">-->
        <!--          <span style="margin-right:20px;">账号: admin</span>-->
        <!--          <span> 密码: admin</span>-->
        <!--        </div>-->

      </el-form>
    </div>
  </div>
</template>

<script>
import defaultSettings from '@/settings'
export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (value.length < 1) {
        callback(new Error('Please enter the correct user name'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 1) {
        callback(new Error('The password can not be less than 6 digits'))
      } else {
        callback()
      }
    }
    return {
      title: defaultSettings.title || '登录',
      // title: '铁总预测分析展示系统',
      loginForm: {
        username: 'admin',
        password: 'admin'
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$router.push('/dashboard')
      // this.$refs.loginForm.validate(valid => {
      //   if (valid) {
      //     this.loading = true
      //     this.$store.dispatch('user/login', this.loginForm).then(() => {
      //       this.$store.dispatch('user/setStatus', false)
      //       this.$router.push({ path: this.redirect || '/' })
      //       this.loading = false
      //     }).catch(() => {
      //       this.loading = false
      //     })
      //   } else {
      //     console.log('error submit!!')
      //     return false
      //   }
      // })
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg:#283443;
$light_gray:#fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 40px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 5px 5px 5px 15px;
      height: 40px;
      caret-color: $cursor;
      color: #606266;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid #ABABAB;
    border-radius: 5px;
  }
}
</style>

<style lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;
.sidebar-logo {
  width: 45px;
  height: 45px;
  vertical-align: middle;
  margin-right: 12px;
}
.login-wrap{
  overflow: hidden;
  width: 50%;
  min-width: 600px;
}
.login-container {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  min-height: 100%;
  width: 100%;
  background: url(../../assets/login_bg.png) no-repeat center center;
  background-size: cover;
  overflow: hidden;

  .login-form {
    box-sizing: border-box;
    position: relative;
    width: 488px;
    height: 357px;
    padding: 40px 80px;
    margin: 0 auto;
    overflow: hidden;
    background-color: #ffffff;
    border-radius: 10px;
    .title-top{
      margin-bottom: 30px;
      height: 30px;
      font-size: 24px;
      font-weight: bold;
      color: #343434;
      text-align: center;
    }
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;
    text-align: center;
    .title {
      display: inline-block;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
      vertical-align: -12px;
      font-size: 42px;
      letter-spacing: 3px;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
