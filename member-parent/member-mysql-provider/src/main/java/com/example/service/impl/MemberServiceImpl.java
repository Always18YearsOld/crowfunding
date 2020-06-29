package com.example.service.impl;

import com.example.entity.po.MemberPO;
import com.example.entity.po.MemberPOExample;
import com.example.mapper.MemberPOMapper;
import com.example.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2020/5/19
 */
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberPOMapper memberPOMapper;

    @Override
    public MemberPO getMemberPOByLoginAcct(String loginAcct) {
        MemberPOExample example = new MemberPOExample();
        MemberPOExample.Criteria criteria = example.createCriteria();
        // 封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);
        // 执行查询
        List<MemberPO> memberPOS = memberPOMapper.selectByExample(example);

        if (memberPOS == null || memberPOS.size() == 0){
            return null;
        }
        // 获取结果
        return memberPOS.get(0);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class,readOnly = false)
    @Override
    public void saveMember(MemberPO memberPO) {
        memberPOMapper.insertSelective(memberPO);
    }
}
