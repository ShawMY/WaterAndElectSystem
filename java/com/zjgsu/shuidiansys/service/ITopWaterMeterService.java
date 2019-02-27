package com.zjgsu.shuidiansys.service;

import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.RESTful.*;

import java.util.List;
import java.util.Map;

public interface ITopWaterMeterService {

    public GetTopWaterMeterListRes getTopWaterMeterList(PageParam pageParam);

    public DeleteTopWaterMeterRes deleteTopWaterMeter(DeleteTopWaterMeterReq topWaterMeterReq);

    public ChangeTopWaterMeterRes changeTopWaterMeter(ChangeTopWaterMeterReq topWaterMeterReq);

    public ImportRes importTopWaterMeter(List<Map<String,Object>> data);

    public List<Map<String,Object>> exportTopWaterMeter(List<String> ids);

    public GetTopWaterMeterListRes queryTopWaterMeter(QueryTopWaterMeterReq req);

    public  List<Map<String,Object>> exportFeeChange(String id);

    public List<Map<String,Object>> exportFeeDelete(List<String> ids);
}
