package com.baidu.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.document.GoodsDoc;
import com.zwt.shop.base.Result;
import com.zwt.shop.dto.SkuDTO;
import com.zwt.shop.entity.SpuDetailEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

/**
 * @ClassName ShopElasticsearchService
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/3/4
 * @Version V1.0
 **/
@Api(tags = "es接口")
public interface ShopElasticsearchService {
//    @ApiOperation(value = "获取商品测试")
//    @GetMapping(value = "es/goodsInfo")
//    Result<JSONObject> esGoodsInfo();

    //ES数据初始化-->索引创建,映射创建,mysql数据同步
    @ApiOperation(value = "ES商品数据初始化-->索引创建,映射创建,mysql数据同步")
    @GetMapping(value = "es/initGoodsEsData")
    Result<JSONObject> initGoodsEsData();
    @ApiOperation(value = "清空ES中的商品数据")
    @GetMapping(value = "es/clearGoodsEsData")
    Result<JSONObject> clearGoodsEsData();
    @ApiOperation(value = "搜索")
    @GetMapping(value = "es/search")
    Result<List<GoodsDoc>> search(@RequestParam String search,Integer page);
}
