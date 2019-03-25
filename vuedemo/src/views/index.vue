<template>
      <div>
          <el-form>
            <el-table
              :data="tableData"
              style="width: 100%"
              max-height="250"
            >
              <el-table-column
                prop="id"
                label="ID"
                width="180">
              </el-table-column>

              <el-table-column
                prop="username"
                label="用户名"
                width="180">
              </el-table-column>


              <el-table-column
                prop="password"
                label="密码"
                width="180">
              </el-table-column>

              <el-table-column
                prop="age"
                label="年龄"
                width="180">
              </el-table-column>

              <el-table-column
                prop="status"
                label="状态"
                width="180">
                <templete slot-scope="scope">
                    {{scope.row.status | statusFilter}}
                </templete>
              </el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                  <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)" v-if="scope.row.status !== 0">删除</el-button>
                </template>
              </el-table-column>

            </el-table>

          </el-form>
          <el-dialog
            title="编辑"
            :visible.sync="dialogVisible"
            width="30%"
          >
            <Editor></Editor>
            <!--<span>这是一段信息</span>-->
            <span slot="footer" class="dialog-footer">
            <div class="btn-group">
               <el-button @click="handleCancle(false)">取 消</el-button>
               <el-button type="primary" @click="handleSure = false">确 定</el-button>
            </div>
        </span>
          </el-dialog>
      </div>

</template>

<script>
  name:"test"
  import {queyUserList,deleteByPrimaryKey} from "@/request/index";
  import Editor from '../views/userEditor'

  export default {
      name: "index",
     components:{Editor},
      data(){
        return{
          tableData:[],
          dialogVisible:false
        }
      },
      methods:{
          _getUserList(){
              queyUserList().then(data =>{
                  this.tableData = data.data.list
              })
          },
          //编辑
          handleEdit(index,row){
              this.dialogVisible = true
          },
          //取消编辑
          handleCancle(close){
            alert(close)
              this.dialogVisible = close
          },
          //确认编辑
          handleSure(){

          },
          //删除
          handleDelete(index,row){
             let param = {id:row.id}
             deleteByPrimaryKey(param).then(res =>{
                 if(res.data.code == 200){
                   this.$message({message:'删除成功',type: 'success'})
                 }else{
                   this.$message({message:'删除失败',type: 'warning'})
                 }
             })
          }
       },
        filters:{
          statusFilter(status){
              if(status == 1)return "正常"
              if(status == 0)return "已失效"
          }
        },
        mounted() {
          this._getUserList()
       }
  }
</script>

<style scoped>

</style>
