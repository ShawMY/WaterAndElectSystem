package com.zjgsu.shuidiansys.controller;


import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.service.IWaterInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class WaterInfoController {

    @Resource
    IWaterInfoService service;

    @ResponseBody
    @RequestMapping(value = "/api/water/meter",method = {RequestMethod.GET})
    public GetWaterInfoListRes getWaterInfoList(@RequestParam int page){
        PageParam pageParam = new PageParam();
        pageParam.setCurrPage(page);
        return service.getWaterInfoList(pageParam);
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/meter/search",method = {RequestMethod.GET})
    public QueryWaterInfoRes queryWaterInfo(QueryWaterInfoReq waterInfoReq){
        return service.queryWaterInfo(waterInfoReq);
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/meter",method = {RequestMethod.DELETE})
    public DeleteWaterInfoRes deleteWaterInfo(@RequestBody DeleteWaterInfoReq waterInfoReq){
        return service.deleteWaterInfo(waterInfoReq);
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/meter/chg",method = {RequestMethod.POST})
    public ChangeWaterInfoRes changeWaterInfo(@RequestBody ChangeWaterInfoReq waterInfoReq){
        return service.changeWaterInfo(waterInfoReq);
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/meter/status",method = {RequestMethod.POST})
    public ChangeWaterStateRes changeWaterState(@RequestBody ChangeWaterStateReq waterStateReq){
        return service.changeWaterState(waterStateReq);
    }

    @ResponseBody
    @RequestMapping(value = "/api/code/first",method = {RequestMethod.GET})
    public GetCodeByAddressLvRes getCodeByAddressLv2(@RequestParam String level){
        return service.getCodeByAddressLv2(level);
    }

    @ResponseBody
    @RequestMapping(value = "/api/code/other",method = {RequestMethod.GET})
    public GetCodeByAddressLvRes getCodeByAddressLv3(@RequestParam String level,@RequestParam String firstAddress,@RequestParam String secondAddress){
        return service.getCodeByAddressLv3(level,firstAddress,secondAddress);
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/meter/add",method = {RequestMethod.POST})
    public AddWaterInfoRes addWaterInfo(@RequestBody AddWaterInfoReq waterInfoReq){
        return service.addWaterInfo(waterInfoReq);
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/history",method = {RequestMethod.GET})
    public GetWaterHistoryRes getWaterHistory(@RequestParam int page){
        PageParam pageParam = new PageParam();
        pageParam.setCurrPage(page);
        return service.getWaterHistoryList(pageParam);
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/code/all",method = {RequestMethod.GET})
    public GetCodeByAddressLvRes getCodeByLevel(@RequestParam String level){
        return service.getCodeByLevel(level);
    }
}
