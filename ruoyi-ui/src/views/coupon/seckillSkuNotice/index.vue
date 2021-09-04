<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="member_id" prop="memberId">
        <el-input
          v-model="queryParams.memberId"
          placeholder="请输入member_id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="sku_id" prop="skuId">
        <el-input
          v-model="queryParams.skuId"
          placeholder="请输入sku_id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动场次id" prop="sessionId">
        <el-input
          v-model="queryParams.sessionId"
          placeholder="请输入活动场次id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订阅时间" prop="subcribeTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.subcribeTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择订阅时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="发送时间" prop="sendTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.sendTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择发送时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="通知方式[0-短信，1-邮件]" prop="noticeType">
        <el-select v-model="queryParams.noticeType" placeholder="请选择通知方式[0-短信，1-邮件]" clearable size="small">
          <el-option label="请选择字典生成" value="" />
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
          v-hasPermi="['coupon:seckillSkuNotice:add']"
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
          v-hasPermi="['coupon:seckillSkuNotice:edit']"
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
          v-hasPermi="['coupon:seckillSkuNotice:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['coupon:seckillSkuNotice:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="seckillSkuNoticeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="member_id" align="center" prop="memberId" />
      <el-table-column label="sku_id" align="center" prop="skuId" />
      <el-table-column label="活动场次id" align="center" prop="sessionId" />
      <el-table-column label="订阅时间" align="center" prop="subcribeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.subcribeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发送时间" align="center" prop="sendTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.sendTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="通知方式[0-短信，1-邮件]" align="center" prop="noticeType" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['coupon:seckillSkuNotice:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['coupon:seckillSkuNotice:remove']"
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

    <!-- 添加或修改秒杀商品通知订阅对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="member_id" prop="memberId">
          <el-input v-model="form.memberId" placeholder="请输入member_id" />
        </el-form-item>
        <el-form-item label="sku_id" prop="skuId">
          <el-input v-model="form.skuId" placeholder="请输入sku_id" />
        </el-form-item>
        <el-form-item label="活动场次id" prop="sessionId">
          <el-input v-model="form.sessionId" placeholder="请输入活动场次id" />
        </el-form-item>
        <el-form-item label="订阅时间" prop="subcribeTime">
          <el-date-picker clearable size="small"
            v-model="form.subcribeTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择订阅时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="发送时间" prop="sendTime">
          <el-date-picker clearable size="small"
            v-model="form.sendTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择发送时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="通知方式[0-短信，1-邮件]" prop="noticeType">
          <el-select v-model="form.noticeType" placeholder="请选择通知方式[0-短信，1-邮件]">
            <el-option label="请选择字典生成" value="" />
          </el-select>
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
import { listSeckillSkuNotice, getSeckillSkuNotice, delSeckillSkuNotice, addSeckillSkuNotice, updateSeckillSkuNotice } from "@/api/coupon/seckillSkuNotice";

export default {
  name: "SeckillSkuNotice",
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
      // 秒杀商品通知订阅表格数据
      seckillSkuNoticeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        memberId: null,
        skuId: null,
        sessionId: null,
        subcribeTime: null,
        sendTime: null,
        noticeType: null
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
    /** 查询秒杀商品通知订阅列表 */
    getList() {
      this.loading = true;
      listSeckillSkuNotice(this.queryParams).then(response => {
        this.seckillSkuNoticeList = response.rows;
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
        memberId: null,
        skuId: null,
        sessionId: null,
        subcribeTime: null,
        sendTime: null,
        noticeType: null
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
      this.title = "添加秒杀商品通知订阅";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSeckillSkuNotice(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改秒杀商品通知订阅";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSeckillSkuNotice(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSeckillSkuNotice(this.form).then(response => {
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
      this.$confirm('是否确认删除秒杀商品通知订阅编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSeckillSkuNotice(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('coupon/seckillSkuNotice/export', {
        ...this.queryParams
      }, `coupon_seckillSkuNotice.xlsx`)
    }
  }
};
</script>