package com.zwt.shop.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName StockEntity
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/1/7
 * @Version V1.0
 **/
@Table(name = "tb_stock")
@Data
public class StockEntity {
    @Id
    private Long skuId;
    private Integer seckillStock;
    private Integer seckillTotal;
    private Integer stock;
}
