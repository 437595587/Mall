<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch">
      <el-form-item label="属性名" prop="attrName">
        <el-input
            v-model="queryParams.attrName"
            placeholder="请输入属性名"
            clearable
            size="small"
            @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="启用状态" prop="enable">
        <el-select v-model="queryParams.enable" clearable size="small">
          <el-option label="启用" :value="1"></el-option>
          <el-option label="禁用" :value="0"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="快速展示" prop="showDesc" size="small">
        <el-select v-model="queryParams.enable" clearable>
          <el-option label="是" :value="1"></el-option>
          <el-option label="否" :value="0"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row type="flex">
      <el-col :span="6">
        <category-tree ref="categoryComponent" @handleNodeClick="handleNodeClick"/>
      </el-col>
      <el-col :span="18">
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
                type="primary"
                plain
                icon="el-icon-plus"
                size="mini"
                @click="handleAdd"
                v-hasPermi="['product:attr:add']"
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
                v-hasPermi="['product:attr:edit']"
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
                v-hasPermi="['product:attr:remove']"
            >删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
                type="warning"
                plain
                icon="el-icon-download"
                size="mini"
                @click="handleExport"
                v-hasPermi="['product:attr:export']"
            >导出</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
        <el-table v-loading="loading" :data="attrList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="属性id" align="center" prop="attrId" />
          <el-table-column label="属性名" align="center" prop="attrName" />
          <el-table-column label="是否需要检索" align="center" prop="searchType" />
          <el-table-column label="启用状态" align="center" prop="enable" />
          <el-table-column label="所属分类" align="center" prop="catelogId" />
          <el-table-column label="快速展示" align="center" prop="showDesc" />
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-edit"
                  @click="handleUpdate(scope.row)"
                  v-hasPermi="['product:attr:edit']"
              >修改</el-button>
              <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  v-hasPermi="['product:attr:remove']"
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>

    <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
    />

    <!-- 添加或修改规格参数对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="属性名" prop="attrName">
          <el-input v-model="form.attrName" placeholder="请输入属性名" />
        </el-form-item>
        <el-form-item label="值类型" prop="valueType">
          <el-select v-model="form.valueType" placeholder="请选择值类型">
            <el-option label="单值" :value="0" />
            <el-option label="多值" :value="1" />
          </el-select>
        </el-form-item>
        <el-form-item label="可选值列表" prop="valueSelect" label-width="88px">
          <el-input type="textarea" v-model="form.valueSelect" placeholder="请输入可选值列表[用逗号分隔]" />
        </el-form-item>
        <el-form-item label="属性图标" prop="icon">
          <image-upload v-model="form.icon" />
        </el-form-item>
        <el-form-item label="所属分类" prop="catelogId">
          <category-cascader v-model="form.catelogId" placeholder="请选择所属分类" />
        </el-form-item>
        <el-form-item label="可检索" prop="searchType">
          <el-switch :inactive-value="0" :active-value="1" v-model="form.searchType" />
        </el-form-item>
        <el-form-item label="快速展示" prop="showDesc">
          <el-switch :inactive-value="0" :active-value="1" v-model="form.showDesc" />
        </el-form-item>
        <el-form-item label="启用状态" prop="enable">
          <el-switch :inactive-value="0" :active-value="1" v-model="form.enable" />
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
import { listAttr, getAttr, delAttr, addAttr, updateAttr } from "@/api/product/attr";
import CategoryTree from "@/views/components/common/CategoryTree";
import CategoryCascader from "@/views/components/common/CategoryCascader";

export default {
  name: "Attr",
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
      // 规格参数表格数据
      attrList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        attrName: null,
        searchType: null,
        valueType: null,
        icon: null,
        valueSelect: null,
        attrType: 0,
        enable: null,
        catelogId: null,
        showDesc: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      // 分类管理树选项
      categoryOptions: null,
      // 属性分组列表
      attrGroupList: null
    };
  },
  components: { CategoryTree, CategoryCascader },
  created() {
    this.getList();
  },
  methods: {
    /** 查询规格参数列表 */
    getList() {
      this.loading = true;
      listAttr(this.queryParams).then(response => {
        this.attrList = response.rows;
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
        attrId: null,
        attrName: null,
        searchType: 0,
        valueType: 0,
        icon: null,
        valueSelect: null,
        attrType: 0,
        enable: 1,
        catelogId: null,
        showDesc: null
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
      this.ids = selection.map(item => item.attrId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加规格参数";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const attrId = row.attrId || this.ids
      getAttr(attrId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改规格参数";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.attrId != null) {
            updateAttr(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAttr(this.form).then(response => {
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
      const attrIds = row.attrId || this.ids;
      this.$confirm('是否确认删除规格参数编号为"' + attrIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return delAttr(attrIds);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('product/attr/export', {
        ...this.queryParams
      }, `product_attr.xlsx`)
    },
    /** 查询分类管理下拉树结构 */
    handleNodeClick(catId) {
      listAttr({ catelogId: catId }).then(response => {
        this.attrList = response.rows;
      });
    }
  }
};
</script>
