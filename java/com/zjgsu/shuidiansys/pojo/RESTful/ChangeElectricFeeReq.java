package com.zjgsu.shuidiansys.pojo.RESTful;

public class ChangeElectricFeeReq {
    private String id;

    private String userCode;

    private String previousNum;

    private String currentNum;

    private String recordTime;

    private String meterCode;

    private String currentFree;

    private String currentPayable;

    private String currentPayed;

    private String payStatus;

    private String payDeadline;

    private String finishPaymentTime;

    private String isInvoice;

    private String overdueFee;

    private String overdueFeeStatus;

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

    public String getPreviousNum() {
        return previousNum;
    }

    public void setPreviousNum(String previousNum) {
        this.previousNum = previousNum;
    }

    public String getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(String currentNum) {
        this.currentNum = currentNum;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getMeterCode() {
        return meterCode;
    }

    public void setMeterCode(String meterCode) {
        this.meterCode = meterCode;
    }

    public String getCurrentFree() {
        return currentFree;
    }

    public void setCurrentFree(String currentFree) {
        this.currentFree = currentFree;
    }

    public String getCurrentPayable() {
        return currentPayable;
    }

    public void setCurrentPayable(String currentPayable) {
        this.currentPayable = currentPayable;
    }

    public String getCurrentPayed() {
        return currentPayed;
    }

    public void setCurrentPayed(String currentPayed) {
        this.currentPayed = currentPayed;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayDeadline() {
        return payDeadline;
    }

    public void setPayDeadline(String payDeadline) {
        this.payDeadline = payDeadline;
    }

    public String getFinishPaymentTime() {
        return finishPaymentTime;
    }

    public void setFinishPaymentTime(String finishPaymentTime) {
        this.finishPaymentTime = finishPaymentTime;
    }

    public String getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(String isInvoice) {
        this.isInvoice = isInvoice;
    }

    public String getOverdueFee() {
        return overdueFee;
    }

    public void setOverdueFee(String overdueFee) {
        this.overdueFee = overdueFee;
    }

    public String getOverdueFeeStatus() {
        return overdueFeeStatus;
    }

    public void setOverdueFeeStatus(String overdueFeeStatus) {
        this.overdueFeeStatus = overdueFeeStatus;
    }
}
