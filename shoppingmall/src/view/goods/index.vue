<template>
    <div>
      <el-button @click="handleAdd">新增</el-button>
        <el-table :data="tableData"  style="width: 100%">
          <el-table-column prop="name" label="名称" width="120"></el-table-column>
<!--          <el-table-column prop="pircture" label="照片"></el-table-column>-->
          <el-table-column prop="type" label="分类"></el-table-column>
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
            background
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
  import {goodList,deleteGoodsById} from "@/api/global";
  import GoodsEdit from './goodsEdit';  // 引入组件(注意命名规范，大小写)
    export default {
      components:{GoodsEdit},
        name: "index",
        data() {
            return {
                form: {},
              tableData:[],
              tableDataGoodsType:[],
              dynamicTitle:'',
              dialogVisible:false,
              data:'',
              isAdd:false,
              hackReset:false,
              total:0,
              currentPage:1, //初始页
              pagesize:10,
            }
        },
        methods: {
          getGoodList(){
            let params = {};
            goodList(params).then(res =>{
              // console.log(res)
              // console.log(res.data.data.list)
              this.tableData = res.data.data.list
              // 总条数
              this.total = res.data.data.total
            })
          }
        ,
          handleEdit(index,row){
            this.hackReset = false,
              this.data = null;
            this.$nextTick(() =>{
              this.hackReset = true
              this.dynamicTitle = '修改'
              this.dialogVisible = true
              this.data = row
              this.isAdd = false
            })

          },
          handleAdd(){
            this.hackReset = false
            this.$nextTick( () =>{
              this.data = null
              this.dynamicTitle = '新增'
              this.hackReset = true
              this.dialogVisible = true
              this.isAdd = true
            })

          },
          handleDelete(index,row){
            deleteGoodsById({id:row.id}).then(res =>{
              if(res.data.code == 200){
                this.$message(res.data.msg);
                this.getGoodList()
              }else{
                this.$message('删除失败');
              }
            })
          },
          handleClose(){
            this.dialogVisible = false
          },
          dialogFormClose(isClose){
            if(isClose){
              this.dialogVisible = false
              this.getGoodList()
            }else{
              this.dialogVisible = false
              this.getGoodList()
            }
          },
          handleSizeChange(val) {
            this.pagesize = val
            let params = {};
            params.pageSize = val
            goodList(params).then(res =>{
              // console.log(res)
              // console.log(res.data.data.list)
              this.tableData = res.data.data.list
              // 总条数
              this.total = res.data.data.total
            })
            console.log(`每页 ${val} 条`);
          },
          handleCurrentChange(val) {
            this.currentPage = val
            let params = {};
            params.page = val
            goodList(params).then(res =>{
              // console.log(res)
              // console.log(res.data.data.list)
              this.tableData = res.data.data.list
              // 总条数
              this.total = res.data.data.total
            })
            console.log(`当前 ${val} 条`);
          },
        },
        mounted() {
            this.getGoodList()
        }
    }
</script>

<style scoped>

</style>
