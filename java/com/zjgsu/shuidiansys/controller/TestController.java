package com.zjgsu.shuidiansys.controller;

import com.sun.deploy.net.HttpResponse;
import com.zjgsu.shuidiansys.common.ExcelUtils;
import com.zjgsu.shuidiansys.common.ResponseUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import static com.zjgsu.shuidiansys.common.ExcelUtils.encodeChineseDownloadFileName;
import static com.zjgsu.shuidiansys.common.WordUtil.createWord;


@Controller
public class TestController {

    @RequestMapping(value = "/test/downloadExcel",method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        String[] excelHeader = {"ID", "国家", "商品编号", "名称", "分类", "创建日期"};
        String[] ds_titles = {"id", "country", "sn", "name", "productCategory", "createdDate"};
        int[] ds_format = {1, 1, 1, 1, 1, 1};
        try {
            List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("id","1");
            map.put("country","china");
            data.add(map);
            ExcelUtils.exportExcel("商品表", "商品", excelHeader, ds_titles, ds_format, null, data, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/test/uploadExcel",method = RequestMethod.POST)
    public void importExcel(@RequestParam(value="excelFile") MultipartFile file,
                            HttpServletRequest request,@RequestParam int number){
        String[] ds_titles = {"id", "country", "sn", "name", "productCategory", "createdDate"};
        Map<String,Object> map = ExcelUtils.importExcel(ds_titles,file);
        System.out.println(number);
        System.out.println(map.get("msg"));
        List<Map<String,Object>> mapList = (List<Map<String, Object>>) map.get("data");
        System.out.println(mapList.get(0).get("id"));
    }

    @RequestMapping(value = "/test/download",method = RequestMethod.GET)
    public ResponseEntity<byte[]> download( HttpServletRequest request) throws IOException {

        String filePath = "./res/test.doc";
        String outputFile = filePath;
        String filename = "test.doc";
        System.out.println("123456");
        OutputStream out=null;
        try {

            out = new FileOutputStream(outputFile);
        }catch (IOException e){
            System.out.println(e);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("nian1", "2018");
        map.put("yue1", "10");
        map.put("ri1", "10");
        map.put("nian2", "2018");
        map.put("yue2", "10");
        map.put("ri2", "11");
        map.put("yue3", "10");
        map.put("ri3", "11");
        map.put("huming", "admin");
        map.put("dizhi", "admin");
        map.put("biao1", "123");
        map.put("biao2", "");
        map.put("biao3", "");
        map.put("shangqi1", "105");
        map.put("shangqi2", "");
        map.put("shangqi3", "");
        map.put("benqi1", "200");
        map.put("benqi2", "");
        map.put("benqi3","");
        map.put("shiyong1","95");
        map.put("shiyong2","");
        map.put("shiyong3","");
        map.put("danjia","1.0");
        map.put("jine","95.0");
        map.put("beizhu","");


        File file = new File("./res/test.doc");
        System.out.println(file.getAbsolutePath());
        HttpHeaders headers = new HttpHeaders();
        String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
        headers.setContentDispositionFormData("attachment", downloadFielName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/test/upload",method = RequestMethod.POST)
    public void  springUpload(HttpServletRequest request) throws IllegalStateException, IOException
    {
        long  startTime=System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();

            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    String path="E:/TestWord"+file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                }

            }

        }
        long  endTime=System.currentTimeMillis();
        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
    }

    @ResponseBody
    @RequestMapping(value="/doUpload", method=RequestMethod.POST)
    public String doUploadFile(@RequestParam("prove") MultipartFile file){

        if(!file.isEmpty()){
            try {

                //这里将上传得到的文件保存至 d:\\temp\\file 目录
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File("e:\\TestWord",
                        System.currentTimeMillis()+ file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "success";  //上传成功则跳转至此success.jsp页面
    }
}
