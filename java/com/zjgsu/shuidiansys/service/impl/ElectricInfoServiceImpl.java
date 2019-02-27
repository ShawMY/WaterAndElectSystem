package com.zjgsu.shuidiansys.service.impl;

import com.zjgsu.shuidiansys.common.ConstantString;
import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.common.PinyinUtils;
import com.zjgsu.shuidiansys.dao.IElectricHistoryDao;
import com.zjgsu.shuidiansys.dao.IElectricInfoDao;
import com.zjgsu.shuidiansys.pojo.ElectricHistory;
import com.zjgsu.shuidiansys.pojo.ElectricInfo;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.service.IElectricInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ElectricInfoServiceImpl implements IElectricInfoService {

    @Resource
    IElectricInfoDao dao;
    @Resource
    IElectricHistoryDao hdao;

    public int getRowCountByPage(){return dao.getRowCountByPage();}

    public int getRowCountByQuery(ElectricInfo info){return dao.getRowCountByQuery(info);}

    public int getHistoryRowCount(){return  hdao.getRowCountByPage();}


    public GetElectricInfoListRes getElectricInfoList(PageParam pageParam) {
        int currPage = pageParam.getCurrPage();
        pageParam.setRowCount(getRowCountByPage());
        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", offset);
        params.put("size", size);

        List<ElectricInfo> electricInfoList = dao.selectByParams(params);

        GetElectricInfoListRes electricListRes = new GetElectricInfoListRes();
        GetElectricInfoListRes.Content content = electricListRes.new Content();
        List<GetElectricInfoListRes.Content.Info> infoList = new ArrayList<GetElectricInfoListRes.Content.Info>();
        electricListRes.setCode(ConstantString.QUEARY_SUCCESS);
        electricListRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");

        for(ElectricInfo electricInfo:electricInfoList){
            GetElectricInfoListRes.Content.Info info = content.new Info();
            info.setArea(electricInfo.getFirstAddress());
            info.setChargeAccount(electricInfo.getCharger());
            info.setCode(electricInfo.getElectricityMeterId());
            info.setFifthAddress(electricInfo.getFifthAddress());
            info.setFirstAddress(electricInfo.getFirstAddress());
            info.setFourthAddress(electricInfo.getFourthAddress());
            if(electricInfo.getInstallTime()!=null) {
                info.setInstallTime(electricInfo.getInstallTime().toString());
            }
            info.setLevel(electricInfo.getLevel());
            info.setMaxScale(String.valueOf(electricInfo.getMaxDegree()));
            info.setOriginScale(String.valueOf(electricInfo.getMeterBottom()));
            info.setPreCode(electricInfo.getSuperiorId());
            info.setRatio(String.valueOf(electricInfo.getMagnification()));
            info.setReceivableAccount(electricInfo.getReceivableParty());
            info.setSecondAddress(electricInfo.getSecondAddress());
            info.setSixthAddress(electricInfo.getSixthAddress());
            info.setStatus(electricInfo.getState());
            info.setThirdAddress(electricInfo.getThirdAddress());
            info.setTransCount(String.valueOf(electricInfo.getFlipTime()));
            if (electricInfo.getScrappedTime()!=null) {
                info.setUninstallTime(electricInfo.getScrappedTime().toString());
            }
            infoList.add(info);
        }
        content.setInfo(infoList);
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        electricListRes.setContent(content);
        return electricListRes;
    }

    public QueryElectricInfoRes queryElectricInfo(QueryElectricInfoReq electricInfoReq) {
        ElectricInfo electric = new ElectricInfo();
        electric.setCharger(electricInfoReq.getChargeAccount());
        electric.setFifthAddress(electricInfoReq.getFifthAddress());
        electric.setFirstAddress(electricInfoReq.getFirstAddress());
        electric.setFourthAddress(electricInfoReq.getFourthAddress());

        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        try {
            Date date = sdf.parse(electricInfoReq.getInstallTime());
            java.sql.Date installTime = new java.sql.Date(date.getTime());
            electric.setInstallTime(installTime);
        }catch (Exception e){
            System.out.println(e);
        }
        electric.setLevel(electricInfoReq.getLevel());
        electric.setReceivableParty(electricInfoReq.getReceivableAccount());
        electric.setSecondAddress(electricInfoReq.getSecondAddress());
        electric.setSixthAddress(electricInfoReq.getSixthAddress());
        electric.setThirdAddress(electricInfoReq.getThirdAddress());
        electric.setElectricityMeterId(electricInfoReq.getCode());

        PageParam pageParam = new PageParam();
        int currPage = electricInfoReq.getPage();
        pageParam.setCurrPage(currPage);
        pageParam.setRowCount(getRowCountByQuery(electric));
        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", offset);
        params.put("size", size);
        params.put("electric",electric);

        List<ElectricInfo> electricInfoList = dao.selectByQuery(params);

        QueryElectricInfoRes electricListRes = new QueryElectricInfoRes();
        QueryElectricInfoRes.Content content = electricListRes.new Content();
        List<QueryElectricInfoRes.Content.Info> infoList = new ArrayList<QueryElectricInfoRes.Content.Info>();
        electricListRes.setCode(ConstantString.QUEARY_SUCCESS);
        electricListRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");

        for(ElectricInfo electricInfo:electricInfoList){
            QueryElectricInfoRes.Content.Info info = content.new Info();
            info.setArea(electricInfo.getFirstAddress());
            info.setChargeAccount(electricInfo.getCharger());
            info.setCode(electricInfo.getElectricityMeterId());
            info.setFifthAddress(electricInfo.getFifthAddress());
            info.setFirstAddress(electricInfo.getFirstAddress());
            info.setFourthAddress(electricInfo.getFourthAddress());
            if(electricInfo.getInstallTime()!=null) {
                info.setInstallTime(electricInfo.getInstallTime().toString());
            }
            info.setLevel(electricInfo.getLevel());
            info.setMaxScale(String.valueOf(electricInfo.getMaxDegree()));
            info.setOriginScale(String.valueOf(electricInfo.getMeterBottom()));
            info.setPreCode(electricInfo.getSuperiorId());
            info.setRatio(String.valueOf(electricInfo.getMagnification()));
            info.setReceivableAccount(electricInfo.getReceivableParty());
            info.setSecondAddress(electricInfo.getSecondAddress());
            info.setSixthAddress(electricInfo.getSixthAddress());
            info.setStatus(electricInfo.getState());
            info.setThirdAddress(electricInfo.getThirdAddress());
            info.setTransCount(String.valueOf(electricInfo.getFlipTime()));
            if (electricInfo.getScrappedTime()!=null) {
                info.setUninstallTime(electricInfo.getScrappedTime().toString());
            }
            infoList.add(info);
        }
        content.setInfo(infoList);
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        electricListRes.setContent(content);
        return electricListRes;
    }

    public DeleteRes deleteElectricInfo(DeleteReq req) {
        int deleteFlag = dao.delete(req.getIds());
        DeleteRes deleteRes = new DeleteRes();

        if(deleteFlag == 0){
            deleteRes.setCode(ConstantString.DELETE_FAILED);
            deleteRes.setMsg("删除数据失败");
        }else if(deleteFlag < req.getIds().size()){
            deleteRes.setCode(ConstantString.DELETE_PART_FAILED);
            deleteRes.setMsg("删除数据部分失败");
        }else{
            deleteRes.setCode(ConstantString.DELETE_SUCCESS);
            deleteRes.setMsg("删除数据成功");
        }
        return deleteRes;
    }

    public AddElectricInfoRes addElectricInfo(AddElectricInfoReq electricInfoReq) {
        ElectricInfo electricInfo = new ElectricInfo();
        electricInfo.setCharger(electricInfoReq.getChargeAccount());
        electricInfo.setFifthAddress(electricInfoReq.getFifthAddress());
        electricInfo.setFirstAddress(electricInfoReq.getFirstAddress());
        if(electricInfoReq.getTransCount()!=null) {
            electricInfo.setFlipTime(Integer.valueOf(electricInfoReq.getTransCount()));
        }
        electricInfo.setFourthAddress(electricInfoReq.getForthAddress());

        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        try {
            Date date = sdf.parse(electricInfoReq.getInstallTime());
            java.sql.Date installTime = new java.sql.Date(date.getTime());
            electricInfo.setInstallTime(installTime);
        }catch (Exception e){
            System.out.println(e);
        }
        electricInfo.setLevel(electricInfoReq.getLevel());
        if(!electricInfoReq.getRatio().equals("")&&electricInfoReq.getRatio()!=null) {
            electricInfo.setMagnification(Integer.valueOf(electricInfoReq.getRatio()));
        }
        if(electricInfoReq.getMaxScale()!=null) {
            electricInfo.setMaxDegree(Integer.valueOf(electricInfoReq.getMaxScale()));
        }
        if(electricInfoReq.getOriginScale()!=null) {
            electricInfo.setMeterBottom(Integer.valueOf(electricInfoReq.getOriginScale()));
        }
        electricInfo.setReceivableParty(electricInfoReq.getReceivableAccount());
        electricInfo.setSecondAddress(electricInfoReq.getSecondAddress());
        electricInfo.setSixthAddress(electricInfoReq.getSixthAddress());
        electricInfo.setState(electricInfoReq.getStatus());
        electricInfo.setSuperiorId(electricInfoReq.getPreCode());
        electricInfo.setThirdAddress(electricInfoReq.getThirdAddress());
        electricInfo.setSuperiorId(electricInfoReq.getPreCode());

        //char cat = (char)(Integer.valueOf(udao.selectByMeterId(electricInfo.getElectricMeterId()).getUserCategoryId())+'A');
        char cat = (electricInfoReq.getUserCat().charAt(0));
        String category = String.valueOf(cat);
        int level = 0;
        if(electricInfo.getLevel().equals("一级")){
            level=1;
        }else if(electricInfo.getLevel() .equals( "二级")){
            level = 2;
        }else if(electricInfo.getLevel().equals("三级")){
            level = 3;
        }else if(electricInfo.getLevel().equals( "四级")){
            level = 4;
        }
        String meterId = "";
        if(electricInfoReq.getLevel().equals("一级")){
            electricInfo.setElectricityMeterId(electricInfoReq.getMeterCode());
        }else {
            meterId = PinyinUtils.getFistCode(electricInfo.getSecondAddress())+ PinyinUtils.getFistCode(electricInfo.getThirdAddress())+
                    PinyinUtils.getFistCode(category)+"D"+ level;
            ElectricInfo topElectricInfo = dao.selectLikeInfo(meterId+"%"); //获取同类同等级最大表号
            Format f1 = new DecimalFormat("000"); // 格式化流水号
            if(topElectricInfo==null){
                meterId = meterId+"000";
            }else{
                int length = topElectricInfo.getElectricityMeterId().length();
                int lastCode = Integer.valueOf(topElectricInfo.getElectricityMeterId().substring(length-3));
                meterId = meterId+f1.format(lastCode+1);
            }
            electricInfo.setElectricityMeterId(meterId);
        }

        int addFlag = dao.insert(electricInfo);
        AddElectricInfoRes electricInfoRes = new AddElectricInfoRes();
        AddElectricInfoRes.Content content = electricInfoRes.new Content();
        content.setMeterCode(meterId);
        if(addFlag == 0){
            electricInfoRes.setCode(ConstantString.INSERT_FAILED);
            electricInfoRes.setMsg("插入数据失败");
        }else {
            electricInfoRes.setCode(ConstantString.INSERT_SUCCESS);
            electricInfoRes.setMsg("插入成功");
            electricInfoRes.setContent(content);
        }

        electricInfoRes.setContent(content);
        return electricInfoRes;
    }

    public ChangeRes changeElectricInfo(ChangeElectricInfoReq electricInfoReq) {
        ElectricInfo electricInfo = new ElectricInfo();
        electricInfo.setCharger(electricInfoReq.getChargeAccount());
        electricInfo.setFifthAddress(electricInfoReq.getFifthAddress());
        electricInfo.setFirstAddress(electricInfoReq.getFirstAddress());
        if(electricInfoReq.getTransCount()!=null&&!electricInfoReq.equals("")) {
            electricInfo.setFlipTime(Integer.valueOf(electricInfoReq.getTransCount()));
        }
        electricInfo.setFourthAddress(electricInfoReq.getForthAddress());


        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        try {
            Date date = sdf.parse(electricInfoReq.getInstallTime());
            java.sql.Date installTime = new java.sql.Date(date.getTime());
            electricInfo.setInstallTime(installTime);
        }catch (Exception e){
            System.out.println(e);
        }
        electricInfo.setLevel(electricInfoReq.getLevel());
        if(electricInfoReq.getRatio()!=null) {
            electricInfo.setMagnification(Integer.valueOf(electricInfoReq.getRatio()));
        }
        if(electricInfoReq.getMaxScale()!=null) {
            electricInfo.setMaxDegree(Integer.valueOf(electricInfoReq.getMaxScale()));
        }
        if(electricInfoReq.getOriginScale()!=null) {
            electricInfo.setMeterBottom(Integer.valueOf(electricInfoReq.getOriginScale()));
        }
        electricInfo.setReceivableParty(electricInfoReq.getReceivableAccount());
        electricInfo.setSecondAddress(electricInfoReq.getSecondAddress());
        electricInfo.setSixthAddress(electricInfoReq.getSixthAddress());
        electricInfo.setState(electricInfoReq.getStatus());
        electricInfo.setSuperiorId(electricInfoReq.getPreCode());
        electricInfo.setThirdAddress(electricInfoReq.getThirdAddress());
        electricInfo.setSuperiorId(electricInfoReq.getPreCode());
        try {
            Date date = sdf.parse(electricInfoReq.getUninstallTime());
            java.sql.Date uninstallTime = new java.sql.Date(date.getTime());
            electricInfo.setScrappedTime(uninstallTime);
        }catch (Exception e){
            System.out.println(e);
        }
        electricInfo.setElectricityMeterId(electricInfoReq.getCode());

        // 检测到将状态修改为废弃时，插入历史表中
        String state = electricInfo.getState();
        if(state.equals("废弃")){
            ElectricHistory electricHistory = new ElectricHistory();
            electricHistory.setFinalDegree(electricInfo.getFinalDegree());
            electricHistory.setFlipTime(electricInfo.getFlipTime());
            electricHistory.setElectricityMeterId(electricInfo.getElectricityMeterId());
            electricHistory.setInstallTime(electricInfo.getInstallTime());
            electricHistory.setScrappedTime(electricInfo.getScrappedTime());
            hdao.insert(electricHistory);
            System.out.println("Insert to History Form");
        }


        int updateFlag = dao.update(electricInfo);
        ChangeRes res = new ChangeRes();

        if(updateFlag == 0){
            res.setCode(ConstantString.UPDATE_FAILED);
            res.setMsg("更新数据失败");
        }else {
            res.setCode(ConstantString.INSERT_SUCCESS);
            res.setMsg("更新数据成功");

        }

        return res;
    }

    public ChangeRes changeElectricState(ChangeElectricStateReq electricStateReq) {
        ElectricInfo electricInfo = new ElectricInfo();
        String state = "启用";
        if(electricStateReq.getState()==0){
            state = "启用";
        }else if(electricStateReq.getState()==1){
            state = "停用";
        }else if(electricStateReq.getState()==2){
            state = "废弃";
        }

        electricInfo.setState(state);
        electricInfo.setElectricityMeterId(electricStateReq.getId());

        int updateFlag = dao.update(electricInfo);
        ChangeRes res  = new ChangeRes();

        if(updateFlag == 0){
            res.setCode(ConstantString.UPDATE_FAILED);
            res.setMsg("更新数据失败");
        }else {
            res.setCode(ConstantString.INSERT_SUCCESS);
            res.setMsg("更新数据成功");

        }
        return res;
    }

    public GetElectricHistoryRes getElectricHistoryList(PageParam pageParam) {
        int currPage = pageParam.getCurrPage();
        pageParam.setRowCount(getHistoryRowCount());
        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", offset);
        params.put("size", size);

        List<ElectricHistory> electricHistoryList = hdao.selectByParams(params);

        GetElectricHistoryRes electricListRes = new GetElectricHistoryRes();
        GetElectricHistoryRes.Content content = electricListRes.new Content();
        List<GetElectricHistoryRes.Content.Info> infoList = new ArrayList<GetElectricHistoryRes.Content.Info>();
        electricListRes.setCode(ConstantString.QUEARY_SUCCESS);
        electricListRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");

        for (ElectricHistory one:electricHistoryList){
            GetElectricHistoryRes.Content.Info info = content.new Info();
            info.setId(String.valueOf(one.getId()));
            info.setCode(one.getElectricityMeterId());
            info.setDeadTime(one.getScrappedTime().toString());
            info.setFinalNum(String.valueOf(one.getFinalDegree()));
            info.setInstallTime(one.getInstallTime().toString());
            infoList.add(info);
        }
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        content.setInfo(infoList);
        electricListRes.setContent(content);

        return electricListRes;
    }

    public GetCodeByAddressLvRes getCodeByLevel(String level) {
        List<String> codes = new ArrayList<String>();
        int levelNum = 0;
        for(int i=0;i<ConstantString.LEVELS.size();i++){
            if(ConstantString.LEVELS.get(i).equals(level)){
                levelNum=i-1;
            }
        }
        List<ElectricInfo> electricInfos = dao.selectByLevel(ConstantString.LEVELS.get(levelNum));
        for(ElectricInfo one :electricInfos){
            codes.add(one.getElectricityMeterId());
        }
        GetCodeByAddressLvRes codeRes = new GetCodeByAddressLvRes();
        codeRes.setCode("200");
        codeRes.setMsg("查询成功");
        GetCodeByAddressLvRes.Content content = codeRes.new Content();
        content.setCode(codes);
        codeRes.setContent(content);
        return codeRes;
    }
}
