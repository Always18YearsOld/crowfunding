package com.example.entity.vo;

import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2020/5/22
 */
public class MemberLoginVO implements Serializable{
    private static final long serialVersionUID = 1L;
    private String email;
    private String userName;
    private Integer id;

    public MemberLoginVO() {
    }

    public MemberLoginVO(Integer id, String userName,String email ) {
        this.email = email;
        this.userName = userName;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MemberLoginVO{" +
                "email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", id=" + id +
                '}';
    }
}
