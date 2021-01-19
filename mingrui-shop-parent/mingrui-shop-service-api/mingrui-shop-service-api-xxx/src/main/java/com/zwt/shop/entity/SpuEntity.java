package com.zwt.shop.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName SpuEntity
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/1/5
 * @Version V1.0
 **/
@Data
@Table(name = "tb_spu")
public class SpuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String sub_title;

    private Integer cid1;

    private Integer cid2;

    private Integer cid3;

    private Integer brand_id;

    private Integer saleable;

    private Integer valid;

    private Date create_time;

    private Date last_update_time;
}
