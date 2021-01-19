package com.zwt.shop.dto;

import com.zwt.shop.base.BaseDTO;
import com.zwt.shop.validate.group.QuanJuErrorOperation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

/**
 * @ClassName SpecParamDTO
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/1/4
 * @Version V1.0
 **/
@ApiModel(value = "规格参数")
@Data
public class SpecParamDTO extends BaseDTO {
    @ApiModelProperty(name = "规格主键",example = "1")
    @NotEmpty(message = "规格主键", groups = {QuanJuErrorOperation.Add.class})
    private Integer id;
    @ApiModelProperty(name = "商品分类id",example = "1")
    @NotEmpty(message = "商品分类id", groups = {QuanJuErrorOperation.Add.class})
    private Integer cid;
    @NotEmpty(message = "规格id", groups = {QuanJuErrorOperation.Add.class})
    private Integer groupId;
    @NotEmpty(message = "参数名不得为空",groups = {QuanJuErrorOperation.Add.class,QuanJuErrorOperation.Update.class})
    @ApiModelProperty(name="参数名")
    private String name;
    @ApiModelProperty(name = "是否是数字类型参数，true或false")
    @Column(name = "`numeric`")
    private Boolean numeric;
    @ApiModelProperty(name = "数字类型参数的单位，非数字类型可以为空")
    private String unit;
    @ApiModelProperty(name = "是否是sku通用属性，true或false")
    private Boolean generic;
    @ApiModelProperty(name = "是否用于搜索过滤，true或false")
    private Boolean searching;
    @ApiModelProperty(name = "数值类型参数，如果需要搜索，则添加分段间隔值，如CPU频率间隔：0.5-1.0")
    private String segments;

}
