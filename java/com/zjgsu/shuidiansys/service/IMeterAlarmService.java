package com.zjgsu.shuidiansys.service;


import com.zjgsu.shuidiansys.pojo.RESTful.ChangeRes;
import com.zjgsu.shuidiansys.pojo.RESTful.GetAlarmCountRes;
import com.zjgsu.shuidiansys.pojo.RESTful.GetAlarmListRes;

import java.util.List;

public interface IMeterAlarmService {

    public GetAlarmListRes getAlarmList();

    public ChangeRes updateAlarmState(List<String> ids);

    public GetAlarmCountRes getAlarmCount();
}
