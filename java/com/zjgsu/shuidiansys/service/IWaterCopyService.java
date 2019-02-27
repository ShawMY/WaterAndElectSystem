package com.zjgsu.shuidiansys.service;

import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.RESTful.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface IWaterCopyService {
    public GetWaterCopyListRes getList(PageParam pageParam);

    public QueryWaterCopyRes query(QueryWaterCopyReq waterCopyReq);

    public DeleteWaterCopyRes delete(DeleteWaterCopyReq waterCopyReq);

    public ChangeWaterCopyRes change(ChangeWaterCopyReq waterCopyReq);

    public List<Map<String,Object>> exportExcel(List<String> ids);

}
