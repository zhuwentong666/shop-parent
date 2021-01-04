package com.zwt.shop.dto;

import com.zwt.shop.base.BaseDTO;
import com.zwt.shop.validate.group.QuanJuErrorOperation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName BrandEntity
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2020/12/25
 * @Version V1.0
 **/
@Data
@ApiModel("品牌Dto")
public class BrandDTO extends BaseDTO {
    @Id
    @ApiModelProperty(value = "品牌id",example = "1")
    @NotNull(message = "主键不得为空",groups = {QuanJuErrorOperation.Update.class})
    private Integer id;
    @ApiModelProperty(value="品牌name")
    @NotEmpty(message = "品牌名称不得为空",groups = {QuanJuErrorOperation.Add.class,QuanJuErrorOperation.Update.class})
    private String name;
    @ApiModelProperty(value = "品牌照片")
    private String image;
    @ApiModelProperty(value = "排序")
    private Character letter;

    @ApiModelProperty(value = "品牌分类信息")
    private String categories;
}
