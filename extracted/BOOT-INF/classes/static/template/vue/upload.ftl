<template>
  <el-dialog
    title="文件上传"
    :close-on-click-modal="false"
    :visible.sync="visible">

    <el-upload
      ref="upload"
      class="upload-demo"
      action="xxxx"
      :http-request="uploadSectionFile"
      :auto-upload="false"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :before-remove="beforeRemove"
      :on-success="handleUploadSuccess"
      :limit="limit"
      :file-list="fileList">
      <el-button size="small" type="primary">选择文件</el-button>
      <div ref="uploadTip" slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
    </el-upload>

    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataUploadSubmit()">上传</el-button>
    </span>

  </el-dialog>
</template>
<script>
  import Vue from 'vue';
  import API from "@/api";
  

  export default {
    data() {
      return {
        fileList: [],
        limit:1,
        visible:false
      };
    },

    methods: {

        dataUploadSubmit: function(){
          this.$refs.upload.submit();
          this.visible = false
        },

        uploadSectionFile: function (param) { //自定义文件上传
            var fileObj = param.file;
            // 接收上传文件的后台地址
            var FileController = "//biz/financialBalanceTemp/upload"; //---并没有使用该路径，而是通过API.
            // FormData 对象
            var form = new FormData();
            // 文件对象
            form.append("file", fileObj);
            // 其他参数
            form.append("token", Vue.cookie.get('token'));

            API.${function}.upload(form).then(({data}) => {
              if (data && data.code === 0) {
                //上传成功后，清空上传文件
                this.$refs.upload.clearFiles();

                //发出提示上传成功
                this.$message({
                  message: '上传成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    //刷新列表页的数据
                    this.$emit('refreshDataList')
                  }
                })
              }else{
                this.$message({
                  message: "上传失败," + data.msg,
                  type: 'error',
                  duration: 0,
                  showClose: true
                })
              }
            })
        },

        //初始化上传模板
        init(limit, tip){
            this.visible = true;
            this.limit = limit;
            //因为在dom加载时是异步的，如果直接打印 this.$refs 是会包含 uploadTip 的对象，但是 this.$refs.uploadTip 却是 undefined 的
            //因此使用nextTick这种回调函数方式，保证可以获取uploadTip对象
            this.$nextTick(function(){
              this.$refs.uploadTip.innerText = tip;
            });
        },

        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },
        handleExceed(files, fileList) {
            this.$message.warning(`当前限制选择<#noparse>${</#noparse>this.limit<#noparse>}</#noparse>个文件，本次选择了 <#noparse>${</#noparse>files.length<#noparse>}</#noparse> 个文件，共选择了 <#noparse>${</#noparse>files.length + fileList.length<#noparse>}</#noparse> 个文件`);
        },
        beforeRemove(file, fileList) {
            return this.$confirm(`确定移除<#noparse> ${</#noparse> file.name <#noparse>}</#noparse>？`);
        },
        handleUploadSuccess(){
          this.$refs.upload.clearFiles();
        }
    }
  }
</script>