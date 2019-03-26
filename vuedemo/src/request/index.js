import axios from 'axios'
import qs from 'qs'
axios.defaults.baseURL = 'http://localhost:8055'
const requst = axios.create({
  timeout: 10000, // 请求超时时间
  headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}
})
export const queyUserList = params=>{
  return axios.post("/getUserList","",params);
}
export const deleteByPrimaryKey = params =>{
   return axios.post('/deleteUserByPrimarykey',params = qs.stringify(params))
}

export const updateUserByPrimaryKey = params =>{
  return axios.post('/updateUserByPrimaryKey',params,)
}
export const addUser = params =>{
  return axios.post('/addUser',params,)
}
