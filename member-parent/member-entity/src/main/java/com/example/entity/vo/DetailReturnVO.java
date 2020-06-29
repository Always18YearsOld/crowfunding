package com.example.entity.vo;

/**
 * @Author Administrator
 * @Date 2020/5/26
 */
public class DetailReturnVO {
    //回报信息的主键
    private Integer returnId;
    // 当前档位支持的金额
    private Integer supportMoney;
    // 单笔限额
    private Integer signalPurchase;
    // 档位支撑者数量
    private Integer supporterCount;
    // 运费
    private Integer freight;
    // 发货时间
    private Integer returnDate;
    // 回报内容
    private String content;

    public DetailReturnVO() {
    }

    public DetailReturnVO(Integer returnId, Integer supportMoney, Integer signalPurchase, Integer supporterCount, Integer freight, Integer returnDate, String content) {
        this.returnId = returnId;
        this.supportMoney = supportMoney;
        this.signalPurchase = signalPurchase;
        this.supporterCount = supporterCount;
        this.freight = freight;
        this.returnDate = returnDate;
        this.content = content;
    }

    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
    }

    public Integer getSupportMoney() {
        return supportMoney;
    }

    public void setSupportMoney(Integer supportMoney) {
        this.supportMoney = supportMoney;
    }

    public Integer getSignalPurchase() {
        return signalPurchase;
    }

    public void setSignalPurchase(Integer signalPurchase) {
        this.signalPurchase = signalPurchase;
    }

    public Integer getSupporterCount() {
        return supporterCount;
    }

    public void setSupporterCount(Integer supporterCount) {
        this.supporterCount = supporterCount;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public Integer getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Integer returnDate) {
        this.returnDate = returnDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "DetailReturnVO{" +
                "returnId=" + returnId +
                ", supportMoney=" + supportMoney +
                ", signalPurchase=" + signalPurchase +
                ", supporterCount=" + supporterCount +
                ", freight=" + freight +
                ", returnDate=" + returnDate +
                ", content='" + content + '\'' +
                '}';
    }
}