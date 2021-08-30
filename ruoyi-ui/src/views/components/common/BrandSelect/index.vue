<template>
  <div>
    <el-select :value="value" @input="handleInput" clearable :size="size">
      <el-option v-for="option in brandOptions" :key="option.brandId"
                 :value="option.brandId" :label="option.name"
      ></el-option>
    </el-select>
  </div>
</template>

<script>
import { listBrand } from "@/api/product/brand";

export default {
  name: '',
  props: {
    value:{
      type: Number
    },
    size: {
      type: String,
      default: ''
    }
  },
  created() {
    this.getList();
  },
  data() {
    return {
      brandOptions: []
    };
  },
  methods: {
    getList() {
      listBrand().then(response => {
        this.brandOptions = response.rows;
      });
    },
    handleInput(val) {
      this.$emit('input', val);
    }
  }
};
</script>

<style scoped>

</style>
