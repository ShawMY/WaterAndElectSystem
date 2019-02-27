package com.zjgsu.shuidiansys.pojo.RESTful;

import java.util.List;

public class QueryElectricFeeRes {

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
            private String id;

            private String userCode;

            private String previousNum;

            private String currentNum;

            private String recordTime;

            private String meterCode;

            private String currentFree;

            private String currentPayable;

            private String currentPayed;

            private String payStatus;

            private String payDeadline;

            private String finishPaymentTime;

            private String isInvoice;

            private String overdueFee;

            private String overdueFeeStatus;

            private String isProve;

            public String getIsProve() {
                return isProve;
            }

            public void setIsProve(String isProve) {
                this.isProve = isProve;
            }


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

            public String getPreviousNum() {
                return previousNum;
            }

            public void setPreviousNum(String previousNum) {
                this.previousNum = previousNum;
            }

            public String getCurrentNum() {
                return currentNum;
            }

            public void setCurrentNum(String currentNum) {
                this.currentNum = currentNum;
            }

            public String getRecordTime() {
                return recordTime;
            }

            public void setRecordTime(String recordTime) {
                this.recordTime = recordTime;
            }

            public String getMeterCode() {
                return meterCode;
            }

            public void setMeterCode(String meterCode) {
                this.meterCode = meterCode;
            }

            public String getCurrentFree() {
                return currentFree;
            }

            public void setCurrentFree(String currentFree) {
                this.currentFree = currentFree;
            }

            public String getCurrentPayable() {
                return currentPayable;
            }

            public void setCurrentPayable(String currentPayable) {
                this.currentPayable = currentPayable;
            }

            public String getCurrentPayed() {
                return currentPayed;
            }

            public void setCurrentPayed(String currentPayed) {
                this.currentPayed = currentPayed;
            }

            public String getPayStatus() {
                return payStatus;
            }

            public void setPayStatus(String payStatus) {
                this.payStatus = payStatus;
            }

            public String getPayDeadline() {
                return payDeadline;
            }

            public void setPayDeadline(String payDeadline) {
                this.payDeadline = payDeadline;
            }

            public String getFinishPaymentTime() {
                return finishPaymentTime;
            }

            public void setFinishPaymentTime(String finishPaymentTime) {
                this.finishPaymentTime = finishPaymentTime;
            }

            public String getIsInvoice() {
                return isInvoice;
            }

            public void setIsInvoice(String isInvoice) {
                this.isInvoice = isInvoice;
            }

            public String getOverdueFee() {
                return overdueFee;
            }

            public void setOverdueFee(String overdueFee) {
                this.overdueFee = overdueFee;
            }

            public String getOverdueFeeStatus() {
                return overdueFeeStatus;
            }

            public void setOverdueFeeStatus(String overdueFeeStatus) {
                this.overdueFeeStatus = overdueFeeStatus;
            }
        }
    }
}
