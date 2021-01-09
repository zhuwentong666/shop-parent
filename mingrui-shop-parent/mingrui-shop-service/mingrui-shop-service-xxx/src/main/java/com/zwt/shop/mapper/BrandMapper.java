package com.zwt.shop.mapper;

import com.zwt.shop.entity.BrandEntity;
import com.zwt.shop.entity.CategoryBrandEntity;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName BrandMapper
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2020/12/25
 * @Version V1.0
 **/
public interface BrandMapper extends Mapper<BrandEntity> {

    @Select(value="select b.id,b.`name` from tb_brand b WHERE b.id in(select cb.brand_id from tb_category_brand cb where cb.category_id=#{cid})")
    List<BrandEntity> getBrandIdByCategoryId(Integer cid);
}
