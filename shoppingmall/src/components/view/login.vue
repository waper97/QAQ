<template>
    <div class="login">
      <el-form :model="loginForm" ref="loginForm" label-width="80px" :rules="rules" class="lf" >
        <el-form-item label="用户名" prop="username" class="input">
          <el-input v-model="loginForm.username"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password" >
          <el-input v-model="loginForm.password"  type="password"></el-input>
        </el-form-item>
        <span  style="cursor: pointer;color: #f19149;font-size: 0.75rem;margin-left: 5px;">忘记密码？</span>

      </el-form>
      <el-button type="primary" @click="submit('loginForm')">登陆</el-button>
    </div>
</template>

<script>
  import {login} from "../../api/global"
  export default {
        name: "login",
        data(){
          return {
            loginForm:{
              username:'',
              password:''
            },
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
              this.$refs[loginForm].validate((valid) =>{
                if(valid){
                  let params = {...this.loginForm}
                  login(params).then(res =>{
                    this.$refs[loginForm].resetFields()
                    console.log(res)
                  }).catch(error =>{
                    console.log(error)
                  })
                }
              })
          }
        }
    }
</script>

<style scoped>
  .login{
    min-width: 350px;
    width: 25%;
    margin: auto
  }
  .lf{
    margin-top: 60%;
  }
</style>
