package com.example.entity.po;

public class MemberPO {
    private Integer id;

    private String loginAcct;

    private String userPswd;

    private String userName;

    private String email;

    private Integer authStatus;

    private Integer userType;

    private String realName;

    private String cardNum;

    private Integer acctType;

    public MemberPO() {
    }

    public MemberPO(Integer id, String loginAcct, String userPswd, String userName, String email, Integer authStatus, Integer userType, String realName, String cardNum, Integer acctType) {
        this.id = id;
        this.loginAcct = loginAcct;
        this.userPswd = userPswd;
        this.userName = userName;
        this.email = email;
        this.authStatus = authStatus;
        this.userType = userType;
        this.realName = realName;
        this.cardNum = cardNum;
        this.acctType = acctType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginAcct() {
        return loginAcct;
    }

    public void setLoginAcct(String loginAcct) {
        this.loginAcct = loginAcct == null ? null : loginAcct.trim();
    }

    public String getUserPswd() {
        return userPswd;
    }

    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd == null ? null : userPswd.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum == null ? null : cardNum.trim();
    }

    public Integer getAcctType() {
        return acctType;
    }

    @Override
    public String toString() {
        return "MemberPO{" +
                "id=" + id +
                ", loginAcct='" + loginAcct + '\'' +
                ", userPswd='" + userPswd + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", authStatus=" + authStatus +
                ", userType=" + userType +
                ", realName='" + realName + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", acctType=" + acctType +
                '}';
    }

    public void setAcctType(Integer acctType) {
        this.acctType = acctType;
    }
}