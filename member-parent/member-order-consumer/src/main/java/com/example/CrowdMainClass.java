package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author Administrator
 * @Date 2020/5/19
 */
//启用Feign客户端功能
@EnableFeignClients
@EnableDiscoveryClient//当前版本可不写
@SpringBootApplication
public class CrowdMainClass {
    public static void main(String[] args) {

        SpringApplication.run(CrowdMainClass.class,args);
    }
}
