package com.example.session.handler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Author Administrator
 * @Date 2020/5/23
 */
@RestController
public class HelloHandler {
    @RequestMapping("/test/spring/session/save")
    public String testSession(HttpSession session){
        session.setAttribute("king","hello-king");
        return "数据存入SESSION域";
    }
}
