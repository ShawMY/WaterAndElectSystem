package com.zjgsu.shuidiansys.pojo.RESTful;

import java.util.List;

public class GetMeterIdByTypeRes {
    private String code;

    private String msg;

    private List<String> content;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
