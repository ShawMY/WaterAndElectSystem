package com.zjgsu.shuidiansys.pojo;

import java.sql.Date;

public class WaterHistory {
    private int id;

    private String waterMeterId;

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

    public String getWaterMeterId() {
        return waterMeterId;
    }

    public void setWaterMeterId(String waterMeterId) {
        this.waterMeterId = waterMeterId;
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
