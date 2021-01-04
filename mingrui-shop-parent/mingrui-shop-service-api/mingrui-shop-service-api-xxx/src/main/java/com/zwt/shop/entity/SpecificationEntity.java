package com.zwt.shop.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName SpecificationEntity
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/1/4
 * @Version V1.0
 **/
@Table(name = "tb_spec_group")
@Data
public class SpecificationEntity {

    @Id
    private Integer id;

    private Integer cid;


    private String name;
}
