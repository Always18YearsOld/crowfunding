package com.alipay.config;
import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号(沙箱APPID)
    public static String app_id =  "2016102300742593";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCPfqktrcw7eTjrQEG6e6oD8MnxTTbhR+1rUNiGjcvb/KloTeG/aSZFaXuZFKC9M7LLc3BeeENtE/JNIlrWrfad0uInGRI+tO1PRFtWQFfQkxb7zP4aCtRUqysaLYtIyDgx0tLaPEnplSyrjEfla/Xp0k8JY6R7uUihMUbNbOFWm56YQLYERcCXrIhVEylENlSZTXA31pJlk8XM9c2jENtnAdMtMnkoSIkkfQpCYkng0q3Teegw7BN5TnVV3MP7nHeI5QKhNv7X9+1g+6HV46XoRx/3tteQRpqvkJgH62XKxy+o6v/At1bEYLk1B7G2Kj26f1FDc3eHnKF+DtVzoWlNAgMBAAECggEAQSUWt0c09jc1kQ/uBzSHXhyeAa+1X5dhARyGAKg1Sd0uJ81b1KNvHYoi8796rgrffCAffrQIoGey1DPpm5cE5GfOBkQKlW9cYWU+Ni0l8OomQBCzt6z88qd2/P56QoOrh2sYEL3YLHRuAD/CkDRwQ0QYX8Hd9dGLWKrfYvmwX7m98dwCp65MJed7AcJiCj917wjzADB32b8uv8e5FE+j63MwF0qXjjxmp/bQGnMU+CULvkcy8yqxBBT5SRLfwbIZLBYdV481b4kdNlKp36BmgvETv0EdJsYZz2viZ95aiFP/F70HKKH1c5IzwxdN3mcLkJ2icPRgFVd/9IryHysFvQKBgQDTIJ4w56k57Tso3uT4eHYz+kqW+pMnxch2Q6+OvVqHKoLSxh5PrhFH+di8e+uQVWMG28AHno1ySCOtsIGd8rp85XGyulGu8leCjxxWvuB8HDbARwBMZMobESlmzn74m7mCHf3R6aFBcfq/1uTv0gmsXBDMVU2cdYGCHw3P5khkvwKBgQCt/iuLre2AW3FJoXtc+RB68YjmvPH8evWc9yvkbsd1Pbvy6nEAm+s0BCjvw4c072hs+Qabd+6ecwPTWxVckCQ/4h7rLyy32tUPTVN04vACWkb8tq+AfjjN8CU3irn48+deVb435sDiT8+71jTaMRwIol5Z/Yihn4KycSAPUSA48wKBgBjEUspZ4rgw5Ce/Pg0H1JX0XpuneWvI/+MfvZB5Fg2VJxOiuhuOx+gxnwM9ZjzgR2WekcBTETS+0bCmbA4jU00jfVsCEGL9etaOAz0J1zu385RKv/Y2PJ9APCMbRjnTysdAzW26gngy7r9ibijT8t6NTksqFpisEC07h1tEIGnxAoGAb7j/eSdrk7oj2mXIcwCy5l7ly1OUBs1lnS7RjlLb3JqRbv+/I+b6dmIdbyecUA9SfHbDGV0/aZrs3/BinRcpT6QAun+GNWBc4wCCEFIQT1JstqUPuHBURVhobWu00vi6nkZsaDTRsgARocmAEoLqP1kTQLGd5DoqkC43qd4r1KUCgYBkhdhGPq2lo936ke4zfngBcbI5EmvTcWdDKIjCpiDLzBLQ4dRIRnB6UQ6UUgx0TlHlAOiMOf5xfz576u6zpFz7g9Dl0zdD2sD50R/a3fmQbkfoupJYOUCwX/enZYvPHjw//kmrWiCUGLbxf7F66NlVn6NCN9j4OhDwq+dTLX3Kqw==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3iX+7fWsINNLHzDa20poR60z9euyBXpmE5191dTSIEZOYZSXLifjJc8sRMB/Jrylp5wBQ8bp6YI7vaQpexEcAHj9I11UUCbLE2d710vN347SeEL7fB+ky/Qc/P+O1/wEDEfUlmlYnCZo97kvBBD3RSaznJld36DZanb/1Vuf9txdcFaXvBdBWVDoD5iHhBfINYtGu0GNqvnDsV7mJ4OOBzt2E1B52XzbuGWchc+2YQfjoftefKKaWHIHcXVAfLfuFMPQ6fnrKhuXiNQFMzF6uue1+UFHqjJa9uDmhaMOGB/Fp6TVUmi8JIIPdiXG9rxBxGzpQtWjbOfkOjNZT/nqiQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 工程访问地址内网穿透url
    public static String notify_url = "http://efry67.natappfree.cc/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 工程访问地址内网穿透url
    public static String return_url = "http://efry67.natappfree.cc/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    //public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
    // 沙箱网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    // 日志路径
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

