package com.zjgsu.shuidiansys.dao;

import com.zjgsu.shuidiansys.pojo.MeterAlarm;

import java.util.List;

public interface IMeterAlarmDao {

    public List<MeterAlarm> getAllUnhandled();

    public int countAllUnhandled();

    public int update(MeterAlarm update);

    public int insert(MeterAlarm insert);

}
