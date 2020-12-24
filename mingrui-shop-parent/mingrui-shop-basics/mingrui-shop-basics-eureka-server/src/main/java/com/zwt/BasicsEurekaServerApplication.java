package com.zwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName BasicsEurekaServerApplication
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2020/12/22
 * @Version V1.0
 **/
@SpringBootApplication
@EnableEurekaServer
public class BasicsEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicsEurekaServerApplication.class);
    }
}
