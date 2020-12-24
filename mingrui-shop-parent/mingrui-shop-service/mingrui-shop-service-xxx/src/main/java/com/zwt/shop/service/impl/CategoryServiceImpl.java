package com.zwt.shop.service.impl;

import com.google.gson.JsonObject;
import com.zwt.shop.base.BaseApiService;
import com.zwt.shop.base.Result;
import com.zwt.shop.entity.CategoryEntity;
import com.zwt.shop.mapper.CategoryMapper;
import com.zwt.shop.service.CategoryService;
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


    @Override
    public Result<List<CategoryEntity>> getCategoryByPid(Integer pid) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setParentId(pid);
        List<CategoryEntity> list = categoryMapper.select(categoryEntity);

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
