<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch">
      <el-form-item label="分类" prop="catalogId">
        <category-cascader v-model="queryParams.catalogId"/>
      </el-form-item>
      <el-form-item label="品牌" prop="brandId">
        <brand-select v-model="queryParams.brandId"></brand-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select style="width:160px" v-model="queryParams.status" clearable>
          <el-option label="新建" :value="0"></el-option>
          <el-option label="上架" :value="1"></el-option>
          <el-option label="下架" :value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    <el-table border v-loading="loading" :data="spuList">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column prop="id" align="center" label="id" />
      <el-table-column prop="spuName" align="center" label="名称" />
      <el-table-column prop="spuDescription" align="center" label="描述" />
      <el-table-column prop="catalogId" align="center" label="分类" />
      <el-table-column prop="brandId" align="center" label="品牌" />
      <el-table-column prop="weight" align="center" label="重量" />
      <el-table-column prop="publishStatus" align="center" label="上架状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.publishStatus === 0">新建</el-tag>
          <el-tag v-if="scope.row.publishStatus === 1">已上架</el-tag>
          <el-tag v-if="scope.row.publishStatus === 2">已下架</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" align="center" label="创建时间" />
      <el-table-column prop="updateTime" align="center" label="修改时间" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="300px">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUp(scope.row)"
              v-hasPermi="['product:attrGroup:edit']"
          >上架</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleBaseAtrr(scope.row)"
              v-hasPermi="['product:attrGroup:remove']"
          >规格</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
  <!--
  <div>
    <el-row>
      <el-col :span="24">
        <el-form :inline="true" :model="dataForm">
          <el-form-item label="分类">

          </el-form-item>
          <el-form-item label="品牌">

          </el-form-item>
          <el-form-item label="状态">
            <el-select style="width:160px" v-model="dataForm.status" clearable>
              <el-option label="新建" :value="0"></el-option>
              <el-option label="上架" :value="1"></el-option>
              <el-option label="下架" :value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="检索">
            <el-input style="width:160px" v-model="dataForm.key" clearable></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="searchSpuInfo">查询</el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="24">

      </el-col>
    </el-row>
  </div>
  -->
</template>

<script>
import CategoryCascader from "@/views/components/common/CategoryCascader";
import BrandSelect from "@/views/components/common/BrandSelect";
import { listSpu } from "@/api/product/spu";

export default {
  //import引入的组件需要注入到对象中才能使用
  components: { CategoryCascader, BrandSelect },
  props: {},
  data() {
    //这里存放数据
    return {
      loading: true,
      categoryList: [],
      brandList: [],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        catalogId: null,
        brandId: null,
        status: null
      },
      showSearch: true,
      spuList: [],
      total: 0
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      listSpu(this.queryParams).then(response => {
        this.spuList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    handleUp() {

    },
    handleBaseAtrr() {

    }
  }
};
</script>
<style scoped>
</style>
