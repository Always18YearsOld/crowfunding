package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author Administrator
 * @Date 2020/5/19
 */

@EnableEurekaServer
@SpringBootApplication
public class CrowdMainClass {
    public static void main(String[] args) {

        SpringApplication.run(CrowdMainClass.class,args);
    }
}
