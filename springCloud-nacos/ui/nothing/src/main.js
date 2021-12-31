import Vue from 'vue'
import App from './App.vue'
import Element from 'element-ui'
import Axios from 'axios'
import Qs from 'qs'
import 'element-ui/lib/theme-chalk/index.css'

import router from './router'


Vue.config.productionTip = false
Vue.use(Element,Axios,Qs,router)


new Vue({
  router,
  render: h => h(App),

}).$mount('#app')
