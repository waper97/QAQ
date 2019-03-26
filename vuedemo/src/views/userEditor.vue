<template>
    <div>
        <el-form :rules="rules" ref="ruleForm" :model="form">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="form.username" :disabled="!this.isAdd"></el-input>
          </el-form-item>

          <el-form-item label="密码" prop="password">
            <el-input v-model="form.password"></el-input>
          </el-form-item>

          <el-form-item label="年龄" prop="age">
            <el-input v-model="form.age"></el-input>
          </el-form-item>

          <el-form-item label="地址" prop="address">
            <el-input v-model="form.address"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button @click="handleCancle">取 消</el-button>
            <el-button type="primary" @click="handleSure('ruleForm')">确 定</el-button>
          </el-form-item>
        </el-form>
    </div>
</template>
<script>
  import {updateUserByPrimaryKey,addUser} from "@/request";

  export default {
        name: "userEditor",
        props:{
           data:{        //data 父组件的  ":data"传过来的值
             type:Object //指定类型
           },
           isAdd:{
             type:Boolean//isAdd 父组件的  ":isAdd"传过来的值,“是否新增的标识”
           }
        },
        data(){
          return {
              form:{
                id:'',
                username:'',
                password:'',
                age:'',
                status:'',
                address:''
              },
              rules:{
                password: [
                  { required: true, message: '必填项', trigger: 'blur' }
                ],
                age: [
                  { required: true, message: '必填项', trigger: 'blur' }
                ],
                address: [
                  { required: true, message: '必填项', trigger: 'blur' }
                ],
                username: [
                  { required: true, message: '必填项', trigger: 'blur' }
                ],
              }
          }
        },
        methods:{
          //赋值
          _setForm(data){
              for(let i in this.form){
                  this.form[i] = data[i];
              }
          },
          //取消
          handleCancle(){
            this.$emit('handleClick',1)
          },
          //确认
          handleSure(ruleForm){
            this.$refs[ruleForm].validate((valid)=>{
               if(valid){
                 if(this.isAdd == true){
                   let Users = {
                     age:this.form.age,
                     username:this.form.username,
                     address:this.form.address,
                     password:this.form.password
                   }
                   addUser(Users).then(res =>{
                     if(res.data.code == 200) {
                       this.$message({'message':"新增成功"})
                       this.$emit('handleClick',1)
                     }
                   }).catch(error =>{
                     console.log(error)
                   })
                 }else{
                   let Users = {
                     age : this.form.age,
                     id :this.form.id
                   }
                   updateUserByPrimaryKey(Users).then(res =>{
                     console.log(res)
                   })
                 }
               }
            })
            // this.$emit(event,arg) :父子组件通讯，
            // event:方法名，arg:参数
            //注意的是 这里的“event”是值的父组件上自定义的"@+方法名"即：
            //“index.vue”里的 <Editor @handleClick="closeDialog"></Editor>中“@handleClick”，然后父组件去寻找"closeDialog方法"

          }
        },
        mounted() {
           if(!this.isAdd){
             this._setForm(this.data)
           }
        }
    }
</script>

<style scoped>

</style>
