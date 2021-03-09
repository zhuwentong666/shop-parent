package com.zwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName RunTemplateServerApplication
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/3/8
 * @Version V1.0
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients
public class RunTemplateServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RunTemplateServerApplication.class);
    }
}
