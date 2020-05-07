<template>
  <el-upload
    class="upload-demo"
    action="/file/file"
    :on-preview="handlePreview"
    :on-remove="handleRemove"
    :before-remove="beforeRemove"
    :before-upload="beforeUpload"
     :http-request="handleFileUpload"
     multiple
    :limit="10"
    :on-exceed="handleExceed"
    ref="newUpload"
    :file-list="fileList">
    <el-button size="small" type="primary">点击上传</el-button>
    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
  </el-upload>

</template>
<script>
import {upload1} from '../../api/global'
  export default {
    data() {
      return {
        fileList: [{name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}, {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}]
       , action:'http://localhost:8078/file/file',
      };
    },

    methods: {
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      },
      handleFileUpload(item){
        let formdata = new FormData();
        formdata.append('file',item.file)
      },
      beforeUpload(file) {
        let fd = new FormData();//通过form数据格式来传
        fd.append("file", file); //传文件
        let url = 'http://localhost:8078/apis/shop/file/uploadFile';
        this.$http
          .post(url, fd)
          .then(res => {
            console.log(res)
            console.log(res.data);
            let data = res.data;
            if (data.code == 200) {
              this.$message({
                message: "上传成功",
                type: "success"
              });
              //再次请求数据，实现本地与服务器内容一致，解决图片删除失败的bug
            } else {
              this.$message.error(data.msg);
            }
          })
          .catch(error => {
            // console.log(error);
          });
      }
    }
  }
</script>

<style scoped>

</style>
