package com.example.mvc.handler;

import com.example.entity.Admin;
import com.example.service.api.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class TestHandler {
    @Autowired
    private AdminService adminService;

    @RequestMapping("send/array/three.html")
    @ResponseBody
    public String testReceiveArrayThree(@RequestBody List<Integer> array){
        Logger logger = LoggerFactory.getLogger(TestHandler.class);
        for (Integer number : array) {
            logger.info("number="+number);
        }

        return "success";
    }
    @RequestMapping("send/array.html")
    @ResponseBody
    public String testReceiveArrayOne(@RequestParam("array[]") List<Integer> array){
        for (Integer number : array) {
            System.out.println("number="+number);
        }
        return "success";
    }
    @RequestMapping("/test/ssm.html")
    public String testSsm(ModelMap modelMap){
        List<Admin> adminList = adminService.getAll();
        modelMap.addAttribute("adminList", adminList);
        System.out.println(10/0);
        return "target";
    }
}
