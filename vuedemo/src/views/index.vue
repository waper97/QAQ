<template>
      <div>
        <el-container>
          <el-header>
              <el-button type="warning" @click="handleAdd">新增</el-button>
          </el-header>
          <el-main>
              <el-table
                :data="tableData"
                style="width: 100%"
                max-height="800px"
                align="center"
                border
              >
                <el-table-column
                  prop="id"
                  label="ID"
                  width="180"
                  align="center"
                >
                </el-table-column>

                <el-table-column
                  prop="username"
                  label="用户名"
                  align="center"
                  width="180">
                </el-table-column>


                <el-table-column
                  prop="password"
                  label="密码"
                  align="center"
                  width="180">
                </el-table-column>

                <el-table-column
                  prop="age"
                  label="年龄"
                  align="center"
                  width="180">
                </el-table-column>

                <el-table-column
                  prop="address"
                  label="地址"
                  align="center"
                  width="180">
                </el-table-column>

                <el-table-column
                  prop="status"
                  label="状态"
                  align="center"
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
            <el-dialog
              title="编辑"
              :visible.sync="dialogVisible"
              width="30%"
            >
              <Editor
                @handleClick="closeDialog"
                :data="editorData"
                :isAdd ="isAdd"
                v-if="hackReset"
              ></Editor>
              <!--<span>这是一段信息</span>-->
              <!--<span slot="footer" class="dialog-footer">-->

              <!--</span>-->
            </el-dialog>
          </el-main>
        </el-container>
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
          form:{
              username:'',
              address:'',
          },
          hackReset:false,
          tableData:[],
          dialogVisible:false,
          editorData:null,
          isAdd:false,
        }
      },
      methods:{
          _getUserList(){
              queyUserList().then(data =>{
                  this.tableData = data.data.list
              })
          },
          //关闭编辑窗体
          closeDialog(isClose){
              if(isClose == 1){
                this.dialogVisible = false
                this._getUserList()
              }else{
                this.dialogVisible = false
              }
          },
          //新增
          handleAdd(){
            this.hackReset = false
              this.$nextTick( ()=>{
                this.hackReset = true
                this.isAdd = true
                this.dialogVisible = true
                this.editorData = null
              })

          },
          //编辑
          handleEdit(index,row){
              this.hackReset = false
              this.$nextTick(()=>{
                this.hackReset = true
                this.dialogVisible = true
                this.isAdd = false
                this.editorData = row //将整行数据传入临时变量editorData
              })

          },
          //取消编辑
          handleCancle(close){
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
