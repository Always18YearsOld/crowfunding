package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author Administrator
 * @Date 2020/5/21
 */
@Component
@ConfigurationProperties(prefix = "short.message")
public class ShortMessageProperties {
    private String host;
    private String path;
    private String method;
    private String appCode;
    private String sign;
    private String skin;

    public ShortMessageProperties() {
    }

    public ShortMessageProperties(String host, String path, String method, String appCode, String sign, String skin) {
        this.host = host;
        this.path = path;
        this.method = method;
        this.appCode = appCode;
        this.sign = sign;
        this.skin = skin;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    @Override
    public String toString() {
        return "ShortMessageProperties{" +
                "host='" + host + '\'' +
                ", path='" + path + '\'' +
                ", method='" + method + '\'' +
                ", appCode='" + appCode + '\'' +
                ", sign='" + sign + '\'' +
                ", skin='" + skin + '\'' +
                '}';
    }
}
