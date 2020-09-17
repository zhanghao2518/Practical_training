import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Logon from '../views/Logon.vue'
import Users from "../views/Users";
import Code from "../views/Code";
import Delete from "../views/Delete";
import Put from "../views/Put";
import Attend from "../views/Attend";

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
    },
    {
      path: '/users',
      name: 'Users',
      component: Users
    },
    {
      path: '/code',
      name: 'Code',
      component: Code
    },
    {
      path: '/delete',
      name: 'Delete',
      component: Delete
    },
    {
      path: '/put',
      name: 'Put',
      component: Put
    },
    {
      path: '/attend',
      name: 'Attend',
      component: Attend
    }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BABEL_ENV,
  routes
})

export default router
