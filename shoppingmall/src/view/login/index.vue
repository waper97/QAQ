<template>
    <div class="login">

      <el-form :model="loginForm" ref="loginForm" label-width="80px" :rules="rules" class="lf" >
        <el-form-item >
          <span>用户登录</span>
        </el-form-item>

        <el-form-item label="用户名" prop="username" class="input">
          <el-input v-model="loginForm.username"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password" >
          <el-input v-model="loginForm.password"  type="password"></el-input>
        </el-form-item>
        <span  style="cursor: pointer;color: #f19149;font-size: 0.75rem;margin-left: 5px;">忘记密码？</span>

      </el-form>
      <el-button type="primary" @click="submit('loginForm')" :loading="isLoading">登陆</el-button>
    </div>
</template>

<script>
  import {login,getSession} from "../../api/global"
  export default {
        name: "login",
        data(){
          return {
            loginForm:{
              username:'',
              password:'',
            },
            isLoading:false,
            rules:{
              username: [
                { required: true,message:'账号不能为空', trigger: 'blur' }
              ],
              password: [
                { required: true,message:'密码不能为空', trigger: 'blur' }
              ],
            }
          }
        },
        methods:{
          submit(loginForm){
            console.log(loginForm)
            this.isLoading = true
              this.$refs[loginForm].validate((valid) =>{
                if(valid){
                  let params = {...this.loginForm}
                  this.isLoading = false
                  login(params).then(res =>{
                    this.$refs[loginForm].resetFields()
                    if(res.data.success){
                      this.$router.replace('/home');
                      this.$router.replace('/home');
                      this.$store.commit('SET_TOKEN',res.data.data.token)
                      this.$store.commit('GET_USER',JSON.stringify(res.data.data))
//                       var username = this.loginForm.username;
//                       var password = this.loginForm.password
//                       this.setCookies(this.loginForm.username,this.loginForm.password,7)
//                         getSession(params).then(res =>{
// console.log(res)
//                         })


                    }
                  }).catch(error =>{
                    console.log(error)
                    this.isLoading = false
                  })
                }
              })
          },
          setCookies(username,password,exdays){
            var exdate = new Date(); //获取时间
            exdate.setTime(exdate.getDate()+24 * 60 * 60 * 1000 * exdays)
            window.document.cookie = username;
            window.document.cookie =  password;
            console.log(document.cookie)

          },
          getCookie(){
            if(document.cookie.length > 0){
                console.log(document.cookie)
            }
          }
        },
        computed:{
            btnText(){
                if(this.isLoading){return '登录中...'}
            }
        }
    }
</script>

<style scoped>

  .login{
    min-width: 350px;
    width: 25%;
    margin: auto;
  }
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;
  }
  .lf{
    margin-top: 60%;
  }



</style>

<style></style>
