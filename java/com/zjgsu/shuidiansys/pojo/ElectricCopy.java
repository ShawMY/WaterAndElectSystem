package com.zjgsu.shuidiansys.pojo;

import java.sql.Date;

public class ElectricCopy {
    private int id;

    private String electricityMeterId;

    private int previousDegree;

    private String meterReader;

    private String firstAddress="";

    private String secondAddress="";

    private String thirdAddress="";

    private String fourthAddress="";

    private String fifthAddress="";

    private String sixthAddress="";

    private Date meterReadTime;

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

    public int getPreviousDegree() {
        return previousDegree;
    }

    public void setPreviousDegree(int previousDegree) {
        this.previousDegree = previousDegree;
    }

    public String getMeterReader() {
        return meterReader;
    }

    public void setMeterReader(String meterReader) {
        this.meterReader = meterReader;
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

    public Date getMeterReadTime() {
        return meterReadTime;
    }

    public void setMeterReadTime(Date meterReadTime) {
        this.meterReadTime = meterReadTime;
    }
}
