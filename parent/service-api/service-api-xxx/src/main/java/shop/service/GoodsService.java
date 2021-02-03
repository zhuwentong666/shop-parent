package shop.service;


import com.alibaba.fastjson.JSONObject;
import com.mr.zwt.shop.base.Result;
import com.mr.zwt.shop.group.MingruiOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import shop.dto.SkuDTO;
import shop.dto.SpuDTO;
import shop.dto.SpuDetailDTO;
import shop.entity.SpuEntity;

import java.util.List;

@Api(tags = "商品接口")
public interface GoodsService {
    @ApiOperation(value = "获取spu信息")
    @GetMapping(value = "/goods/getSpuInfo")
    Result<List<SpuEntity>>getSpuInfo(SpuDTO spuDTO);

    @ApiOperation(value = "商新新增")
    @PostMapping(value = "goods/save")
    Result<JSONObject> saveGoods(@Validated({MingruiOperation.Add.class}) @RequestBody SpuDTO spuDTO);

    @ApiOperation(value = "获取spu信息")
    @GetMapping(value = "goods/getSpuDetailByIdSpu")
    Result<SpuDetailDTO> getSpuDetailByIdSpu(Integer spuId);

    @ApiOperation(value = "获取sku信息")
    @GetMapping(value = "goods/getSkuBySpuId")
    Result<List<SkuDTO>> getSkuBySpuId(Integer spuId);

    @ApiOperation(value = "商品修改")
    @PutMapping(value = "goods/save")
    Result<JSONObject>editGoods(@Validated({MingruiOperation.Update.class}) @RequestBody SpuDTO spuDTO);

    @ApiOperation(value = "删除商品")
    @DeleteMapping(value = "goods/delete")
    Result<JSONObject> deleteGoods(Integer spuId);

    @ApiOperation(value = "商品上下架")
    @PutMapping(value = "goods/downOrUp")
    Result<JSONObject> downOrUp(@RequestBody SpuDTO spuDTO);
}
