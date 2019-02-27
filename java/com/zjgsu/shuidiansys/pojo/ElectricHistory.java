package com.zjgsu.shuidiansys.pojo;

import java.sql.Date;

public class ElectricHistory {
    private int id;

    private String electricityMeterId;

    private Date installTime;

    private Date scrappedTime;

    private int flipTime;

    private int finalDegree;

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

    public Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    public Date getScrappedTime() {
        return scrappedTime;
    }

    public void setScrappedTime(Date scrappedTime) {
        this.scrappedTime = scrappedTime;
    }

    public int getFlipTime() {
        return flipTime;
    }

    public void setFlipTime(int flipTime) {
        this.flipTime = flipTime;
    }

    public int getFinalDegree() {
        return finalDegree;
    }

    public void setFinalDegree(int finalDegree) {
        this.finalDegree = finalDegree;
    }
}
