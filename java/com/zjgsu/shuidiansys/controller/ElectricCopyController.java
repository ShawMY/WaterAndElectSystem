package com.zjgsu.shuidiansys.controller;

import com.zjgsu.shuidiansys.common.ExcelModel;
import com.zjgsu.shuidiansys.common.ExcelUtils;
import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.service.IElectricCopyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class ElectricCopyController {
    @Resource
    IElectricCopyService service;


    @ResponseBody
    @RequestMapping(value = "/api/electric/copy/chg",method = {RequestMethod.POST})
    public ChangeRes changeElectricCopy(@RequestBody ChangeElectricCopyReq electricCopyReq){

        return service.change(electricCopyReq);
    }

    @ResponseBody
    @RequestMapping(value = "/api/electric/copy",method = {RequestMethod.GET})
    public GetElectricCopyListRes getElectricCopyList(@RequestParam int page){

        PageParam pageParam = new PageParam();
        pageParam.setCurrPage(page);
        return service.getList(pageParam);
    }

    @ResponseBody
    @RequestMapping(value = "/api/electric/copy/search",method = {RequestMethod.GET})
    public QueryElectricCopyRes queryElectricCopy(QueryElectricCopyReq electricCopyReq){

        return service.query(electricCopyReq);

    }


    @ResponseBody
    @RequestMapping(value = "/api/electric/copy",method = {RequestMethod.DELETE})
    public DeleteRes deleteElectricCopy(@RequestBody DeleteReq electricCopyReq){
        return service.delete(electricCopyReq);
    }


    @RequestMapping(value = "/api/electric/copy/export",method = {RequestMethod.GET})
    public void exportElectricCopyExcel(@RequestParam("ids[]") String[] ids, HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Object>> data = service.exportExcel(Arrays.asList(ids));
        try {

            ExcelUtils.exportExcel("电表抄表", "电表抄表", ExcelModel.ELECTRIC_COPY_HEADER, ExcelModel.ELECTRIC_COPY_DS_TITLES, ExcelModel.ELECTRIC_COPY_DS_FORMAT, null, data, request, response);

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
