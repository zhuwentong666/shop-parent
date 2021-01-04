package com.zwt.shop.service;

import com.google.gson.JsonObject;
import com.zwt.shop.base.Result;
import com.zwt.shop.dto.BrandDTO;
import com.zwt.shop.entity.BrandEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName BrandService
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2020/12/25
 * @Version V1.0
 **/
@Api(tags = "品牌接口")
public interface BrandService {
    @ApiOperation("获取品牌信息")
    @GetMapping(value="brand/getbrandList")
    public Result<List<BrandEntity>> getBrandApi(BrandDTO brandDTO);
    @ApiOperation("删除品牌")
    @DeleteMapping(value="/brand/DeleteId")
    public Result<JsonObject> deleteById(Integer id);
    @ApiOperation("新增")
    @PostMapping(value="brand/save")
    public Result<JsonObject> brandSave(@RequestBody BrandDTO brandDTO);
    @ApiOperation("修改")
    @PutMapping(value="brand/save")
    public Result<JsonObject> brandPut(@RequestBody BrandDTO brandDTO);
}
