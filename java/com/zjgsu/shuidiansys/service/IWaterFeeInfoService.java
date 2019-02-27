package com.zjgsu.shuidiansys.service;

import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.RESTful.*;

import java.util.List;
import java.util.Map;

public interface IWaterFeeInfoService {

    public GetWaterFeeListRes getWaterFeeList(PageParam pageParam);

    public QueryWaterFeeRes getWaterFeeListByQuery(QueryWaterFeeReq waterFeeReq);

    public DeleteWaterFeeRes deleteWaterFee(DeleteWaterFeeReq waterFeeReq);

    public ChangeWaterFeeRes changeWaterFee(ChangeWaterFeeReq waterFeeReq);

    public ImportRes importWaterCopy(List<Map<String,Object>> mapList);

    public void exportWaterFeeInfo(List<String> ids);

    public  List<Map<String,Object>> exportWaterFeeChange(String id);

    public ImportRes importFeeExcel(List<Map<String,Object>> mapList);

    public List<Map<String,Object>> exportFeeExcelModel(List<String> ids);

    public List<Map<String,Object>> exportFeeDelete(List<String> ids);

    public ImportRes setProve(String id,String path);

    public String getProve(String id);


}
