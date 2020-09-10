import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Logon from '../views/Logon.vue'

Vue.use(VueRouter)

  const routes = [
    {
      path: '/',
      name: 'Start',
      redirect: {name: "Login"}
    },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
    {
      path: '/logon',
      name: 'Logon',
      component: Logon
    }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BABEL_ENV,
  routes
})

export default router
