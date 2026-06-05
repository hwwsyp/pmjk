<!-- 请将该文件拷贝到vue前端项目的views下，新建的${function}文件夹中 -->
<template>
  <el-row :gutter="20">
    <div class="mod-user">
      <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList(1)">
    <#list list as entity>
      <#if entity.columnExtra != "PRI">
        <el-form-item>
          <el-input v-model="dataForm.${entity.entityColumnName}" placeholder="${entity.columnComment}" clearable></el-input>
        </el-form-item>
      </#if>
    </#list>

        <el-form-item>
          <el-button @click="getDataList(1)">查询</el-button>
          <el-button v-if="isAuth('${prefix}:${function}:save')" type="primary" @click="addOrUpdateHandle()" >新增</el-button>
          <el-button v-if="isAuth('${prefix}:${function}:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
          <el-button type="success" @click="uploadHandle()">上传</el-button>
        </el-form-item>
      </el-form>

      <el-table :data="dataList" border v-loading="dataListLoading" ref="table" :height="tableHeight"
      	@selection-change="selectionChangeHandle" style="width: 100%">
        <el-table-column type="selection" header-align="center" align="center"  min-width="5%"></el-table-column>
    <#list list as entity>
      <#if entity.columnExtra != "PRI">
        <el-table-column prop="${entity.entityColumnName}" header-align="center" align="center" label="${entity.columnComment}" min-width="15%"></el-table-column>
      </#if>
    </#list>

        <el-table-column fixed="right" header-align="center" align="center" min-width="10%" label="操作">
          <template slot-scope="scope">
            <el-button v-if="isAuth('${prefix}:${function}:update')" type="text" size="small" @click="addOrUpdateHandle(scope.row.id)" >修改</el-button>
            <el-button v-if="isAuth('${prefix}:${function}:delete')" type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
        :page-sizes="[500, 1000, 1500, 2000]" :page-size="pageSize" :total="totalPage" layout="total, sizes, prev, pager, next, jumper">
      </el-pagination>
      
      <!-- 弹窗, 新增 / 修改 -->
      <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList(1)"></add-or-update>
      <upload ref="uploadTemplate" @refreshDataList="getDataList(arguments)"></upload>
      
    </div>
    <!--  </el-col>-->
  </el-row>
</template>

<script>
import API from "@/api";
import AddOrUpdate from "./add-or-update";
import Upload from "./upload";

export default {
  data() {
    return {
      dataForm: {
  <#list list as entity>
      <#if entity.columnExtra != "PRI">
          ${entity.entityColumnName}:''<#sep>,
      </#if>
  </#list>
      },
      
      tableHeight:400,
      
      dataList: [],
      pageIndex: 1,
      pageSize: 500,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      innerVisible: false,
    };
  },

  components: {
    AddOrUpdate,Upload
  },
  
  activated() {
    this.$nextTick(() => {
        this.countHeight();
        this.$refs["table"].doLayout();//解决tab切换回来时，表格的高度自动缩小的bug
    });
    
    this.getDataList();    
  },

  methods: {
      //千分位设置
    thousandFormatterForRow(row, column, cellValue, index){
      if(cellValue != null){
        cellValue += '';
        if (!cellValue.includes('.')) cellValue += '.00';

        //判断是否小数点后只有1位，如果是，追加1个0，保持2位小数
        var decimalStr = cellValue.substr(cellValue.indexOf("."));
        if(decimalStr.length==2){
          cellValue += '0';
        }

        return cellValue.replace(/(\d)(?=(\d{3})+\.)/g, function ($0, $1) {
          return $1 + ',';
        }).replace(/\.$/, '');
      }
    },

    //加载上传
    uploadHandle(){
      this.dataListLoading = true;
      this.$nextTick(() => {
        this.$refs.uploadTemplate.init(1,"仅支持单个xls、xlsx的excel文件上传");
      });
    },
  
  	//计算高度
    countHeight(){
      const offsetTop = window.innerHeight - this.$refs.table.$el.offsetTop - 200  
      this.tableHeight = offsetTop
    },
 
    // 获取数据列表
    getDataList(type) {
      this.dataListLoading = true;
      var params = {
        page: this.pageIndex,
        limit: this.pageSize,
    <#list list as entity>
      <#if entity.columnExtra != "PRI">
        ${entity.entityColumnName}: this.dataForm.${entity.entityColumnName}<#sep>,
      </#if>
    </#list>
      };

      if(type == 1){
		   	params.page= 1;
		  }

      API.${function}.list(params).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.page.list;
          this.totalPage = data.page.totalCount;
        } else {
          this.dataList = [];
          this.totalPage = 0;
        }
        this.dataListLoading = false;
      }).catch(error =>{
          //发生异常，系统统一拦截并展示错误信息，这里直接返回即可
          this.dataListLoading = false;   
      });
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    // 多选
    selectionChangeHandle(val) {
      this.dataListSelections = val;
    },
    // 新增 / 修改
    addOrUpdateHandle(id) {
      this.addOrUpdateVisible = true;
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id);
      });
    },

    // 删除
    deleteHandle(id) {
      var ids = id
        ? [id]
        : this.dataListSelections.map((item) => {
            return item.id;
          });
      this.$confirm(
        `确定对[id=${r'${'}ids.join(",")${r'}'}]进行[${r'${'}id ? "删除" : "批量删除"${r'}'}]操作?`,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      ).then(() => {
        API.${function}.del(ids).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.getDataList();
              },
            });
          } else {
            this.$message.error(data.msg);
          }
        }).catch(error =>{
          //发生异常，系统统一拦截并展示错误信息，这里直接返回即可
          this.dataListLoading = false;   
      	});
      });
    },
  },
};
</script>
