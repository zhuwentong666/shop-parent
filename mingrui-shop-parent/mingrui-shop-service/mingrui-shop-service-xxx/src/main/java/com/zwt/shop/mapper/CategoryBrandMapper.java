package com.zwt.shop.mapper;

import com.zwt.shop.entity.CategoryBrandEntity;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

public interface CategoryBrandMapper extends Mapper<CategoryBrandEntity>, InsertListMapper<CategoryBrandEntity> {

    @Select(value="select * from tb_category_brand where category_id =#{id}")
    List<CategoryBrandEntity> selectByCategoryId(Integer id);

}
