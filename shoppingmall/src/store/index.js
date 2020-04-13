
const user = {
  state:{
    user:window.sessionStorage.getItem('user'),
    token:window.sessionStorage.getItem('token'),

  },
  mutations:{
    // changeLogin(state,user){
    //   state.Authorization = user.Authorization
    //   localStorage.setItem("Authorization",user.Authorization)
    // },
    // token保存包sessionStorage
    SET_TOKEN:(state, data)=>{
      state.token = data
      window.sessionStorage.setItem('token',data)

    },
    // 获取用户
    GET_USER : (state, data)=>{
      state.user = data
      window.sessionStorage.setItem('user',data)
    },
    // 登出
    LOGOUT :(state, data)=>{
      // 登出清空token
      state.user = null
      state.token = null
      window.sessionStorage.removeItem('user')
      window.sessionStorage.removeItem('token')
    }
  }
}
export default user;
