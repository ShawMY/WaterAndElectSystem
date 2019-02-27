package com.zjgsu.shuidiansys.pojo.RESTful;

public class CreateAddressNameRes {

    private String code;

    private String msg;

    private Content content;

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

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public class Content{
        private String code;

        private String preNum;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPreNum() {
            return preNum;
        }

        public void setPreNum(String preNum) {
            this.preNum = preNum;
        }
    }
}
