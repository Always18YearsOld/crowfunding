package com.example.filter;

import com.example.constant.AccessPassResource;
import com.example.constant.Constant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author Administrator
 * @Date 2020/5/23
 */
@Component
public class CrowdAccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        // 在目标微服务执行前过滤
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 1.获取RequestContext对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        // 2.通过RequestContext对象获取当前请求对象(theadLocal中获取Request对象)
        HttpServletRequest request = requestContext.getRequest();
        // 3.获取servletPath
        String servletPath = request.getServletPath();

        // 4.判定是否放行
        boolean containsResult = AccessPassResource.PASS_RES_SET.contains(servletPath);
        if (containsResult){
            // 不拦截
            return false;
        }
        boolean judgeStaticResult = AccessPassResource.judgeCurrentServletPathWetherStaticResource(servletPath);
        if (judgeStaticResult){
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        HttpSession session =  request.getSession();
        // 从session对象中获取已登录的用户
        Object loginMember = session.getAttribute(Constant.ATTR_NAME_LOGIN_MEMBER);
        if (loginMember == null){
            // 从requestContext对象中获取Response对象
            HttpServletResponse response = requestContext.getResponse();
            // 6.存入session
            session.setAttribute(Constant.ATTR_MANE_MESSAGE,Constant.MESSAGE_ACCESS_FORBIDEN);

            try {
                response.sendRedirect("/auth/member/to/login/page");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
