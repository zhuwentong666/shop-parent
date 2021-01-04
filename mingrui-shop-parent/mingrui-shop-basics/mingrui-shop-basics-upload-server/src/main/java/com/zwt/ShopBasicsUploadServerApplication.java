package com.zwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName ShopBasicsUploadServerApplication
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2020/12/29
 * @Version V1.0
 **/
@SpringBootApplication
@EnableEurekaClient
public class ShopBasicsUploadServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopBasicsUploadServerApplication.class);
    }

}
