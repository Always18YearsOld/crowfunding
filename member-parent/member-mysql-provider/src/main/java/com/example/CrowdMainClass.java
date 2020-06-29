package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author Administrator
 * @Date 2020/5/19
 */

@MapperScan("com.example.mapper")
@SpringBootApplication
public class CrowdMainClass {
    public static void main(String[] args) {

        SpringApplication.run(CrowdMainClass.class,args);
    }
}
