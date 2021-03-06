package com.zwt.shop.service;

import com.google.gson.JsonObject;
import com.zwt.shop.base.Result;
import com.zwt.shop.dto.SkuDTO;
import com.zwt.shop.dto.SpuDTO;
import com.zwt.shop.dto.SpuDetailDTO;
import com.zwt.shop.entity.SpuEntity;
import com.zwt.shop.validate.group.QuanJuErrorOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品列表接口")
public interface SpuService {

    @ApiOperation(value = "通过spuId查询sku和库存")
    @GetMapping(value = "goods/skuBySpuIdStock")
    Result<List<SkuDTO>> skuBySpuIdStock(@RequestParam Integer spuId);

    @ApiOperation(value = "通过spuId回显商品详情detail")
    @GetMapping(value = "goods/spuByIdGetDetail")
    Result<JsonObject> spuByIdGetDetail(@RequestParam Integer spuId);

    @ApiOperation(value = "通过spuId回显商品详情detail")
    @GetMapping(value = "goods/spuGetDetail")
    Result<SpuDetailDTO> spuGetDetail(@RequestParam Integer spuId);

    @ApiOperation(value = "商品列表查询")
    @GetMapping(value = "spu/spuList")
    Result<List<SpuDTO>> SpuList(@SpringQueryMap SpuDTO spuDTO);

    @PostMapping(value="goods/save")
    @ApiOperation(value="商品列表新增")
    Result<List<JsonObject>> goodsSave(@Validated(QuanJuErrorOperation.Add.class)@RequestBody SpuDTO spuDTO);

    @PutMapping(value="goods/save")
    @ApiOperation(value="商品列表修改")
    Result<List<JsonObject>> goodsEdit(@Validated(QuanJuErrorOperation.Update.class)@RequestBody SpuDTO spuDTO);

    @DeleteMapping(value="goods/delete")
    @ApiOperation(value="商品删除")
    Result<JsonObject> goodsDelete(Integer spuId);

    @PutMapping(value="goods/xiajia")
    @ApiOperation(value="商品下架")
    Result<JsonObject> goodsXiajia(@RequestBody SpuDTO spuDTO);
}
