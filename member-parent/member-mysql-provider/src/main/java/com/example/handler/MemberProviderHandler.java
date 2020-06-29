package com.example.handler;

import com.example.constant.Constant;
import com.example.entity.po.MemberPO;
import com.example.service.api.MemberService;
import com.example.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Administrator
 * @Date 2020/5/19
 */
@RestController
public class MemberProviderHandler {
    @Autowired
    private MemberService memberService;
    @RequestMapping("/save/member/remote")
    public ResultEntity<String> saveMember(@RequestBody MemberPO memberPO){

        try {
            memberService.saveMember(memberPO);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException){
                return ResultEntity.failed(Constant.MESSAGE_LOGIN_ACCT_ALREADY_IN);
            }
            return ResultEntity.failed(e.getMessage());
        }

        }

        @RequestMapping("/get/member/by/login/acct/remote")
    public ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginAcct") String loginAcct){
        try {
            MemberPO memberPO = memberService.getMemberPOByLoginAcct(loginAcct);
            return ResultEntity.sucessWithData(memberPO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }

    }

}
