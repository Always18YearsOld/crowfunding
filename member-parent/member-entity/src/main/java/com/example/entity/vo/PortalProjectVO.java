package com.example.entity.vo;

/**
 * @Author Administrator
 * @Date 2020/5/25
 */
public class PortalProjectVO {
    private Integer projectId;
    private String projectName;
    private String headPicturePath;
    private Integer money;
    private String deployDate;
    private Integer percentage;
    private Integer supporter;

    public PortalProjectVO() {
    }

    public PortalProjectVO(Integer projectId, String projectName, String headPicturePath, Integer money, String deployDate, Integer percentage, Integer supporter) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.headPicturePath = headPicturePath;
        this.money = money;
        this.deployDate = deployDate;
        this.percentage = percentage;
        this.supporter = supporter;
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

    public String getHeadPicturePath() {
        return headPicturePath;
    }

    public void setHeadPicturePath(String headPicturePath) {
        this.headPicturePath = headPicturePath;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getDeployDate() {
        return deployDate;
    }

    public void setDeployDate(String deployDate) {
        this.deployDate = deployDate;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Integer getSupporter() {
        return supporter;
    }

    public void setSupporter(Integer supporter) {
        this.supporter = supporter;
    }

    @Override
    public String toString() {
        return "PortalProjectVO{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", headPicturePath='" + headPicturePath + '\'' +
                ", money=" + money +
                ", deployDate='" + deployDate + '\'' +
                ", percentage=" + percentage +
                ", supporter=" + supporter +
                '}';
    }
}
