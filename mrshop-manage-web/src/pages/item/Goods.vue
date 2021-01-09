<template>
  <v-card>
    <v-toolbar class="elevation-0">
      <v-btn @click="addItem" color="primary">新增商品</v-btn>
      <v-spacer/>
      <v-flex xs3>
        状态：
        <v-btn-toggle mandatory v-model="search.saleable">
          <v-btn flat :value="2">
            全部
          </v-btn>
          <v-btn flat :value="1">
            上架
          </v-btn>
          <v-btn flat :value="0">
            下架
          </v-btn>
        </v-btn-toggle>
      </v-flex>
      <v-flex xs3>
        <v-text-field
          append-icon="search"
          label="搜索"
          single-line
          hide-details
          v-model="search.key"
        />
      </v-flex>
    </v-toolbar>
    <v-divider/>
    <v-data-table
      :headers="headers"
      :items="items"
      :pagination.sync="pagination"
      :total-items="totalItems"
      :loading="loading"
      class="elevation-1"
    >
      <template slot="items" slot-scope="props">
        <td class="text-xs-center">{{ props.item.id }}</td>
        <td class="text-xs-center">{{ props.item.title }}</td>
        <td class="text-xs-center">{{ props.item.categoryName}}</td>
        <td class="text-xs-center">{{ props.item.brandName }}</td>
        <td class="justify-center layout px-0">
          <v-btn icon small @click="editItem(props.item)">
            <i class="el-icon-edit"/>
          </v-btn>
          <v-btn icon small @click="deleteItem(props.item.id)">
            <i class="el-icon-delete"/>
          </v-btn>
          <v-btn icon small v-if="props.item.saleable" @click="editSaleable(props.item)">下架</v-btn>
          <v-btn icon v-else @click="editSaleable(props.item)">上架</v-btn>
        </td>
      </template>
      <template slot="no-data">
        <v-alert :value="true" color="error" icon="warning">
          对不起，没有查询到任何数据 :(
        </v-alert>
      </template>
      <template slot="pageText" slot-scope="props">
        共{{props.itemsLength}}条,当前:{{ props.pageStart }} - {{ props.pageStop }}
      </template>
    </v-data-table>

    <v-dialog v-if="show" v-model="show" max-width="900" scrollable persistent>
      <v-card>
        <v-toolbar dense dark color="primary">
          <v-toolbar-title>{{isEdit ? '修改商品' : '新增商品'}}</v-toolbar-title>
          <v-spacer/>
          <v-btn icon @click="closeForm">
            <v-icon>close</v-icon>
          </v-btn>
        </v-toolbar>
        <v-card-text style="height: 600px;">
          <!-- 表单 -->
          <goods-form :oldGoods="selectedGoods" :isEdit="isEdit" :step="step" ref="goodsForm" @closeForm="closeForm"/>
        </v-card-text>
        <v-card-actions>
          <v-layout row justify-center>
            <v-flex xs2>
              <v-btn :disabled="step === 1" @click="lastStep">上一步</v-btn>
            </v-flex>
            <v-flex xs2>
              <v-btn :disabled="step === 4" color="primary" @click="nextStep">下一步</v-btn>
            </v-flex>
          </v-layout>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script>
  import GoodsForm from './GoodsForm'
  // import {goodsData} from "../../mockDB";

  export default {
    name: "item",
    components: {
      GoodsForm
    },
    data() {
      return {
        search: {
          key: '',
          saleable: true,
        },// 过滤字段
        totalItems: 0,// 总条数
        items: [],// 表格数据
        loading: true,
        pagination: {},// 分页信息
        headers: [// 表头
          {text: 'id', align: 'center', value: 'id'},
          {text: '标题', align: 'center', sortable: false, value: 'name'},
          {text: '商品分类', align: 'center', sortable: false, value: 'image'},
          {text: '品牌', align: 'center', value: 'letter', sortable: true,},
          {text: '操作', align: 'center', value: 'id', sortable: false}
        ],
        show: false,// 是否弹出窗口
        selectedGoods: null, // 选中的商品信息
        isEdit: false, // 判断是编辑还是新增
        step: 1// 表单中的导航条
      }
    },
    watch: {
      pagination: {
        handler() {
          this.getDataFromApi();
        },
        deep: true
      },
      search: {
        handler() {
          this.getDataFromApi();
        },
        deep: true
      }
    },
    mounted() {
      this.getDataFromApi();
    },
    methods: {
      editSaleable(item){
        let tishi = item.saleable==1?"下架":"上架"

        this.$message.confirm("是否"+tishi+ "?").then(()=>{
        this.$http.put("goods/xiajia",{
            id:item.id,
            saleable:item.saleable
        }).then(resp=>{
          this.closeForm();
          console.log(resp)
        })
        })
        
      },
      closeForm() {
        this.isEdit = false;
        this.show = false;
        this.step = 1;
        this.selectedGoods = null;
        this.getDataFromApi();
      },
      lastStep() {
        if (this.step === 1) {
          return;
        }
        this.step--;
      },
      nextStep() {
        if (this.step === 4) {
          return;
        }
        if (this.$refs.goodsForm.$refs.basic.validate()) {
          this.step++;
        }
      },
      addItem() {
        this.selectedGoods = null;
        this.isEdit = false;
        this.show = true;
      },
      editItem(item) {
        this.isEdit = true;
        this.show = true;
        // this.selectedGoods = item;
        // const names = item.categoryName.split("/");
        // this.selectedGoods.categories = [
        //   {id: item.cid1, name: names[0]},
        //   {id: item.cid2, name: names[1]},
        //   {id: item.cid3, name: names[2]}
        // ];
        // 查询商品详情
        let obj = item;
        this.$http.get("/goods/spuByIdGetDetail/",{
          params:{
            spuId:item.id
          }
        }).then(resp => {
            obj.categories = []; //监控到的值为undefined
            // this.selectedGoods.spuDetail = resp.data.data;
            // this.selectedGoods.spuDetail.specTemplate = JSON.parse(resp.data.data.specialSpec);
            // this.selectedGoods.spuDetail.specifications = JSON.parse(resp.data.data.genericSpec);
              obj.spuDetail = resp.data.data;
              obj.spuDetail.specTemplate = JSON.parse(resp.data.data.specialSpec);//特有的规格参数
              obj.spuDetail.specifications = JSON.parse(resp.data.data.genericSpec); //通用规格参数
            //查询sku
           
          this.$http.get("goods/skuBySpuIdStock",{
              params:{
                 spuId:item.id
              }
            }).then(resp=>{
        
         
            obj.skus = resp.data.data;
            this.selectedGoods = obj;
          })
           
          })
           
        
      },
      deleteItem(id) {
        this.$message.confirm('此操作将永久删除该商品, 是否继续?')
          .then(() => {
            // 发起删除请求
            this.$http.delete("/goods/delete?spuId=" + id)
              .then(() => {
                // 删除成功，重新加载数据
                this.getDataFromApi();
                this.$message.info('删除成功!');
              })
          })
          .catch(() => {
            this.$message.info('已取消删除');
          });

      },
      getDataFromApi() {
        this.loading = true;
        this.$http.get('spu/spuList',{
          params:{
                    page:this.pagination.page,
                    rows:this.pagination.rowsPerPage,
                    sort: this.pagination.sortBy,
                    order: this.pagination.descending,
                    title: this.search.key,
                    saleable:this.search.saleable
                }
        }).then(resp=>{
          // console.log(resp)
           this.items = resp.data.data
           this.totalItems=resp.data.message - 0;
          this.loading = false;
         
        }).catch(error=>console.log(error))
      }
    }
  }
</script>

<style scoped>
  label {
    font-size: 14px;
  }
</style>
