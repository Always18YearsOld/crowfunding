package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Administrator
 * @Date 2020/5/21
 */
@Configuration
public class CrowdWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // view-controller是project-consumer内部定义的，所以这是一盒不经过Zuul访问的地址，不加路由定义的前缀
        registry.addViewController("/agree/protocol/page").setViewName("project-agree");
        registry.addViewController("/launch/project/page").setViewName("project-launch");
        registry.addViewController("/return/info/page").setViewName("project-return");
        registry.addViewController("/create/confirm/page").setViewName("project-confirm");
        registry.addViewController("/create/success").setViewName("project-success");
        registry.addViewController("/show/detail").setViewName("project-detail");


    }


}
