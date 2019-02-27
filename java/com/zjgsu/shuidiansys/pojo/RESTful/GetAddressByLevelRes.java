package com.zjgsu.shuidiansys.pojo.RESTful;

import java.util.List;

public class GetAddressByLevelRes {

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

        private List<Addr> addr;

        private int page;

        private int pageNum;

        public List<Addr> getAddr() {
            return addr;
        }

        public void setAddr(List<Addr> addr) {
            this.addr = addr;
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

        public class Addr{
            private String name;

            private String code;

            private String level;

            private String preCode;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getPreCode() {
                return preCode;
            }

            public void setPreCode(String preCode) {
                this.preCode = preCode;
            }
        }
    }
}


