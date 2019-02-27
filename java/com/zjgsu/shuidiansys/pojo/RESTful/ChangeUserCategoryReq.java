package com.zjgsu.shuidiansys.pojo.RESTful;

public class ChangeUserCategoryReq {

    private String typeCode;

    private String typeName;

    private String waterUnitPrice;

    private String electricUnitPrice;

    private String waterFreeLimit;

    private String electricFreeLimit;

    private String waterPriceIntax;

    private String electricPriceIntax;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getWaterUnitPrice() {
        return waterUnitPrice;
    }

    public void setWaterUnitPrice(String waterUnitPrice) {
        this.waterUnitPrice = waterUnitPrice;
    }

    public String getElectricUnitPrice() {
        return electricUnitPrice;
    }

    public void setElectricUnitPrice(String electricUnitPrice) {
        this.electricUnitPrice = electricUnitPrice;
    }

    public String getWaterFreeLimit() {
        return waterFreeLimit;
    }

    public void setWaterFreeLimit(String waterFreeLimit) {
        this.waterFreeLimit = waterFreeLimit;
    }

    public String getElectricFreeLimit() {
        return electricFreeLimit;
    }

    public void setElectricFreeLimit(String electricFreeLimit) {
        this.electricFreeLimit = electricFreeLimit;
    }

    public String getWaterPriceIntax() {
        return waterPriceIntax;
    }

    public void setWaterPriceIntax(String waterPriceIntax) {
        this.waterPriceIntax = waterPriceIntax;
    }

    public String getElectricPriceIntax() {
        return electricPriceIntax;
    }

    public void setElectricPriceIntax(String electricPriceIntax) {
        this.electricPriceIntax = electricPriceIntax;
    }

}
