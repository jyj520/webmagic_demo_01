package cn.panda.entity;

import java.util.Date;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class TradeManage {

    private Integer id;         //ID
    private String sn;                   //编号
    private String buyName;           //买方姓名
    private String buyPhone;    //联系方式
    private String sellName;        //卖方姓名
    private String sellPhone;         //联系方式
    private String houseAdress;        //房源坐落
    private String otherAndDec;          //配套及装修情况
    private Double area;       //面积
    private Double mprice;             //单价
    private Double totalPrice;          //房源总房款
    private String isLoan;             //是否贷款
    private String serviceFeeType;             //代办服务费种类
    private Double totalServiceFeeCharge;               //共计收费
    private Double chargedMoney;           //已收费
    private Date chargeTime;           //交费时间
    private String houseAgent;            //经纪人
    private String servicePerson;         //后期服务人员
    private String customerServiceIn;            //客服参与
    private Date doneTime;                //完成时间
    private String tracking;             //其他
    private Date addTime;             //添加时间
    private Integer isDelete;           //是否删除



    //toString开始

    @Override
    public String toString() {
        return "TradeManage{" +
                "id=" + id +
                ", sn='" + sn + '\'' +
                ", buyName='" + buyName + '\'' +
                ", buyPhone='" + buyPhone + '\'' +
                ", sellName='" + sellName + '\'' +
                ", sellPhone='" + sellPhone + '\'' +
                ", houseAdress='" + houseAdress + '\'' +
                ", otherAndDec='" + otherAndDec + '\'' +
                ", area=" + area +
                ", mprice=" + mprice +
                ", totalPrice=" + totalPrice +
                ", isLoan='" + isLoan + '\'' +
                ", serviceFeeType='" + serviceFeeType + '\'' +
                ", totalServiceFeeCharge=" + totalServiceFeeCharge +
                ", chargedMoney=" + chargedMoney +
                ", chargeTime=" + chargeTime +
                ", houseAgent='" + houseAgent + '\'' +
                ", servicePerson='" + servicePerson + '\'' +
                ", customerServiceIn='" + customerServiceIn + '\'' +
                ", doneTime=" + doneTime +
                ", tracking='" + tracking + '\'' +
                ", addTime=" + addTime +
                ", isDelete=" + isDelete +
                '}';
    }

    //toString结束


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getBuyName() {
        return buyName;
    }

    public void setBuyName(String buyName) {
        this.buyName = buyName;
    }

    public String getBuyPhone() {
        return buyPhone;
    }

    public void setBuyPhone(String buyPhone) {
        this.buyPhone = buyPhone;
    }

    public String getSellName() {
        return sellName;
    }

    public void setSellName(String sellName) {
        this.sellName = sellName;
    }

    public String getSellPhone() {
        return sellPhone;
    }

    public void setSellPhone(String sellPhone) {
        this.sellPhone = sellPhone;
    }

    public String getHouseAdress() {
        return houseAdress;
    }

    public void setHouseAdress(String houseAdress) {
        this.houseAdress = houseAdress;
    }

    public String getOtherAndDec() {
        return otherAndDec;
    }

    public void setOtherAndDec(String otherAndDec) {
        this.otherAndDec = otherAndDec;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getMprice() {
        return mprice;
    }

    public void setMprice(Double mprice) {
        this.mprice = mprice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getIsLoan() {
        return isLoan;
    }

    public void setIsLoan(String isLoan) {
        this.isLoan = isLoan;
    }

    public String getServiceFeeType() {
        return serviceFeeType;
    }

    public void setServiceFeeType(String serviceFeeType) {
        this.serviceFeeType = serviceFeeType;
    }

    public Double getTotalServiceFeeCharge() {
        return totalServiceFeeCharge;
    }

    public void setTotalServiceFeeCharge(Double totalServiceFeeCharge) {
        this.totalServiceFeeCharge = totalServiceFeeCharge;
    }

    public Double getChargedMoney() {
        return chargedMoney;
    }

    public void setChargedMoney(Double chargedMoney) {
        this.chargedMoney = chargedMoney;
    }

    public Date getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(Date chargeTime) {
        this.chargeTime = chargeTime;
    }

    public String getHouseAgent() {
        return houseAgent;
    }

    public void setHouseAgent(String houseAgent) {
        this.houseAgent = houseAgent;
    }

    public String getServicePerson() {
        return servicePerson;
    }

    public void setServicePerson(String servicePerson) {
        this.servicePerson = servicePerson;
    }

    public String getCustomerServiceIn() {
        return customerServiceIn;
    }

    public void setCustomerServiceIn(String customerServiceIn) {
        this.customerServiceIn = customerServiceIn;
    }

    public Date getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(Date doneTime) {
        this.doneTime = doneTime;
    }

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
