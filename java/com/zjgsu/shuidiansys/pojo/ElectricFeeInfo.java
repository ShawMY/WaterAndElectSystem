package com.zjgsu.shuidiansys.pojo;

import java.sql.Date;

public class ElectricFeeInfo {
    private int id;

    private String electricityMeterId;

    private Date meterReadTime;

    private int previousDegree;

    private int thisDegree;

    private int thisUsing;

    private int thisRelief = 0;

    private double thisDue;

    private double thisPaid;

    private String paymentState;

    private String paymentDeadLine;

    private String paymentTime;

    private String isBilling;

    private double latePayment;

    private String latePaymentState;

    private String meterReader;

    private String reporter;

    private String userId;

    private String certificate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getElectricityMeterId() {
        return electricityMeterId;
    }

    public void setElectricityMeterId(String electricityMeterId) {
        this.electricityMeterId = electricityMeterId;
    }

    public Date getMeterReadTime() {
        return meterReadTime;
    }

    public void setMeterReadTime(Date meterReadTime) {
        this.meterReadTime = meterReadTime;
    }

    public int getPreviousDegree() {
        return previousDegree;
    }

    public void setPreviousDegree(int previousDegree) {
        this.previousDegree = previousDegree;
    }

    public int getThisDegree() {
        return thisDegree;
    }

    public void setThisDegree(int thisDegree) {
        this.thisDegree = thisDegree;
    }

    public int getThisUsing() {
        return thisUsing;
    }

    public void setThisUsing(int thisUsing) {
        this.thisUsing = thisUsing;
    }

    public int getThisRelief() {
        return thisRelief;
    }

    public void setThisRelief(int thisRelief) {
        this.thisRelief = thisRelief;
    }

    public double getThisDue() {
        return thisDue;
    }

    public void setThisDue(double thisDue) {
        this.thisDue = thisDue;
    }

    public double getThisPaid() {
        return thisPaid;
    }

    public void setThisPaid(double thisPaid) {
        this.thisPaid = thisPaid;
    }

    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    public String getPaymentDeadLine() {
        return paymentDeadLine;
    }

    public void setPaymentDeadLine(String paymentDeadLine) {
        this.paymentDeadLine = paymentDeadLine;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getIsBilling() {
        return isBilling;
    }

    public void setIsBilling(String isBilling) {
        this.isBilling = isBilling;
    }

    public double getLatePayment() {
        return latePayment;
    }

    public void setLatePayment(double latePayment) {
        this.latePayment = latePayment;
    }

    public String getLatePaymentState() {
        return latePaymentState;
    }

    public void setLatePaymentState(String latePaymentState) {
        this.latePaymentState = latePaymentState;
    }

    public String getMeterReader() {
        return meterReader;
    }

    public void setMeterReader(String meterReader) {
        this.meterReader = meterReader;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }
    public void setThisDue(int maxDegree,double fee){
        if(thisDegree<previousDegree){
            this.thisDue = (maxDegree+thisDegree-previousDegree-thisRelief)*fee;
        }else{
            this.thisDue = (thisDegree-previousDegree-thisRelief)*fee;
        }
    }

    public void setThisUsing2(int maxDegree){

        if(thisDegree<previousDegree){
            this.thisUsing = maxDegree+thisDegree-previousDegree-thisRelief;
        }else{
            this.thisUsing = thisDegree-previousDegree-thisRelief;
        }

    }
}
