package com.zwt.shop.feign;

import com.zwt.shop.service.SpuService;
import org.springframework.cloud.openfeign.FeignClient;
//指向调用的模块
@FeignClient(contextId = "SpuService",value = "xxx-server")
public interface GoodsFeign extends SpuService {

}
