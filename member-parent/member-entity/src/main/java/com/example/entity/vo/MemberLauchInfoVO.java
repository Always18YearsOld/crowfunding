package com.example.entity.vo;

import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2020/5/24
 */
public class MemberLauchInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;
    // 简单介绍
    private String descriptionSimple;
    // 详细介绍
    private String descriptionDetail;
    // 联系电话
    private String phoneNum;
    // 客服电话
    private String serviceNum;

    public MemberLauchInfoVO() {
    }

    public MemberLauchInfoVO(String descriptionSimple, String descriptionDetail, String phoneNum, String serviceNum) {
        this.descriptionSimple = descriptionSimple;
        this.descriptionDetail = descriptionDetail;
        this.phoneNum = phoneNum;
        this.serviceNum = serviceNum;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDescriptionSimple() {
        return descriptionSimple;
    }

    public void setDescriptionSimple(String descriptionSimple) {
        this.descriptionSimple = descriptionSimple;
    }

    public String getDescriptionDetail() {
        return descriptionDetail;
    }

    public void setDescriptionDetail(String descriptionDetail) {
        this.descriptionDetail = descriptionDetail;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(String serviceNum) {
        this.serviceNum = serviceNum;
    }

    @Override
    public String toString() {
        return "MemberLauchInfoVO{" +
                "descriptionSimple='" + descriptionSimple + '\'' +
                ", descriptionDetail='" + descriptionDetail + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", serviceNum='" + serviceNum + '\'' +
                '}';
    }
}

