package com.zjgsu.shuidiansys.pojo;

import java.sql.Date;

public class TopWaterMeter {
    private int id;

    private String userNumber;

    private String userName;

    private String waterMeterId;

    private String location;

    private String area;

    private int previousDegree;

    private int thisDegree;

    private int actualUsing;

    private int nominalUsage;

    private double price;

    private double actualPrice;

    private String remarks;

    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWaterMeterId() {
        return waterMeterId;
    }

    public void setWaterMeterId(String waterMeterId) {
        this.waterMeterId = waterMeterId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public int getActualUsing() {
        return actualUsing;
    }

    public void setActualUsing(int actualUsing) {
        this.actualUsing = actualUsing;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNominalUsage() {
        return nominalUsage;
    }

    public void setNominalUsage(int nominalUsage) {
        this.nominalUsage = nominalUsage;
    }
}
