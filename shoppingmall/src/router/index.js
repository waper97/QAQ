import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '../components/HelloWorld'
import Login from '../view/login'
import Register from '../view/register'
import Home from '../view/home'
import GoodsType from  '../view/goodsType/index'
import Home1 from  '../view/goodsType/home1'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/index',
      name: 'index',
      component: Home
    },

    {
      path: '/Login',
      name: 'Login',
      component: Login
    } ,
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/goodsType',
      name: 'goodsType',
      component: GoodsType
    },
    {
      path: '/home1',
      name: 'home1',
      component: Home1
    }
  ]
})
