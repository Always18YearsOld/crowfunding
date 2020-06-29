package com.example.entity.vo;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2020/5/26
 */
public class DetailProjectVO {
    private Integer projectId;
    private String projectName;
    private String projectDesc;
    private Integer followerCount;
    private Integer status;
    private Integer day;
    private String statusText;
    private Integer money;
    private Integer supportMoney;
    private Integer percentage;
    private String deployDate;
    private Integer lastDay;
    private Integer supporterCount;
    private String headerPicturePath;
    private List<String> detailPicturePathList;
    private List<DetailReturnVO> detailReturnVOList;

    public DetailProjectVO() {
    }

    public DetailProjectVO(Integer projectId, String projectName, String projectDesc, Integer followerCount, Integer status, Integer day, String statusText, Integer money, Integer supportMoney, Integer percentage, String deployDate, Integer lastDay, Integer supporterCount, String headerPicturePath, List<String> detailPicturePathList, List<DetailReturnVO> detailReturnVOList) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.followerCount = followerCount;
        this.status = status;
        this.day = day;
        this.statusText = statusText;
        this.money = money;
        this.supportMoney = supportMoney;
        this.percentage = percentage;
        this.deployDate = deployDate;
        this.lastDay = lastDay;
        this.supporterCount = supporterCount;
        this.headerPicturePath = headerPicturePath;
        this.detailPicturePathList = detailPicturePathList;
        this.detailReturnVOList = detailReturnVOList;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getSupportMoney() {
        return supportMoney;
    }

    public void setSupportMoney(Integer supportMoney) {
        this.supportMoney = supportMoney;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public String getDeployDate() {
        return deployDate;
    }

    public void setDeployDate(String deployDate) {
        this.deployDate = deployDate;
    }

    public Integer getLastDay() {
        return lastDay;
    }

    public void setLastDay(Integer lastDay) {
        this.lastDay = lastDay;
    }

    public Integer getSupporterCount() {
        return supporterCount;
    }

    public void setSupporterCount(Integer supporterCount) {
        this.supporterCount = supporterCount;
    }

    public String getHeaderPicturePath() {
        return headerPicturePath;
    }

    public void setHeaderPicturePath(String headerPicturePath) {
        this.headerPicturePath = headerPicturePath;
    }

    public List<String> getDetailPicturePathList() {
        return detailPicturePathList;
    }

    public void setDetailPicturePathList(List<String> detailPicturePathList) {
        this.detailPicturePathList = detailPicturePathList;
    }

    public List<DetailReturnVO> getDetailReturnVOList() {
        return detailReturnVOList;
    }

    public void setDetailReturnVOList(List<DetailReturnVO> detailReturnVOList) {
        this.detailReturnVOList = detailReturnVOList;
    }

    @Override
    public String toString() {
        return "DetailProjectVO{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectDesc='" + projectDesc + '\'' +
                ", followerCount=" + followerCount +
                ", status=" + status +
                ", day=" + day +
                ", statusText='" + statusText + '\'' +
                ", money=" + money +
                ", supportMoney=" + supportMoney +
                ", percentage=" + percentage +
                ", deployDate='" + deployDate + '\'' +
                ", lastDay=" + lastDay +
                ", supporterCount=" + supporterCount +
                ", headerPicturePath='" + headerPicturePath + '\'' +
                ", detailPicturePathList=" + detailPicturePathList +
                ", detailReturnVOList=" + detailReturnVOList +
                '}';
    }
}
