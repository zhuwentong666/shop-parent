package com.zwt.shop.service;

import com.google.gson.JsonObject;
import com.zwt.shop.base.Result;
import com.zwt.shop.entity.CategoryEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author zhuwentong
 */
@Api(tags = "商品分类的接口")
public interface CategoryService {

    @ApiOperation(value = "通过父类id查取所有商品")
    @GetMapping(value="category/list")
    Result<List<CategoryEntity>> getCategoryByPid(Integer pid);

    @ApiOperation(value = "通过id来进行删除操作")
    @DeleteMapping(value="category/deleteById")
    Result<JsonObject> deleteById(Integer id);

    @ApiOperation(value = "修改数据")
    @PutMapping(value="category/updateById")//requestBody 的作用 接受前台传来的json数据 那么 它还必须是string类型的
    Result<CategoryEntity> updateById(@RequestBody CategoryEntity categoryEntity);

}
