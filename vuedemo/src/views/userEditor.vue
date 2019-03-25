<template>
    <div>
        <el-form>
          <el-form-item label="用户名">
            <el-input v-model="form.username" disabled></el-input>
          </el-form-item>

          <el-form-item label="密码">
            <el-input v-model="form.password"></el-input>
          </el-form-item>
        </el-form>
    </div>
</template>
<script>
    export default {
        name: "userEditor",
        data(){
          return {
              form:{
                username:'',
                password:'',
                age:'',
                status:'',
              }
          }
        }
    }
</script>

<style scoped>

</style>
