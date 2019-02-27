package com.zjgsu.shuidiansys.pojo.RESTful;

import java.util.List;

public class QueryUserInfoRes {
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

        private int page;

        private int pageNum;

        private List<Info> info;


        public int getPage() {
            return page;
        }


        public void setPage(int page) {
            this.page = page;
        }


        public int getPageNum() {
            return pageNum;
        }


        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }


        public List<Info> getInfo() {
            return info;
        }


        public void setInfo(List<Info> info) {
            this.info = info;
        }


        public class Info{

            private String code;

            private String name;

            private String typeCode;

            private String typeName;

            private String meterCode;

            private String meterType;

            private String payment;

            private String openTime;

            private String closeTime;

            private String contactPeople;

            private String contactPhone;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMeterCode() {
                return meterCode;
            }

            public void setMeterCode(String meterCode) {
                this.meterCode = meterCode;
            }

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

            public String getMeterType() {
                return meterType;
            }

            public void setMeterType(String meterType) {
                this.meterType = meterType;
            }

            public String getPayment() {
                return payment;
            }

            public void setPayment(String payment) {
                this.payment = payment;
            }

            public String getOpenTime() {
                return openTime;
            }

            public void setOpenTime(String openTime) {
                this.openTime = openTime;
            }

            public String getCloseTime() {
                return closeTime;
            }

            public void setCloseTime(String closeTime) {
                this.closeTime = closeTime;
            }

            public String getContactPeople() {
                return contactPeople;
            }

            public void setContactPeople(String contactPeople) {
                this.contactPeople = contactPeople;
            }

            public String getContactPhone() {
                return contactPhone;
            }

            public void setContactPhone(String contactPhone) {
                this.contactPhone = contactPhone;
            }
        }
    }
}
