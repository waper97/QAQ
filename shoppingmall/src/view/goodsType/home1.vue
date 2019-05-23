<template>
  <div id="app">
    <el-container class="con_section">
      <el-header class="blueheader">
        <h2 class="headlogo">我的公司logo</h2>
        <el-col :span="3" class="userinfo">
          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>查看</el-dropdown-item>
              <el-dropdown-item>新增</el-dropdown-item>
              <el-dropdown-item>删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span>王小虎</span>
        </el-col>
      </el-header>
      <el-container>
        <el-aside style="flex: 0 0 230px;width: 230px;background:#eef1f6">
          <el-menu default-active="1" class="el-menu-vertical-demo" @select="handleSelect">
            <el-submenu index="1">
              <template slot="title"><i class="el-icon-message"></i>管理系统</template>
              <el-menu-item index="1">商品管理</el-menu-item>
              <el-menu-item index="2">商品分类管理</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main class="home_main">
          <el-col :span="24" class="breadcrumb-container">
            <strong class="title">{{$route.name}}</strong>
            <el-breadcrumb separator="/" class="breadcrumb-inner">
              <el-breadcrumb-item v-for="item in $route.matched" :key="item.path">
                {{ item.name }}
              </el-breadcrumb-item>
            </el-breadcrumb>
          </el-col>
          <el-col :span="24">
            <div style="margin-top:10px">
              <router-view></router-view>
            </div>
          </el-col>
        </el-main>
      </el-container>
    </el-container>

  </div>

</template>
<script>
  export default {
    data(){
      return {
        searchCriteria: '',
      }
    },
    methods:{
      handleIconClick(ev) {
        console.log(ev);
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
      },
    },
  }
</script>
