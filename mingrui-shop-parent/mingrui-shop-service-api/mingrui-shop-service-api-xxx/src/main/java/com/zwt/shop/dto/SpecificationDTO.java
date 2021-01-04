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
 * @ClassName SpecificationDTO
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/1/4
 * @Version V1.0
 **/
@Data
@ApiModel(value = "规格组")
public class SpecificationDTO extends BaseDTO {
    @ApiModelProperty(name = "主键",example = "1")
    @NotEmpty(message = "规格组名称不能为空", groups = {QuanJuErrorOperation.Add.class})
    private Integer id;
    @ApiModelProperty(name = "商品分类id，一个分类下有多个规格组",example = "1")
    @NotNull(message = "类型id不能为空", groups = {QuanJuErrorOperation.Add.class})
    private Integer cid;
    @ApiModelProperty(name = "规格组的名称")
    @NotEmpty(message = "规格组名称不能为空", groups = {QuanJuErrorOperation.Add.class})
    private String name;

}
