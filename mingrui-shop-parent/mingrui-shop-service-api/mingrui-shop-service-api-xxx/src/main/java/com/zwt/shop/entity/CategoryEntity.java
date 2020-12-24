package com.zwt.shop.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName CategoryEntity
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2020/12/22
 * @Version V1.0
 **/
@Table(name = "tb_category")
@Data
@ApiModel(value = "商品分类实体类")
public class CategoryEntity {
    //定义一个主键
    @Id
    //swagger2 value="显示内容",example="默认值"
    @ApiModelProperty(value = "类目id",example = "1")
    private Integer id;
    @ApiModelProperty(value="类目名称")
    private String name;
    @ApiModelProperty(value = "父类目id,顶级类目填0",example = "1")
    private Integer parentId;

    @ApiModelProperty(value = "是否为父节点，0为否，1为是",example = "1")
    private Integer isParent;

    @ApiModelProperty(value = "排序指数，越小越靠前",example = "1")
    private Integer sort;
}
