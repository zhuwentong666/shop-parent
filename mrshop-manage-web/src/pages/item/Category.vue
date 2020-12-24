<template>
  <v-card>
      <v-flex xs12 sm10>
        <v-tree url="/category/list"
                :isEdit="isEdit"
                :key="key1"
                @handleAdd="handleAdd"
                @handleEdit="handleEdit"
                @handleDelete="handleDelete"
                @handleClick="handleClick"
        />
      </v-flex>
  </v-card>
</template>

<script>
  // import {treeData} from '../../mockDB'
  export default {
    name: "category",
    data() {
      return {
        key1:0,
        isEdit:true
      }
    },
    methods: {
      handleAdd(node) {
        console.log("add .... ");
        console.log(node);
      },
      handleEdit(id, name) {
        console.log("edit... id: " + id + ", name: " + name)
      },
      handleDelete(id) {
        console.log("删除....")
        this.$http.delete('/category/deleteById?id=' + id).then(res=>{
          if(res.data.code==200){
          this.key1 = new Date().getTime
          this.$message.success("删除成功");
          }else{
            this.$message.error("删除失败:"+res.data.message)
          }
          
        }).catch(error=>console.log(error))
      },
      handleClick(node) {
        console.log(node)
      }
    }
  };
</script>

<style scoped>

</style>
