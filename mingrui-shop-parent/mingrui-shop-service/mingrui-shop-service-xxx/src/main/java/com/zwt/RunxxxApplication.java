package com.zwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName RunxxxApplication
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2020/12/22
 * @Version V1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.zwt.shop.mapper")
public class RunxxxApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunxxxApplication.class);
    }
}
