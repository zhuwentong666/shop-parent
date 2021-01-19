package com.zwt.shop.dto;

import com.zwt.shop.base.BaseDTO;
import com.zwt.shop.entity.SkuEntity;
import com.zwt.shop.entity.SpuDetailEntity;
import com.zwt.shop.validate.group.QuanJuErrorOperation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SpuDTO
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/1/5
 * @Version V1.0
 **/
@Data
@ApiModel(value = "商品列表")
public class SpuDTO extends BaseDTO {
    @ApiModelProperty(value = "主键",example = "1")
    @NotNull(message = "主键不得为空",groups = {QuanJuErrorOperation.Update.class})
    private Integer id;
    @ApiModelProperty(value = "标题")
    @NotEmpty(message = "标题不得为空",groups = {QuanJuErrorOperation.Add.class,QuanJuErrorOperation.Update.class})
    private String title;
    @ApiModelProperty(value = "子标题")
    @NotEmpty(message = "子标题不得为空",groups = {QuanJuErrorOperation.Add.class,QuanJuErrorOperation.Update.class})
    private String sub_title;
    @NotNull(message = "1级类目id",groups = {QuanJuErrorOperation.Update.class})
    @ApiModelProperty(value = "1级类目id",example = "1")
    private Integer cid1;
    @NotNull(message = "2级类目id",groups = {QuanJuErrorOperation.Update.class})
    @ApiModelProperty(value = "2级类目id",example = "1")
    private Integer cid2;
    @ApiModelProperty(value = "3级类目id",example = "1")
    @NotNull(message = "3级类目id",groups = {QuanJuErrorOperation.Update.class})
    private Integer cid3;
    @ApiModelProperty(value = "商品所属品牌id",example = "1")
    @NotNull(message = "商品所属品牌id",groups = {QuanJuErrorOperation.Update.class})
    private Integer brand_id;
    @ApiModelProperty(value = "是否上架，0下架，1上架",example = "1")
    @NotNull(message = "是否上架",groups = {QuanJuErrorOperation.Update.class})
    private Integer saleable;
    @ApiModelProperty(value = "是否有效，0已删除，1有效",example = "1")
    @NotNull(message = "是否有效",groups = {QuanJuErrorOperation.Update.class})
    private Integer valid;
    @ApiModelProperty(value = "添加时间")
    private Date create_time;
    @ApiModelProperty(value = "最后修改时间")
    private Date last_update_time;

    private String categoryName;

    private String brandName;

    private List<SkuDTO> skus;

    private SpuDetailEntity spuDetail;


}
