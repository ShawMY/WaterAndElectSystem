package com.zjgsu.shuidiansys.controller;

import com.zjgsu.shuidiansys.common.ExcelModel;
import com.zjgsu.shuidiansys.common.ExcelUtils;
import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.service.IWaterCopyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class WaterCopyController {

    @Resource
    IWaterCopyService service;


    @ResponseBody
    @RequestMapping(value = "/api/water/copy/chg",method = {RequestMethod.POST})
    public ChangeWaterCopyRes changeWaterCopy(@RequestBody ChangeWaterCopyReq electricCopyReq){

        return service.change(electricCopyReq);
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/copy",method = {RequestMethod.GET})
    public GetWaterCopyListRes getWaterCopyList(@RequestParam int page){

        PageParam pageParam = new PageParam();
        pageParam.setCurrPage(page);
        return service.getList(pageParam);
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/copy/search",method = {RequestMethod.GET})
    public QueryWaterCopyRes queryWaterCopy(QueryWaterCopyReq electricCopyReq){

        return service.query(electricCopyReq);

    }


    @ResponseBody
    @RequestMapping(value = "/api/water/copy",method = {RequestMethod.DELETE})
    public DeleteWaterCopyRes deleteWaterCopy(@RequestBody DeleteWaterCopyReq electricCopyReq){
        return service.delete(electricCopyReq);
    }


    @RequestMapping(value = "/api/water/copy/export",method = {RequestMethod.GET})
    public void exportWaterCopyExcel(@RequestParam(value="ids[]") String[] ids, HttpServletRequest request, HttpServletResponse response) {
        //String[] idArray = ids.split(",");
        //System.out.println(idArray[2]);
        List<Map<String, Object>> data = service.exportExcel(Arrays.asList(ids));
        try {

            ExcelUtils.exportExcel("水表抄表", "水表抄表", ExcelModel.WATER_COPY_HEADER, ExcelModel.WATER_COPY_DS_TITLES, ExcelModel.WATER_COPY_DS_FORMAT, null, data, request, response);

        }catch (Exception e){
            System.out.println(e);
        }
    }


}
