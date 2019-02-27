package com.zjgsu.shuidiansys.service;

import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.RESTful.*;

public interface IWaterInfoService {

    public GetWaterInfoListRes getWaterInfoList(PageParam pageParam);

    public QueryWaterInfoRes queryWaterInfo(QueryWaterInfoReq waterInfoReq);

    public DeleteWaterInfoRes deleteWaterInfo(DeleteWaterInfoReq waterInfoReq);

    public AddWaterInfoRes addWaterInfo(AddWaterInfoReq waterInfoReq);

    public ChangeWaterInfoRes changeWaterInfo(ChangeWaterInfoReq waterInfoReq);

    public ChangeWaterStateRes changeWaterState(ChangeWaterStateReq waterStateReq);

    public GetCodeByAddressLvRes getCodeByAddressLv2(String level);

    public GetCodeByAddressLvRes getCodeByAddressLv3(String level,String firstAddr,String secondAddr);

    public GetWaterHistoryRes getWaterHistoryList(PageParam pageParam);

    public GetCodeByAddressLvRes getCodeByLevel(String level);
}
