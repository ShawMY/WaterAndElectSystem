package com.zjgsu.shuidiansys.service;

import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.RESTful.*;

import java.util.List;
import java.util.Map;

public interface ITopElectricityMeterService {

    public GetTopElectricityMeterListRes getTopElectricityMeterRes(PageParam pageParam);

    public DeleteTopElectricityMeterRes deleteTopElectricityMeterRes(DeleteTopElectricityMeterReq topElectricMeterReq);

    public ChangeTopElectricityMeterRes changeTopElectricityMeterRes(ChangeTopElectricityMeterReq topElectricMeterReq);

    public ImportRes importTopElectricityMeter(List<Map<String,Object>> data);

    public List<Map<String,Object>> exportTopElectricityMeter(List<String> ids);

    public GetTopElectricityMeterListRes queryTopElectricMeter(QueryTopElectricMeterReq req);

    public  List<Map<String,Object>> exportFeeChange(String id);

    public List<Map<String,Object>> exportFeeDelete(List<String> ids);

}
