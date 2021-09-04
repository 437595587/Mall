<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="活动id" prop="promotionId">
        <el-input
          v-model="queryParams.promotionId"
          placeholder="请输入活动id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动场次id" prop="promotionSessionId">
        <el-input
          v-model="queryParams.promotionSessionId"
          placeholder="请输入活动场次id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品id" prop="skuId">
        <el-input
          v-model="queryParams.skuId"
          placeholder="请输入商品id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="秒杀价格" prop="seckillPrice">
        <el-input
          v-model="queryParams.seckillPrice"
          placeholder="请输入秒杀价格"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="秒杀总量" prop="seckillCount">
        <el-input
          v-model="queryParams.seckillCount"
          placeholder="请输入秒杀总量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="每人限购数量" prop="seckillLimit">
        <el-input
          v-model="queryParams.seckillLimit"
          placeholder="请输入每人限购数量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排序" prop="seckillSort">
        <el-input
          v-model="queryParams.seckillSort"
          placeholder="请输入排序"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['coupon:seckillSkuRelation:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['coupon:seckillSkuRelation:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['coupon:seckillSkuRelation:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['coupon:seckillSkuRelation:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="seckillSkuRelationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="活动id" align="center" prop="promotionId" />
      <el-table-column label="活动场次id" align="center" prop="promotionSessionId" />
      <el-table-column label="商品id" align="center" prop="skuId" />
      <el-table-column label="秒杀价格" align="center" prop="seckillPrice" />
      <el-table-column label="秒杀总量" align="center" prop="seckillCount" />
      <el-table-column label="每人限购数量" align="center" prop="seckillLimit" />
      <el-table-column label="排序" align="center" prop="seckillSort" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['coupon:seckillSkuRelation:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['coupon:seckillSkuRelation:remove']"
          >删除</el-button>
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

    <!-- 添加或修改秒杀活动商品关联对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="活动id" prop="promotionId">
          <el-input v-model="form.promotionId" placeholder="请输入活动id" />
        </el-form-item>
        <el-form-item label="活动场次id" prop="promotionSessionId">
          <el-input v-model="form.promotionSessionId" placeholder="请输入活动场次id" />
        </el-form-item>
        <el-form-item label="商品id" prop="skuId">
          <el-input v-model="form.skuId" placeholder="请输入商品id" />
        </el-form-item>
        <el-form-item label="秒杀价格" prop="seckillPrice">
          <el-input v-model="form.seckillPrice" placeholder="请输入秒杀价格" />
        </el-form-item>
        <el-form-item label="秒杀总量" prop="seckillCount">
          <el-input v-model="form.seckillCount" placeholder="请输入秒杀总量" />
        </el-form-item>
        <el-form-item label="每人限购数量" prop="seckillLimit">
          <el-input v-model="form.seckillLimit" placeholder="请输入每人限购数量" />
        </el-form-item>
        <el-form-item label="排序" prop="seckillSort">
          <el-input v-model="form.seckillSort" placeholder="请输入排序" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSeckillSkuRelation, getSeckillSkuRelation, delSeckillSkuRelation, addSeckillSkuRelation, updateSeckillSkuRelation } from "@/api/coupon/seckillSkuRelation";

export default {
  name: "SeckillSkuRelation",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 秒杀活动商品关联表格数据
      seckillSkuRelationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        promotionId: null,
        promotionSessionId: null,
        skuId: null,
        seckillPrice: null,
        seckillCount: null,
        seckillLimit: null,
        seckillSort: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询秒杀活动商品关联列表 */
    getList() {
      this.loading = true;
      listSeckillSkuRelation(this.queryParams).then(response => {
        this.seckillSkuRelationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        promotionId: null,
        promotionSessionId: null,
        skuId: null,
        seckillPrice: null,
        seckillCount: null,
        seckillLimit: null,
        seckillSort: null
      };
      this.resetForm("form");
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加秒杀活动商品关联";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSeckillSkuRelation(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改秒杀活动商品关联";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSeckillSkuRelation(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSeckillSkuRelation(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除秒杀活动商品关联编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSeckillSkuRelation(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('coupon/seckillSkuRelation/export', {
        ...this.queryParams
      }, `coupon_seckillSkuRelation.xlsx`)
    }
  }
};
</script>