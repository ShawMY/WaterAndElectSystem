package com.zjgsu.shuidiansys.service.impl;

import com.zjgsu.shuidiansys.common.ConstantString;
import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.dao.IElectricCopyDao;
import com.zjgsu.shuidiansys.pojo.ElectricCopy;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.service.IElectricCopyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ElectricCopyServiceImpl implements IElectricCopyService {
    @Resource
    IElectricCopyDao dao;

    private int getRowCountByPage(){return  dao.getRowCountByPage();}

    private int getRowCountByQuery(Map<String,Object> query){return dao.getRowCountByQuery(query);}
    public GetElectricCopyListRes getList(PageParam pageParam) {
        int currPage = pageParam.getCurrPage();
        pageParam.setRowCount(getRowCountByPage());
        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", offset);
        params.put("size", size);

        List<ElectricCopy> electricCopyList = dao.selectByParams(params);

        GetElectricCopyListRes electricCopyListRes = new GetElectricCopyListRes();
        GetElectricCopyListRes.Content content = electricCopyListRes.new Content();
        List<GetElectricCopyListRes.Content.Info> infoList = new ArrayList<GetElectricCopyListRes.Content.Info>();
        electricCopyListRes.setCode(ConstantString.QUEARY_SUCCESS);
        electricCopyListRes.setMsg("共查询到数据" + pageParam.getRowCount() + "条");

        for (ElectricCopy one : electricCopyList) {
            GetElectricCopyListRes.Content.Info info = content.new Info();
            String addr = one.getFirstAddress() + one.getSecondAddress() +
                    one.getThirdAddress() + one.getFourthAddress() + one.getFifthAddress()
                    + one.getSixthAddress();
            info.setInstallAddress1(one.getFirstAddress());
            info.setInstallAddress2(one.getSecondAddress());
            info.setInstallAddress3(one.getThirdAddress());
            info.setInstallAddress4(one.getFourthAddress());
            info.setInstallAddress5(one.getFifthAddress());
            info.setInstallAddress6(one.getSixthAddress());
            info.setMeterCode(one.getElectricityMeterId());
            info.setPreviousNum(String.valueOf(one.getPreviousDegree()));
            info.setRecordPeople(one.getMeterReader());
            if (one.getMeterReadTime()!=null) {
                info.setRecordTime(one.getMeterReadTime().toString());
            }
            info.setCopyCode(String.valueOf(one.getId()));
            infoList.add(info);

        }

        content.setInfo(infoList);
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        electricCopyListRes.setContent(content);
        return electricCopyListRes;

    }

    public QueryElectricCopyRes query(QueryElectricCopyReq req) {
        ElectricCopy electric = new ElectricCopy();
        electric.setFirstAddress(req.getInstallAddress1());
        electric.setSecondAddress(req.getInstallAddress2());
        electric.setThirdAddress(req.getInstallAddress3());
        electric.setFourthAddress(req.getInstallAddress4());
        electric.setFifthAddress(req.getInstallAddress5());
        electric.setSixthAddress(req.getInstallAddress6());
        System.out.println(req.getInstallAddress1());

        PageParam pageParam = new PageParam();
        pageParam.setCurrPage(req.getPage());
        int currPage = pageParam.getCurrPage();

        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;


        Map<String,Object> query = new HashMap<String, Object>();
        query.put("electric",electric);
        query.put("beginTime",req.getRecordTime1());
        query.put("endTime",req.getRecordTime2());
        query.put("offset", offset);
        query.put("size", size);

        pageParam.setRowCount(getRowCountByQuery(query));
        List<ElectricCopy> electricCopyList=dao.selectByQuery(query);


        QueryElectricCopyRes electricCopyListRes = new QueryElectricCopyRes();
        QueryElectricCopyRes.Content content = electricCopyListRes.new Content();
        List<QueryElectricCopyRes.Content.Info> infoList = new ArrayList<QueryElectricCopyRes.Content.Info>();
        electricCopyListRes.setCode(ConstantString.QUEARY_SUCCESS);
        electricCopyListRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");

        try {

            for (ElectricCopy one : electricCopyList) {
                QueryElectricCopyRes.Content.Info info = content.new Info();
                String addr = one.getFirstAddress() + one.getSecondAddress() +
                        one.getThirdAddress() + one.getFourthAddress() + one.getFifthAddress()
                        + one.getSixthAddress();
                info.setInstallAddress1(one.getFirstAddress());
                info.setInstallAddress2(one.getSecondAddress());
                info.setInstallAddress3(one.getThirdAddress());
                info.setInstallAddress4(one.getFourthAddress());
                info.setInstallAddress5(one.getFifthAddress());
                info.setInstallAddress6(one.getSixthAddress());
                info.setMeterCode(one.getElectricityMeterId());
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
        electricCopyListRes.setContent(content);
        return electricCopyListRes;
    }

    public DeleteRes delete(DeleteReq req) {
        int deleteFlag = dao.delete(req.getIds());
        DeleteRes res = new DeleteRes();

        if(deleteFlag == 0){
            res.setCode(ConstantString.DELETE_FAILED);
            res.setMsg("删除数据失败");
        }else if(deleteFlag < req.getIds().size()){
            res.setCode(ConstantString.DELETE_PART_FAILED);
            res.setMsg("删除数据部分失败");
        }else{
            res.setCode(ConstantString.DELETE_SUCCESS);
            res.setMsg("删除数据成功");
        }
        return res;
    }

    public ChangeRes change(ChangeElectricCopyReq req) {
        ElectricCopy electric = new ElectricCopy();
        electric.setId(Integer.valueOf(req.getCopyCode()));
        electric.setFifthAddress(req.getInstallAddress1());
        electric.setSecondAddress(req.getInstallAddress2());
        electric.setThirdAddress(req.getInstallAddress3());
        electric.setFourthAddress(req.getInstallAddress4());
        electric.setFifthAddress(req.getInstallAddress5());
        electric.setSixthAddress(req.getInstallAddress6());
        electric.setMeterReader(req.getRecordPeople());
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        try {
            Date date = sdf.parse(req.getRecordTime());
            java.sql.Date uninstallTime = new java.sql.Date(date.getTime());
            electric.setMeterReadTime(uninstallTime);
        }catch (Exception e){
            System.out.println(e);
        }
        electric.setPreviousDegree(Integer.valueOf(req.getPreviousNum()));
        electric.setElectricityMeterId(req.getMeterCode());

        int updateFlag = dao.update(electric);
        ChangeRes electricCopyRes = new ChangeRes();

        if(updateFlag == 0){
            electricCopyRes.setCode(ConstantString.UPDATE_FAILED);
            electricCopyRes.setMsg("更新数据失败");
        }else {
            electricCopyRes.setCode(ConstantString.INSERT_SUCCESS);
            electricCopyRes.setMsg("更新数据成功");

        }

        return electricCopyRes;
    }

    public List<Map<String, Object>> exportExcel(List<String> ids) {
        List<ElectricCopy> electricCopyList = dao.selectByIds(ids);
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            java.util.Date date = df.parse(df.format(new Date()));// new Date()为获取当前系统时间
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            for(ElectricCopy one:electricCopyList){
                Map<String,Object> map = new HashMap<String, Object>();
                String addr = one.getFirstAddress() + one.getSecondAddress() +
                        one.getThirdAddress() + one.getFourthAddress() + one.getFifthAddress()
                        + one.getSixthAddress();
                map.put("electricMeterId",one.getElectricityMeterId());
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
