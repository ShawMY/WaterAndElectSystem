package com.zjgsu.shuidiansys.pojo;

import java.sql.Date;

public class UserInfo{

    private String userId;

    private String userName;

    private String userCategoryName;

    private int userCategoryId;

    private String meterId;

    private String meterCategory;

    private String paymentMethod;

    private String accountOpenTime;

    private String accountCloseTime;

    private String contact;

    private String phoneNumber;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserCategoryId() {
        return userCategoryId;
    }

    public void setUserCategoryId(int userCategoryId) {
        this.userCategoryId = userCategoryId;
    }

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public String getMeterCategory() {
        return meterCategory;
    }

    public void setMeterCategory(String meterCategory) {
        this.meterCategory = meterCategory;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAccountOpenTime() {
        return accountOpenTime;
    }

    public void setAccountOpenTime(String accountOpenTime) {
        this.accountOpenTime = accountOpenTime;
    }

    public String getAccountCloseTime() {
        return accountCloseTime;
    }

    public void setAccountCloseTime(String accountCloseTime) {
        this.accountCloseTime = accountCloseTime;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserCategoryName() {
        return userCategoryName;
    }

    public void setUserCategoryName(String userCategoryName) {
        this.userCategoryName = userCategoryName;
    }

}
