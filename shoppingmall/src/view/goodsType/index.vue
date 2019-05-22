<template>
    <div>
      <el-table :data="tableData"  style="width: 100%">
        <el-table-column prop="name" label="名称" width="120"></el-table-column>
        <el-table-column prop="addTime" label="添加時間"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
</template>

<script>
  import {getGoodsTypeList} from "@/api/global";

  export default {
        name: "index",
        data() {
            return {
                form: {

                },
              tableData:[]
            }
        },
        methods: {
            getGoodsTypeList(){
              let res = null
              getGoodsTypeList(res).then(res =>{
               this.tableData =  res.data.data.content
              })
            }
        },
        mounted() {
            this.getGoodsTypeList()
        }
    }
</script>

<style scoped>

</style>
