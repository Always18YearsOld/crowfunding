package com.example.service.impl;

import com.example.entity.po.AddressPO;
import com.example.entity.po.AddressPOExample;
import com.example.entity.po.OrderPO;
import com.example.entity.po.OrderProjectPO;
import com.example.entity.vo.AddressVO;
import com.example.entity.vo.OrderProjectVO;
import com.example.entity.vo.OrderVO;
import com.example.mapper.AddressPOMapper;
import com.example.mapper.OrderPOMapper;
import com.example.mapper.OrderProjectPOMapper;
import com.example.service.api.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Administrator
 * @Date 2020/5/27
 */
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderProjectPOMapper orderProjectPOMapper;
    @Autowired
    private OrderPOMapper orderPOMapper;
    @Autowired
    private AddressPOMapper addressPOMapper;

    @Override
    public OrderProjectVO getOrderProjectVO( Integer returnId) {
        return orderProjectPOMapper.selectOrderProjectVO(returnId);
    }

    @Override
    public List<AddressVO> getAddressVOList(Integer memberId) {

        AddressPOExample addressPOExample = new AddressPOExample();
        addressPOExample.createCriteria().andMemberIdEqualTo(memberId);
        List<AddressPO> addressPOList = addressPOMapper.selectByExample(addressPOExample);;
        List<AddressVO> addressVOList = new ArrayList<>();
        for (AddressPO addressPO : addressPOList) {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(addressPO,addressVO);
            addressVOList.add(addressVO);
        }


        return addressVOList;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void saveAddress(AddressVO addressVO) {

        AddressPO addressPO = new AddressPO();

        BeanUtils.copyProperties(addressVO,addressPO);

        addressPOMapper.insert(addressPO);
    }

    // 保存支付宝订单信息
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void saveOrder(OrderVO orderVO) {
        OrderPO orderPO = new OrderPO();
        BeanUtils.copyProperties(orderVO,orderPO);
        OrderProjectPO orderProjectPO = new OrderProjectPO();
        BeanUtils.copyProperties(orderVO.getOrderProjectVO(),orderProjectPO);
        // 保存orderPO并生成orderProjectPO的OrderId外键
        orderPOMapper.insert(orderPO);
        // 获取id  在OrderPOMapper.xml进行设置
        // <insert id="insert" parameterType="com.example.entity.po.OrderPO"
        // useGeneratedKeys="true" keyProperty="id">

        Integer id = orderPO.getId();
        // 作为orderProjectPO外键
        orderProjectPO.setOrderId(id);

        orderProjectPOMapper.insert(orderProjectPO);
    }
}
