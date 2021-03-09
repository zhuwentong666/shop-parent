package com.zwt.shop.service;

import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.zwt.shop.base.Result;
import com.zwt.shop.dto.BrandDTO;
import com.zwt.shop.entity.BrandEntity;
import com.zwt.shop.validate.group.QuanJuErrorOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.validation.annotation.Validated;
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
    @GetMapping(value = "brand/list")
    @ApiOperation(value = "查询品牌列表")
    Result<PageInfo<BrandEntity>> getBrandInfo(@SpringQueryMap BrandDTO brandDTO);

    @ApiOperation("获取品牌信息")
    @GetMapping(value="brand/getbrandList")
    public Result<List<BrandEntity>> getBrandApi(@SpringQueryMap BrandDTO brandDTO);
    @ApiOperation("通过categoryid获取品牌信息")
    @GetMapping(value="/brand/getBrandIdByCategoryId")
    public Result<List<BrandEntity>> getBrandIdByCategoryId(@RequestParam Integer cid);

    @ApiOperation("删除品牌")
    @DeleteMapping(value="/brand/DeleteId")
    public Result<JsonObject> deleteById(Integer id);
    @ApiOperation("新增")
    @PostMapping(value="brand/save")
    public Result<JsonObject> brandSave(@Validated(QuanJuErrorOperation.Add.class)@RequestBody BrandDTO brandDTO);
    @ApiOperation("修改")
    @PutMapping(value="brand/save")
    public Result<JsonObject> brandPut(@Validated(QuanJuErrorOperation.Update.class)@RequestBody BrandDTO brandDTO);

    @ApiOperation(value="通过品牌id集合获取品牌")
    @GetMapping(value = "brand/getBrandByIds")
    Result<List<BrandEntity>> getBrandByIds(@RequestParam String brandIds);
}
