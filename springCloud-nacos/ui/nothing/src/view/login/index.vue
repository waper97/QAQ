<template>
    <div class="login">

      <el-form :model="loginForm" ref="loginForm" label-width="80px" :rules="rules"  class="login-form">
        <el-form-item >
          <span>用户登录</span>
        </el-form-item>

        <el-form-item label="用户名" prop="username" class="el-input">
          <el-input v-model="loginForm.username"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password" class="el-input" >
          <el-input v-model="loginForm.password"  type="password"></el-input>
        </el-form-item>

        <el-form-item label="验证码" prop="captch"  class="el-input" >
          <el-input style="width: 100px;" v-model="loginForm.captch" placeholder="请填写验证码"></el-input>
          <img :src="imgCodeUrl" style="width: 120px;height: 100%;cursor: pointer;" @click="changeImg()"/>
        </el-form-item>

        <el-form-item>
            <a href="javascript:void(0);" @click="changeImg()">看不清,换一张？</a>
          <span  style="cursor: pointer;color: #f19149;font-size: 0.75rem;margin-left: 5px;">忘记密码？</span>
        </el-form-item>

          <el-button
            type="primary"
            @click.native.prevent="submit('loginForm')"
            :loading="isLoading">
            <span >登 录</span>
          </el-button>


      </el-form>

    </div>
</template>

<script>
  import {login,getCode} from "../../api/global"

  export default {
        name: "login",
        data(){
          return {
            loginForm:{
              username:'',
              password:'',
              captch:'',
            },
            imgCodeUrl:'',
            isLoading:false,
            rules:{
              username: [
                { required: true,message:'账号不能为空', trigger: 'blur' }
              ],
              password: [
                { required: true,message:'密码不能为空', trigger: 'blur' }
              ],
              captch: [
                { required: true,message:'验证码不能为空', trigger: 'blur' }
              ],
            }
          }
        },
        methods:{
          changeImg() {
            getCode().then(res => {
              this.imgCodeUrl = window.URL.createObjectURL(res.data)
            })
          },
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
                  this.isLoading = false
                }
              })
            this.isLoading = false
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
    mounted() {
      this.changeImg()
    },
    computed:{

            // btnText(){
            //     if(this.isLoading){return '登录中...'}
            // }
        },
    }
</script>

<style scoped>

.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  /*background-image: url("../assets/images/login-background.jpg");*/
  background-size: cover;
}


.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;
  .el-input {
    height: 38px;
    input {
      height: 38px;
    }
  }
  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 2px;
  }
}





  /*.login{*/
  /*  min-width: 350px;*/
  /*  width: 25%;*/
  /*  margin: auto;*/
  /*}*/
  /*.el-input {*/
  /*  display: inline-block;*/
  /*  height: 47px;*/
  /*  width: 85%;*/
  /*}*/
  /*.lf{*/
  /*  margin-top: 60%;*/
  /*}*/



</style>

<style></style>
