package com.zjgsu.shuidiansys.controller;

import com.zjgsu.shuidiansys.common.ExcelModel;
import com.zjgsu.shuidiansys.common.ExcelUtils;
import com.zjgsu.shuidiansys.service.IStatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Controller
public class StatisticsController {
    @Resource
    IStatisticsService service;

    @RequestMapping(value = "/api/electric/top/second/cmp",method = {RequestMethod.GET})
    public void exportWaterDelete(@RequestParam(value="year") String year, HttpServletRequest request, HttpServletResponse response) {
        //String[] idArray = ids.split(",");
        //System.out.println(idArray[2]);
        List<Map<String, Object>> data = service.getEYearTopSecondCompare(year);
        try {

            ExcelUtils.exportExcel("各生活园区一级电表支付与二级表回收对比", "表", ExcelModel.TOP_SECOND_CMP_HEADER, ExcelModel.TOP_SECOND_CMP_DS_TITLES, ExcelModel.TOP_SECOND_CMP_DS_FORMAT, null, data, request, response);

        }catch (IOException e){
            System.out.println(e);
        }
    }
}
