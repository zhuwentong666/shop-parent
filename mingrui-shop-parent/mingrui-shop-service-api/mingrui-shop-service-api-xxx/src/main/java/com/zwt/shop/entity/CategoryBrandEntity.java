package com.zwt.shop.entity;

import lombok.Data;
import lombok.Value;

import javax.persistence.Table;

/**
 * @ClassName CategoryBrandEntity
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2020/12/28
 * @Version V1.0
 **/
@Data
@Table(name="tb_category_brand")
public class CategoryBrandEntity {

    private Integer categoryId;
    private Integer brandId;

}
