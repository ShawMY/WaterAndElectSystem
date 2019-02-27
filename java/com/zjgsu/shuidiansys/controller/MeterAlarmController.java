package com.zjgsu.shuidiansys.controller;

import com.zjgsu.shuidiansys.dao.IMeterAlarmDao;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.service.IMeterAlarmService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class MeterAlarmController {


    @Resource
    IMeterAlarmService service;

    // 获取报警列表
    @ResponseBody
    @RequestMapping(value = "/api/alarm/detail" ,method = {RequestMethod.GET})
    public GetAlarmListRes getAlarmList(){

        return service.getAlarmList();

    }

    // 获取报警数目
    @ResponseBody
    @RequestMapping(value = "/api/alarm/count" ,method = {RequestMethod.GET})
    public GetAlarmCountRes getAlarmCount(){

        return service.getAlarmCount();

    }

    // 处理报警信息
    @ResponseBody
    @RequestMapping(value = "/api/alarm/update" ,method = {RequestMethod.POST})
    public ChangeRes getAlarmCount(@RequestBody DeleteReq id){

        return service.updateAlarmState(id.getIds());

    }
}
