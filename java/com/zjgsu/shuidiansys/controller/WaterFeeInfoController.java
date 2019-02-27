package com.zjgsu.shuidiansys.controller;

import com.zjgsu.shuidiansys.common.ConstantString;
import com.zjgsu.shuidiansys.common.ExcelModel;
import com.zjgsu.shuidiansys.common.ExcelUtils;
import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.service.IWaterFeeInfoService;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class WaterFeeInfoController {

    @Resource
    IWaterFeeInfoService service;

    @ResponseBody
    @RequestMapping(value = "/api/water/price",method = {RequestMethod.GET})
    public GetWaterFeeListRes getWaterFeeList(@RequestParam int page){
        PageParam pageParam = new PageParam();
        pageParam.setCurrPage(page);
        return service.getWaterFeeList(pageParam);
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/price/search",method = {RequestMethod.GET})
    public QueryWaterFeeRes queryWaterFee(QueryWaterFeeReq waterFeeReq){
        return service.getWaterFeeListByQuery(waterFeeReq);
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/price",method = {RequestMethod.DELETE})
    public DeleteWaterFeeRes deleteWaterFee(@RequestBody DeleteWaterFeeReq waterFeeReq){
        return service.deleteWaterFee(waterFeeReq);
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/price/chg",method = {RequestMethod.POST})
    public ChangeWaterFeeRes changeWaterFee(@RequestBody ChangeWaterFeeReq waterFeeReq){
        return service.changeWaterFee(waterFeeReq);
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/price/copy",method = {RequestMethod.POST})
    public ImportRes importWaterCopy(@RequestParam(value="excelFile") MultipartFile file,
                                     HttpServletRequest request){
        Map<String,Object> map  = ExcelUtils.importExcel(ExcelModel.WATER_COPY_DS_TITLES,file);
        ImportRes waterCopyRes = new ImportRes();
        if(((String) map.get("code")).equals("500")) {
            waterCopyRes.setCode((String) map.get("code"));
            waterCopyRes.setMsg((String) map.get("msg"));
            return waterCopyRes;
        }
        waterCopyRes = service.importWaterCopy((List<Map<String,Object>>)map.get("data"));
        return waterCopyRes;
    }

    @RequestMapping(value = "/api/water/price/export",method = {RequestMethod.GET})
    public ResponseEntity<byte[]> exportWaterFee(@RequestParam("ids[]") String[] ids, HttpServletRequest request ) throws IOException {
        //System.out.println(ids[0]);
        service.exportWaterFeeInfo(Arrays.asList(ids));
        String fileName = "浙江工商大学水费缴费单.doc";
        String filePath = ConstantString.WORD_FILE_PATH+fileName;
        File file = new File(filePath);
        System.out.println(file.getAbsolutePath());
        HttpHeaders headers = new HttpHeaders();
        String downloadFielName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        headers.setContentDispositionFormData("attachment", downloadFielName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/water/price/chg",method = {RequestMethod.GET})
    public void exportWaterFeeChangeExcel(@RequestParam(value="id") String id, HttpServletRequest request, HttpServletResponse response) {
        //String[] idArray = ids.split(",");
        //System.out.println(idArray[2]);
        List<Map<String, Object>> data = service.exportWaterFeeChange(id);
        try {

            ExcelUtils.exportExcel("水表修改", "水表修改", ExcelModel.TABLE_HEADER, ExcelModel.TABLE_DS_TITLES, ExcelModel.TABLE_DS_FORMAT, null, data, request, response);

        }catch (IOException e){
            System.out.println(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/api/water/price/info",method = {RequestMethod.POST})
    public ImportRes importWaterFeeExcel(@RequestParam(value="excelFile") MultipartFile file,
                                     HttpServletRequest request){
        Map<String,Object> map  = ExcelUtils.importExcel(ExcelModel.TABLE_FEE_DS_TITLES,file);
        ImportRes waterCopyRes = new ImportRes();
        if(((String) map.get("code")).equals("500")) {
            waterCopyRes.setCode((String) map.get("code"));
            waterCopyRes.setMsg((String) map.get("msg"));
            return waterCopyRes;
        }
        waterCopyRes = service.importFeeExcel((List<Map<String,Object>>)map.get("data"));
        return waterCopyRes;
    }

    @RequestMapping(value = "/api/water/price/export/template",method = {RequestMethod.GET})
    public void exportWaterFeeExcelModel(@RequestParam(value="ids[]") String[] ids, HttpServletRequest request, HttpServletResponse response) {
        //String[] idArray = ids.split(",");
        //System.out.println(idArray[2]);
        List<Map<String, Object>> data = service.exportFeeExcelModel(Arrays.asList(ids));
        try {

            ExcelUtils.exportExcel("水表费用更新", "水表", ExcelModel.TABLE_FEE_HEADER, ExcelModel.TABLE_FEE_DS_TITLES, ExcelModel.TABLE_FEE_DS_FORMAT, null, data, request, response);

        }catch (IOException e){
            System.out.println(e);
        }
    }

    @RequestMapping(value = "/api/water/price/del",method = {RequestMethod.GET})
    public void exportWaterDelete(@RequestParam(value="ids[]") String[] ids, HttpServletRequest request, HttpServletResponse response) {
        //String[] idArray = ids.split(",");
        //System.out.println(idArray[2]);
        List<Map<String, Object>> data = service.exportFeeDelete(Arrays.asList(ids));
        try {

            ExcelUtils.exportExcel("水表删除", "水表", ExcelModel.TABLE_DLETE_HEADER, ExcelModel.TABLE_DLETE_DS_TITLES, ExcelModel.TABLE_DLETE_DS_FORMAT, null, data, request, response);

        }catch (IOException e){
            System.out.println(e);
        }
    }

    @ResponseBody
    @RequestMapping(value="/api/water/price/free/prove", method=RequestMethod.POST)
    public ImportRes doUploadFile(@RequestParam("prove") MultipartFile file,@RequestParam("id") String id){

        ImportRes importRes = new ImportRes();
        if(!file.isEmpty()){
            if(file.getSize() / 1024>ConstantString.MAX_IMAGE_SIZE ){
                importRes.setCode("300");
                importRes.setMsg("图片过大（图片大小不得大于10M）");
                return importRes;
            }
            String orinFileName = file.getOriginalFilename();
            int pos = orinFileName.lastIndexOf(".");
            String extName = orinFileName.substring(pos);
            String fileName = id+extName;
            String path = ConstantString.PIC_WATER_FILE_PATH+fileName;
            importRes = service.setProve(id,path);
            try {

                //这里将上传得到的文件保存至 d:\\temp\\file 目录
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(ConstantString.PIC_WATER_FILE_PATH,
                        fileName));
                return importRes;
            } catch (IOException e) {
                e.printStackTrace();
                importRes.setCode("250");
                importRes.setMsg("更新数据库成功，上传失败。");
                return importRes;
            }
        }
        return importRes;

    }

    @RequestMapping(value = "/api/water/price/free/prove/download",method = {RequestMethod.GET})
    public ResponseEntity<byte[]> downloadProve(@RequestParam("id") String id, HttpServletRequest request ) throws IOException {
        //System.out.println(ids[0]);
        String filePath = service.getProve(id);

        //String filePath = ConstantString.WORD_FILE_PATH+fileName;
        File file = new File(filePath);
        if(file!=null) {
            String fileName = file.getName();
            //System.out.println(file.getAbsolutePath());
            HttpHeaders headers = new HttpHeaders();
            String downloadFielName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            headers.setContentDispositionFormData("attachment", downloadFielName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        }
        return null;
    }

}
