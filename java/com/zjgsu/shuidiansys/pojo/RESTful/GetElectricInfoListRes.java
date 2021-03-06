package com.zjgsu.shuidiansys.pojo.RESTful;

import java.util.List;

public class GetElectricInfoListRes {

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

            private String preCode;

            private String chargeAccount;

            private String receivableAccount;

            private String installTime;

            private String originScale;

            private String maxScale;

            private String uninstallTime;

            private String level;

            private String transCount;

            private String ratio;

            private String status;

            private String area;

            private String firstAddress;

            private String secondAddress;

            private String thirdAddress;

            private String fourthAddress;

            private String fifthAddress;

            private String sixthAddress;

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

            public String getChargeAccount() {
                return chargeAccount;
            }

            public void setChargeAccount(String chargeAccount) {
                this.chargeAccount = chargeAccount;
            }

            public String getReceivableAccount() {
                return receivableAccount;
            }

            public void setReceivableAccount(String receivableAccount) {
                this.receivableAccount = receivableAccount;
            }

            public String getInstallTime() {
                return installTime;
            }

            public void setInstallTime(String installTime) {
                this.installTime = installTime;
            }

            public String getOriginScale() {
                return originScale;
            }

            public void setOriginScale(String originScale) {
                this.originScale = originScale;
            }

            public String getMaxScale() {
                return maxScale;
            }

            public void setMaxScale(String maxScale) {
                this.maxScale = maxScale;
            }

            public String getUninstallTime() {
                return uninstallTime;
            }

            public void setUninstallTime(String uninstallTime) {
                this.uninstallTime = uninstallTime;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getTransCount() {
                return transCount;
            }

            public void setTransCount(String transCount) {
                this.transCount = transCount;
            }

            public String getRatio() {
                return ratio;
            }

            public void setRatio(String ratio) {
                this.ratio = ratio;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getFirstAddress() {
                return firstAddress;
            }

            public void setFirstAddress(String firstAddress) {
                this.firstAddress = firstAddress;
            }

            public String getSecondAddress() {
                return secondAddress;
            }

            public void setSecondAddress(String secondAddress) {
                this.secondAddress = secondAddress;
            }

            public String getThirdAddress() {
                return thirdAddress;
            }

            public void setThirdAddress(String thirdAddress) {
                this.thirdAddress = thirdAddress;
            }

            public String getFourthAddress() {
                return fourthAddress;
            }

            public void setFourthAddress(String fourthAddress) {
                this.fourthAddress = fourthAddress;
            }

            public String getFifthAddress() {
                return fifthAddress;
            }

            public void setFifthAddress(String fifthAddress) {
                this.fifthAddress = fifthAddress;
            }

            public String getSixthAddress() {
                return sixthAddress;
            }

            public void setSixthAddress(String sixthAddress) {
                this.sixthAddress = sixthAddress;
            }
        }
    }
}
