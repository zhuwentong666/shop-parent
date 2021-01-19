package com.zwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName EureKaServerApplication
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/1/19
 * @Version V1.0
 **/
@EnableEurekaServer
@SpringBootApplication
public class EureKaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EureKaServerApplication.class);
    }
}
