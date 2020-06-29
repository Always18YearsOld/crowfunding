package com.example.handler;

import com.example.api.MySQLRemoteService;
import com.example.constant.Constant;
import com.example.entity.vo.PortalTypeVO;
import com.example.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2020/5/19
 */
@Controller
public class PortalHandler {
    @Autowired
    private MySQLRemoteService mySQLRemoteService;
    @RequestMapping("/")
    public String showPortalPage(Model model){
        ResultEntity<List<PortalTypeVO>> resultEntity = mySQLRemoteService.getPortalTypeProjectDataRemote();
        String result = resultEntity.getResult();
        if (ResultEntity.SUCCESS.equals(result)){
            List<PortalTypeVO> list = resultEntity.getData();
                model.addAttribute(Constant.ATTR_NAME_PORTAL_DATDA,list);

        }
        return "portal";
    }

}
