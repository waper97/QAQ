import Vue from 'vue'
import Router from 'vue-router'
import Login from '../view/login/index'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    // {
    //   path: '/index',
    //   name: 'index',
    //   component: Home,
    //   children:[{
    //     path: '/goods',
    //     name: 'goods',
    //     component: Goods
    //   },
    //     {
    //       path: '/goodsType',
    //       name: 'goodsType',
    //       component: GoodsType
    //     },
    //     {
    //       path: '/user',
    //       name: 'user',
    //       component: User
    //     }
    //   ]
    // },

    {
      path: '/login',
      name: 'Login',
      component: Login
    }

  ]
})
