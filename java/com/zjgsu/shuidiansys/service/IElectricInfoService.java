package com.zjgsu.shuidiansys.service;

import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.RESTful.*;

public interface IElectricInfoService {
    public GetElectricInfoListRes getElectricInfoList(PageParam pageParam);

    public QueryElectricInfoRes queryElectricInfo(QueryElectricInfoReq electricInfoReq);

    public DeleteRes deleteElectricInfo(DeleteReq req);

    public AddElectricInfoRes addElectricInfo(AddElectricInfoReq electricInfoReq);

    public ChangeRes changeElectricInfo(ChangeElectricInfoReq electricInfoReq);

    public ChangeRes changeElectricState(ChangeElectricStateReq electricStateReq);

    public GetElectricHistoryRes getElectricHistoryList(PageParam pageParam);

    public GetCodeByAddressLvRes getCodeByLevel(String level);
}
