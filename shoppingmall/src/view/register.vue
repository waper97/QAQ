<template>
    <div class="register">
      <el-form ref="form" :model="form" label-width="100px" class="form" :rules="rules" v-loading="loading">
        <el-form-item >
          <span>用户注册</span>
        </el-form-item>

        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" type="password"></el-input>
        </el-form-item>

        <el-form-item label="再次输入密码" prop="rePassword">
          <el-input v-model="form.rePassword" type="password"></el-input>
        </el-form-item>
          <el-button type="primary" @click="handleRegister('form')">注册</el-button>
          <el-button>取消</el-button>
      </el-form>
    </div>
</template>

<script>
  import {register} from "@/api/global";

  export default {
        name: "register",
        data(){
          var  validatePass2 = (rules,value ,callback) =>{
              if(value == ''){
                callback(new Error("请再次输入密码"));
              }else if(value != this.form.password) {
                console.log(value)
                callback(new Error("两次输入的密码不一致！"));
              }else{
                callback();
              }
          }
          return {
            form :{
              username:'',
              password:'',
              rePassword:''
            },
            rules:{
              username: [
                {  required: true ,message:'用户名不能为空',trigger: 'blur' },
                // { min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur' }
              ],
              password: [
                {  required: true , message:'密码不能为空',trigger: 'blur' },
              ],
              rePassword: [
                { validator:validatePass2, trigger: 'blur' },
              ],
            },
            loading:false
          }
        },
        methods:{
          handleRegister(form){
              this.loading = true;
              this.$refs[form].validate((valide) =>{
                if(valide){
                  let params = {
                      username : this.form.username,
                      password : this.form.password
                  }
                  register(params).then(res =>{
                    if (res.data.success) {
                      this.$message({message:"注册成功", type:"success"})
                      this.loading = false
                      this.$router.push({path:'/login'})
                    }
                      console.log(res)
                  }).catch(res =>{
                    this.loading = false
                  })
                }
              })
          }
        }
    }
</script>

<style scoped>
 .register{
   margin: auto;
   width: 25%;
 }
  .form{
    margin-top: 50%;
  }
</style>
