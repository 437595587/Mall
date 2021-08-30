<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch">
      <el-form-item label="组名" prop="attrGroupName">
        <el-input
          v-model="queryParams.attrGroupName"
          placeholder="请输入组名"
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

    <el-row type="flex">
      <el-col :span="6">
        <category-tree @handleNodeClick="handleNodeClick"/>
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
                v-hasPermi="['product:attrGroup:add']"
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
                v-hasPermi="['product:attrGroup:edit']"
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
                v-hasPermi="['product:attrGroup:remove']"
            >删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
                type="warning"
                plain
                icon="el-icon-download"
                size="mini"
                @click="handleExport"
                v-hasPermi="['product:attrGroup:export']"
            >导出</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
        <el-table v-loading="loading" :data="attrGroupList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="分组id" align="center" prop="attrGroupId" />
          <el-table-column label="组名" align="center" prop="attrGroupName" />
          <el-table-column label="排序" align="center" prop="sort" />
          <el-table-column label="描述" align="center" prop="descript" />
          <el-table-column label="组图标" align="center" prop="icon" />
          <el-table-column label="所属分类" align="center" prop="catelogId" />
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="300px">
            <template slot-scope="scope">
              <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-edit"
                  @click="handleAttrGroupAttr(scope.row)"
                  v-hasPermi="['product:attrGroup:list', 'product:attr:list']"
              >关联</el-button>
              <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-edit"
                  @click="handleUpdate(scope.row)"
                  v-hasPermi="['product:attrGroup:edit']"
              >修改</el-button>
              <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  v-hasPermi="['product:attrGroup:remove']"
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

    <!-- 添加或修改属性分组对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="组名" prop="attrGroupName">
          <el-input v-model="form.attrGroupName" placeholder="请输入组名" />
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排序" />
        </el-form-item>
        <el-form-item label="描述" prop="descript">
          <el-input v-model="form.descript" placeholder="请输入描述" />
        </el-form-item>
        <el-form-item label="组图标" prop="icon">
          <el-input v-model="form.icon" placeholder="请输入组图标" />
        </el-form-item>
        <el-form-item label="所属分类" prop="catelogId">
          <category-cascader v-model="form.catelogId" placeholder="请选择所属分类" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 属性分组的关联属性 -->
    <el-dialog title="关联属性" :visible.sync="attrOpen" width="1000px" append-to-body>
      <div class="mb10">
        <el-button class="el-button--cyan" @click="handleAddAttr">新建关联</el-button>
        <el-button type="danger" @click="handleDeleteAttr">批量删除</el-button>
      </div>
      <div class="mb10">
        <el-form :model="attrQueryParams" ref="attrQueryForm" :inline="true">
          <el-form-item label="属性名" prop="attrName">
            <el-input clearable size="small" v-model="attrQueryParams.attrName" placeholder="属性名"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button size="mini" type="primary" icon="el-icon-search" @click="getAttrList">搜索</el-button>
            <el-button size="mini" icon="el-icon-refresh" @click="resetQuery('attrQueryForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <el-table v-loading="attrLoading" :data="attrList" @selection-change="handleAttrSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="属性名" align="center" prop="attrName" />
        <el-table-column label="图标" align="center" prop="icon" />
        <el-table-column label="可选值列表" align="center" prop="valueSelect" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="300">
          <template slot-scope="scope">
            <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDeleteAttr(scope.row)"
                v-hasPermi="['product:attr:remove']"
            >移除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <pagination
          v-show="attrTotal>0"
          :total="attrTotal"
          :page.sync="attrQueryParams.pageNum"
          :limit.sync="attrQueryParams.pageSize"
          @pagination="getAttrList"
      />
      <div slot="footer" class="dialog-footer">
        <el-button @click="attrOpen = false">关闭</el-button>
      </div>
    </el-dialog>

    <!--未属性分组的基本属性-->
    <el-dialog title="选择属性" :visible.sync="noRelationAttrOpen" width="1000px" append-to-body>
      <div class="mb10">
        <el-form :model="noRelationAttrQueryParams" ref="noRelationAttrQueryForm" :inline="true">
          <el-form-item label="属性名" prop="attrName">
            <el-input clearable size="small" v-model="noRelationAttrQueryParams.attrName" placeholder="属性名"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button size="mini" type="primary" icon="el-icon-search" @click="getNoRelationAttrList">搜索</el-button>
            <el-button size="mini" icon="el-icon-refresh" @click="resetQuery('noRelationAttrQueryForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <el-table v-loading="noRelationAttrLoading" :data="noRelationAttrList" @selection-change="handleNoRelationAttrSelectionChange">
        <el-table-column type="selection" width="55" />
        <el-table-column label="属性名" align="center" prop="attrName" />
        <el-table-column label="图标" align="center" prop="icon" />
        <el-table-column label="可选值列表" align="center" prop="valueSelect" />
      </el-table>
      <pagination
          v-show="noRelationAttrTotal>0"
          :total="noRelationAttrTotal"
          :page.sync="noRelationAttrQueryParams.pageNum"
          :limit.sync="noRelationAttrQueryParams.pageSize"
          @pagination="getNoRelationAttrList"
      />
      <div slot="footer" class="dialog-footer">
        <el-button @click="noRelationAttrOpen = false">关闭</el-button>
        <el-button class="el-button--cyan" @click="submitNoRelationAttr">新增</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAttrGroup, getAttrGroup, delAttrGroup, addAttrGroup, updateAttrGroup, getAttr,delAttr, getNoRelationAttr, addNoRelationAttr } from "@/api/product/attrGroup";
import CategoryTree from "@/views/components/common/CategoryTree";
import CategoryCascader from "@/views/components/common/CategoryCascader"
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "AttrGroup",
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
      // 属性分组表格数据
      attrGroupList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        attrGroupName: null,
        sort: null,
        descript: null,
        icon: null,
        catelogId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      // 分类管理树选项
      categoryOptions: null,
      // 属性信息
      attrList: [],
      // 是否显示关联属性弹出层
      attrOpen: false,
      attrLoading: true,
      attrQueryParams: {
        attrName: null,
        pageNum: 1,
        pageSize: 10
      },
      attrTotal: 0,
      attrIds: [],
      attrGroupId: null,
      noRelationAttrList: [],
      noRelationAttrQueryParams: {
        attrName: null,
        pageNum: 1,
        pageSize: 10
      },
      noRelationAttrTotal: 0,
      noRelationAttrOpen: false,
      noRelationAttrLoading: true,
      noRelationAttrIds: []
    };
  },
  components: { CategoryTree, CategoryCascader },
  created() {
    this.getList();
  },
  methods: {
    /** 查询属性分组列表 */
    getList() {
      this.loading = true;
      listAttrGroup(this.queryParams).then(response => {
        this.attrGroupList = response.rows;
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
        attrGroupId: null,
        attrGroupName: null,
        sort: null,
        descript: null,
        icon: null,
        catelogId: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery(form) {
      if (form === 'noRelationAttrQueryForm') {
        this.resetForm(form);
        this.handleQueryNoRelationAttr();
      }else if (form === 'attrQueryForm') {
        this.resetForm(form);
        this.handleQueryAttr();
      } else {
        this.resetForm("queryForm");
        this.handleQuery();
      }
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.attrGroupId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加属性分组";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const attrGroupId = row.attrGroupId || this.ids
      getAttrGroup(attrGroupId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改属性分组";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.attrGroupId != null) {
            updateAttrGroup(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAttrGroup(this.form).then(response => {
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
      const attrGroupIds = row.attrGroupId || this.ids;
      this.$confirm('是否确认删除属性分组编号为"' + attrGroupIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delAttrGroup(attrGroupIds);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('product/attrGroup/export', {
        ...this.queryParams
      }, `product_attrGroup.xlsx`)
    },
    /** 点击了分类 */
    handleNodeClick(catId) {
      this.loading = true;
      listAttrGroup({ catelogId: catId }).then(response => {
        this.attrGroupList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 关联按钮操作 */
    handleAttrGroupAttr(row) {
      this.attrGroupId = row.attrGroupId;
      this.attrQueryParams = {
        attrName: null,
        pageNum: 1,
        pageSize: 10
      };
      this.noRelationAttrQueryParams = {
        attrName: null,
        pageNum: 1,
        pageSize: 10
      };
      this.attrOpen = true;
      this.getAttrList();
    },
    getAttrList() {
      this.attrLoading = true;
      getAttr(this.attrGroupId, this.attrQueryParams).then(response => {
        this.attrList = response.rows;
        this.attrTotal = response.total;
        this.attrLoading = false;
      });
    },
    handleAttrSelectionChange(selection) {
      this.attrIds = selection.map(item => item.attrId);
    },
    handleDeleteAttr(row) {
      const attrIds = row.attrId || this.attrIds;
      this.$confirm('是否确认删除属性编号为"' + attrIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delAttr(attrIds);
      }).then(() => {
        this.getAttrList();
        this.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    handleAddAttr() {
      this.noRelationAttrOpen = true;
      this.getNoRelationAttrList();
    },
    getNoRelationAttrList() {
      this.noRelationAttrLoading = true;
      getNoRelationAttr(this.noRelationAttrQueryParams).then(response => {
        this.noRelationAttrList = response.rows;
        this.noRelationAttrTotal = response.total;
        this.noRelationAttrLoading = false;
      });
    },
    handleNoRelationAttrSelectionChange(selection) {
      this.noRelationAttrIds = selection.map(item => item.attrId);
    },
    submitNoRelationAttr() {
      addNoRelationAttr(this.attrGroupId, this.noRelationAttrIds).then(response => {
        this.msgSuccess("新增成功");
        this.getNoRelationAttrList();
        this.getAttrList();
      });
    },
    handleQueryNoRelationAttr() {
      this.noRelationAttrQueryParams.pageNum = 1;
      this.getNoRelationAttrList();
    },
    handleQueryAttr() {
      this.attrQueryParams.pageNum = 1;
      this.getAttrList();
    }
  }
};
</script>
