package com.zjgsu.shuidiansys.service.impl;

import com.zjgsu.shuidiansys.common.ConstantString;
import com.zjgsu.shuidiansys.common.ExcelModel;
import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.common.Util;
import com.zjgsu.shuidiansys.dao.ITopWaterMeterDao;
import com.zjgsu.shuidiansys.dao.IUserCategoryDao;
import com.zjgsu.shuidiansys.dao.IUserInfoDao;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.pojo.TopElectricityMeter;
import com.zjgsu.shuidiansys.pojo.TopWaterMeter;
import com.zjgsu.shuidiansys.pojo.UserCategory;
import com.zjgsu.shuidiansys.pojo.UserInfo;
import com.zjgsu.shuidiansys.service.ITopWaterMeterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.DataFormatException;

@Service
public class TopWaterMeterServiceImpl implements ITopWaterMeterService {

    @Resource
    ITopWaterMeterDao dao;

    @Resource
    IUserInfoDao uidao;

    @Resource
    IUserCategoryDao ucdao;

    public int getRowCount() {
        return dao.getRowCount();
    }


    public GetTopWaterMeterListRes getTopWaterMeterList(PageParam pageParam) {
        int currPage = pageParam.getCurrPage();
        pageParam.setRowCount(getRowCount());
        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", offset);
        params.put("size", size);

        List<TopWaterMeter> topWaterMeterList = dao.selectByParams(params);

        GetTopWaterMeterListRes topWaterMeterRes = new GetTopWaterMeterListRes();
        GetTopWaterMeterListRes.Content content = topWaterMeterRes.new Content();
        List<GetTopWaterMeterListRes.Content.Info> infos = new ArrayList<GetTopWaterMeterListRes.Content.Info>();
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        topWaterMeterRes.setCode(ConstantString.QUEARY_SUCCESS);
        topWaterMeterRes.setMsg("共查询到数据"+getRowCount()+"条");


        for(TopWaterMeter topWater:topWaterMeterList){
            GetTopWaterMeterListRes.Content.Info info = content.new Info();
            info.setActualPrice(String.valueOf(topWater.getActualPrice()));
            info.setArea(topWater.getArea());
            info.setMeterCode(topWater.getWaterMeterId());
            info.setUserName(topWater.getUserName());
            info.setActualUsage(String.valueOf(topWater.getActualUsing()));
            info.setCurrentNum(String.valueOf(topWater.getThisDegree()));
            info.setPreNum(String.valueOf(topWater.getPreviousDegree()));
            info.setUnitprice(String.valueOf(topWater.getPrice()));
            info.setUserCode(topWater.getUserNumber());
            info.setRecordTime(String.valueOf(topWater.getDate()));
            info.setNominalUsage(String.valueOf(topWater.getNominalUsage()));
            info.setId(String.valueOf(topWater.getId()));
            infos.add(info);
        }
        content.setInfo(infos);
        topWaterMeterRes.setContent(content);


        return topWaterMeterRes;

    }

    public DeleteTopWaterMeterRes deleteTopWaterMeter(DeleteTopWaterMeterReq topWaterMeterReq) {
        int deleteFlag = dao.deleteTopWaterMeter(topWaterMeterReq.getIds());
        DeleteTopWaterMeterRes topWaterMeterRes = new DeleteTopWaterMeterRes();

        if(deleteFlag == 0){
            topWaterMeterRes.setCode(ConstantString.DELETE_FAILED);
            topWaterMeterRes.setMsg("删除数据失败");
        }else if(deleteFlag < topWaterMeterReq.getIds().size()){
            topWaterMeterRes.setCode(ConstantString.DELETE_PART_FAILED);
            topWaterMeterRes.setMsg("删除数据部分失败");
        }else{
            topWaterMeterRes.setCode(ConstantString.DELETE_SUCCESS);
            topWaterMeterRes.setMsg("删除数据成功");
        }
        return topWaterMeterRes;
    }

    public ChangeTopWaterMeterRes changeTopWaterMeter(ChangeTopWaterMeterReq topWaterMeterReq) {
        TopWaterMeter topWaterMeter = new TopWaterMeter();

        if (topWaterMeterReq.getActualPrice() != null && !topWaterMeterReq.getActualPrice().equals("")) {
            topWaterMeter.setActualPrice(Double.valueOf(topWaterMeterReq.getActualPrice()));
        }
        if (topWaterMeterReq.getActualUsage() != null && !topWaterMeterReq.getActualUsage().equals(""))
            topWaterMeter.setActualUsing(Integer.valueOf(topWaterMeterReq.getActualUsage()));
        topWaterMeter.setArea(topWaterMeterReq.getArea());
        topWaterMeter.setLocation(topWaterMeterReq.getLocation());
        if (topWaterMeterReq.getPreNum() != null && !topWaterMeterReq.getPreNum().equals(""))
            topWaterMeter.setPreviousDegree(Integer.valueOf(topWaterMeterReq.getPreNum()));
        if (topWaterMeterReq.getUnitprice() != null && !topWaterMeterReq.getUnitprice().equals(""))
            topWaterMeter.setPrice(Double.valueOf(topWaterMeterReq.getUnitprice()));
        topWaterMeter.setRemarks(topWaterMeterReq.getRemarks());
        topWaterMeter.setUserName(topWaterMeterReq.getUserName());
        topWaterMeter.setUserNumber(topWaterMeterReq.getUserCode());
        topWaterMeter.setWaterMeterId(topWaterMeterReq.getMeterCode());
        if (topWaterMeterReq.getCurrentNum() != null && !topWaterMeterReq.getCurrentNum().equals(""))
            topWaterMeter.setThisDegree(Integer.valueOf(topWaterMeterReq.getCurrentNum()));
        topWaterMeter.setId(Integer.valueOf(topWaterMeterReq.getId()));
        topWaterMeter.setNominalUsage(Integer.valueOf(topWaterMeterReq.getNominalUsage()));
        topWaterMeter.setDate(Util.formatToSqlDate(topWaterMeterReq.getRecordTime()));


        int updateFlag = dao.updateTopWaterMeter(topWaterMeter);
        ChangeTopWaterMeterRes userInfoRes = new ChangeTopWaterMeterRes();

        if(updateFlag == 0){
            userInfoRes.setCode(ConstantString.UPDATE_FAILED);
            userInfoRes.setMsg("更新数据失败");
        }else {
            userInfoRes.setCode(ConstantString.INSERT_SUCCESS);
            userInfoRes.setMsg("更新数据成功");

        }
        return userInfoRes;
    }

    @Transactional
    public ImportRes importTopWaterMeter(List<Map<String, Object>> data) {
        ImportRes importRes = new ImportRes();
        try{
            for(Map<String,Object> one:data){
                TopWaterMeter waterMeter = new TopWaterMeter();
                waterMeter.setUserName((String) one.get("userName"));
                waterMeter.setWaterMeterId((String)one.get("waterMeterId"));
                if(!"".equals(one.get("thisDegree"))&&one.get("thisDegree")!=null)
                    waterMeter.setThisDegree(Integer.valueOf((String)one.get("thisDegree")));
                waterMeter.setUserNumber((String)one.get("userNumber"));
                waterMeter.setRemarks((String)one.get("remarks"));
                if(!"".equals(one.get("price"))&&one.get("price")!=null)
                    waterMeter.setPrice(Double.valueOf((String)one.get("price")));
                if(!"".equals(one.get("previousDegree"))&&one.get("previousDegree")!=null)
                    waterMeter.setPreviousDegree(Integer.valueOf((String)one.get("previousDegree")));
                waterMeter.setLocation((String)one.get("location"));
                if(!"".equals(one.get("actualUsing"))&&one.get("actualUsing")!=null)
                    waterMeter.setActualUsing(Integer.valueOf((String)one.get("actualUsing")));
                if(!"".equals(one.get("actualPrice"))&&one.get("actualPrice")!=null)
                    waterMeter.setActualPrice(Double.valueOf((String)one.get("actualPrice")));
                try {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                    java.util.Date date = df.parse(df.format(new Date()));// new Date()为获取当前系统时间
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    waterMeter.setDate(sqlDate);
                }catch (Exception e){
                    System.out.println(e);
                }
                if(!"".equals(one.get("nominalUsage"))&&one.get("nominalUsage")!=null)
                    waterMeter.setNominalUsage(Integer.valueOf((String) one.get("nominalUsage")));
                dao.addTopWaterMeter(waterMeter);
            }
            importRes.setCode("200");
            importRes.setMsg("导入成功");
        }catch (Exception e){
            e.printStackTrace();
            importRes.setCode("500");
            importRes.setMsg("发生内部错误，请检查数据格式");
        }


        return importRes;
    }

    public List<Map<String, Object>> exportTopWaterMeter(List<String> ids) {
        List<Map<String,Object>> data = new ArrayList<Map<String, Object>>();
        List<TopWaterMeter> topWaterMeterList = dao.selectByIds(ids);
        int number = 0;
        for(TopWaterMeter one:topWaterMeterList){
            number+=1;
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("number",number);
            map.put("userName",one.getUserName());
            map.put("location",one.getLocation());
            map.put("userNumber",one.getUserNumber());
            map.put("previousDegree",one.getPreviousDegree());
            map.put("thisDegree",one.getThisDegree());
            map.put("actualUsing",one.getActualUsing());
            map.put("price",one.getPrice());
            map.put("actualPrice",one.getPrice());
            map.put("remarks",one.getRemarks());
            map.put("waterMeterId",one.getWaterMeterId());
            map.put("nominalUsage",one.getNominalUsage());
            map.put("date",one.getDate());
            data.add(map);
        }

        return data;
    }

    public GetTopWaterMeterListRes queryTopWaterMeter(QueryTopWaterMeterReq req) {

        PageParam pageParam = new PageParam();
        pageParam.setCurrPage(req.getPage());
        int currPage = pageParam.getCurrPage();
        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;
        int lastset = offset+size; // 最终条数

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("area",req.getArea());
        map.put("timeStart",req.getTime1());
        map.put("timeEnd",req.getTime2());
        System.out.println(req.getTime1());
        List<TopWaterMeter> topWaterMeterList = dao.selectByQuery(map);
        List<TopWaterMeter> topWaterMeters = new ArrayList<TopWaterMeter>();// 筛选同类型的一级水表
        if(!"".equals(req.getCat())&&req.getCat()!=null){
            System.out.println("Start compare cat!");
            for(TopWaterMeter one:topWaterMeterList){
                UserInfo user = uidao.selectByMeterId(one.getWaterMeterId());
                UserCategory userCat = ucdao.selectById(user.getUserCategoryId());
                if(userCat.getUserCategoryname().equals(req.getCat())){
                    topWaterMeters.add(one);
                }
            }
        }else{
            topWaterMeters=topWaterMeterList;
        }
        pageParam.setRowCount(topWaterMeters.size()); // 获取到实际条数之后插入分页类
        if(lastset>pageParam.getRowCount()){
            lastset = pageParam.getRowCount();
        }


        GetTopWaterMeterListRes topWaterMeterRes = new GetTopWaterMeterListRes();
        GetTopWaterMeterListRes.Content content = topWaterMeterRes.new Content();
        List<GetTopWaterMeterListRes.Content.Info> infos = new ArrayList<GetTopWaterMeterListRes.Content.Info>();
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        topWaterMeterRes.setCode(ConstantString.QUEARY_SUCCESS);
        topWaterMeterRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");


        for(int i= offset;i<lastset;i++){// 获取页码中的记录数
            TopWaterMeter topWater = topWaterMeters.get(i);
            GetTopWaterMeterListRes.Content.Info info = content.new Info();
            info.setActualPrice(String.valueOf(topWater.getActualPrice()));
            info.setArea(topWater.getArea());
            info.setMeterCode(topWater.getWaterMeterId());
            info.setUserName(topWater.getUserName());
            info.setActualUsage(String.valueOf(topWater.getActualUsing()));
            info.setCurrentNum(String.valueOf(topWater.getThisDegree()));
            info.setPreNum(String.valueOf(topWater.getPreviousDegree()));
            info.setUnitprice(String.valueOf(topWater.getPrice()));
            info.setUserCode(topWater.getUserNumber());
            info.setRecordTime(String.valueOf(topWater.getDate()));
            info.setId(String.valueOf(topWater.getId()));
            info.setNominalUsage(String.valueOf(topWater.getNominalUsage()));
            infos.add(info);
        }
        content.setInfo(infos);
        topWaterMeterRes.setContent(content);


        return topWaterMeterRes;
    }

    public List<Map<String, Object>> exportFeeChange(String id) {
        TopWaterMeter one = dao.selectById(id);
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("cell","原数据");
        map.put("number",one.getId());
        map.put("userName",one.getUserName());
        map.put("location",one.getLocation());
        map.put("userNumber",one.getUserNumber());
        map.put("previousDegree",one.getPreviousDegree());
        map.put("thisDegree",one.getThisDegree());
        map.put("actualUsing",one.getActualUsing());
        map.put("price",one.getPrice());
        map.put("actualPrice",one.getPrice());
        map.put("remarks",one.getRemarks());
        map.put("waterMeterId",one.getWaterMeterId());
        map.put("nominalUsage",one.getNominalUsage());
        map.put("date",one.getDate());
        Map<String,Object> map2 = new HashMap<String, Object>();
        map2.put("cell","新数据");
        data.add(map);
        data.add(map2);
        return data;
    }

    public List<Map<String, Object>> exportFeeDelete(List<String> ids) {
        List<Map<String,Object>> data = new ArrayList<Map<String, Object>>();
        List<TopWaterMeter> topWaterMeterList = dao.selectByIds(ids);
        for(TopWaterMeter one:topWaterMeterList){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("number",one.getId());
            map.put("userName",one.getUserName());
            map.put("location",one.getLocation());
            map.put("userNumber",one.getUserNumber());
            map.put("previousDegree",one.getPreviousDegree());
            map.put("thisDegree",one.getThisDegree());
            map.put("actualUsing",one.getActualUsing());
            map.put("price",one.getPrice());
            map.put("actualPrice",one.getPrice());
            map.put("remarks",one.getRemarks());
            map.put("waterMeterId",one.getWaterMeterId());
            map.put("nominalUsage",one.getNominalUsage());
            map.put("date",one.getDate());
            data.add(map);
        }

        return data;
    }
}
