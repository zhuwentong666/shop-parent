package com.zwt.shop.dto;

import com.zwt.shop.validate.group.QuanJuErrorOperation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName SpuDetailEntity
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/1/7
 * @Version V1.0
 **/
@Data
@ApiModel("库存表")
public class SpuDetailDTO {

    @Id
    @ApiModelProperty(value = "spu主键",example = "1")
    @NotNull(message = "spu主键不得为空",groups = {QuanJuErrorOperation.Add.class})
    private Integer spuId;
    @ApiModelProperty(value = "spu主键",example = "1")
    @NotEmpty(message = "description不得为空",groups = {QuanJuErrorOperation.Add.class,QuanJuErrorOperation.Update.class})
    private String description;
    @ApiModelProperty(value = "通用规格参数数据")
    @NotEmpty(message = "通用规格参数数据不得为空",groups = {QuanJuErrorOperation.Add.class,QuanJuErrorOperation.Update.class})
    private String genericSpec;
    @NotEmpty(message = "特有规格参数及可选值信息不得为空",groups = {QuanJuErrorOperation.Add.class,QuanJuErrorOperation.Update.class})
    @ApiModelProperty(value = "特有规格参数及可选值信息，json格式")
    private String specialSpec;
    @NotEmpty(message = "包装清单不得为空",groups = {QuanJuErrorOperation.Add.class,QuanJuErrorOperation.Update.class})
    @ApiModelProperty(value = "包装清单")
    private String packingList;
    @NotEmpty(message = "售后服务不得为空",groups = {QuanJuErrorOperation.Add.class,QuanJuErrorOperation.Update.class})
    @ApiModelProperty(value = "售后服务")
    private String afterService;

}
