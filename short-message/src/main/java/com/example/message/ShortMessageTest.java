package com.example.message;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;


import java.util.HashMap;
import java.util.Map;

/**
 * @Author Administrator
 * @Date 2020/5/20
 */
public class ShortMessageTest {
    public static void main(String[] args) {
//        短信接口调用的url地址
        String host = "https://feginesms.market.alicloudapi.com";
//        具体发送短信功能的地址
        String path = "/codeNotice";
        String method = "GET";
        String appcode = "8845c99cd0bc4597bdbd4282a110ef3b";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
//        验证码
        querys.put("param", "123456");
        querys.put("phone", "18050952279");
        querys.put("sign", "1");
        querys.put("skin", "10");
        //JDK 1.8示例代码请在这里下载：  http://code.fegine.com/Tools.zip

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 或者直接下载：
             * http://code.fegine.com/HttpUtils.zip
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             * 相关jar包（非pom）直接下载：
             * http://code.fegine.com/aliyun-jar.zip
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //System.out.println(response.toString());如不输出json, 请打开这行代码，打印调试头部状态码。
            //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
