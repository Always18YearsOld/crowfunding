package com.example.service.api;

import com.example.entity.vo.AddressVO;
import com.example.entity.vo.OrderProjectVO;
import com.example.entity.vo.OrderVO;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2020/5/27
 */
public interface OrderService {
    OrderProjectVO getOrderProjectVO( Integer returnId);

    List<AddressVO> getAddressVOList(Integer memberId);

    void saveAddress(AddressVO addressVO);

    void saveOrder(OrderVO orderVO);
}
