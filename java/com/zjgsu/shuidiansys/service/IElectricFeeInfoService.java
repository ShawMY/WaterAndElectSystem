package com.zjgsu.shuidiansys.service;

import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.RESTful.*;

import java.util.List;
import java.util.Map;

public interface IElectricFeeInfoService {

    public GetElectricFeeListRes getElectricFeeList(PageParam pageParam);

    public QueryElectricFeeRes getElectricFeeListByQuery(QueryElectricFeeReq electricFeeReq);

    public DeleteRes deleteElectricFee(DeleteReq electricFeeReq);

    public ChangeRes changeElectricFee(ChangeElectricFeeReq electricFeeReq);

    public ImportRes importElectricCopy(List<Map<String,Object>> mapList);

    public void exportElectricFeeInfo(List<String> ids);

    public  List<Map<String,Object>> exportElectricFeeChange(String id);

    public ImportRes importFeeExcel(List<Map<String,Object>> mapList);

    public List<Map<String,Object>> exportFeeExcelModel(List<String> ids);

    public List<Map<String, Object>> exportFeeDelete(List<String> ids);

    public ImportRes setProve(String id,String path);

    public String getProve(String id);
}
