package com.zjgsu.shuidiansys.pojo.RESTful;

public class ChangeTopElectricityMeterReq {

    private String id;

    private String userCode;

    private String meterCode;

    private String currentNum;

    private String preNum;

    private String actualUsage;

    private String unitPrice;

    private String actualPrice;

    private String ratio;

    private String damage;

    private String area;

    private String userName;

    private String location;

    private String recordTime;

    private String adjustPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getMeterCode() {
        return meterCode;
    }

    public void setMeterCode(String meterCode) {
        this.meterCode = meterCode;
    }

    public String getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(String currentNum) {
        this.currentNum = currentNum;
    }

    public String getPreNum() {
        return preNum;
    }

    public void setPreNum(String preNum) {
        this.preNum = preNum;
    }

    public String getActualUsage() {
        return actualUsage;
    }

    public void setActualUsage(String actualUsage) {
        this.actualUsage = actualUsage;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getAdjustPrice() {
        return adjustPrice;
    }

    public void setAdjustPrice(String adjustPrice) {
        this.adjustPrice = adjustPrice;
    }
}
