<template>
    <div>
      <el-form ref="form" :model="form" inline :rules="rules" @submit.native.prevent>
        <el-form-item label="名称" prop="name" >
          <el-input v-model="form.name" auto-complete="off"></el-input>
        </el-form-item>


        <el-form-item label="类型" prop="type" >
          <el-input v-model="form.type" auto-complete="off"></el-input>
        </el-form-item>

        <el-form-item prop="pircture">
            <el-upload
              class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              list-type="picture-card">
              <el-button size="small" type="primary">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>
        </el-form-item>



        <el-form-item  prop="status">
          <el-switch
            v-model="form.status"
            active-value="1"
            inactive-value="0"
            inactive-color="#ff4949"
          ></el-switch>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormCancel('form')">取 消</el-button>
        <el-button type="primary" @click="dialogFormSure('form')">确 定</el-button>
      </div>
    </div>
</template>

<script>
  import {insertGoods,updateGoods} from "@/api/global";

  export default {
        name: "goodsEdit",
        props:{
          isAdd:{
            type:Boolean
          },
          data:{
            type:Object
          }
        },
        data() {
            return {
                form: {
                  id:'',
                  name:'',
                  type:''
                },
                rules:{
                  name: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                  ],
                  // status: [
                  //   { required: true, message: '不能为空', trigger: 'blur' },
                  // ],
                  type: [
                    { required: true, message: '不能为空', trigger: 'blur' },
                  ],
                  // pircture: [
                  //   { required: true, message: '不能为空', trigger: 'blur' },
                  // ],
                }
            }
        },
        methods:{
          dialogFormCancel(form){
              this.$emit('dialogFormClose')
              this.$refs[form].resetFields()
          },
          handleRemove(){

          },
          dialogFormSure(form){
              this.$refs[form].validate((valid) =>{
                if(valid){
                  let params = {...this.form}
                  insertGoods(params).then(res =>{
                      if(res.data.success){
                        this.$message(res.data.msg)
                        this.$emit('dialogFormClose')
                        this.$refs[form].resetFields()
                      }
                  })
                }
              })
          },
          handlePreview(){

          },
          _setForm(data){
              for(let i in this.form){
                  this.form[i] = data[i]
              }
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
