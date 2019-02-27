package com.zjgsu.shuidiansys.pojo;

import java.sql.Date;

public class WaterInfo {

    private String waterMeterId;

    private String level;

    private String superiorId;

    private String charger;

    private String receivableParty;

    private Date installTime;

    private Date scrappedTime;

    private String firstAddress="";

    private String secondAddress="";

    private String thirdAddress="";

    private String fourthAddress="";

    private String fifthAddress="";

    private String sixthAddress="";

    private int meterBottom;

    private int finalDegree;

    private int maxDegree;

    private int flipTime;

    private int pipeSize;

    private int magnification;

    private String state;

    public String getWaterMeterId() {
        return waterMeterId;
    }

    public void setWaterMeterId(String waterMeterId) {
        this.waterMeterId = waterMeterId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(String superiorId) {
        this.superiorId = superiorId;
    }

    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }

    public String getReceivableParty() {
        return receivableParty;
    }

    public void setReceivableParty(String receivableParty) {
        this.receivableParty = receivableParty;
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

    public String getFirstAddress() {
        return firstAddress;
    }

    public void setFirstAddress(String firstAddress) {
        this.firstAddress = firstAddress;
    }

    public String getSecondAddress() {
        return secondAddress;
    }

    public void setSecondAddress(String secondAddress) {
        this.secondAddress = secondAddress;
    }

    public String getThirdAddress() {
        return thirdAddress;
    }

    public void setThirdAddress(String thirdAddress) {
        this.thirdAddress = thirdAddress;
    }

    public String getFourthAddress() {
        return fourthAddress;
    }

    public void setFourthAddress(String fourthAddress) {
        this.fourthAddress = fourthAddress;
    }

    public String getFifthAddress() {
        return fifthAddress;
    }

    public void setFifthAddress(String fifthAddress) {
        this.fifthAddress = fifthAddress;
    }

    public String getSixthAddress() {
        return sixthAddress;
    }

    public void setSixthAddress(String sixthAddress) {
        this.sixthAddress = sixthAddress;
    }

    public int getMeterBottom() {
        return meterBottom;
    }

    public void setMeterBottom(int meterBottom) {
        this.meterBottom = meterBottom;
    }

    public int getFinalDegree() {
        return finalDegree;
    }

    public void setFinalDegree(int finalDegree) {
        this.finalDegree = finalDegree;
    }

    public int getMaxDegree() {
        return maxDegree;
    }

    public void setMaxDegree(int maxDegree) {
        this.maxDegree = maxDegree;
    }

    public int getFlipTime() {
        return flipTime;
    }

    public void setFlipTime(int flipTime) {
        this.flipTime = flipTime;
    }

    public int getPipeSize() {
        return pipeSize;
    }

    public void setPipeSize(int pipeSize) {
        this.pipeSize = pipeSize;
    }

    public int getMagnification() {
        return magnification;
    }

    public void setMagnification(int magnification) {
        this.magnification = magnification;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
