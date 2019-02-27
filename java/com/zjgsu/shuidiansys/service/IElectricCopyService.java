package com.zjgsu.shuidiansys.service;

import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.RESTful.*;

import java.util.List;
import java.util.Map;

public interface IElectricCopyService {
    public GetElectricCopyListRes getList(PageParam pageParam);

    public QueryElectricCopyRes query(QueryElectricCopyReq req);

    public DeleteRes delete(DeleteReq req);

    public ChangeRes change(ChangeElectricCopyReq req);

    public List<Map<String,Object>> exportExcel(List<String> ids);
}
