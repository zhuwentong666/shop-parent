package com.zwt.shop.mapper;


import shop.entity.StockEntity;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface StockMapper extends Mapper<StockEntity>, DeleteByIdListMapper<StockEntity,Long> {
}
