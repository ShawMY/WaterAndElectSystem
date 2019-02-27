package com.zjgsu.shuidiansys.pojo;

public class AddressInfo {

    private String addressId;

    private String addressLevel;

    private String address;

    private String superiorId;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressLevel() {
        return addressLevel;
    }

    public void setAddressLevel(String addressLevel) {
        this.addressLevel = addressLevel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(String superiorId) {
        this.superiorId = superiorId;
    }
}
