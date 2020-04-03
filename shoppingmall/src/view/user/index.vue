<template>
    <div>
      <el-button @click="handleAdd">新增</el-button>
      <el-table :data="tableData"  style="width: 100%">
        <el-table-column prop="name" label="名称" width="120"></el-table-column>
        <!--          <el-table-column prop="pircture" label="照片"></el-table-column>-->
        <el-table-column prop="username" label="用户名"></el-table-column>
        <el-table-column prop="mobile" label="电话"></el-table-column>
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
      <div class="block">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"> // 总条数
        </el-pagination>
      </div>

<!--      <el-dialog-->
<!--        :title="dynamicTitle"-->
<!--        :visible.sync="dialogVisible"-->
<!--        width="30%"-->
<!--        :before-close="handleClose"-->
<!--        center-->
<!--      >-->
<!--        <goods-edit :data="data" :isAdd="isAdd" @dialogFormClose="dialogFormClose" v-if="hackReset"></goods-edit>-->
<!--        <span slot="footer" class="dialog-footer">-->
<!--            </span>-->
<!--      </el-dialog>-->


    </div>
</template>

<script>
  import {getUserList} from "../../api/global";

  export default {
        name: "index",
        data() {
            return {
                form: {},
                tableData:[],
                currentPage:10,
                total:0,

            }
        },
        methods: {
            _getUserList(){
              getUserList().then(res =>{
                  this.tableData = res.data.data.content
                   console.log(res.data.data)
              })
            },
           handleAdd(){

           },
           handleSizeChange(){

           },
          handleCurrentChange(){

          }
        },
        mounted() {
            this._getUserList();
        }
    }
</script>

<style scoped>

</style>
