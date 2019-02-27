package com.zjgsu.shuidiansys.controller;

import com.zjgsu.shuidiansys.common.ExcelModel;
import com.zjgsu.shuidiansys.common.ExcelUtils;
import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.service.ITopWaterMeterService;
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
public class TopWaterMeterController {

    @Resource
    ITopWaterMeterService service;

    @ResponseBody
    @RequestMapping(value = "/api/water/meter/top",method = {RequestMethod.GET})
    public GetTopWaterMeterListRes getTopWaterMeterList(@RequestParam int page){
        PageParam pageParam = new PageParam();
        pageParam.setCurrPage(page);

        return service.getTopWaterMeterList(pageParam);
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/meter/top",method = {RequestMethod.DELETE})
    public DeleteTopWaterMeterRes deleteTopWaterMeterList(@RequestBody DeleteTopWaterMeterReq topWaterMeterReq){

        return service.deleteTopWaterMeter(topWaterMeterReq);
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/meter/top/chg",method = {RequestMethod.POST})
    public ChangeTopWaterMeterRes changeTopWaterMeter(@RequestBody ChangeTopWaterMeterReq topWaterMeterReq){

        return service.changeTopWaterMeter(topWaterMeterReq);
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/meter/top/import",method = {RequestMethod.POST})
    public ImportRes importTopWaterMeter(@RequestParam(value="excelFile") MultipartFile file,
                                         HttpServletRequest request){
        Map<String,Object> map  = ExcelUtils.importExcel(ExcelModel.TOP_WATER_DS_TITLES,file);
        ImportRes importRes = new ImportRes();
        if(((String) map.get("code")).equals("500")) {
            importRes.setCode((String) map.get("code"));
            importRes.setMsg((String) map.get("msg"));
            return importRes;
        }
        importRes = service.importTopWaterMeter((List<Map<String,Object>>)map.get("data"));
        return importRes;
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/meter/top/export",method = {RequestMethod.GET})
    public void exportTopWaterMeter(@RequestParam("ids[]") String[] ids, HttpServletRequest request, HttpServletResponse response){
        List<Map<String,Object>> data = service.exportTopWaterMeter(Arrays.asList(ids));
        try{
            ExcelUtils.exportExcel("一级水表费用","一级水表",ExcelModel.TOP_WATER_HEADER,ExcelModel.TOP_WATER_DS_TITLES,ExcelModel.TOP_WATER_DS_FORMAT,null,data,request,response);
        }catch (IOException e){
            System.out.println(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/meter/top/search",method = {RequestMethod.GET})
    public GetTopWaterMeterListRes queryTopWaterMeter(QueryTopWaterMeterReq req){
        return service.queryTopWaterMeter(req);
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/meter/top/chg",method = {RequestMethod.GET})
    public void exportChange(@RequestParam String id, HttpServletRequest request, HttpServletResponse response){
        List<Map<String,Object>> data = service.exportFeeChange(id);
        try{
            ExcelUtils.exportExcel("一级水表修改","一级水表",ExcelModel.TOP_WATER_CHANGE_HEADER,ExcelModel.TOP_WATER_CHANGE_DS_TITLES,ExcelModel.TOP_WATER_CHANGE_DS_FORMAT,null,data,request,response);
        }catch (IOException e){
            System.out.println(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/meter/top/del",method = {RequestMethod.GET})
    public void exportDelete(@RequestParam("ids[]") String[] ids, HttpServletRequest request, HttpServletResponse response){
        List<Map<String,Object>> data = service.exportFeeDelete(Arrays.asList(ids));
        try{
            ExcelUtils.exportExcel("一级水表删除","一级水表",ExcelModel.TOP_WATER_HEADER,ExcelModel.TOP_WATER_DS_TITLES,ExcelModel.TOP_WATER_DS_FORMAT,null,data,request,response);
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
