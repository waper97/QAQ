// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import  Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueAxios  from 'vue-axios'
import Axios from 'axios'
import store from './store'

Axios.interceptors.request.use(
  config =>{
    if (localStorage.getItem('SET_TOKEN')) {
        config.headers.Authorization = localStorage.getItem('token');
    }
    return config;

  } ,
error =>{
            return Promise.reject(error)
    }
)
Axios.interceptors.response.use(

  response =>{
    return response;
  },
  error => {
    if (error.response) {
        console.log(this)
        this.$store.commit("logout")
        Vue.$route.push({path:"/login"})
    }
    return Promise.reject(error.response.data)
  })


Vue.config.productionTip = false
Vue.use(VueAxios,Axios)
Vue.use(Element)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
