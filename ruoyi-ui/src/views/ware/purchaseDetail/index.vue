<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch">
      <el-form-item label="仓库" prop="wareId">
        <ware-select  v-model="queryParams.wareId"/>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
          <el-option label="新建" :value="0" />
          <el-option label="已分配" :value="1" />
          <el-option label="正在采购" :value="2" />
          <el-option label="已完成" :value="3" />
          <el-option label="采购失败" :value="4" />
        </el-select>
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
          v-hasPermi="['ware:purchaseDetail:add']"
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
          v-hasPermi="['ware:purchaseDetail:edit']"
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
          v-hasPermi="['ware:purchaseDetail:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ware:purchaseDetail:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            size="mini"
            :disabled="multiple"
            @click="handleMerge"
            v-has-permi="['ware:purchaseDetail:edit']"
        >合并整单</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="purchaseDetailList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="采购单id" align="center" prop="purchaseId" />
      <el-table-column label="采购商品id" align="center" prop="skuId" />
      <el-table-column label="采购数量" align="center" prop="skuNum" />
      <el-table-column label="采购金额" align="center" prop="skuPrice" />
      <el-table-column label="仓库id" align="center" prop="wareId" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status===0">新建</el-tag>
          <el-tag type="info" v-if="scope.row.status===1">已分配</el-tag>
          <el-tag type="wanring" v-if="scope.row.status===2">正在采购</el-tag>
          <el-tag type="success" v-if="scope.row.status===3">已完成</el-tag>
          <el-tag type="danger" v-if="scope.row.status===4">采购失败</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ware:purchaseDetail:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ware:purchaseDetail:remove']"
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

    <!-- 添加或修改采购需求对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="采购商品id" prop="skuId">
          <el-input v-model="form.skuId" placeholder="请输入采购商品id" />
        </el-form-item>
        <el-form-item label="采购数量" prop="skuNum">
          <el-input v-model="form.skuNum" placeholder="请输入采购数量" />
        </el-form-item>
        <el-form-item label="仓库" prop="wareId">
          <ware-select v-model="form.wareId" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="合并采购单" :visible.sync="mergeOpen" width="600px" append-to-body>
      <el-select v-model="purchaseId">
        <el-option :label="option.id + ':' + option.assignee_name" :value="option.id" v-for="option in unReceivePurchase" />
      </el-select>
    </el-dialog>
  </div>
</template>

<script>
import { listPurchaseDetail, getPurchaseDetail, delPurchaseDetail, addPurchaseDetail, updatePurchaseDetail } from "@/api/ware/purchaseDetail";
import { listUnReceivePurchase } from "@/api/ware/purchase";
import WareSelect from "@/views/components/common/WareSelect"

export default {
  name: "PurchaseDetail",
  components: { WareSelect },
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
      // 采购需求表格数据
      purchaseDetailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        purchaseId: null,
        skuId: null,
        skuNum: null,
        skuPrice: null,
        wareId: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      mergeOpen: false,
      unReceivePurchase: [],
      purchaseId: null
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询采购需求列表 */
    getList() {
      this.loading = true;
      listPurchaseDetail(this.queryParams).then(response => {
        this.purchaseDetailList = response.rows;
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
        purchaseId: null,
        skuId: null,
        skuNum: null,
        skuPrice: null,
        wareId: null,
        status: 0
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
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加采购需求";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPurchaseDetail(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改采购需求";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePurchaseDetail(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPurchaseDetail(this.form).then(response => {
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
      this.$confirm('是否确认删除采购需求编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delPurchaseDetail(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ware/purchaseDetail/export', {
        ...this.queryParams
      }, `ware_purchaseDetail.xlsx`)
    },
    handleMerge() {
      listUnReceivePurchase().then(response => {
        this.unReceivePurchase = response.data;
        this.mergeOpen = true;
      });
    }
  }
};
</script>
