<template>
    <v-card>
        <v-card-title>
           <v-btn color="info" @click="addData()">新增</v-btn>
      
       <div class="text-xs-center">
    <v-dialog
      v-model="dialog"
      width="500"
>
      <v-card>
        <v-card-title
          class="headline grey lighten-2"
          primary-title
        >
          品牌{{isEdit?'修改':'新增'}}
        </v-card-title>

        <new-brand-form @closeDialog="closeDialog"  :isEdit="isEdit" :dialog="dialog" :brandDetail="brandDetail" />

      </v-card>
    </v-dialog>
  </div>
      
      <v-spacer/>
      <v-text-field
        append-icon="search"
        label="搜索"
        @keyup.enter="getDataFormApi()"
        v-model="search"
      />
    </v-card-title>
    <v-divider/>
    <v-data-table
      :headers="headers"
      :items="desserts"
      :pagination.sync="pagination"
      :total-items="total"
      class="elevation-1"
    >
     <template slot="items" slot-scope="props">
        <td class="text-xs-center">{{ props.item.id }}</td>
        <td class="text-xs-center">{{ props.item.name }}</td>
        <td class="text-xs-center"><img v-if="!!props.item.image" width="102" height="36" :src="props.item.image"/></td>
        <td class="text-xs-center">{{ props.item.letter }}</td>
        <td class="text-xs-center">
            
              <v-btn flat icon color="indigo" @click="editdata(props.item)">
                <v-icon>edit</v-icon>
              </v-btn>
           
            
              <v-btn flat icon color="indigo" @click="deleteData(props.item.id)">
                <v-icon>delete</v-icon>
              </v-btn>
        
        </td>
      </template>
    </v-data-table>
    </v-card>
</template>
<script>
import NewBrandForm from './NewBrandForm';
export default {
    name:"NewBrand",
    components:{
        NewBrandForm
    },
    data(){    
        return{
          brandDetail:{},
          isEdit: false, // 判断是编辑还是新增
            dialog:false,//是否取消默认false
            pagination:{},// 分页信息
            total:0,// 总条数
            search:"",// 过滤字段
            headers:[// 表头
                { text:"id",align:"center", value:"id"},
                { text: "name", value: "name", align: "center" },
                { text: "image", value: "image", align: "center",sortable: false },
                { text: "letter", value: "letter", align: "center" },
                { text: "操作", align: "center",sortable: false, value:"id" }

            ],
            desserts:[]// 表格数据
        }
    },
    mounted(){
        this.getDataFormApi();
    },
    methods:{
        closeDialog(){
          this.dialog = false
          this.getDataFormApi();
        },
        addData(){
          this.isEdit = false;
          this.dialog = true;
        },
        editdata(obj){
          this.isEdit = true
          this.dialog =true
          this.brandDetail = obj
          console.log(obj)
          
        },
        deleteData(id){
            this.$message.confirm('此操作将永久删除该品牌, 是否继续?').then(() => {
            // 发起删除请求
            this.$http.delete("/brand/DeleteId?id=" + id,)
              .then(() => {
                // 删除成功，重新加载数据
                this.$message.success("删除成功！");
                this.getDataFormApi();
              })
          }).catch(() => {
            this.$message.info("删除已取消！");
        });

        },
      
        getDataFormApi(){
            this.$http.get('brand/getbrandList',{
                params:{
                    page:this.pagination.page,
                    rows:this.pagination.rowsPerPage,
                    sort: this.pagination.sortBy,
                    order: this.pagination.descending,
                    name:this.search
                }
            }).then(resp=>{
              console.log(resp);
                this.desserts = resp.data.data.list
                this.total= resp.data.data.total
               
            }).catch(error => console.log(error))
        }
    },
    watch:{
        pagination(){
            this.getDataFormApi();
        }
    }
}
</script>