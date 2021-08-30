<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true">
      <el-form-item label="分类" prop="catalogId">
        <category-cascader v-model="queryParams.catalogId" />
      </el-form-item>
      <el-form-item label="品牌" prop="brandId">
        <brand-select v-model="queryParams.brandId" />
      </el-form-item>
      <el-form-item label="价格" prop="price">
        <el-form-item label="价格">
          <el-input-number style="width:160px" v-model="queryParams.priceMin" :min="0"></el-input-number> -
          <el-input-number style="width:160px" v-model="queryParams.priceMax" :min="0"></el-input-number>
        </el-form-item>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="skuInfoList" @selection-change="handleSelectionChange" border>
      <el-table-column type="expand">
        <template slot-scope="scope">
          商品标题：{{scope.row.skuTitle}}
          <br />
          商品副标题：{{scope.row.skuSubtitle}}
          <br />
          商品描述：{{scope.row.skuDesc}}
          <br />
          分类ID：{{scope.row.catalogId}}
          <br />
          SpuID：{{scope.row.spuId}}
          <br />
          品牌ID：{{scope.row.brandId}}
          <br />
        </template>
      </el-table-column>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="skuId" align="center" prop="skuId" />
      <el-table-column label="名称" align="center" prop="skuName" />
      <el-table-column label="所属分类id" align="center" prop="catalogId" />
      <el-table-column label="品牌id" align="center" prop="brandId" />
      <el-table-column label="默认图片" align="center" prop="skuDefaultImg">
        <template v-slot="scope">
          <img :src="scope.row.skuDefaultImg" />
        </template>
      </el-table-column>
      <el-table-column label="价格" align="center" prop="price" />
      <el-table-column label="销量" align="center" prop="saleCount" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="small"
            type="text"
            v-hasPermi="['product:skuInfo:edit']"
          >预览</el-button>
          <el-button
            size="small"
            type="text"
            v-hasPermi="['product:skuInfo:remove']"
          >评论</el-button>
          <el-dropdown
              @command="handleCommand(scope.row,$event)"
              size="small"
              split-button
              type="text"
          >
            更多
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="uploadImages">上传图片</el-dropdown-item>
              <el-dropdown-item command="seckillSettings">参与秒杀</el-dropdown-item>
              <el-dropdown-item command="reductionSettings">满减设置</el-dropdown-item>
              <el-dropdown-item command="discountSettings">折扣设置</el-dropdown-item>
              <el-dropdown-item command="memberPriceSettings">会员价格</el-dropdown-item>
              <el-dropdown-item command="stockSettings">库存管理</el-dropdown-item>
              <el-dropdown-item command="couponSettings">优惠劵</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import BrandSelect from "@/views/components/common/BrandSelect";
import CategoryCascader from "@/views/components/common/CategoryCascader";
import { listSkuInfo } from "@/api/product/skuInfo";

export default {
  name: "SkuInfo",
  components: { BrandSelect, CategoryCascader },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 总条数
      total: 0,
      // sku信息表格数据
      skuInfoList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        spuId: null,
        skuName: null,
        skuDesc: null,
        catalogId: null,
        brandId: null,
        skuDefaultImg: null,
        skuTitle: null,
        skuSubtitle: null,
        price: null,
        priceMin: 0,
        priceMax: 0,
        saleCount: null
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询sku信息列表 */
    getList() {
      this.loading = true;
      listSkuInfo(this.queryParams).then(response => {
        this.skuInfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.skuId)
    },
    handleCommand(row, command) {
      if (command === 'stockSettings') {
        this.$router.push({
          path: '/ware/wareSku',
          query: {
            skuId: row.skuId
          }
        });
      }
    }
  }
};
</script>
