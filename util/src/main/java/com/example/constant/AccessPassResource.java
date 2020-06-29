package com.example.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * 进行路径是否过滤工具类
 * @Author Administrator
 * @Date 2020/5/23
 */
public class AccessPassResource {
    public static final Set<String> PASS_RES_SET = new HashSet<>();
    static {
        PASS_RES_SET.add("/");
        PASS_RES_SET.add("/auth/member/to/reg/page");
        PASS_RES_SET.add("/auth/member/to/login/page");
        PASS_RES_SET.add("/auth/member/do/login");
        PASS_RES_SET.add("/auth/do/member/register");
        PASS_RES_SET.add("/auth/member/logout");
        PASS_RES_SET.add("/auth/member/send/short/message.json");

    }
    public static final Set<String> STATIC_RES_SET = new HashSet<>();
    static {
        STATIC_RES_SET.add("bootstrap");
        STATIC_RES_SET.add("css");
        STATIC_RES_SET.add("fonts");
        STATIC_RES_SET.add("img");
        STATIC_RES_SET.add("jquery");
        STATIC_RES_SET.add("layer");
        STATIC_RES_SET.add("script");
        STATIC_RES_SET.add("ztree");

    }
    // 判断是否是静态资源
    public static boolean judgeCurrentServletPathWetherStaticResource(String servletPath){
        if (servletPath == null || servletPath.length() == 0){
            throw new RuntimeException(Constant.MESSAGE_STRING_INVALIDATE);
        }
        String[] split = servletPath.split("/");
        // 第一个是/前，后是第一个地址，可能是静态地址
        String firstLevelPath = split[1];

        return STATIC_RES_SET.contains(firstLevelPath);
    }

//    public static void main(String[] args) {
//        String ff="/aaa/bbb/ccc";
//        String tt="/ztree/aaa/bbb";
//        System.out.println(judgeCurrentServletPathWetherStaticResource(ff));
//        System.out.println(judgeCurrentServletPathWetherStaticResource(tt));
//    }
 }
