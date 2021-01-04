package com.zwt.shop.service.impl;

import com.google.gson.JsonObject;
import com.zwt.shop.base.BaseApiService;
import com.zwt.shop.base.Result;
import com.zwt.shop.entity.CategoryBrandEntity;
import com.zwt.shop.entity.CategoryEntity;
import com.zwt.shop.mapper.CategoryBrandMapper;
import com.zwt.shop.mapper.CategoryMapper;
import com.zwt.shop.service.CategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CategoryServiceImpl
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2020/12/22
 * @Version V1.0
 **/
@RestController
public class CategoryServiceImpl extends BaseApiService implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private CategoryBrandMapper categoryBrandMapper;

    @Override
    public Result<CategoryEntity> updateById(CategoryEntity categoryEntity) {
        try {
            categoryMapper.updateByPrimaryKeySelective(categoryEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.setResultSuccess();
    }

    @Override
    public Result<CategoryEntity> save(CategoryEntity categoryEntity) {


        if(categoryEntity == null){return this.setResultError("为空");};
        //判断 这个新增的子节点的父节点 是否是父类
        CategoryEntity parentCategoryEntity1 = new CategoryEntity();
        parentCategoryEntity1.setId(categoryEntity.getParentId());
        CategoryEntity select = categoryMapper.selectByPrimaryKey(parentCategoryEntity1);
        if(select.getIsParent() == 0){
            select.setIsParent(1);
            categoryMapper.updateByPrimaryKeySelective(select);
        }

        //如果不是 那么把他的状态改成1

        categoryMapper.insertSelective(categoryEntity);
        return this.setResultSuccess();
    }


    @Override
    public Result<List<CategoryEntity>> getCategoryByPid(Integer pid) {

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setParentId(pid);
        List<CategoryEntity> list = categoryMapper.select(categoryEntity);

        return this.setResultSuccess(list);
    }

    @Override
    public Result<List<CategoryEntity>> getBranId(Integer brandId) {
        List<CategoryEntity> list =  categoryMapper.getCategoryByBrandId(brandId);

        return this.setResultSuccess(list);
    }

    @Transactional
    @Override
    public Result<JsonObject> deleteById(Integer id) {

            //判断传来的id是否有异常
            if(id != null && id < 0){ return this.setResultError("当前数据不正确"); }

            //通过id查询出子节点的数据
            CategoryEntity categoryEntity = categoryMapper.selectByPrimaryKey(id);

            //判断当前节点是否是父级节点(安全)
            if (categoryEntity.getIsParent()==1) {return this.setResultError("父类节点不可删除");}

            //如果当前分类被品牌绑定的话不能被删除 --> 通过分类id查询中间表是否有数据 true : 当前分类不能被删除 false:继续执行
//        CategoryBrandEntity brandEntity = new CategoryBrandEntity();
//            brandEntity.setCategoryId(id);
//        List<CategoryBrandEntity> select = categoryBrandMapper.select(brandEntity);
//        if(select.size() != 0){
//            this.setResultError("当前数据被绑定 无法被删除");
//        }

        CategoryBrandEntity categoryBrandEntity = categoryBrandMapper.selectByCategoryId(id);

        if (categoryBrandEntity != null){
                this.setResultError("当前数据被绑定 无法被删除");
            }

        //判断当前父类节点除了当前子节点是否还有其他子节点
            Example example = new Example(CategoryEntity.class);
            example.createCriteria().andEqualTo("parentId",categoryEntity.getParentId());
            List<CategoryEntity> list = categoryMapper.selectByExample(example);


            if (list.size()==1){
                //判断通过父类id查询出的子类是否有其他子类 如果 只有一条 那么改掉父类节点的isprent = 0
                CategoryEntity categoryEntity1 = new CategoryEntity();
                categoryEntity1.setId(categoryEntity.getParentId());
                categoryEntity1.setIsParent(0);

             categoryMapper.updateByPrimaryKeySelective(categoryEntity1);
            }
        categoryMapper.deleteByPrimaryKey(id);
        return this.setResultSuccess();
    }



}
