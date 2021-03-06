package com.zwt.shop.feign;

import com.zwt.shop.service.BrandService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName BrandFeign
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/3/6
 * @Version V1.0
 **/
@FeignClient(contextId = "BrandFeign",value = "xxx-server")
public interface BrandFeign extends BrandService {
}
