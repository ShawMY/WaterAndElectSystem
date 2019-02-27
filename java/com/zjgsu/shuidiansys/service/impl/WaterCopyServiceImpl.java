package com.zjgsu.shuidiansys.service.impl;

import com.sun.deploy.net.HttpResponse;
import com.zjgsu.shuidiansys.common.ConstantString;
import com.zjgsu.shuidiansys.common.ExcelModel;
import com.zjgsu.shuidiansys.common.ExcelUtils;
import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.dao.IWaterCopyDao;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.pojo.WaterCopy;
import com.zjgsu.shuidiansys.service.IWaterCopyService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WaterCopyServiceImpl implements IWaterCopyService {


    @Resource
    IWaterCopyDao dao;

    private int getRowCountByPage(){return  dao.getRowCountByPage();}

    private int getRowCountByQuery( Map<String,Object> query){return dao.getRowCountByQuery(query);}
    public GetWaterCopyListRes getList(PageParam pageParam) {
        int currPage = pageParam.getCurrPage();
        pageParam.setRowCount(getRowCountByPage());
        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", offset);
        params.put("size", size);

        List<WaterCopy> waterCopyList = dao.selectByParams(params);

        GetWaterCopyListRes waterCopyListRes = new GetWaterCopyListRes();
        GetWaterCopyListRes.Content content = waterCopyListRes.new Content();
        List<GetWaterCopyListRes.Content.Info> infoList = new ArrayList<GetWaterCopyListRes.Content.Info>();
        waterCopyListRes.setCode(ConstantString.QUEARY_SUCCESS);
        waterCopyListRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");
        try {

            for (WaterCopy one : waterCopyList) {
                GetWaterCopyListRes.Content.Info info = content.new Info();
                String addr = one.getFirstAddress() + one.getSecondAddress() +
                        one.getThirdAddress() + one.getFourthAddress() + one.getFifthAddress()
                        + one.getSixthAddress();
                info.setInstallAddress1(one.getFirstAddress());
                info.setInstallAddress2(one.getSecondAddress());
                info.setInstallAddress3(one.getThirdAddress());
                info.setInstallAddress4(one.getFourthAddress());
                info.setInstallAddress5(one.getFifthAddress());
                info.setInstallAddress6(one.getSixthAddress());

                info.setMeterCode(one.getWaterMeterId());
                info.setPreviousNum(String.valueOf(one.getPreviousDegree()));
                info.setRecordPeople(one.getMeterReader());
                info.setRecordTime(one.getMeterReadTime().toString());
                info.setCopyCode(String.valueOf(one.getId()));
                infoList.add(info);

            }
        }catch (Exception e){
            System.out.println(e);
        }
        content.setInfo(infoList);
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        waterCopyListRes.setContent(content);
        return waterCopyListRes;
    }

    public QueryWaterCopyRes query(QueryWaterCopyReq waterCopyReq) {
        WaterCopy water = new WaterCopy();
        water.setFifthAddress(waterCopyReq.getInstallAddress1());
        water.setSecondAddress(waterCopyReq.getInstallAddress2());
        water.setThirdAddress(waterCopyReq.getInstallAddress3());
        water.setFourthAddress(waterCopyReq.getInstallAddress4());
        water.setFifthAddress(waterCopyReq.getInstallAddress5());
        water.setSixthAddress(waterCopyReq.getInstallAddress6());

        PageParam pageParam = new PageParam();
        pageParam.setCurrPage(waterCopyReq.getPage());
        int currPage = pageParam.getCurrPage();

        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;

        Map<String,Object> query = new HashMap<String, Object>();
        query.put("water",water);
        query.put("beginTime",waterCopyReq.getRecordTime1());
        query.put("endTime",waterCopyReq.getRecordTime2());
        query.put("offset", offset);
        query.put("size", size);

        pageParam.setRowCount(getRowCountByQuery(query));

        List<WaterCopy> waterCopyList=dao.selectByQuery(query);
        QueryWaterCopyRes waterCopyListRes = new QueryWaterCopyRes();
        QueryWaterCopyRes.Content content = waterCopyListRes.new Content();
        List<QueryWaterCopyRes.Content.Info> infoList = new ArrayList<QueryWaterCopyRes.Content.Info>();
        waterCopyListRes.setCode(ConstantString.QUEARY_SUCCESS);
        waterCopyListRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");

        try {

            for (WaterCopy one : waterCopyList) {
                QueryWaterCopyRes.Content.Info info = content.new Info();
                String addr = one.getFirstAddress() + one.getSecondAddress() +
                        one.getThirdAddress() + one.getFourthAddress() + one.getFifthAddress()
                        + one.getSixthAddress();
                info.setInstallAddress1(one.getFirstAddress());
                info.setInstallAddress2(one.getSecondAddress());
                info.setInstallAddress3(one.getThirdAddress());
                info.setInstallAddress4(one.getFourthAddress());
                info.setInstallAddress5(one.getFifthAddress());
                info.setInstallAddress6(one.getSixthAddress());
                info.setMeterCode(one.getWaterMeterId());
                info.setPreviousNum(String.valueOf(one.getPreviousDegree()));
                info.setRecordPeople(one.getMeterReader());
                if(one.getMeterReadTime()!=null) {
                    info.setRecordTime(one.getMeterReadTime().toString());
                }
                info.setCopyCode(String.valueOf(one.getId()));
                infoList.add(info);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        content.setInfo(infoList);
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        waterCopyListRes.setContent(content);
        return waterCopyListRes;
    }

    public DeleteWaterCopyRes delete(DeleteWaterCopyReq waterCopyReq) {

        int deleteFlag = dao.delete(waterCopyReq.getIds());
        DeleteWaterCopyRes waterCopyRes = new DeleteWaterCopyRes();

        if(deleteFlag == 0){
            waterCopyRes.setCode(ConstantString.DELETE_FAILED);
            waterCopyRes.setMsg("删除数据失败");
        }else if(deleteFlag < waterCopyReq.getIds().size()){
            waterCopyRes.setCode(ConstantString.DELETE_PART_FAILED);
            waterCopyRes.setMsg("删除数据部分失败");
        }else{
            waterCopyRes.setCode(ConstantString.DELETE_SUCCESS);
            waterCopyRes.setMsg("删除数据成功");
        }
        return waterCopyRes;
    }

    public ChangeWaterCopyRes change(ChangeWaterCopyReq waterCopyReq) {
        WaterCopy water = new WaterCopy();
        water.setId(Integer.valueOf(waterCopyReq.getCopyCode()));
        water.setFifthAddress(waterCopyReq.getInstallAddress1());
        water.setSecondAddress(waterCopyReq.getInstallAddress2());
        water.setThirdAddress(waterCopyReq.getInstallAddress3());
        water.setFourthAddress(waterCopyReq.getInstallAddress4());
        water.setFifthAddress(waterCopyReq.getInstallAddress5());
        water.setSixthAddress(waterCopyReq.getInstallAddress6());
        water.setMeterReader(waterCopyReq.getRecordPeople());
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        try {
            Date date = sdf.parse(waterCopyReq.getRecordTime());
            java.sql.Date uninstallTime = new java.sql.Date(date.getTime());
            water.setMeterReadTime(uninstallTime);
        }catch (Exception e){
            System.out.println(e);
        }
        water.setPreviousDegree(Integer.valueOf(waterCopyReq.getPreviousNum()));
        water.setWaterMeterId(waterCopyReq.getMeterCode());

        int updateFlag = dao.update(water);
        ChangeWaterCopyRes waterCopyRes = new ChangeWaterCopyRes();

        if(updateFlag == 0){
            waterCopyRes.setCode(ConstantString.UPDATE_FAILED);
            waterCopyRes.setMsg("更新数据失败");
        }else {
            waterCopyRes.setCode(ConstantString.INSERT_SUCCESS);
            waterCopyRes.setMsg("更新数据成功");

        }

        return waterCopyRes;
    }

    public List<Map<String,Object>> exportExcel(List<String> ids) {
        List<WaterCopy> waterCopyList = dao.selectByIds(ids);
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            java.util.Date date = df.parse(df.format(new Date()));// new Date()为获取当前系统时间
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            for(WaterCopy one:waterCopyList){
                Map<String,Object> map = new HashMap<String, Object>();
                String addr = one.getFirstAddress() + one.getSecondAddress() +
                        one.getThirdAddress() + one.getFourthAddress() + one.getFifthAddress()
                        + one.getSixthAddress();
                map.put("waterMeterId",one.getWaterMeterId());
                map.put("installAddress",addr);
                map.put("previousDegree",one.getPreviousDegree());
                data.add(map);
                one.setMeterReadTime(sqlDate);
                System.out.println(one.getMeterReadTime());
                dao.update(one);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return data;
    }
}
