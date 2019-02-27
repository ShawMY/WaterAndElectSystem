package com.zjgsu.shuidiansys.controller;

import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.dao.IElectricInfoDao;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.service.IElectricInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class ElectricInfoController {
    @Resource
    IElectricInfoService service;

    @ResponseBody
    @RequestMapping(value = "/api/electric/meter",method = {RequestMethod.GET})
    public GetElectricInfoListRes getWaterInfoList(@RequestParam int page){
        PageParam pageParam = new PageParam();
        pageParam.setCurrPage(page);
        return service.getElectricInfoList(pageParam);
    }

    @ResponseBody
    @RequestMapping(value = "/api/electric/meter/search",method = {RequestMethod.GET})
    public QueryElectricInfoRes queryWaterInfo(QueryElectricInfoReq electricInfoReq){
        return service.queryElectricInfo(electricInfoReq);
    }

    @ResponseBody
    @RequestMapping(value = "/api/electric/meter",method = {RequestMethod.DELETE})
    public DeleteRes deleteWaterInfo(@RequestBody DeleteReq electricInfoReq){
        return service.deleteElectricInfo(electricInfoReq);
    }

    @ResponseBody
    @RequestMapping(value = "/api/electric/meter/chg",method = {RequestMethod.POST})
    public ChangeRes changeWaterInfo(@RequestBody ChangeElectricInfoReq electricInfoReq){
        return service.changeElectricInfo(electricInfoReq);
    }

    @ResponseBody
    @RequestMapping(value = "/api/electric/meter/status",method = {RequestMethod.POST})
    public ChangeRes changeWaterState(@RequestBody ChangeElectricStateReq electricStateReq){
        return service.changeElectricState(electricStateReq);
    }


    @ResponseBody
    @RequestMapping(value = "/api/electric/meter/add",method = {RequestMethod.POST})
    public AddElectricInfoRes addWaterInfo(@RequestBody AddElectricInfoReq electricInfoReq){
        return service.addElectricInfo(electricInfoReq);
    }

    @ResponseBody
    @RequestMapping(value = "/api/electric/history",method = {RequestMethod.GET})
    public GetElectricHistoryRes getWaterHistory(@RequestParam int page){
        PageParam pageParam = new PageParam();
        pageParam.setCurrPage(page);
        return service.getElectricHistoryList(pageParam);
    }

    @ResponseBody
    @RequestMapping(value = "/api/electric/code/all",method = {RequestMethod.GET})
    public GetCodeByAddressLvRes getCodeByLevel(@RequestParam String level){
        return service.getCodeByLevel(level);
    }

}
