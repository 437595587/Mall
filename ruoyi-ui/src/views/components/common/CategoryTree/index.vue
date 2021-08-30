<template>
  <div>
    <el-tree :data="categoryList" :props="defaultProps" @node-click="handleNodeClick" highlight-current></el-tree>
  </div>
</template>

<script>
import { listCategory } from "@/api/product/category";
export default {
  name: '',
  data() {
    return {
      // 分类管理表格数据
      categoryList: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询分类管理列表 */
    getList() {
      this.loading = true;
      listCategory(this.queryParams).then(response => {
        this.categoryList = this.handleTree(response.data, "catId", "parentCid");
      });
    },
    /** 分类点击事件 */
    handleNodeClick(data) {
      this.$emit("handleNodeClick", data.catId);
    }
  }
};
</script>

<style lang="scss" scoped>

</style>
