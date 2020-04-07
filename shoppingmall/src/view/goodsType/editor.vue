<template>
    <div>
      <el-form ref="form" :model="form" inline :rules="rules" @submit.native.prevent >
        <el-form-item label="分类编号" prop="typeId" >
          <el-input v-model.number="form.typeId" auto-complete="off" style="width: 400px;"></el-input>
        </el-form-item>

        <el-form-item label="上级分类" prop="parentId" >
          <el-input v-model.number="form.parentId" auto-complete="off" style="width: 400px;" ></el-input>
        </el-form-item>

        <el-form-item label="名称" prop="name" >
          <el-input v-model="form.name" auto-complete="off" style="width: 400px;"></el-input>
        </el-form-item>
      </el-form>
      <div  class="dialog-footer" style="margin-left: 148px">
        <el-button @click="dialogFormCancel('form')">取 消</el-button>
        <el-button type="primary" @click="dialogFormSure('form')">确 定</el-button>
      </div>
    </div>
</template>

<script>
  import {insertGoodsType,updateGoodsType} from "@/api/global";
  export default {
        name: "editor",
        props:{
          data:Object,
          isAdd:Boolean
        },
        data() {
            return {
                form: {
                  id:null,
                  typeId:null,
                  name:null,
                  parentId:null,
                },
              rules:{
                name: [
                  { required: true, message: '不能为空', trigger: 'blur' },
                ], typeId: [
                  { required: true, message: '不能为空', trigger: 'blur' },
                ],
              }
            }
        },
        methods: {
          dialogFormCancel(form){

              this.$emit('handleCancle',false)
              this.$refs[form].resetFields();
          },
          dialogFormSure(form){
            this.$refs[form].validate((valide) =>{
              if(valide){
                let params = {...this.form}
                if (params.id == null) {

                  insertGoodsType(params).then(res =>{
                    if(res.data.success){
                      this.$message(res.data.msg)
                      // 传值给父组件的@handleCancle方法
                      this.$emit('handleCancle',false)
                    }
                  })
                }else{
                  updateGoodsType(params).then(res =>{
                    if(res.data.success){
                      this.$message(res.data.msg)
                      // 传值给父组件的@handleCancle方法
                      this.$emit('handleCancle',false)
                    }
                  })
                }
              }
            })
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
