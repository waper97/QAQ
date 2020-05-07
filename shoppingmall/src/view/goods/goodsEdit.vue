<template>
    <div>
      <el-form ref="form" :model="form" inline :rules="rules" @submit.native.prevent>
        <el-form-item label="名称" prop="name" >
          <el-input v-model="form.name" auto-complete="off"></el-input>
        </el-form-item>


        <el-form-item label="类型" prop="goodsTypId" >
          <el-select v-model="form.goodsTypId" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item prop="pircture">
            <el-upload
              class="upload-demo"
              action="http://localhost:8078/file/file"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              list-type="picture-card">
              <el-button size="small" type="primary">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>
        </el-form-item>

<!--        action="https://jsonplaceholder.typicode.com/posts/"-->

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
  import {insertOrUpdateGoods,getGoodsTypeList} from "@/api/global";

  export default {
        name: "goodsEdit",
        props:{
          isAdd:{
            type:Boolean
          },
          data:{
            type:Array
          }
        },
        data() {
            return {
                form: {
                  id:'',
                  name:'',
                  goodsTypId:''
                },
              options:[],
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
                  console.log(params)
                  insertOrUpdateGoods(params).then(res =>{
                      if(res.data.success){
                        this.$message(res.data.msg)
                        this.$emit('dialogFormClose')
                        this.$refs[form].resetFields()
                        this._goodsTypeList()
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
          },
          _goodsTypeList(){
            let res = null
            getGoodsTypeList(res).then(res =>{
              // console.log(res)
               this.options =  res.data.data
            })
            // console.log(this.options)

            // let res = null
            // getGoodsTypeList(res).then(res =>{
            //   console.log(res.data.data)
            //   this.tableData =  res.data.data
            // })
          }
        },
        mounted() {
          if(!this.isAdd){
            this._setForm(this.data)
          }
          this._goodsTypeList()
        }
    }
</script>

<style scoped>

</style>
