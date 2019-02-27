package com.zjgsu.shuidiansys.pojo;

import java.sql.Date;

public class TopElectricityMeter {
    private int id;

    private String userNumber;

    private String userName;

    private String electricityMeterId;

    private String location;

    private String area;

    private int previousDegree;

    private int thisDegree;

    private int magnification;

    private int copperLoss;

    private int actualUsing;

    private double price;

    private double actualPrice;

    private double adjustPrice;

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

    public String getElectricityMeterId() {
        return electricityMeterId;
    }

    public void setElectricityMeterId(String electricityMeterId) {
        this.electricityMeterId = electricityMeterId;
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

    public int getMagnification() {
        return magnification;
    }

    public void setMagnification(int magnification) {
        this.magnification = magnification;
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

    public double getAdjustPrice() {
        return adjustPrice;
    }

    public void setAdjustPrice(double adjustPrice) {
        this.adjustPrice = adjustPrice;
    }

    public int getCopperLoss() {
        return copperLoss;
    }

    public void setCopperLoss(int copperLoss) {
        this.copperLoss = copperLoss;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
