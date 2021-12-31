import axios from 'axios'
import qs from 'qs'

export const login = params =>{
  return axios.post('/api/shop/user/login',qs.stringify(params));
}
export const getCode = () =>{
  return axios.get('api/auth/captcha/getNumberCaptcha',{responseType:'blob'});
}


