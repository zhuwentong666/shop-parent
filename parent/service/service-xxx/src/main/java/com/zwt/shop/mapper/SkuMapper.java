package com.zwt.shop.mapper;


import org.apache.ibatis.annotations.Select;
import shop.dto.SkuDTO;
import shop.entity.SkuEntity;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

public interface SkuMapper extends Mapper<SkuEntity>, InsertListMapper<SkuEntity>, DeleteByIdListMapper<SkuEntity,Long> {
    @Select(value="select k.*,stock from tb_sku k, tb_stock t where k.id=t.sku_id and k.spu_id =#{spuId}")
    List<SkuDTO> selectSkuAndStockSpuId(Integer spuId);
}
