package shop.service;

import com.alibaba.fastjson.JSONObject;

import com.mr.zwt.shop.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import shop.dto.SpecGroupDTO;
import shop.dto.SpecParamDTO;
import shop.entity.SpecGroupEntity;
import shop.entity.SpecParamEntity;

import java.util.List;

@Api(value = "规格组接口")
public interface SpecificationService {

    @ApiOperation("规格组查询")
    @GetMapping("specgroup/getSpecGroupInfo")
    Result<List<SpecGroupEntity>>getSpecGroupInfo(SpecGroupDTO specGroupDTO);

    @ApiOperation("规格组新增")
    @PostMapping("specgroup/save")
    Result<JSONObject> saveSpecGroupInfo(@RequestBody SpecGroupDTO specGroupDTO);

    @ApiOperation("修改规则数据")
    @PutMapping("specgroup/save")
    Result<JSONObject> editSpecGroupInfo(@RequestBody  SpecGroupDTO specGroupDTO);

    @ApiOperation("删除规则数据")
    @DeleteMapping("specgroup/delete")
    Result<JSONObject> deleteSpecGroupInfo(Integer id);

    @ApiOperation("查询规格参数")
    @GetMapping("specparam/getSpecParamInfo")
    Result<List<SpecParamEntity>>getSpecParamInfo(SpecParamDTO specParamDTO);

    @ApiOperation("新增规格参数")
    @PostMapping("specparam/saveSpecParamInfo")
    Result<JSONObject>saveSpecParamInfo(@RequestBody SpecParamDTO specParamDTO);

    @ApiOperation("修改规格参数")
    @PutMapping("specparam/saveSpecParamInfo")
    Result<JSONObject>editSpecParamInfo(@RequestBody SpecParamDTO specParamDTO);

    @ApiOperation("删除规格参数")
    @DeleteMapping("specparam/delete")
    Result<JSONObject>deleteSpecParamInfo(Integer id);
}
