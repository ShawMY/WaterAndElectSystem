package com.zjgsu.shuidiansys.service.impl;

import com.zjgsu.shuidiansys.common.ConstantString;
import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.common.PinyinUtils;
import com.zjgsu.shuidiansys.common.Util;
import com.zjgsu.shuidiansys.dao.ITopWaterMeterDao;
import com.zjgsu.shuidiansys.dao.IUserInfoDao;
import com.zjgsu.shuidiansys.dao.IWaterHistoryDao;
import com.zjgsu.shuidiansys.dao.IWaterInfoDao;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.pojo.WaterFeeInfo;
import com.zjgsu.shuidiansys.pojo.WaterHistory;
import com.zjgsu.shuidiansys.pojo.WaterInfo;
import com.zjgsu.shuidiansys.service.IWaterInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WaterInfoServiceImpl implements IWaterInfoService {

    @Resource
    IWaterInfoDao dao;
    @Resource
    IWaterHistoryDao hdao;

    public int getRowCountByPage(){return dao.getRowCountByPage();}

    public int getRowCountByQuery(WaterInfo waterInfo){return dao.getRowCountByQuery(waterInfo);}

    public int getHistoryRowCount(){return hdao.getRowCountByPage();}

    public GetWaterInfoListRes getWaterInfoList(PageParam pageParam) {
        int currPage = pageParam.getCurrPage();
        pageParam.setRowCount(getRowCountByPage());
        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", offset);
        params.put("size", size);

        List<WaterInfo> waterInfoList = dao.selectByParams(params);

        GetWaterInfoListRes waterListRes = new GetWaterInfoListRes();
        GetWaterInfoListRes.Content content = waterListRes.new Content();
        List<GetWaterInfoListRes.Content.Info> infoList = new ArrayList<GetWaterInfoListRes.Content.Info>();
        waterListRes.setCode(ConstantString.QUEARY_SUCCESS);
        waterListRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");

        for(WaterInfo waterInfo:waterInfoList){
            GetWaterInfoListRes.Content.Info info = content.new Info();
            info.setArea(waterInfo.getFirstAddress());
            info.setChargeAccount(waterInfo.getCharger());
            info.setCode(waterInfo.getWaterMeterId());
            info.setFifthAddress(waterInfo.getFifthAddress());
            info.setFirstAddress(waterInfo.getFirstAddress());
            info.setFourthAddress(waterInfo.getFourthAddress());
            if(waterInfo.getInstallTime()!=null) {
                info.setInstallTime(waterInfo.getInstallTime().toString());
            }
            info.setLevel(waterInfo.getLevel());
            info.setMaxScale(String.valueOf(waterInfo.getMaxDegree()));
            info.setOriginScale(String.valueOf(waterInfo.getMeterBottom()));
            info.setPreCode(waterInfo.getSuperiorId());
            info.setRatio(String.valueOf(waterInfo.getMagnification()));
            info.setReceivableAccount(waterInfo.getReceivableParty());
            info.setSecondAddress(waterInfo.getSecondAddress());
            info.setSixthAddress(waterInfo.getSixthAddress());
            info.setStatus(waterInfo.getState());
            info.setRadius(String.valueOf(waterInfo.getPipeSize()));
            info.setThirdAddress(waterInfo.getThirdAddress());
            info.setTransCount(String.valueOf(waterInfo.getFlipTime()));
            if (waterInfo.getScrappedTime()!=null) {
                info.setUninstallTime(waterInfo.getScrappedTime().toString());
            }
            infoList.add(info);
        }
        content.setInfo(infoList);
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        waterListRes.setContent(content);
        return waterListRes;
    }

    public QueryWaterInfoRes queryWaterInfo(QueryWaterInfoReq waterInfoReq) {
        WaterInfo water = new WaterInfo();
        water.setCharger(waterInfoReq.getChargeAccount());
        water.setFifthAddress(waterInfoReq.getFifthAddress());
        water.setFirstAddress(waterInfoReq.getFirstAddress());
        water.setFourthAddress(waterInfoReq.getFourthAddress());
        water.setWaterMeterId(waterInfoReq.getCode());
        water.setSuperiorId(waterInfoReq.getPreCode());

        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        try {
            Date date = sdf.parse(waterInfoReq.getInstallTime());
            java.sql.Date installTime = new java.sql.Date(date.getTime());
            water.setInstallTime(installTime);
        }catch (Exception e){
            System.out.println(e);
        }
        water.setLevel(waterInfoReq.getLevel());
        water.setReceivableParty(waterInfoReq.getReceivableAccount());
        water.setSecondAddress(waterInfoReq.getSecondAddress());
        water.setSixthAddress(waterInfoReq.getSixthAddress());
        water.setThirdAddress(waterInfoReq.getThirdAddress());

        PageParam pageParam = new PageParam();
        int currPage = waterInfoReq.getPage();
        pageParam.setCurrPage(currPage);
        pageParam.setRowCount(getRowCountByQuery(water));
        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", offset);
        params.put("size", size);
        params.put("water",water);

        List<WaterInfo> waterInfoList = dao.selectByQuery(params);

        QueryWaterInfoRes waterListRes = new QueryWaterInfoRes();
        QueryWaterInfoRes.Content content = waterListRes.new Content();
        List<QueryWaterInfoRes.Content.Info> infoList = new ArrayList<QueryWaterInfoRes.Content.Info>();
        waterListRes.setCode(ConstantString.QUEARY_SUCCESS);
        waterListRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");

        for(WaterInfo waterInfo:waterInfoList){
            QueryWaterInfoRes.Content.Info info = content.new Info();
            info.setArea(waterInfo.getFirstAddress());
            info.setChargeAccount(waterInfo.getCharger());
            info.setCode(waterInfo.getWaterMeterId());
            info.setFifthAddress(waterInfo.getFifthAddress());
            info.setFirstAddress(waterInfo.getFirstAddress());
            info.setFourthAddress(waterInfo.getFourthAddress());
            if(waterInfo.getInstallTime()!=null) {
                info.setInstallTime(waterInfo.getInstallTime().toString());
            }
            info.setLevel(waterInfo.getLevel());
            info.setMaxScale(String.valueOf(waterInfo.getMaxDegree()));
            info.setOriginScale(String.valueOf(waterInfo.getMeterBottom()));
            info.setPreCode(waterInfo.getSuperiorId());
            info.setRatio(String.valueOf(waterInfo.getMagnification()));
            info.setReceivableAccount(waterInfo.getReceivableParty());
            info.setSecondAddress(waterInfo.getSecondAddress());
            info.setSixthAddress(waterInfo.getSixthAddress());
            info.setStatus(waterInfo.getState());
            info.setRadius(String.valueOf(waterInfo.getPipeSize()));
            info.setThirdAddress(waterInfo.getThirdAddress());
            info.setTransCount(String.valueOf(waterInfo.getFlipTime()));
            if (waterInfo.getScrappedTime()!=null) {
                info.setUninstallTime(waterInfo.getScrappedTime().toString());
            }
            infoList.add(info);
        }
        content.setInfo(infoList);
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        waterListRes.setContent(content);
        return waterListRes;
    }

    public DeleteWaterInfoRes deleteWaterInfo(DeleteWaterInfoReq waterInfoReq) {
        int deleteFlag = dao.deleteWaterInfo(waterInfoReq.getIds());
        DeleteWaterInfoRes waterInfoRes = new DeleteWaterInfoRes();

        if(deleteFlag == 0){
            waterInfoRes.setCode(ConstantString.DELETE_FAILED);
            waterInfoRes.setMsg("删除数据失败");
        }else if(deleteFlag < waterInfoReq.getIds().size()){
            waterInfoRes.setCode(ConstantString.DELETE_PART_FAILED);
            waterInfoRes.setMsg("删除数据部分失败");
        }else{
            waterInfoRes.setCode(ConstantString.DELETE_SUCCESS);
            waterInfoRes.setMsg("删除数据成功");
        }
        return waterInfoRes;
    }

    public AddWaterInfoRes addWaterInfo(AddWaterInfoReq waterInfoReq) {
        WaterInfo waterInfo = new WaterInfo();
        waterInfo.setCharger(waterInfoReq.getChargeAccount());
        waterInfo.setFifthAddress(waterInfoReq.getFifthAddress());
        waterInfo.setFirstAddress(waterInfoReq.getFirstAddress());
        if(waterInfoReq.getTransCount()!=null) {
            waterInfo.setFlipTime(Integer.valueOf(waterInfoReq.getTransCount()));
        }
        waterInfo.setFourthAddress(waterInfoReq.getForthAddress());

        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        try {
            Date date = sdf.parse(waterInfoReq.getInstallTime());
            java.sql.Date installTime = new java.sql.Date(date.getTime());
            waterInfo.setInstallTime(installTime);
        }catch (Exception e){
            System.out.println(e);
        }
        waterInfo.setLevel(waterInfoReq.getLevel());
        if(waterInfoReq.getRatio()!=null) {
            waterInfo.setMagnification(Integer.valueOf(waterInfoReq.getRatio()));
        }
        if(waterInfoReq.getMaxScale()!=null) {
            waterInfo.setMaxDegree(Integer.valueOf(waterInfoReq.getMaxScale()));
        }
        if(waterInfoReq.getOriginScale()!=null) {
            waterInfo.setMeterBottom(Integer.valueOf(waterInfoReq.getOriginScale()));
        }
        if (waterInfoReq.getRadius()!=null) {
            waterInfo.setPipeSize(Integer.valueOf(waterInfoReq.getRadius()));
        }
        waterInfo.setReceivableParty(waterInfoReq.getReceivableAccount());
        waterInfo.setSecondAddress(waterInfoReq.getSecondAddress());
        waterInfo.setSixthAddress(waterInfoReq.getSixthAddress());
        waterInfo.setState(waterInfoReq.getStatus());
        waterInfo.setSuperiorId(waterInfoReq.getPreCode());
        waterInfo.setThirdAddress(waterInfoReq.getThirdAddress());
        waterInfo.setSuperiorId(waterInfoReq.getPreCode());

        //char cat = (char)(Integer.valueOf(udao.selectByMeterId(waterInfo.getWaterMeterId()).getUserCategoryId())+'A');
        char cat = (waterInfoReq.getUserCat().charAt(0));
        String catgory = String.valueOf(cat);
        int level = 0;
        String meterId = "";
        if(waterInfo.getLevel().equals("一级")){
            level=1;
        }else if(waterInfo.getLevel() .equals( "二级")){
            level = 2;
        }else if(waterInfo.getLevel().equals("三级")){
            level = 3;
        }else if(waterInfo.getLevel().equals( "四级")){
            level = 4;
        }
        if(waterInfo.getLevel().equals("一级")){
            meterId = waterInfoReq.getMeterCode();
        }else {
            meterId=PinyinUtils.getFistCode(waterInfo.getSecondAddress())+ PinyinUtils.getFistCode(waterInfo.getThirdAddress())+
                    PinyinUtils.getFistCode(catgory)+"S"+ level;
            WaterInfo topWaterInfo = dao.selectLikeInfo(meterId+"%"); //获取同类同等级最大表号
            Format f1 = new DecimalFormat("000"); // 格式化流水号
            if(topWaterInfo==null){
                meterId = meterId+"000";
            }else{
                int length = topWaterInfo.getWaterMeterId().length();
                int lastCode = Integer.valueOf(topWaterInfo.getWaterMeterId().substring(length-3));
                meterId = meterId+f1.format(lastCode+1);
            }
        }

        waterInfo.setWaterMeterId(meterId);

        int addFlag = dao.addWaterInfo(waterInfo);
        AddWaterInfoRes waterInfoRes = new AddWaterInfoRes();
        AddWaterInfoRes.Content content = waterInfoRes.new Content();
        content.setMeterCode(meterId);
        if(addFlag == 0){
            waterInfoRes.setCode(ConstantString.INSERT_FAILED);
            waterInfoRes.setMsg("插入数据失败");
        }else {
            waterInfoRes.setCode(ConstantString.INSERT_SUCCESS);
            waterInfoRes.setMsg("插入成功");
            waterInfoRes.setContent(content);
        }

        waterInfoRes.setContent(content);
        return waterInfoRes;
    }

    public ChangeWaterInfoRes changeWaterInfo(ChangeWaterInfoReq waterInfoReq) {
        WaterInfo waterInfo = new WaterInfo();
        waterInfo.setCharger(waterInfoReq.getChargeAccount());
        waterInfo.setFifthAddress(waterInfoReq.getFifthAddress());
        waterInfo.setFirstAddress(waterInfoReq.getFirstAddress());
        if(waterInfoReq.getTransCount()!=null) {
            waterInfo.setFlipTime(Integer.valueOf(waterInfoReq.getTransCount()));
        }
        waterInfo.setFourthAddress(waterInfoReq.getForthAddress());


        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        try {
            Date date = sdf.parse(waterInfoReq.getInstallTime());
            java.sql.Date installTime = new java.sql.Date(date.getTime());
            waterInfo.setInstallTime(installTime);
        }catch (Exception e){
            System.out.println(e);
        }
        waterInfo.setLevel(waterInfoReq.getLevel());
        if(waterInfoReq.getRatio()!=null) {
            waterInfo.setMagnification(Integer.valueOf(waterInfoReq.getRatio()));
        }
        if(waterInfoReq.getMaxScale()!=null) {
            waterInfo.setMaxDegree(Integer.valueOf(waterInfoReq.getMaxScale()));
        }
        if(waterInfoReq.getOriginScale()!=null) {
            waterInfo.setMeterBottom(Integer.valueOf(waterInfoReq.getOriginScale()));
        }
        if (waterInfoReq.getRadius()!=null) {
            waterInfo.setPipeSize(Integer.valueOf(waterInfoReq.getRadius()));
        }
        waterInfo.setReceivableParty(waterInfoReq.getReceivableAccount());
        waterInfo.setSecondAddress(waterInfoReq.getSecondAddress());
        waterInfo.setSixthAddress(waterInfoReq.getSixthAddress());
        waterInfo.setState(waterInfoReq.getStatus());
        waterInfo.setSuperiorId(waterInfoReq.getPreCode());
        waterInfo.setThirdAddress(waterInfoReq.getThirdAddress());
        waterInfo.setSuperiorId(waterInfoReq.getPreCode());
        try {
            Date date = sdf.parse(waterInfoReq.getUninstallTime());
            java.sql.Date uninstallTime = new java.sql.Date(date.getTime());
            waterInfo.setScrappedTime(uninstallTime);
        }catch (Exception e){
            System.out.println(e);
        }
        waterInfo.setWaterMeterId(waterInfoReq.getCode());

        // 检测到将状态修改为废弃时，插入历史表中
        String state = waterInfo.getState();
        if(state.equals("废弃")){
            WaterHistory waterHistory = new WaterHistory();
            waterHistory.setFinalDegree(waterInfo.getFinalDegree());
            waterHistory.setFlipTime(waterInfo.getFlipTime());
            waterHistory.setWaterMeterId(waterInfo.getWaterMeterId());
            waterHistory.setInstallTime(waterInfo.getInstallTime());
            String scrappedUtilTime = sdf.format(new Date());
            java.sql.Date scrappedTime = Util.formatToSqlDate(scrappedUtilTime);
            waterHistory.setScrappedTime(scrappedTime);
            hdao.insert(waterHistory);
            System.out.println("Insert to History Form");
        }


        int updateFlag = dao.updateWaterInfo(waterInfo);
        ChangeWaterInfoRes waterInfoRes = new ChangeWaterInfoRes();

        if(updateFlag == 0){
            waterInfoRes.setCode(ConstantString.UPDATE_FAILED);
            waterInfoRes.setMsg("更新数据失败");
        }else {
            waterInfoRes.setCode(ConstantString.INSERT_SUCCESS);
            waterInfoRes.setMsg("更新数据成功");

        }

        return waterInfoRes;
    }

    public ChangeWaterStateRes changeWaterState(ChangeWaterStateReq waterStateReq) {
        WaterInfo waterInfo = new WaterInfo();
        String state = "启用";
        if(waterStateReq.getState()==0){
            state = "启用";
        }else if(waterStateReq.getState()==1){
            state = "停用";
        }else if(waterStateReq.getState()==2){
            state = "废弃";

        }

        waterInfo.setState(state);
        waterInfo.setWaterMeterId(waterStateReq.getId());

        int updateFlag = dao.updateWaterInfo(waterInfo);
        ChangeWaterStateRes waterInfoRes = new ChangeWaterStateRes();

        if(updateFlag == 0){
            waterInfoRes.setCode(ConstantString.UPDATE_FAILED);
            waterInfoRes.setMsg("更新数据失败");
        }else {
            waterInfoRes.setCode(ConstantString.INSERT_SUCCESS);
            waterInfoRes.setMsg("更新数据成功");

        }
        return waterInfoRes;
    }

    public GetCodeByAddressLvRes getCodeByAddressLv2(String level) {
        List<WaterInfo> topWaterInfos = dao.selectByLevel("一级");
        GetCodeByAddressLvRes codeRes = new GetCodeByAddressLvRes();
        GetCodeByAddressLvRes.Content content = codeRes.new Content();
        List<String> codeList = new ArrayList<String>();
        for(WaterInfo topWaterInfo:topWaterInfos){
            codeList.add(topWaterInfo.getWaterMeterId());
        }
        content.setCode(codeList);
        codeRes.setContent(content);
        codeRes.setCode(ConstantString.QUEARY_SUCCESS);
        codeRes.setMsg("查询成功");

        return codeRes;
    }

    public GetCodeByAddressLvRes getCodeByAddressLv3(String level, String firstAddr, String secondAddr) {
        System.out.println(firstAddr);
        List<WaterInfo> waterInfos = dao.selectByAddr(firstAddr,secondAddr);
        GetCodeByAddressLvRes codeRes = new GetCodeByAddressLvRes();
        GetCodeByAddressLvRes.Content content = codeRes.new Content();
        List<String> codeList = new ArrayList<String>();
        for(WaterInfo waterInfo:waterInfos){
            codeList.add(waterInfo.getWaterMeterId());
        }
        content.setCode(codeList);
        codeRes.setContent(content);
        codeRes.setCode(ConstantString.QUEARY_SUCCESS);
        codeRes.setMsg("查询成功");

        return codeRes;
    }

    public GetWaterHistoryRes getWaterHistoryList(PageParam pageParam) {
        int currPage = pageParam.getCurrPage();
        pageParam.setRowCount(getHistoryRowCount());
        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", offset);
        params.put("size", size);

        List<WaterHistory> waterHistoryList = hdao.selectByParams(params);

        GetWaterHistoryRes waterListRes = new GetWaterHistoryRes();
        GetWaterHistoryRes.Content content = waterListRes.new Content();
        List<GetWaterHistoryRes.Content.Info> infoList = new ArrayList<GetWaterHistoryRes.Content.Info>();
        waterListRes.setCode(ConstantString.QUEARY_SUCCESS);
        waterListRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");

        for (WaterHistory one:waterHistoryList){
            GetWaterHistoryRes.Content.Info info = content.new Info();
            info.setId(String.valueOf(one.getId()));
            info.setCode(one.getWaterMeterId());
            info.setDeadTime(one.getScrappedTime().toString());
            info.setFinalNum(String.valueOf(one.getFinalDegree()));
            info.setInstallTime(one.getInstallTime().toString());
            infoList.add(info);
        }
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        content.setInfo(infoList);
        waterListRes.setContent(content);

        return waterListRes;
    }

    public GetCodeByAddressLvRes getCodeByLevel(String level) {
        List<String> codes = new ArrayList<String>();
        int levelNum = 0;
        for(int i=0;i<ConstantString.LEVELS.size();i++){
            if(ConstantString.LEVELS.get(i).equals(level)){
                levelNum=i-1;
            }
        }
        List<WaterInfo> waterInfos = dao.selectByLevel(ConstantString.LEVELS.get(levelNum));
        for(WaterInfo one :waterInfos){
            codes.add(one.getWaterMeterId());
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
