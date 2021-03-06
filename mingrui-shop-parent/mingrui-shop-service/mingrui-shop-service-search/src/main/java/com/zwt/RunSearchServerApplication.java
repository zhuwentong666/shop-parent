package com.zwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName RunSearchServerApplication
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2021/3/4
 * @Version V1.0
 **/
//加上这个的作用就是取消他的连接池
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients
public class RunSearchServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RunSearchServerApplication.class);
    }
}
