<template>
    <div class="home">

          <el-container>
            <el-aside width="200px" >
              <el-menu>
                <el-submenu index="1">
                  <template slot="title"><i class="el-icon-message"></i>系统管理</template>
                  <el-menu-item-group>
                    <el-menu-item index="1-1" @click="getGoodList">商品基础信息</el-menu-item>
                    <el-menu-item index="1-2">商品分类管理</el-menu-item>
                  </el-menu-item-group>
                </el-submenu>
                <el-submenu index="2">
                  <template slot="title"><i class="el-icon-message"></i>基础信息管理</template>
                </el-submenu>
                <el-submenu index="3">
                  <template slot="title"><i class="el-icon-message"></i>商品分类管理</template>
                </el-submenu>
                <el-submenu index="4">
                  <template slot="title" @click="getGoodList"><i class="el-icon-message"></i>商品管理</template>
                </el-submenu>
              </el-menu>
            </el-aside>
            <el-container>
              <el-header>Header</el-header>
              <el-main>
                <el-button style="margin-left: 80%" @click="handleAdd">新增</el-button>
                <el-table :data="tableData"  style="width: 100%">
<!--                    <el-table-column prop="id" label="ID" width="140"></el-table-column>-->
                    <el-table-column prop="name" label="名称" width="120"></el-table-column>
                    <el-table-column prop="pircture" label="照片"></el-table-column>
                    <el-table-column prop="status" label="状态"></el-table-column>
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
              </el-main>
              <el-footer>Footer</el-footer>
            </el-container>
          </el-container>

      <el-dialog
        :title="dynamicTitle"
        :visible.sync="dialogVisible"
         width="30%"
        :before-close="handleClose"
        center
      >
        <goods-edit :data="data" :isAdd="isAdd" @dialogFormClose="dialogFormClose" v-if="hackReset"></goods-edit>
        <span slot="footer" class="dialog-footer">
<!--          <el-button @click="dialogVisible = false">取 消</el-button>-->
<!--          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>-->
      </span>
      </el-dialog>
      <div class="block">
<!--        currentPage默认显示多少页

-->
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"> // 总条数
        </el-pagination>
      </div>
    </div>
</template>

<script>
  import {goodList,deleteGoodsById} from "@/api/global";
  import GoodsEdit from "./goodsEdit";  // 引入组件(注意命名规范，大小写)
  export default {
        name: "home",
        components:{GoodsEdit, GoodsEdit}, // 引入了还要注册，否则不生效
        data() {
            return {
                form: {},
                tableData:[],
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
            goodList().then(res =>{
              console.log(res)
              console.log(res.data.data.content)
               this.tableData = res.data.data.content
              // 总条数
               this.total = res.data.data.totalElements
            })
          },
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
            this.data = null
            this.hackReset = false
            this.$nextTick( () =>{
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
              }else{
                this.dialogVisible = false
              }
          },
          handleSizeChange(val) {
            this.pagesize = val
            console.log(`每页 ${val} 条`);
          },
          handleCurrentChange(val) {
            this.currentPage = val
            console.log(`当前 ${val} 条`);
          }
        },
        mounted() {
        }
    }
</script>

<style scoped>





</style>
