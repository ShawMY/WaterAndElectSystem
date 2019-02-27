package com.zjgsu.shuidiansys.pojo.RESTful;

public class ChangeAddressReq {

    private String addressName;

    private String level;

    private String code;

    private String preCode;

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPreCode() {
        return preCode;
    }

    public void setPreCode(String preCode) {
        this.preCode = preCode;
    }
}
