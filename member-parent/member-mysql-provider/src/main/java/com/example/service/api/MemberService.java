package com.example.service.api;

import com.example.entity.po.MemberPO;

/**
 * @Author Administrator
 * @Date 2020/5/19
 */
public interface MemberService {
    MemberPO getMemberPOByLoginAcct(String loginAcct);

    void saveMember(MemberPO memberPO);
}
