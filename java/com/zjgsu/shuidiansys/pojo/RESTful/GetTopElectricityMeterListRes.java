package com.zjgsu.shuidiansys.pojo.RESTful;

import java.util.List;

public class GetTopElectricityMeterListRes {
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

        private List<Info> info;

        private int page;

        private int pageNum;

        public List<Info> getInfo() {
            return info;
        }

        public void setInfo(List<Info> info) {
            this.info = info;
        }

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

        public class Info{

            private String id;

            private String userCode;

            private String meterCode;

            private String currentNum;

            private String preNum;

            private String actualUsage;

            private String unitprice;

            private String actualPrice;

            private String ratio;

            private String damage;

            private String area;

            private String userName;

            private String location;

            private String recordTime;

            private String adjustPrice;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public String getMeterCode() {
                return meterCode;
            }

            public void setMeterCode(String meterCode) {
                this.meterCode = meterCode;
            }

            public String getCurrentNum() {
                return currentNum;
            }

            public void setCurrentNum(String currentNum) {
                this.currentNum = currentNum;
            }

            public String getPreNum() {
                return preNum;
            }

            public void setPreNum(String preNum) {
                this.preNum = preNum;
            }

            public String getActualUsage() {
                return actualUsage;
            }

            public void setActualUsage(String actualUsage) {
                this.actualUsage = actualUsage;
            }

            public String getUnitprice() {
                return unitprice;
            }

            public void setUnitprice(String unitprice) {
                this.unitprice = unitprice;
            }

            public String getActualPrice() {
                return actualPrice;
            }

            public void setActualPrice(String actualPrice) {
                this.actualPrice = actualPrice;
            }

            public String getRatio() {
                return ratio;
            }

            public void setRatio(String ratio) {
                this.ratio = ratio;
            }

            public String getDamage() {
                return damage;
            }

            public void setDamage(String damage) {
                this.damage = damage;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getRecordTime() {
                return recordTime;
            }

            public void setRecordTime(String recordTime) {
                this.recordTime = recordTime;
            }

            public String getAdjustPrice() {
                return adjustPrice;
            }

            public void setAdjustPrice(String adjustPrice) {
                this.adjustPrice = adjustPrice;
            }
        }

    }
}
