package com.zjgsu.shuidiansys.controller;

import com.zjgsu.shuidiansys.common.ExcelModel;
import com.zjgsu.shuidiansys.common.ExcelUtils;
import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.service.ITopElectricityMeterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Controller
public class TopElectricityMeterController {

    @Resource
    ITopElectricityMeterService service;

    @ResponseBody
    @RequestMapping(value = "/api/electric/meter/top",method = {RequestMethod.GET})
    public GetTopElectricityMeterListRes getTopElectricityMeterList(@RequestParam int page){
        PageParam pageParam = new PageParam();
        pageParam.setCurrPage(page);

        return service.getTopElectricityMeterRes(pageParam);
    }

    @ResponseBody
    @RequestMapping(value = "/api/electric/meter/top",method = {RequestMethod.DELETE})
    public DeleteTopElectricityMeterRes deleteTopElectricityMeter(@RequestBody DeleteTopElectricityMeterReq topElectricityMeterReq){

        return service.deleteTopElectricityMeterRes(topElectricityMeterReq);
    }

    @ResponseBody
    @RequestMapping(value = "/api/electric/meter/top/chg",method = {RequestMethod.POST})
    public ChangeTopElectricityMeterRes changeTopElectricityMeter(@RequestBody ChangeTopElectricityMeterReq topElectricityMeterReq){

        return service.changeTopElectricityMeterRes(topElectricityMeterReq);
    }

    @ResponseBody
    @RequestMapping(value = "/api/electric/meter/top/import",method = {RequestMethod.POST})
    public ImportRes importTopElectricityMeter(@RequestParam(value="excelFile") MultipartFile file,
                                               HttpServletRequest request){

        Map<String,Object> map  = ExcelUtils.importExcel(ExcelModel.TOP_ELECTRIC_DS_TITLES,file);
        ImportRes importRes = new ImportRes();
        if(((String) map.get("code")).equals("500")) {
            importRes.setCode((String) map.get("code"));
            importRes.setMsg((String) map.get("msg"));
            return importRes;
        }
        importRes = service.importTopElectricityMeter((List<Map<String,Object>>)map.get("data"));
        return importRes;

    }

    @ResponseBody
    @RequestMapping(value = "/api/electric/meter/top/export",method = {RequestMethod.GET})
    public void exportTopElectricityMeter(@RequestParam("ids[]") String[] ids, HttpServletRequest request, HttpServletResponse response){
        List<Map<String,Object>> data = service.exportTopElectricityMeter(Arrays.asList(ids));
        try{
            ExcelUtils.exportExcel("TopElectricMeterFee","一级电表",ExcelModel.TOP_ELECTRIC_HEADER,ExcelModel.TOP_ELECTRIC_DS_TITLES,ExcelModel.TOP_ELECTRIC_DS_FORMAT,null,data,request,response);
        }catch (IOException e){
            System.out.println(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/api/electric/meter/top/search",method = {RequestMethod.GET})
    public GetTopElectricityMeterListRes queryTopElectricMeter(QueryTopElectricMeterReq req){

        return service.queryTopElectricMeter(req);

    }

    @ResponseBody
    @RequestMapping(value = "/api/electric/meter/top/chg",method = {RequestMethod.GET})
    public void exportChange(@RequestParam String id, HttpServletRequest request, HttpServletResponse response){
        List<Map<String,Object>> data = service.exportFeeChange(id);
        try{
            ExcelUtils.exportExcel("TopElectricMeterChg","一级电表",ExcelModel.TOP_ELECTRIC_CHANGE_HEADER,ExcelModel.TOP_ELECTRIC_CHANGE_DS_TITLES,ExcelModel.TOP_ELECTRIC_CHANGE_DS_FORMAT,null,data,request,response);
        }catch (IOException e){
            System.out.println(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/api/electric/meter/top/del",method = {RequestMethod.GET})
    public void exportDelete(@RequestParam("ids[]") String[] ids, HttpServletRequest request, HttpServletResponse response){
        List<Map<String,Object>> data = service.exportFeeDelete(Arrays.asList(ids));
        try{
            ExcelUtils.exportExcel("TopElectricMeterDel","一级电表",ExcelModel.TOP_ELECTRIC_HEADER,ExcelModel.TOP_ELECTRIC_DS_TITLES,ExcelModel.TOP_ELECTRIC_DS_FORMAT,null,data,request,response);
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
