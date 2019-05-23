<template>
    <div class="home">
          <el-container>





          <el-container>
            <el-aside width="200px" >
              <el-row >
                <el-col :span="24">
                  <el-menu
                    default-active="/goods"
                    class="el-menu-vertical-demo"
                    @open="handleOpen"
                    @close="handleClose"
                    unique-opened
                    background-color="#545c64"
                    text-color="#fff"
                    active-text-color="#ffd04b"
                    @select="handleSelect"
                  >
                    <el-submenu index="1">
                      <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>系统管理</span>
                      </template>
                      <el-menu-item-group>
                        <el-menu-item index="1" >商品</el-menu-item>
                        <el-menu-item index="2" >商品分类</el-menu-item>
                      </el-menu-item-group>
                    </el-submenu>
                    <el-menu-item index="2">
                      <i class="el-icon-menu"></i>
                      <span slot="title">导航二</span>
                    </el-menu-item>
                    <el-menu-item index="3">
                      <i class="el-icon-setting"></i>
                      <span slot="title">导航三</span>
                    </el-menu-item>
                  </el-menu>
                </el-col>
              </el-row>

            </el-aside>
              <el-main>
                    <div>
                      <router-view></router-view>
                    </div>


              </el-main>
          </el-container>

      </el-container>

    </div>
</template>

<script>

  import {goodList,deleteGoodsById} from "@/api/global";
  import GoodsType from '../view/goodsType/index'
  import GoodsEdit from "./goodsEdit";  // 引入组件(注意命名规范，大小写)
  import Goods from './goods/index'
  export default {
        name: "home",
        components:{GoodsEdit, GoodsType,Goods}, // 引入了还要注册，否则不生效
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
          },
          getGoodsTypeList(){

          },
          handleOpen(){
          },
          handleSelect(key, keyPath){
            switch(key){
              case '1':
                this.$router.push('/goods');
                break;
              case '2':
                this.$router.push('/goodsType')
                break;
            }
            this.$router.push(keyPath)
          },
          goto(path){
              this.$router.replace(path)
          },

        },
        mounted() {
        }
    }
</script>

<style scoped>





</style>
