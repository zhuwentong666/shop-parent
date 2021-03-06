package com.zwt.shop.feign;

import com.zwt.shop.service.CategoryService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(contextId = "CategoryFeign",value = "xxx-server")
public interface CategoryFeign extends CategoryService {
}
