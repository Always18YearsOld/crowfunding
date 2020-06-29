package com.example.handler;

import com.example.api.MySQLRemoteService;
import com.example.api.RedisRemoteService;
import com.example.config.ShortMessageProperties;
import com.example.constant.Constant;
import com.example.entity.po.MemberPO;
import com.example.entity.vo.MemberLoginVO;
import com.example.entity.vo.MemberVO;
import com.example.util.CrowdUtil;
import com.example.util.ResultEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author Administrator
 * @Date 2020/5/21
 */

@Controller
public class MemberHandler {
    @Autowired
    private ShortMessageProperties shortMessageProperties;

    @Autowired
    private RedisRemoteService redisRemoteService;
    @Autowired
    private MySQLRemoteService mySQLRemoteService;
    //登出
    @RequestMapping("/auth/member/logout")
    public String loginout(HttpSession session){
        session.invalidate();
        return "redirect:http://localhost:80/";
    }

    // 登录判断
    @RequestMapping("/auth/member/do/login")
    public String login(
            @RequestParam("loginacct") String loginacct,
            @RequestParam("userpswd") String userpswd,
            ModelMap modelMap,
            HttpSession session) {
        //根据登录账户查询MemberPO对象
        ResultEntity<MemberPO> resultEntity = mySQLRemoteService.getMemberPOByLoginAcctRemote(loginacct);
        if (ResultEntity.FAILED.equals(resultEntity.getResult())) {
            modelMap.addAttribute(Constant.ATTR_MANE_MESSAGE,resultEntity.getMessage());
            return "member-login";
        }
        MemberPO memberPO = resultEntity.getData();
        if (memberPO == null){
            modelMap.addAttribute(Constant.ATTR_MANE_MESSAGE,Constant.MESSAGE_LOGIN_FAILED);
            return "member-login";
        }
        String userpswdDataBase = memberPO.getUserPswd();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matcheResult = passwordEncoder.matches(userpswd, userpswdDataBase);
        if (!matcheResult){
            modelMap.addAttribute(Constant.ATTR_MANE_MESSAGE,Constant.MESSAGE_LOGIN_FAILED);
            return "member-login";
        }
        MemberLoginVO memberLoginVO = new MemberLoginVO(memberPO.getId(), memberPO.getUserName(), memberPO.getEmail());
        session.setAttribute(Constant.ATTR_NAME_LOGIN_MEMBER,memberLoginVO);
        // 访问时4000和80cookie不共享，默认访问时变成4000
        return "redirect:http://localhost:80/auth/member/to/center/page";
    }
        // 注册按钮
    @RequestMapping("/auth/do/member/register")
    public String authDoMemberRegister(MemberVO memberVO, ModelMap modelMap) {
        // 手机号
        String phoneNum = memberVO.getPhoneNum();
        // 验证码key
        String key = Constant.REDIS_CODE_PREFIX + phoneNum;
        // 验证码value
        ResultEntity<String> resultEntity = redisRemoteService.getRedisStringValueByKey(key);
        // 有效性
        String result = resultEntity.getResult();
        if (ResultEntity.FAILED.equals(result)) {
            modelMap.addAttribute(Constant.ATTR_MANE_MESSAGE, resultEntity.getMessage());
            return "member-reg";
        }


        if (ResultEntity.SUCCESS.equals(result)) {
            String redisCode = resultEntity.getData();
            if (redisCode == null) {
                modelMap.addAttribute(Constant.ATTR_MANE_MESSAGE, Constant.MESSAGE_CODE_NOT_EXISTS);
                return "member-reg";
            }
            if (redisCode != null) {
                String formCode = memberVO.getCode();
                if (!Objects.equals(formCode, redisCode)) {
                    modelMap.addAttribute(Constant.ATTR_MANE_MESSAGE, Constant.MESSAGE_CODE_INVALID);
                    return "member-reg";
                }
                // 验证码对比，Redisvalue和网页输入
                if (Objects.equals(formCode, redisCode)) {
                    // 如果验证码一致，则从Redis删除
                    redisRemoteService.removeRedisKeyRemote(key);

                    // 密码加密
                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    String userPswdBeforeEncode = memberVO.getUserPswd();
                    String userPswdAfterEncode = passwordEncoder.encode(userPswdBeforeEncode);
                    memberVO.setUserPswd(userPswdAfterEncode);
                    // 执行保存
                    MemberPO memberPO = new MemberPO();
                    // 复制属性
                    BeanUtils.copyProperties(memberVO, memberPO);
                    // 调用远程方法
                    ResultEntity<String> saveMemberResultEntity = mySQLRemoteService.saveMember(memberPO);
                    if (ResultEntity.FAILED.equals(saveMemberResultEntity.getResult())) {
                        modelMap.addAttribute(Constant.ATTR_MANE_MESSAGE, saveMemberResultEntity.getMessage());
                        return "member-reg";
                    }
                    return "member-login";
                }
            }
        }


        return "redirect:/auth/member/to/login/page";
    }
    // 发送验证码
    @ResponseBody
    @RequestMapping("/auth/member/send/short/message.json")
    public ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum) {

        ResultEntity<String> sendMessageResultEntity = CrowdUtil.sendCodeByShortMessage(
                shortMessageProperties.getHost(),
                shortMessageProperties.getPath(),
                shortMessageProperties.getMethod(),
                phoneNum,
                shortMessageProperties.getAppCode(),
                shortMessageProperties.getSign(),
                shortMessageProperties.getSkin());
        if (ResultEntity.SUCCESS.equals(sendMessageResultEntity.getResult())) {
            // 获取验证码
            String code = sendMessageResultEntity.getData();
            String key = Constant.REDIS_CODE_PREFIX + phoneNum;
            ResultEntity<String> saveCodeResultEntity = redisRemoteService.setRedisKeyValueRemoteWithTimeout(key, code, 15, TimeUnit.MINUTES);
            if (ResultEntity.SUCCESS.equals(saveCodeResultEntity.getResult())) {
                return ResultEntity.successWithoutData();
            } else {
                return saveCodeResultEntity;
            }

        } else {
            return sendMessageResultEntity;
        }

    }
}
