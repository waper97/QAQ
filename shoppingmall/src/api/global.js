import axios from 'axios'
import qs from 'qs'
// axios.defaults.baseURL= 'http://localhost:8080'
export const login = params =>{
  return axios.post('apis/shop/user/login',qs.stringify(params));
}
export const register = params =>{
  return axios.post('apis/shop/user/userRegister',qs.stringify(params));
}
