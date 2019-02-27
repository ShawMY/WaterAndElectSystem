package com.zjgsu.shuidiansys.pojo.RESTful;

import java.util.List;

public class QueryElectricCopyRes {
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
            private String meterCode;

            private String previousNum;

            private String recordPeople;

            private String recordTime;


            private String installAddress1;

            private String installAddress2;

            private String installAddress3;

            private String installAddress4;

            private String installAddress5;

            private String installAddress6;

            private String copyCode;

            public String getMeterCode() {
                return meterCode;
            }

            public void setMeterCode(String meterCode) {
                this.meterCode = meterCode;
            }

            public String getPreviousNum() {
                return previousNum;
            }

            public void setPreviousNum(String previousNum) {
                this.previousNum = previousNum;
            }

            public String getRecordPeople() {
                return recordPeople;
            }

            public void setRecordPeople(String recordPeople) {
                this.recordPeople = recordPeople;
            }

            public String getRecordTime() {
                return recordTime;
            }

            public void setRecordTime(String recordTime) {
                this.recordTime = recordTime;
            }

            public String getInstallAddress1() {
                return installAddress1;
            }

            public void setInstallAddress1(String installAddress1) {
                this.installAddress1 = installAddress1;
            }

            public String getInstallAddress2() {
                return installAddress2;
            }

            public void setInstallAddress2(String installAddress2) {
                this.installAddress2 = installAddress2;
            }

            public String getInstallAddress3() {
                return installAddress3;
            }

            public void setInstallAddress3(String installAddress3) {
                this.installAddress3 = installAddress3;
            }

            public String getInstallAddress4() {
                return installAddress4;
            }

            public void setInstallAddress4(String installAddress4) {
                this.installAddress4 = installAddress4;
            }

            public String getInstallAddress5() {
                return installAddress5;
            }

            public void setInstallAddress5(String installAddress5) {
                this.installAddress5 = installAddress5;
            }

            public String getInstallAddress6() {
                return installAddress6;
            }

            public void setInstallAddress6(String installAddress6) {
                this.installAddress6 = installAddress6;
            }

            public String getCopyCode() {
                return copyCode;
            }

            public void setCopyCode(String copyCode) {
                this.copyCode = copyCode;
            }
        }
    }

}
