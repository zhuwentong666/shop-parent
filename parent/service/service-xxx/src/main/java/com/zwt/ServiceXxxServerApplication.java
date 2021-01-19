package com.zwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName ServiceXxxServerApplication
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/1/19
 * @Version V1.0
 **/
@EnableEurekaClient
@SpringBootApplication
public class ServiceXxxServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceXxxServerApplication.class);
    }
}
