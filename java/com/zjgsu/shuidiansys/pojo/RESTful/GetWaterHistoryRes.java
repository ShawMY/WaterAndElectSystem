package com.zjgsu.shuidiansys.pojo.RESTful;

import java.util.List;

public class GetWaterHistoryRes {
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

            private String code;

            private String installTime;

            private String deadTime;

            private String transCount;

            private String finalNum;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getInstallTime() {
                return installTime;
            }

            public void setInstallTime(String installTime) {
                this.installTime = installTime;
            }

            public String getDeadTime() {
                return deadTime;
            }

            public void setDeadTime(String deadTime) {
                this.deadTime = deadTime;
            }

            public String getTransCount() {
                return transCount;
            }

            public void setTransCount(String transCount) {
                this.transCount = transCount;
            }

            public String getFinalNum() {
                return finalNum;
            }

            public void setFinalNum(String finalNum) {
                this.finalNum = finalNum;
            }
        }

    }
}
