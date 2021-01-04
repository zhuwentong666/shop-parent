<template>
  <div>
    <v-card-text>
      <v-form v-model="valid" ref="form">
        <v-text-field
          v-model="brand.name"
          label="品牌名称"
          :rules="nameRules"
          required
        ></v-text-field>

        <v-cascader
          url="/category/list"
          required
          v-model="brand.categories"
          multiple
          label="商品分类"
        />

        <!--文件上传-->
        <!--布局,不加此标签模态框样式会乱,因为联动组件占用了一部分位置-->
        <v-layout row>
          <!--栅格布局 将一行分成12分 xs3:当前组件占3分-->
          <v-flex xs3>
            <span style="font-size: 16px; color: #444">品牌LOGO：</span>
          </v-flex>
          <v-flex>
            <v-upload
              v-model="brand.image"
              url="/upload"
              :multiple="false"   
              :pic-width="250"
              :pic-height="90"
              required 

            />
          </v-flex>
        </v-layout>
      </v-form>
    </v-card-text>

    <v-divider></v-divider>

    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn small @click="cancel()">取消</v-btn>
      <v-btn small color="primary" @click="submitForm()">确认</v-btn>
    </v-card-actions>
  </div>
</template>
<script>
export default {
  name: "NewBrandForm",
  props: {
    dialog: Boolean,
    brandDetail:Object,
    isEdit:Boolean
  },
  data() {
    return {
      valid: true,
      brand: {
        image:"",
        name: "",
        letter: "",
        categories: [],
      },
      nameRules: [
        (v) => !!v || "品牌名称不能为空",
        (v) =>
          (v && v.length > 1 && v.length <= 10) ||
          "品牌名称长度必须大于1且小于 10",
      ],
    };
  },
  // components:{
  //   cascader,
  // },
  watch: {
    
    dialog(val) {
      // if (this.dialog) {
      //   if (this.brand.id) {
      //     return (this.brand.id = null);
      //   }
       
       
      // }
    // delete this.brandDetail.id
    if(!this.isEdit){
      if (val) this.$refs.form.reset();
    }
       
    },
    brandDetail(val){
      console.log(val)
      // this.brand.name = val.name; 
      if(this.isEdit){
    this.$http.get('/category/brand',{
            params:{
              brandId:val.id
            }
          }).then(resp=>{
              let brand = val;
              brand.categories = resp.data.data;
              this.brand = brand;
            console.log(resp)
          })
      }
      
    },
  },
  methods: {
    cancel() {
      this.$emit("closeDialog");
    },

    submitForm() {
      if (!this.$refs.form.validate()) {
        return;
      }
      let formData = this.brand;
       
      let categorierArr = this.brand.categories.map((category) => category.id);
      formData.categories = categorierArr.join();

      this.$http({
        url:'brand/save',
        method:this.isEdit ? 'put' : 'post',
        data:formData
      }).then((resp) => {
          this.cancel();
        }).catch((error) => console.log("error"));
    },
  },
};
</script>