import axios from 'axios'
axios.defaults.baseURL = 'http://localhost:8081'
export const queyUserList = params=>{
  return axios.post("http://localhost:8080/getDemoList","",params);
}
