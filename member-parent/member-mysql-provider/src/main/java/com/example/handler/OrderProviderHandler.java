package com.example.handler;

import com.example.entity.vo.AddressVO;
import com.example.entity.vo.OrderProjectVO;
import com.example.entity.vo.OrderVO;
import com.example.service.api.OrderService;
import com.example.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2020/5/27
 */
@RestController
public class OrderProviderHandler {
    @Autowired
    private OrderService orderService;

    @RequestMapping("save/order/remote")
    ResultEntity<String> saveOrderRemote(@RequestBody OrderVO orderVO){
        try {
            orderService.saveOrder(orderVO);
            return ResultEntity.successWithoutData();

        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());

        }
    }


    @RequestMapping("save/address/remote")
    ResultEntity<String> saveAddressRemote(
            @RequestBody AddressVO addressVO){
        try {
            orderService.saveAddress( addressVO);
            return ResultEntity.successWithoutData();

        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());

        }


    }


    @RequestMapping("get/address/vo/remote")
    ResultEntity<List<AddressVO>> getAddressVORemote(
            @RequestParam("memberId") Integer memberId){

        try {
            List<AddressVO> addressVOList = orderService.getAddressVOList(memberId);
            return ResultEntity.sucessWithData(addressVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }



    }

    @RequestMapping("get/order/project/vo/remote")
    ResultEntity<OrderProjectVO> getOrderProjectVORemote(

            @RequestParam("returnId") Integer returnId){
        try {
            OrderProjectVO orderProjectVO=orderService.getOrderProjectVO(returnId);
            return ResultEntity.sucessWithData(orderProjectVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }

    }
}
