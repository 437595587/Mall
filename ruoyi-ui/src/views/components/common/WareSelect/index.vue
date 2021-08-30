<template>
  <div>
    <el-select :value="value" @input="handleInput" clearable :size="size">
      <el-option v-for="option in wareOptions" :key="option.id"
                 :value="option.id" :label="option.name"
      ></el-option>
    </el-select>
  </div>
</template>

<script>
import { listWareInfo } from "@/api/ware/wareInfo";

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
      wareOptions: []
    };
  },
  methods: {
    getList() {
      listWareInfo().then(response => {
        this.wareOptions = response.rows;
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
