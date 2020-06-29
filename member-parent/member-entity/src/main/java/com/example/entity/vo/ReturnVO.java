package com.example.entity.vo;

import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2020/5/24
 */
public class ReturnVO implements Serializable {
    private static final long serialVersionUID = 1L;
    // 回报类型：0 - 实物回报， 1 虚拟物品回报
    private Integer type;
    // 支持金额
    private Integer supportmoney;
    // 回报内容介绍
    private String content;
    // 总回报数量，0 为不限制
    private Integer count;
    // 是否限制单笔购买数量，0 表示不限购，1 表示限购
    private Integer signalpurchase;
    // 如果单笔限购，那么具体的限购数量
    private Integer purchase;
    // 运费，“0”为包邮
    private Integer freight;
    // 是否开发票，0 - 不开发票， 1 - 开发票
    private Integer invoice;
    // 众筹结束后返还回报物品天数
    private Integer returndate;
    // 说明图片路径
    private String describPicPath;

    public ReturnVO() {
    }

    public ReturnVO(Integer type, Integer supportmoney, String content, Integer count, Integer signalpurchase, Integer purchase, Integer freight, Integer invoice, Integer returndate, String describPicPath) {
        this.type = type;
        this.supportmoney = supportmoney;
        this.content = content;
        this.count = count;
        this.signalpurchase = signalpurchase;
        this.purchase = purchase;
        this.freight = freight;
        this.invoice = invoice;
        this.returndate = returndate;
        this.describPicPath = describPicPath;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSupportmoney() {
        return supportmoney;
    }

    public void setSupportmoney(Integer supportmoney) {
        this.supportmoney = supportmoney;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSignalpurchase() {
        return signalpurchase;
    }

    public void setSignalpurchase(Integer signalpurchase) {
        this.signalpurchase = signalpurchase;
    }

    public Integer getPurchase() {
        return purchase;
    }

    public void setPurchase(Integer purchase) {
        this.purchase = purchase;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public Integer getInvoice() {
        return invoice;
    }

    public void setInvoice(Integer invoice) {
        this.invoice = invoice;
    }

    public Integer getReturndate() {
        return returndate;
    }

    public void setReturndate(Integer returndate) {
        this.returndate = returndate;
    }

    public String getDescribPicPath() {
        return describPicPath;
    }

    public void setDescribPicPath(String describPicPath) {
        this.describPicPath = describPicPath;
    }

    @Override
    public String toString() {
        return "ReturnVO{" +
                "type=" + type +
                ", supportmoney=" + supportmoney +
                ", content='" + content + '\'' +
                ", count=" + count +
                ", signalpurchase=" + signalpurchase +
                ", purchase=" + purchase +
                ", freight=" + freight +
                ", invoice=" + invoice +
                ", returndate=" + returndate +
                ", describPicPath='" + describPicPath + '\'' +
                '}';
    }
}
