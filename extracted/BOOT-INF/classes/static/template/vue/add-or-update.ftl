<!-- 请将该文件拷贝到vue前端项目的views下，新建的${function}文件夹中 -->
<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">

    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="150px" v-loading="loading">
    <#list list as entity>
      <#if entity.columnExtra != "PRI">
      <el-form-item label="${entity.columnComment}："  prop="${entity.entityColumnName}">
        <el-input v-model="dataForm.${entity.entityColumnName}" clearable></el-input>
      </el-form-item>
      </#if>
    </#list>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>

  </el-dialog>
</template>

<script>
  import API from '@/api'
  export default {
    data () {
      return {
        visible: false,
        innerVisible: false,
        loading:false,
        
        dataForm: {
          id: 0,
<#list list as entity>
    <#if entity.columnExtra != "PRI">
        ${entity.entityColumnName}: ''<#sep>,
    </#if>
</#list>
        },

        dataRule: {
    <#list list as entity>
        <#if entity.columnExtra != "PRI">
            ${entity.entityColumnName}: [
	            { required: true, message: '不能为空', trigger: 'blur' }
	        ]<#sep>,
        </#if>
    </#list>
        }
      }
    },
    methods: {
      init (id) {
        this.visible = true
      	this.loading=true
        this.dataForm.id = id || 0

        if (this.dataForm.id) {
            API.${function}.info(this.dataForm.id).then(({data}) => {
              if (data && data.code === 0) {
        <#list list as entity>
	        <#if entity.columnExtra != "PRI">
	            this.dataForm.${entity.entityColumnName} = data.${function}Info.${entity.entityColumnName}<#sep>,
	        </#if>
    	</#list>
              }
              this.loading=false
            })
        }else{
            this.loading=false
            
            //新增，清空所有定义的变量，避免发生缓存问题。
            this.$nextTick(() => {
            	this.$refs['dataForm'].resetFields()
            })
		    //除了form表单，自定义的参数同样需要清空，自行补充
        }
        
      },

      changeType (obj){
      	this.$nextTick(() => {
	        this.$refs['dataForm'].resetFields()
	        this.dataForm.type=obj
	      })
      },
      
      // 表单提交
      dataFormSubmit () {
        this.$refs.dataForm.validate((valid) => {
          if (valid) {
            var params = {
              'id': this.dataForm.id || undefined,
        <#list list as entity>
	        <#if entity.columnExtra != "PRI">
	            '${entity.entityColumnName}': this.dataForm.${entity.entityColumnName} <#sep>,
	        </#if>
	    </#list>
            }

            this.loading=true;
            var tick = !this.dataForm.id ? API.${function}.save(params) : API.${function}.update(params)
            tick.then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
              this.loading=false;
            })
          }
        })
      }
    }
  }
</script>
<style scoped>
	.el-checkbox{
		margin-right: 30px;
	}
</style>
