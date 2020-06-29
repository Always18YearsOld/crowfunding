package com.example.mvc.config;

import com.example.constant.Constant;
import com.example.exception.LoginAcctAlreadyInUseException;
import com.example.exception.LoginAcctAlreadyInUseForUpdateException;
import com.example.exception.LoginFailedException;
import com.example.util.CrowdUtil;
import com.example.util.ResultEntity;
//import com.example.util.exception.LoginFailedException;
import com.google.gson.Gson;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author NGX
 * @Date 2020/5/1 10:40
 * @Description 异常处理类
 */

// @ControllerAdvice,表示这是一个基于注解的 异常处理类
@ControllerAdvice
public class ExceptionResolver {
    //new
    @ExceptionHandler(value = LoginAcctAlreadyInUseForUpdateException.class)
    public ModelAndView LoginAcctAlreadyInUseForUpdateException(
            LoginAcctAlreadyInUseForUpdateException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String viewName = "system-error";
        ModelAndView modelAndView = common(viewName,exception,request,response);
        return modelAndView;
    }
    @ExceptionHandler(value = LoginAcctAlreadyInUseException.class)
    public ModelAndView LoginAcctAlreadyInUseException(
            LoginAcctAlreadyInUseException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String viewName = "admin-add";
        ModelAndView modelAndView = common(viewName,exception,request,response);
        return modelAndView;
    }
    // 基于注解的异常映射和基于xml的异常映射同一个异常类型时
    // ，注解异常生效
    @ExceptionHandler(Exception.class)
    public ModelAndView resolverException(
            Exception exception,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String viewName = "admin-login";
        ModelAndView modelAndView = common(viewName,exception,request,response);
        return modelAndView;
    }
//    @ExceptionHandler(LoginFailedException.class)
//    public ModelAndView resolverLoginFailedException(LoginFailedException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String viewName = "admin-login";
//        ModelAndView modelAndView = common(viewName,exception,request,response);
//        return modelAndView;
//    }
    // @ExceptionHandler 建立异常和类的映射关系
    @ExceptionHandler(value = ArithmeticException.class)
    public ModelAndView resolverException(ArithmeticException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 判断请求的类型
        boolean requestType = CrowdUtil.isAjaxRequest(request);

        // 如果是ajax请求
        if (requestType) {
            // 创建ResultEntity
            ResultEntity<Object> failed = ResultEntity.failed(exception.getMessage());
            // 创建Gson
            Gson gson = new Gson();
            // 将ResultEntity转为json
            String json = gson.toJson(failed);
            // 将json数据返还给浏览器
            response.getWriter().write(json);
            // 因为使用了原生respones对象返回了响应，不提供ModelAndView对象
            return null;
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("exception", exception);
            modelAndView.setViewName("system-error");
            return modelAndView;
        }
    }

    /**
     * 提取可复用部分
     *
     * @return
     */
    public ModelAndView common(String viewName, Exception exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean requestType = CrowdUtil.isAjaxRequest(request);
        if (requestType) {
            ResultEntity<Object> failed = ResultEntity.failed(exception.getMessage());

            String json = new Gson().toJson(failed);

            response.getWriter().write(json);
            return null;
        } else {
            ModelAndView modelAndView = new ModelAndView();
//            将exception对象存入模型：类setattribute
            modelAndView.addObject(Constant.ATTR_NAME_EXCEPTION, exception);
            modelAndView.setViewName(viewName);
            return modelAndView;
        }
    }

//    @ExceptionHandler(Exception.class)
//    public ModelAndView Exception(Exception exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String viewName = "system-error";
//        ModelAndView modelAndView = common(viewName,exception,request,response);
//        return modelAndView;
//    }
}
