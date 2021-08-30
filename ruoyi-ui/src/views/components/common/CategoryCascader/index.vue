<template>
  <div>
    <el-cascader
        clearable
        :value="value"
        @input="handleInput"
        :options="categoryListOptions"
        :props="defaultProps"
    ></el-cascader>
  </div>
</template>

<script>
import { listCategory } from "@/api/product/category";
export default {
  name: '',
  props: {
    value: {
      type: Number
    }
  },
  data() {
    return {
      // 分类管理表格数据
      categoryListOptions: [],
      defaultProps: {
        children: 'children',
        label: 'name',
        value: 'catId'
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询分类管理列表 */
    getList() {
      listCategory(this.queryParams).then(response => {
        this.categoryListOptions = this.handleTree(response.data, "catId", "parentCid");
      });
    },
    handleInput(val) {
      this.$emit('input', val[val.length - 1]);
    }
  }
};
</script>

<style lang="scss" scoped>

</style>
