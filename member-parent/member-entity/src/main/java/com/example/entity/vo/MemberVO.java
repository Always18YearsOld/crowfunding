package com.example.entity.vo;

/**
 * @Author Administrator
 * @Date 2020/5/22
 */
public class MemberVO {
 private String loginAcct;
    private String userPswd;
    private String email;
    private String userName;
    private String phoneNum;
    private String code;

    public MemberVO() {
    }

    public MemberVO(String loginAcct, String userPswd, String email, String userName, String phoneNum, String code) {
        this.loginAcct = loginAcct;
        this.userPswd = userPswd;
        this.email = email;
        this.userName = userName;
        this.phoneNum = phoneNum;
        this.code = code;
    }

    public String getLoginAcct() {
        return loginAcct;
    }

    public void setLoginAcct(String loginAcct) {
        this.loginAcct = loginAcct;
    }

    public String getUserPswd() {
        return userPswd;
    }

    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "loginAcct='" + loginAcct + '\'' +
                ", userPswd='" + userPswd + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
