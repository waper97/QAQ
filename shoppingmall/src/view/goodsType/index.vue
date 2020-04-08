<template>
    <div>
      <el-form ref="form" :model="form" label-width="200px" inline>
        <el-form-item label="活动名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
      </el-form>
      <el-button @click="handleAdd">新增</el-button>
      <el-table :data="tableData"  style="width: 100%">
        <el-table-column prop="name" label="名称" ></el-table-column>
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

      <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%"
        center>
        <editor  @handleCancle="dialogClose" :data="data" :isAdd="isAdd" v-if="reset"></editor>
      </el-dialog>
    </div>
</template>

<script>
  import {getGoodsTypeList,deleteGoodType} from "@/api/global";
  import GoodsTypeEditor from '../goodsType/editor'
  import Editor from "./editor";
  export default {
        name: "index",
        components:{Editor, GoodsTypeEditor},
        data() {
            return {
                form: {

                },
              dialogVisible:false,
              tableData:[],
              data:'',
              isAdd:false,
              reset:false
            }
        },
        methods: {
            getGoodsTypeList(){
              let res = null
              getGoodsTypeList(res).then(res =>{
               this.tableData =  res.data.data
              })
            },
          handleEdit(index,row){
              this.reset = false
              this.$nextTick( () =>{
                this.data = row
                this.dialogVisible = true
                this.isAdd = false
                this.reset = true
              })
          },
          dialogClose(isFlash){
              if(isFlash) {
                this.dialogVisible = true
                this.getGoodsTypeList()
              }else {
                this.dialogVisible = false
                this.getGoodsTypeList()
              }
          },
          handleAdd(){
              this.reset = false
              this.$nextTick(() =>{
                this.reset = true
                this.dialogVisible = true
                this.data = null
              })
          },
          handleDelete(index,row){
            deleteGoodType({goodsTypeId:row.id}).then( res =>{
               if (res.data.success){
                  this.$message(res.data.msg)
                  this.tableData.splice(index, 1)
               }
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
