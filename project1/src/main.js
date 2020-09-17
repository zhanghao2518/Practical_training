import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import axios from 'axios'
import qs from 'qs'

import "element-ui/lib/theme-chalk/index.css"

import "./axios"

Vue.use(Element)

Vue.config.productionTip = false
Vue.prototype.$axios = axios
Vue.prototype.$qs = qs

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
