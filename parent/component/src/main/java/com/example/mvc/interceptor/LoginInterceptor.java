package com.example.mvc.interceptor;

import com.example.constant.Constant;
import com.example.entity.Admin;
import com.example.exception.AccessForbiddenException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author Administrator
 * @Date 2020/5/9
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 1.通过request获取session对象
        HttpSession session = httpServletRequest.getSession();
        // 2.然后获取Admin
        Admin admin = (Admin) session.getAttribute(Constant.ATTR_NAME_LOGIN_ADMIN);
        // 3.是否为空
        if (admin == null){
            // 4.抛出异常
            throw new AccessForbiddenException(Constant.MESSAGE_ACCESS_FORBIDEN);
        }
        // 5.不为空true放行
        return true;
    }


}
