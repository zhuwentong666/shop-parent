package shop.service;

import com.alibaba.fastjson.JSONObject;

import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.mr.zwt.shop.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import shop.dto.BrandDTO;
import shop.entity.BrandEntity;

import java.util.List;

@Api(tags = "品牌接口")
public interface BrandService {

    @GetMapping(value="brand/list")
    @ApiOperation(value = "查询品牌列表")
    Result<PageInfo<BrandEntity>>getBrandInfo(BrandDTO brandDTO);

    @PostMapping(value = "brand/save")
    @ApiOperation(value = "新增品牌")
    Result<JSONObject> saveBrand(@RequestBody BrandDTO brandDTO);

    @PutMapping(value = "brand/save")
    @ApiOperation(value = "修改品牌")
    Result<JSONObject>editBrand(@RequestBody BrandDTO brandDTO);

    @DeleteMapping(value="brand/delete")
    @ApiOperation(value="删除品牌")
    Result<JSONObject>deleteBrand(Integer id);

    @ApiOperation(value = "根据分类查询品牌")
    @GetMapping(value = "/brand/getBrandInfoCategoryById")
    Result<List<BrandEntity>> getBrandInfoByCategoryId(Integer cid);
}
