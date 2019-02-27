package com.zjgsu.shuidiansys.pojo.RESTful;

import java.util.List;

public class DeleteUserInfoReq {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        private List<Ids> ids;

        public List<Ids> getIds() {
            return ids;
        }

        public void setIds(List<Ids> ids) {
            this.ids = ids;
        }

        public class Ids{
            private String meterId;

            private String userId;

            public String getMeterId() {
                return meterId;
            }

            public void setMeterId(String meterId) {
                this.meterId = meterId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }
    }
}
