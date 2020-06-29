package com.example.handler;

import com.example.api.MySQLRemoteService;

import com.example.constant.Constant;
import com.example.entity.vo.AddressVO;
import com.example.entity.vo.MemberLoginVO;
import com.example.entity.vo.OrderProjectVO;
import com.example.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author Administrator
 * @Date 2020/5/27
 */
@Controller
public class OrderHanlder {
    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    private Logger logger=LoggerFactory.getLogger(OrderHanlder.class);

    @RequestMapping("/save/address")
    public String saveAddress(AddressVO addressVO,HttpSession session){
//        执行地址保存
        ResultEntity<String> resultEntity=mySQLRemoteService.saveAddressRemote(addressVO);
        logger.debug("地址保存处理结果"+resultEntity.getResult());
        OrderProjectVO orderProjectVO = (OrderProjectVO) session.getAttribute("orderProjectVO");

        Integer returnCount = orderProjectVO.getReturnCount();

        return "redirect:http://localhost:80/order/confirm/order/"+returnCount;
    }


    @RequestMapping("/confirm/order/{returnCount}")
    public String showConfirmOderInfo(
            @PathVariable("returnCount") Integer returnCount,
            HttpSession session){
        // 把回报数量传入session
        OrderProjectVO orderProjectVO = (OrderProjectVO) session.getAttribute("orderProjectVO");
        orderProjectVO.setReturnCount(returnCount);
        // 为了在后续操作中保持orderProjectVO数据，存入session
        session.setAttribute("orderProjectVO",orderProjectVO);
        // 获取已登录用户的id
        MemberLoginVO memberLoginVO = (MemberLoginVO) session.getAttribute(Constant.ATTR_NAME_LOGIN_MEMBER);
        Integer memberId = memberLoginVO.getId();
        // 查询目前的收获地址数据
        ResultEntity<List<AddressVO>> resultEntity=mySQLRemoteService.getAddressVORemote(memberId);
        if (ResultEntity.SUCCESS.equals(resultEntity.getResult())){
            List<AddressVO> resultEntityData = resultEntity.getData();
            // 为了在后续操作中保持orderProjectVO数据，存入session
            session.setAttribute("addressVOList",resultEntityData);

        }
        return "confirm_order";
    }
    @RequestMapping("/confirm/return/info/{returnId}")
    public String showReturnConfirmInfo(

            @PathVariable("returnId") Integer returnId,
            HttpSession session){
        ResultEntity<OrderProjectVO> resultEntity =
                mySQLRemoteService.getOrderProjectVORemote(returnId);

        if (ResultEntity.SUCCESS.equals(resultEntity.getResult())){
            OrderProjectVO orderProjectVO = resultEntity.getData();
            // 为了在后续操作中保持orderProjectVO数据，存入session
            session.setAttribute("orderProjectVO",orderProjectVO);

        }

        return "confirm_return";
    }
}
