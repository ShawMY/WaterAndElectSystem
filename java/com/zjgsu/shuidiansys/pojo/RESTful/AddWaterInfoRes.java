package com.zjgsu.shuidiansys.pojo.RESTful;

public class AddWaterInfoRes {
    private String code;

    private String msg;

    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

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

    public class Content{
        private String meterCode;

        public String getMeterCode() {
            return meterCode;
        }

        public void setMeterCode(String meterCode) {
            this.meterCode = meterCode;
        }
    }
}
