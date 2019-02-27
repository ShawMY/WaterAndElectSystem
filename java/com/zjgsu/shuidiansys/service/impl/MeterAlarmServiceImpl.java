package com.zjgsu.shuidiansys.service.impl;

import com.zjgsu.shuidiansys.dao.IMeterAlarmDao;
import com.zjgsu.shuidiansys.pojo.MeterAlarm;
import com.zjgsu.shuidiansys.pojo.RESTful.ChangeRes;
import com.zjgsu.shuidiansys.pojo.RESTful.GetAlarmCountRes;
import com.zjgsu.shuidiansys.pojo.RESTful.GetAlarmListRes;
import com.zjgsu.shuidiansys.service.IMeterAlarmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MeterAlarmServiceImpl implements IMeterAlarmService {

    @Resource
    IMeterAlarmDao dao;


    public GetAlarmListRes getAlarmList() {
        List<MeterAlarm> alarmList = dao.getAllUnhandled();
        GetAlarmListRes alarmListRes = new GetAlarmListRes();
        GetAlarmListRes.Content content = alarmListRes.new Content();
        List<GetAlarmListRes.Content.Info> infos = new ArrayList<GetAlarmListRes.Content.Info>();

        for(MeterAlarm one:alarmList){
            GetAlarmListRes.Content.Info info = content.new Info();
            info.setCode(one.getMeterId());
            info.setInfo(one.getMsg());
            info.setTime(one.getDate().toString());
            info.setId(String.valueOf(one.getId()));
            infos.add(info);
        }
        content.setInfo(infos);
        content.setCount(infos.size());
        alarmListRes.setContent(content);
        alarmListRes.setMsg("获取列表成功");
        alarmListRes.setCode("200");

        return alarmListRes;
    }

    public ChangeRes updateAlarmState(List<String> ids) {
        ChangeRes changeRes = new ChangeRes();
        int updateFlag = 0;
        for(String id:ids){
            MeterAlarm one = new MeterAlarm();
            one.setState("已处理");
            one.setId(Integer.valueOf(id));
            if(dao.update(one)==1){
                updateFlag+=updateFlag;
            }
        }
        changeRes.setCode("200");

        changeRes.setMsg("更新成功！");
        return changeRes;
    }

    public GetAlarmCountRes getAlarmCount() {
        int num = dao.countAllUnhandled();
        GetAlarmCountRes alarmCountRes = new GetAlarmCountRes();

        alarmCountRes.setCode("200");
        alarmCountRes.setMsg("查询成功");
        GetAlarmCountRes.Content content = alarmCountRes.new Content();
        content.setCount(num);
        alarmCountRes.setContent(content);
        return alarmCountRes;
    }
}
