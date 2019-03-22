<template>
      <div>
        123
          <el-form>
            <el-table
              :data="tableData"
              border
              tooltip-effect="light"
              align="center"
              style="width: 100%"
            >
            </el-table>

          </el-form>
      </div>
</template>

<script>
  name:"test"
  import {queyUserList} from "@/request/index";
  export default {
      name: "index",
      data(){
        return{
          tableData:[]
        }
      }
    }
</script>

<style scoped>

</style>
