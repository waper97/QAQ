import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '../components/HelloWorld'
import Login from '../view/login/index'
import Register from '../view/register'
import Home from '../view/home'
import GoodsType from  '../view/goodsType/index'
import Goods from '../view/goods/index'
import User from '../view/user/index'
import Upload from '../view/upload/index'
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
      component: Home,
      children:[{
        path: '/goods',
        name: 'goods',
        component: Goods
      },
        {
          path: '/goodsType',
          name: 'goodsType',
          component: GoodsType
        },
        {
          path: '/user',
          name: 'user',
          component: User
        }
      ]
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
      path: '/goods',
      name: 'goods',
      component: Goods
    } , {
      path: '/upload',
      name: 'upload',
      component: Upload
    }
  ]
})
