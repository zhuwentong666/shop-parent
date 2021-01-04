package com.zwt.shop.service;

import com.google.gson.JsonObject;
import com.zwt.shop.base.Result;
import com.zwt.shop.entity.CategoryEntity;
import com.zwt.shop.validate.group.QuanJuErrorOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhuwentong
 */
@Api(tags = "商品分类的接口")
public interface CategoryService {

    @ApiOperation(value = "通过父类id查取所有商品")
    @GetMapping(value="category/list")
    Result<List<CategoryEntity>> getCategoryByPid(Integer pid);

    @ApiOperation(value = "通过brandId查取所有商品")
    @GetMapping(value="category/brand")
    Result<List<CategoryEntity>> getBranId(Integer brandId);


    @ApiOperation(value = "通过id来进行删除操作")
    @DeleteMapping(value="category/deleteById")
    Result<JsonObject> deleteById(Integer id);

    @ApiOperation(value = "修改商品name")
    @PutMapping(value="category/updateById")//requestBody 的作用 接受前台传来的json数据 那么 它还必须是string类型的
    Result<CategoryEntity> updateById(@Validated(QuanJuErrorOperation.Update.class) @RequestBody CategoryEntity categoryEntity);
    @ApiOperation(value = "新增")
    @PostMapping(value="category/save")
    Result<CategoryEntity> save(@Validated(QuanJuErrorOperation.Add.class) @RequestBody CategoryEntity categoryEntity);


}
