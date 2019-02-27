package com.zjgsu.shuidiansys.pojo.RESTful;

import java.util.List;

public class GetCodeByAddressLvRes {
    private String msg;

    private String code;

    private Content content;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public class Content{
        private List<String> code;

        public List<String> getCode() {
            return code;
        }

        public void setCode(List<String> code) {
            this.code = code;
        }
    }
}
