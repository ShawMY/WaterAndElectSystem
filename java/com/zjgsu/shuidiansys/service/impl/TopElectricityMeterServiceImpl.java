package com.zjgsu.shuidiansys.service.impl;

import com.zjgsu.shuidiansys.common.ConstantString;
import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.common.Util;
import com.zjgsu.shuidiansys.dao.ITopElectricMeterDao;
import com.zjgsu.shuidiansys.dao.IUserCategoryDao;
import com.zjgsu.shuidiansys.dao.IUserInfoDao;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.pojo.TopElectricityMeter;
import com.zjgsu.shuidiansys.pojo.UserCategory;
import com.zjgsu.shuidiansys.pojo.UserInfo;
import com.zjgsu.shuidiansys.service.ITopElectricityMeterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TopElectricityMeterServiceImpl implements ITopElectricityMeterService {

    @Resource
    ITopElectricMeterDao dao;

    @Resource
    IUserInfoDao uidao;

    @Resource
    IUserCategoryDao ucdao;

    public int getRowCount() {
        return dao.getRowCount();
    }

    public GetTopElectricityMeterListRes getTopElectricityMeterRes(PageParam pageParam) {
        int currPage = pageParam.getCurrPage();
        pageParam.setRowCount(getRowCount());
        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", offset);
        params.put("size", size);

        List<TopElectricityMeter> topElectricMeterList = dao.selectByParams(params);

        GetTopElectricityMeterListRes topElectricityMeterRes = new GetTopElectricityMeterListRes();
        GetTopElectricityMeterListRes.Content content = topElectricityMeterRes.new Content();
        List<GetTopElectricityMeterListRes.Content.Info> infoList = new ArrayList<GetTopElectricityMeterListRes.Content.Info>();
        topElectricityMeterRes.setCode(ConstantString.QUEARY_SUCCESS);
        topElectricityMeterRes.setMsg("共查询到数据"+getRowCount()+"条");

        for(TopElectricityMeter topElectric:topElectricMeterList){
            GetTopElectricityMeterListRes.Content.Info info = content.new Info();
            info.setActualPrice(String.valueOf(topElectric.getActualPrice()));
            info.setArea(topElectric.getArea());
            info.setMeterCode(topElectric.getElectricityMeterId());
            info.setUserName(topElectric.getUserName());
            info.setActualUsage(String.valueOf(topElectric.getActualUsing()));
            info.setCurrentNum(String.valueOf(topElectric.getThisDegree()));
            info.setPreNum(String.valueOf(topElectric.getPreviousDegree()));
            info.setUnitprice(String.valueOf(topElectric.getPrice()));
            info.setUserCode(topElectric.getUserNumber());
            info.setDamage(String.valueOf(topElectric.getCopperLoss()));
            info.setRatio(String.valueOf(topElectric.getMagnification()));
            info.setRecordTime(String.valueOf(topElectric.getDate()));
            info.setLocation(topElectric.getLocation());
            info.setAdjustPrice(String.valueOf(topElectric.getAdjustPrice()));
            info.setId(String.valueOf(topElectric.getId()));
            infoList.add(info);
        }

        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        content.setInfo(infoList);
        topElectricityMeterRes.setContent(content);

        return topElectricityMeterRes;
    }

    public DeleteTopElectricityMeterRes deleteTopElectricityMeterRes(DeleteTopElectricityMeterReq topElectricMeterReq) {
        int deleteFlag = dao.deleteTopElectricityMeter(topElectricMeterReq.getIds());
        DeleteTopElectricityMeterRes topElectricityMeterRes = new DeleteTopElectricityMeterRes();

        if(deleteFlag == 0){
            topElectricityMeterRes.setCode(ConstantString.DELETE_FAILED);
            topElectricityMeterRes.setMsg("删除数据失败");
        }else if(deleteFlag < topElectricMeterReq.getIds().size()){
            topElectricityMeterRes.setCode(ConstantString.DELETE_PART_FAILED);
            topElectricityMeterRes.setMsg("删除数据部分失败");
        }else{
            topElectricityMeterRes.setCode(ConstantString.DELETE_SUCCESS);
            topElectricityMeterRes.setMsg("删除数据成功");
        }
        return topElectricityMeterRes;
    }

    public ChangeTopElectricityMeterRes changeTopElectricityMeterRes(ChangeTopElectricityMeterReq topElectricMeterReq) {
        TopElectricityMeter topElectricityMeter = new TopElectricityMeter();
        topElectricityMeter.setId(Integer.valueOf(topElectricMeterReq.getId()));
        topElectricityMeter.setActualPrice(Double.valueOf(topElectricMeterReq.getActualPrice()));
        topElectricityMeter.setActualUsing(Integer.valueOf(topElectricMeterReq.getActualUsage()));
        topElectricityMeter.setArea(topElectricMeterReq.getArea());
        topElectricityMeter.setLocation(topElectricMeterReq.getLocation());
        topElectricityMeter.setPreviousDegree(Integer.valueOf(topElectricMeterReq.getPreNum()));
        topElectricityMeter.setPrice(Double.valueOf(topElectricMeterReq.getUnitPrice()));
        topElectricityMeter.setUserName(topElectricMeterReq.getUserName());
        topElectricityMeter.setUserNumber(topElectricMeterReq.getUserCode());
        topElectricityMeter.setElectricityMeterId(topElectricMeterReq.getMeterCode());
        topElectricityMeter.setThisDegree(Integer.valueOf(topElectricMeterReq.getCurrentNum()));
        topElectricityMeter.setAdjustPrice(Double.valueOf(topElectricMeterReq.getAdjustPrice()));
        topElectricityMeter.setCopperLoss(Integer.valueOf(topElectricMeterReq.getDamage()));
        topElectricityMeter.setMagnification(Integer.valueOf(topElectricMeterReq.getRatio()));
        topElectricityMeter.setDate(Util.formatToSqlDate(topElectricMeterReq.getRecordTime()));

        int updateFlag = dao.updateTopElectricityMeter(topElectricityMeter);
        ChangeTopElectricityMeterRes userInfoRes = new ChangeTopElectricityMeterRes();

        if(updateFlag == 0){
            userInfoRes.setCode(ConstantString.UPDATE_FAILED);
            userInfoRes.setMsg("更新数据失败");
        }else {
            userInfoRes.setCode(ConstantString.INSERT_SUCCESS);
            userInfoRes.setMsg("更新数据成功");

        }
        return userInfoRes;
    }

    public ImportRes importTopElectricityMeter(List<Map<String, Object>> data) {
        ImportRes importRes = new ImportRes();
//        try{
            for(Map<String,Object> one:data){
                TopElectricityMeter electricityMeter = new TopElectricityMeter();
                electricityMeter.setUserName((String) one.get("userName"));
                electricityMeter.setElectricityMeterId((String)one.get("electricityMeterId"));
                if(!"".equals(one.get("copperLoss")))
                    electricityMeter.setCopperLoss(Integer.valueOf((String) one.get("copperLoss")));
                if(!"".equals(one.get("magnification")))
                    electricityMeter.setMagnification(Integer.valueOf((String)one.get("magnification")));
                if(!"".equals(one.get("thisDegree")))
                    electricityMeter.setThisDegree(Integer.valueOf((String)one.get("thisDegree")));
                electricityMeter.setUserNumber((String)one.get("userNumber"));
                if(!"".equals(one.get("price")))
                    electricityMeter.setPrice(Double.valueOf((String)one.get("price")));
                if(!"".equals(one.get("previousDegree")))
                    electricityMeter.setPreviousDegree(Integer.valueOf((String)one.get("previousDegree")));
                electricityMeter.setLocation((String)one.get("location"));
                if(!"".equals(one.get("actualUsing")))
                    electricityMeter.setActualUsing(Integer.valueOf((String)one.get("actualUsing")));
                if(!"".equals(one.get("actualPrice")))
                    electricityMeter.setActualPrice(Double.valueOf((String)one.get("actualPrice")));
                if(!"".equals(one.get("adjustPrice")))
                    electricityMeter.setAdjustPrice(Double.valueOf((String)one.get("adjustPrice")));
                if(!"".equals(one.get("date")))
                    electricityMeter.setDate(Util.formatToSqlDate((String)one.get("date")));
                try {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                    java.util.Date date = df.parse(df.format(new Date()));// new Date()为获取当前系统时间
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    electricityMeter.setDate(sqlDate);
                }catch (Exception e){
                    System.out.println(e);
                }
                dao.addTopElectricityMeter(electricityMeter);
            }
            importRes.setCode("200");
            importRes.setMsg("导出成功");
//        }catch (Exception e){
//            System.out.println(e);
//            importRes.setCode("500");
//            importRes.setMsg("发生内部错误，请检查数据格式");
//        }


        return importRes;
    }

    public List<Map<String, Object>> exportTopElectricityMeter(List<String> ids) {
        List<Map<String,Object>> data = new ArrayList<Map<String, Object>>();
        List<TopElectricityMeter> topElectricityMeterList = dao.selectByIds(ids);
        int number = 0;
        for(TopElectricityMeter one:topElectricityMeterList){
            number+=1;
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("number",number);
            map.put("userName",one.getUserName());
            map.put("location",one.getLocation());
            map.put("userNumber",one.getUserNumber());
            map.put("previousDegree",one.getPreviousDegree());
            map.put("thisDegree",one.getThisDegree());
            map.put("magnification",one.getMagnification());
            map.put("copperLoss",one.getMagnification());
            map.put("actualUsing",one.getActualUsing());
            map.put("price",one.getPrice());
            map.put("adjustPrice",one.getAdjustPrice());
            map.put("actualPrice",one.getPrice());
            map.put("electricityMeterId",one.getElectricityMeterId());
            map.put("date",one.getDate());
            data.add(map);
        }

        return data;
    }

    public GetTopElectricityMeterListRes queryTopElectricMeter(QueryTopElectricMeterReq req) {

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
        map.put("userNumber",req.getUserCode());
        map.put("electricityMeterId",req.getMeterCode());
        //System.out.println(req.getTime1());
        List<TopElectricityMeter> topElectricMeterList = dao.selectByQuery(map);
        List<TopElectricityMeter> topElectricMeters = new ArrayList<TopElectricityMeter>();// 筛选同类型的一级水表
        if(!"".equals(req.getCat())&&req.getCat()!=null){
            System.out.println("Start compare cat!");
            for(TopElectricityMeter one:topElectricMeterList){
                UserInfo user = uidao.selectByMeterId(one.getElectricityMeterId());
                UserCategory userCat = ucdao.selectById(user.getUserCategoryId());
                if(userCat.getUserCategoryname().equals(req.getCat())){
                    topElectricMeters.add(one);
                }
            }
        }else{
            topElectricMeters=topElectricMeterList;
        }
        pageParam.setRowCount(topElectricMeters.size()); // 获取到实际条数之后插入分页类
        if(lastset>pageParam.getRowCount()){
            lastset = pageParam.getRowCount();
        }


        GetTopElectricityMeterListRes topElectricMeterRes = new GetTopElectricityMeterListRes();
        GetTopElectricityMeterListRes.Content content = topElectricMeterRes.new Content();
        List<GetTopElectricityMeterListRes.Content.Info> infos = new ArrayList<GetTopElectricityMeterListRes.Content.Info>();
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        topElectricMeterRes.setCode(ConstantString.QUEARY_SUCCESS);
        topElectricMeterRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");

        //System.out.println("lastset:"+lastset);
        for(int i= offset;i<lastset;i++){// 获取页码中的记录数
            TopElectricityMeter topElectric = topElectricMeters.get(i);
            GetTopElectricityMeterListRes.Content.Info info = content.new Info();
            info.setActualPrice(String.valueOf(topElectric.getActualPrice()));
            info.setArea(topElectric.getArea());
            info.setMeterCode(topElectric.getElectricityMeterId());
            info.setUserName(topElectric.getUserName());
            info.setActualUsage(String.valueOf(topElectric.getActualUsing()));
            info.setCurrentNum(String.valueOf(topElectric.getThisDegree()));
            info.setPreNum(String.valueOf(topElectric.getPreviousDegree()));
            info.setUnitprice(String.valueOf(topElectric.getPrice()));
            info.setUserCode(topElectric.getUserNumber());
            info.setRecordTime(String.valueOf(topElectric.getDate()));
            info.setId(String.valueOf(topElectric.getId()));
            info.setDamage(String.valueOf(topElectric.getCopperLoss()));
            info.setAdjustPrice(String.valueOf(topElectric.getAdjustPrice()));
            info.setRatio(String.valueOf(topElectric.getMagnification()));
            info.setLocation(String.valueOf(topElectric.getLocation()));
            infos.add(info);
        }
        content.setInfo(infos);
        topElectricMeterRes.setContent(content);


        return topElectricMeterRes;
    }

    public List<Map<String, Object>> exportFeeChange(String id) {
        TopElectricityMeter one = dao.selectById(id);
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("cell","原数据");
        map.put("number",one.getId());
        map.put("userName",one.getUserName());
        map.put("location",one.getLocation());
        map.put("userNumber",one.getUserNumber());
        map.put("previousDegree",one.getPreviousDegree());
        map.put("thisDegree",one.getThisDegree());
        map.put("magnification",one.getMagnification());
        map.put("copperLoss",one.getMagnification());
        map.put("actualUsing",one.getActualUsing());
        map.put("price",one.getPrice());
        map.put("adjustPrice",one.getAdjustPrice());
        map.put("actualPrice",one.getPrice());
        map.put("electricityMeterId",one.getElectricityMeterId());
        map.put("date",one.getDate());
        Map<String,Object> map2 = new HashMap<String, Object>();
        map2.put("cell","新数据");
        data.add(map);
        data.add(map2);
        return data;
    }

    public List<Map<String, Object>> exportFeeDelete(List<String> ids) {
        List<Map<String,Object>> data = new ArrayList<Map<String, Object>>();
        List<TopElectricityMeter> topElectricMeterList = dao.selectByIds(ids);
        for(TopElectricityMeter one:topElectricMeterList){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("number",one.getId());
            map.put("userName",one.getUserName());
            map.put("location",one.getLocation());
            map.put("userNumber",one.getUserNumber());
            map.put("previousDegree",one.getPreviousDegree());
            map.put("thisDegree",one.getThisDegree());
            map.put("magnification",one.getMagnification());
            map.put("copperLoss",one.getMagnification());
            map.put("actualUsing",one.getActualUsing());
            map.put("price",one.getPrice());
            map.put("adjustPrice",one.getAdjustPrice());
            map.put("actualPrice",one.getPrice());
            map.put("electricityMeterId",one.getElectricityMeterId());
            map.put("date",one.getDate());
            data.add(map);
        }

        return data;
    }
}
