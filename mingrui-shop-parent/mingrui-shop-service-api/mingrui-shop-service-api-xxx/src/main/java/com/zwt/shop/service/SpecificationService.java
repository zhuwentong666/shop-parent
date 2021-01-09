package com.zwt.shop.service;

import com.google.gson.JsonObject;
import com.zwt.shop.base.Result;
import com.zwt.shop.dto.SpecParamDTO;
import com.zwt.shop.dto.SpecificationDTO;
import com.zwt.shop.entity.SpecParamEntity;
import com.zwt.shop.entity.SpecificationEntity;
import com.zwt.shop.validate.group.QuanJuErrorOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "规格参数")
public interface SpecificationService {

    @ApiOperation(value="通过条件查询查出数据")
    @GetMapping("speci/list")
    Result<List<SpecificationEntity>> specificationList(SpecificationDTO specificationDTO);

    @ApiOperation(value="新增数据")
    @PostMapping("speci/save")
    Result<List<JsonObject>> speciSave(@Validated(QuanJuErrorOperation.Add.class)@RequestBody SpecificationDTO specificationDTO);

    @ApiOperation(value="修改数据")
    @PutMapping("speci/save")
    Result<List<JsonObject>> speciEdit(@Validated(QuanJuErrorOperation.Update.class)@RequestBody SpecificationDTO specificationDTO);

    @ApiOperation(value="删除数据")
    @DeleteMapping("spec/group/{id}")
    Result<List<JsonObject>> deleteById(@PathVariable Integer id);


    @ApiOperation(value = "查询规格参数")
    @GetMapping(value = "spec/list")
    Result<List<SpecParamEntity>> specList(SpecParamDTO specParamDTO);
    @ApiOperation(value="新增规格参数")
    @PostMapping(value="spec/param")
    Result<List<JsonObject>> specSave(@Validated(QuanJuErrorOperation.Add.class)@RequestBody SpecParamDTO specParamDTO);

    @ApiOperation(value="新增规格参数")
    @PutMapping(value="spec/param")
    Result<List<JsonObject>> specEdit(@Validated(QuanJuErrorOperation.Update.class)@RequestBody SpecParamDTO specParamDTO);

    @ApiOperation(value="删除规格参数")
    @DeleteMapping(value="spec/delete")
    Result<List<JsonObject>> specDelete(Integer id);


}
