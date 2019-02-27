package com.zjgsu.shuidiansys.pojo.RESTful;

public class ChangeElectricStateReq {

    private int state;

    private String id;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
