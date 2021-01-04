package com.zwt.shop.service;

import com.google.gson.JsonObject;
import com.zwt.shop.base.Result;
import com.zwt.shop.dto.SpecificationDTO;
import com.zwt.shop.entity.SpecificationEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "规格参数")
public interface SpecificationService {

    @ApiOperation(value="通过条件查询查出数据")
    @GetMapping("speci/list")
    Result<List<SpecificationEntity>> specificationList(SpecificationDTO specificationDTO);

    @ApiOperation(value="新增数据")
    @PostMapping("speci/save")
    Result<List<JsonObject>> speciSave(@RequestBody SpecificationDTO specificationDTO);

    @ApiOperation(value="修改数据")
    @PutMapping("speci/save")
    Result<List<JsonObject>> speciEdit(@RequestBody SpecificationDTO specificationDTO);

    @ApiOperation(value="删除数据")
    @DeleteMapping("spec/group/{id}")
    Result<List<JsonObject>> deleteById(@PathVariable Integer id);


}