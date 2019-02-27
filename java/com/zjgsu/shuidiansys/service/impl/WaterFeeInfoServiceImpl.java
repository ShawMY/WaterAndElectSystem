package com.zjgsu.shuidiansys.service.impl;

import com.zjgsu.shuidiansys.common.ConstantString;
import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.common.Util;
import com.zjgsu.shuidiansys.common.WordUtil;
import com.zjgsu.shuidiansys.dao.*;
import com.zjgsu.shuidiansys.pojo.*;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.service.IWaterFeeInfoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.DataFormatException;

import static com.zjgsu.shuidiansys.common.WordUtil.createWord;

@Service
public class WaterFeeInfoServiceImpl implements IWaterFeeInfoService {

    @Resource
    IWaterFeeInfoDao dao;
    @Resource
    IWaterInfoDao widao;
    @Resource
    IUserInfoDao uidao;
    @Resource
    IWaterCopyDao wcdao;
    @Resource
    IUserCategoryDao ucdao;
    @Resource
    IMeterAlarmDao madao;

    public int getRowCountByPage(){return dao.getRowCountByPage();}

    public int getRowCountByQuery(WaterFeeInfo water){return dao.getRowCountByQuery(water);}
    public GetWaterFeeListRes getWaterFeeList(PageParam pageParam) {
        int currPage = pageParam.getCurrPage();
        pageParam.setRowCount(getRowCountByPage());
        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", offset);
        params.put("size", size);

        List<WaterFeeInfo> waterFeeInfoList = dao.selectByParams(params);

        GetWaterFeeListRes waterFeeListRes = new GetWaterFeeListRes();
        GetWaterFeeListRes.Content content = waterFeeListRes.new Content();
        List<GetWaterFeeListRes.Content.Info> infoList = new ArrayList<GetWaterFeeListRes.Content.Info>();
        waterFeeListRes.setCode(ConstantString.QUEARY_SUCCESS);
        waterFeeListRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");

        for(WaterFeeInfo waterFeeInfo:waterFeeInfoList){
            GetWaterFeeListRes.Content.Info info = content.new Info();
            info.setCurrentFree(String.valueOf(waterFeeInfo.getThisRelief()));
            info.setCurrentNum(String.valueOf(waterFeeInfo.getThisDegree()));
            info.setCurrentPayable(String.valueOf(waterFeeInfo.getThisDue()));
            info.setCurrentPayed(String.valueOf(waterFeeInfo.getThisPaid()));
            info.setFinishPaymentTime(waterFeeInfo.getPaymentTime());
            info.setId(String.valueOf(waterFeeInfo.getId()));
            info.setIsInvoice(waterFeeInfo.getIsBilling());
            info.setMeterCode(waterFeeInfo.getWaterMeterId());
            info.setOverdueFee(String.valueOf(waterFeeInfo.getLatePayment()));
            info.setOverdueFeeStatus(waterFeeInfo.getLatePaymentState());
            info.setPayDeadline(waterFeeInfo.getPaymentDeadLine());
            info.setPayStatus(waterFeeInfo.getPaymentState());
            info.setPreviousNum(String.valueOf(waterFeeInfo.getPreviousDegree()));
            info.setRecordTime(String.valueOf(waterFeeInfo.getMeterReadTime()));
            info.setUserCode(waterFeeInfo.getUserId());
            if(waterFeeInfo.getCertificate()!=null&&!waterFeeInfo.getCertificate().equals("")){
                info.setIsProve("true");
            }else{
                info.setIsProve("false");
            }
            infoList.add(info);
        }
        content.setInfo(infoList);
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        waterFeeListRes.setContent(content);

        return waterFeeListRes;
    }

    public QueryWaterFeeRes getWaterFeeListByQuery(QueryWaterFeeReq waterFeeReq) {
        int currPage = waterFeeReq.getPage();
        PageParam pageParam = new PageParam();
        pageParam.setCurrPage(currPage);

        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;
        int lastset = offset+size;


        System.out.println(waterFeeReq.getRecordTime1());



        // 通过水表地址查询ID列表

        WaterInfo waterInfo = new WaterInfo();
        waterInfo.setFirstAddress(waterFeeReq.getInstallAddress1());
        waterInfo.setSecondAddress(waterFeeReq.getInstallAddress2());
        waterInfo.setThirdAddress(waterFeeReq.getInstallAddress3());
        waterInfo.setFourthAddress(waterFeeReq.getInstallAddress4());
        waterInfo.setFifthAddress(waterFeeReq.getInstallAddress5());
        waterInfo.setSixthAddress(waterFeeReq.getInstallAddress6());

        List<WaterInfo> waterInfos = widao.selectAllByQuery(waterInfo);
        System.out.println(waterInfos.size());

        //获取查询列表
        List<WaterFeeInfo> waterFeeList = new ArrayList<WaterFeeInfo>();
        for (WaterInfo water:waterInfos){
            WaterFeeInfo waterFeeInfo = new WaterFeeInfo();
            waterFeeInfo.setPaymentState(waterFeeReq.getStatus());
            waterFeeInfo.setWaterMeterId(water.getWaterMeterId());
            waterFeeList.add(waterFeeInfo);
        }



        List<WaterFeeInfo> waterFeeInfoList = new ArrayList<WaterFeeInfo>();// 逐条查询获取返回列表
        for(WaterFeeInfo waterFeeInfo:waterFeeList){
            System.out.println(waterFeeInfo.getWaterMeterId());
            Map<String, Object> params = new HashMap<String, Object>(); // 查询1
            params.put("beginTime",waterFeeReq.getRecordTime1());
            params.put("endTime",waterFeeReq.getRecordTime2());
            params.put("water",waterFeeInfo);
            List<WaterFeeInfo> one = dao.selectByQuery(params);
            if(one!=null) {
                waterFeeInfoList.addAll(one);
            }

        }

        pageParam.setRowCount(waterFeeInfoList.size());
        if(lastset>waterFeeInfoList.size()){
            lastset=waterFeeInfoList.size();
        }

        QueryWaterFeeRes waterFeeRes = new QueryWaterFeeRes();
        QueryWaterFeeRes.Content content = waterFeeRes.new Content();
        List<QueryWaterFeeRes.Content.Info> infoList = new ArrayList<QueryWaterFeeRes.Content.Info>();
        waterFeeRes.setCode(ConstantString.QUEARY_SUCCESS);
        waterFeeRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");

        for(int i=offset;i<lastset;i++){
            WaterFeeInfo waterFeeInfo = waterFeeInfoList.get(i);
            QueryWaterFeeRes.Content.Info info = content.new Info();
            try {
                info.setCurrentFree(String.valueOf(waterFeeInfo.getThisRelief()));
                info.setCurrentNum(String.valueOf(waterFeeInfo.getThisDegree()));
                info.setCurrentPayable(String.valueOf(waterFeeInfo.getThisDue()));
                info.setCurrentPayed(String.valueOf(waterFeeInfo.getThisPaid()));
                info.setFinishPaymentTime(waterFeeInfo.getPaymentTime());
                info.setId(String.valueOf(waterFeeInfo.getId()));
                info.setIsInvoice(waterFeeInfo.getIsBilling());
                info.setMeterCode(waterFeeInfo.getWaterMeterId());
                info.setOverdueFee(String.valueOf(waterFeeInfo.getLatePayment()));
                info.setOverdueFeeStatus(waterFeeInfo.getLatePaymentState());
                info.setPayDeadline(waterFeeInfo.getPaymentDeadLine());
                info.setPayStatus(waterFeeInfo.getPaymentState());
                info.setPreviousNum(String.valueOf(waterFeeInfo.getPreviousDegree()));
                info.setRecordTime(String.valueOf(waterFeeInfo.getMeterReadTime()));
                if(waterFeeInfo.getCertificate()!=null&&!waterFeeInfo.getCertificate().equals("")){
                    info.setIsProve("true");
                }else{
                    info.setIsProve("false");
                }
                info.setUserCode(waterFeeInfo.getUserId());
            }catch (Exception e){
                System.out.println(e);
            }
            infoList.add(info);
        }
        content.setInfo(infoList);
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        waterFeeRes.setContent(content);

        return waterFeeRes;
    }

    public DeleteWaterFeeRes deleteWaterFee(DeleteWaterFeeReq waterFeeReq) {

        int deleteFlag = dao.deleteWaterFee(waterFeeReq.getIds());
        DeleteWaterFeeRes waterFeeRes = new DeleteWaterFeeRes();

        if(deleteFlag == 0){
            waterFeeRes.setCode(ConstantString.DELETE_FAILED);
            waterFeeRes.setMsg("删除数据失败");
        }else if(deleteFlag < waterFeeReq.getIds().size()){
            waterFeeRes.setCode(ConstantString.DELETE_PART_FAILED);
            waterFeeRes.setMsg("删除数据部分失败");
        }else{
            waterFeeRes.setCode(ConstantString.DELETE_SUCCESS);
            waterFeeRes.setMsg("删除数据成功");
        }
        return waterFeeRes;
    }

    public ChangeWaterFeeRes changeWaterFee(ChangeWaterFeeReq waterFeeReq) {

        WaterFeeInfo waterFeeInfo = new WaterFeeInfo();
        try {
            waterFeeInfo.setId(Integer.valueOf(waterFeeReq.getId()));
            waterFeeInfo.setIsBilling(waterFeeReq.getIsInvoice());
            waterFeeInfo.setLatePayment(Double.valueOf(waterFeeReq.getOverdueFee()));
            waterFeeInfo.setLatePaymentState(waterFeeReq.getOverdueFeeStatus());

            //日期转化
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = sdf.parse(waterFeeReq.getRecordTime());
                java.sql.Date meterReadTime = new java.sql.Date(date.getTime());
                waterFeeInfo.setMeterReadTime(meterReadTime);
            } catch (Exception e) {
                System.out.println(e);
            }
            waterFeeInfo.setPaymentDeadLine(waterFeeReq.getPayDeadline());
            waterFeeInfo.setPaymentState(waterFeeReq.getPayStatus());
            waterFeeInfo.setPaymentTime(waterFeeReq.getFinishPaymentTime());
            waterFeeInfo.setPreviousDegree(Integer.valueOf(waterFeeReq.getPreviousNum()));
            waterFeeInfo.setThisDue(Double.valueOf(waterFeeReq.getCurrentPayable()));
            waterFeeInfo.setThisDegree(Integer.valueOf(waterFeeReq.getCurrentNum()));
            waterFeeInfo.setThisPaid(Double.valueOf(waterFeeReq.getCurrentPayed()));
            waterFeeInfo.setThisRelief(Integer.valueOf(waterFeeReq.getCurrentFree()));
            waterFeeInfo.setWaterMeterId(waterFeeReq.getMeterCode());
            UserInfo userInfo = dao.selectOneById(waterFeeInfo.getWaterMeterId());
            UserCategory userCat = ucdao.selectById(userInfo.getUserCategoryId());
            WaterInfo waterInfo = widao.selectById(waterFeeInfo.getWaterMeterId());

            waterFeeInfo.setThisUsing2(waterInfo.getMaxDegree());
            if(waterFeeInfo.getIsBilling().equals("已开")){
                waterFeeInfo.setThisDue(waterInfo.getMaxDegree(),userCat.getTaxWaterPrice());
            }else{
                waterFeeInfo.setThisDue(waterInfo.getMaxDegree(),userCat.getWaterPrice());
            }
        }catch (Exception e){
            System.out.println(e);
        }
        int updateFlag = dao.updateWaterFee(waterFeeInfo);
        ChangeWaterFeeRes waterFeeRes = new ChangeWaterFeeRes();

        if(updateFlag == 0){
            waterFeeRes.setCode(ConstantString.UPDATE_FAILED);
            waterFeeRes.setMsg("更新数据失败");
        }else {
            waterFeeRes.setCode(ConstantString.INSERT_SUCCESS);
            waterFeeRes.setMsg("更新数据成功");

        }

        return waterFeeRes;
    }

    @Transactional
    public ImportRes importWaterCopy(List<Map<String, Object>> mapList) {
        ImportRes waterCopyRes = new ImportRes();
        //try{
            for (Map<String, Object> one : mapList) {
                WaterFeeInfo water = new WaterFeeInfo();
                WaterCopy waterCopy = new WaterCopy();
                water.setWaterMeterId((String) one.get("waterMeterId"));
                UserInfo user = dao.selectOneById(water.getWaterMeterId());
                WaterInfo waterInfo = widao.selectById(water.getWaterMeterId());
                UserCategory userCat = ucdao.selectById(user.getUserCategoryId());

                int thisDegree = Integer.valueOf((String) one.get("thisDegree"));
                int previousDegree =  Integer.valueOf((String) one.get("previousDegree"));
                java.sql.Date date = Util.formatToSqlDate((String) one.get("meterReadTime"));

                water.setUserId(user.getUserId());
                water.setThisDegree(thisDegree);
                water.setPreviousDegree(previousDegree);
                water.setThisUsing2(waterInfo.getMaxDegree());


                // 获取去年与今年的本期示数
                Date date1 = Util.formatToUtilDate((String) one.get("meterReadTime"));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date1);
                int year =calendar.get(Calendar.YEAR);
                int month =calendar.get(Calendar.MONTH)+1;
                int day = calendar.get(Calendar.DATE);
                String thisTime1 = String.valueOf(year)+"-"+String.valueOf(month);
                WaterFeeInfo info = dao.selectOneByDateLike(thisTime1,water.getWaterMeterId());
                double rate = 0;
                if(info==null) {
                    int month2 = month - 1;
                    if (month == 1) {
                        month2 = 12;
                    }
                    int year2 = year - 1;
                    String thisTime2 = String.valueOf(year) + "-" + String.format("%02d",month2);
                    WaterFeeInfo info2 = dao.selectOneByDateLike(thisTime2,water.getWaterMeterId());
                    System.out.println(thisTime2);
                    //System.out.println("info2:"+info2.getWaterMeterId());
                    String lastTime1 = String.valueOf(year2) + "-" + String.format("%02d",month);
                    WaterFeeInfo info3 = dao.selectOneByDateLike(lastTime1,water.getWaterMeterId());
                    //System.out.println("info3:"+info3.getWaterMeterId());
                    String lastTime2 = String.valueOf(year2) + "-" + String.format("%02d",month2);
                    WaterFeeInfo info4 = dao.selectOneByDateLike(lastTime2,water.getWaterMeterId());
                    //System.out.println("info3:"+info3.getWaterMeterId());
                    if(info2!=null&&info3!=null&&info4!=null) {
                        int using1 = Util.getActualUsing(waterInfo.getMaxDegree(),info.getThisDegree(),info2.getThisDegree());
                        int using2 = Util.getActualUsing(waterInfo.getMaxDegree(),info3.getThisDegree(),info4.getThisDegree());
                        rate = (Math.abs(using1 - using2) * 1.0) / using2;
                    }
                }



                // 检测报警

                MeterAlarm ma = new MeterAlarm();
                System.out.println(waterInfo.getWaterMeterId());
                if(Util.isNearlyFlip(waterInfo.getMaxDegree(),thisDegree)){
                    ma.setDate(date);
                    ma.setMeterId((String) one.get("waterMeterId"));
                    ma.setMsg(ConstantString.ALARM_NEARLY_FLIP);
                    ma.setState(ConstantString.ALARM_NOREADING);
                    madao.insert(ma);
                    System.out.println("Create ALARM!");
                }else if(previousDegree>thisDegree){
                    ma.setDate(date);
                    ma.setMeterId((String) one.get("waterMeterId"));
                    ma.setMsg(ConstantString.ALARM_ALREADY_FLIP);
                    ma.setState(ConstantString.ALARM_NOREADING);
                    madao.insert(ma);
                    System.out.println("Create ALARM!");
                }else if(rate>0.5){
                    ma.setDate(date);
                    ma.setMeterId((String) one.get("waterMeterId"));
                    ma.setMsg(ConstantString.ALARM_UNUSUAL_USE);
                    ma.setState(ConstantString.ALARM_NOREADING);
                    madao.insert(ma);
                    System.out.println("Create ALARM!rate="+rate*100);
                }



                water.setThisDue(waterInfo.getMaxDegree(), userCat.getWaterPrice());
                water.setMeterReader((String) one.get("meterReader"));
                try {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                    java.util.Date utilDate = df.parse((String) one.get("meterReadTime"));// new Date()为获取当前系统时间
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    water.setMeterReadTime(sqlDate);
                }catch (Exception e){
                    e.printStackTrace();
                }

                waterCopy.setWaterMeterId(water.getWaterMeterId());
                waterCopy.setPreviousDegree(water.getThisDegree());
                waterCopy.setMeterReader(water.getMeterReader());
                if(wcdao.getRowCountById(water.getWaterMeterId())==0){
                    waterCopy.setFirstAddress(waterInfo.getFirstAddress());
                    waterCopy.setSecondAddress(waterInfo.getSecondAddress());
                    waterCopy.setThirdAddress(waterInfo.getThirdAddress());
                    waterCopy.setFourthAddress(waterInfo.getFourthAddress());
                    waterCopy.setFifthAddress(waterInfo.getFifthAddress());
                    waterCopy.setSixthAddress(waterInfo.getSixthAddress());
                    wcdao.insert(waterCopy);
                }else if(wcdao.getRowCountById(water.getWaterMeterId())==1){
                    wcdao.update(waterCopy);
                }

                dao.insert(water);
            }


            waterCopyRes.setCode("200");
            waterCopyRes.setMsg("导出成功");
//        }catch (Exception e){
//            System.out.println(e);
//            waterCopyRes.setCode("500");
//            waterCopyRes.setMsg("发生内部错误，请检查数据格式");
//        }

        return waterCopyRes;
    }

    public void exportWaterFeeInfo(List<String> ids) {

        // 获取ID对应的费用列表
        List<WaterFeeInfo> waterFeeInfoList = dao.selectByIds(ids);
        FeeInfoFtl ftlModel = new FeeInfoFtl();
        Calendar calendar = Calendar.getInstance();
        ftlModel.nian2 = String.valueOf(calendar.get(Calendar.YEAR));
        ftlModel.yue3 = String.valueOf(calendar.get(Calendar.MONTH)+1);
        ftlModel.ri3 = String.valueOf(calendar.get(Calendar.DATE));
        WaterFeeInfo first  = waterFeeInfoList.get(0);
        UserInfo user = dao.selectOneById(first.getWaterMeterId());
        WaterInfo waterInfo = widao.selectById(first.getWaterMeterId());
        ftlModel.huming = user.getUserName();
        ftlModel.dizhi = waterInfo.getFirstAddress()+waterInfo.getSecondAddress()+waterInfo.getThirdAddress()
                +waterInfo.getFourthAddress()+waterInfo.getFifthAddress()+waterInfo.getSixthAddress();
        UserCategory userCat = ucdao.selectById(user.getUserCategoryId());

        double jine = 0;
        for(int i=0;i<waterFeeInfoList.size();i++){
            if(waterFeeInfoList.get(i)!=null){
                WaterFeeInfo one = waterFeeInfoList.get(i);
                switch (i){
                    case 0:{
                        ftlModel.biao1 = one.getWaterMeterId();
                        ftlModel.shangqi1 = String.valueOf(one.getPreviousDegree());
                        ftlModel.benqi1 = String.valueOf(one.getThisDegree());
                        ftlModel.shiyong1 = String .valueOf(one.getThisUsing());
                        break;
                    }
                    case 1:{
                        ftlModel.biao2 = one.getWaterMeterId();
                        ftlModel.shangqi2 = String.valueOf(one.getPreviousDegree());
                        ftlModel.benqi2 = String.valueOf(one.getThisDegree());
                        ftlModel.shiyong2 = String .valueOf(one.getThisUsing());
                        break;
                    }
                    case 2:{
                        ftlModel.biao3 = one.getWaterMeterId();
                        ftlModel.shangqi3 = String.valueOf(one.getPreviousDegree());
                        ftlModel.benqi3 = String.valueOf(one.getThisDegree());
                        ftlModel.shiyong3 = String .valueOf(one.getThisUsing());
                        break;
                    }
                }
                Date date = new Date();
                one.setPaymentDeadLine(Util.getPaymentDate(date).toString());
                dao.updateWaterFee(one);
                System.out.println("update success!");
                jine = jine+one.getThisDue();
            }
        }
        ftlModel.jine = String.valueOf(jine);
        if(first.getIsBilling()!=null && first.getIsBilling().equals("已开")) {
            ftlModel.danjia = String.valueOf(userCat.getTaxWaterPrice());
        }else{
            ftlModel.danjia = String.valueOf(userCat.getWaterPrice());
        }
        String fileName = "浙江工商大学水费缴费单.doc";
        String filePath = ConstantString.WORD_FILE_PATH+fileName;
        OutputStream out=null;
        try {
            out = new FileOutputStream(filePath);
        }catch (IOException e){
            System.out.println(e);
        }
        Map<String,Object> map = WordUtil.matchFtl(ftlModel);
        createWord(out, "/WaterFeeInfo.ftl", map);


    }

    public  List<Map<String,Object>> exportWaterFeeChange(String id) {
        WaterFeeInfo feeInfo = dao.selectById(id);
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Map<String,Object> map1 = new HashMap<String, Object>();
        Map<String,Object> map2 = new HashMap<String, Object>();
        map1.put("cell","原数据");
        map1.put("userId",feeInfo.getUserId());
        map1.put("meterId",feeInfo.getWaterMeterId());
        map1.put("previousDegree",feeInfo.getPreviousDegree());
        map1.put("thisDegree",feeInfo.getThisDegree());
        map1.put("thisUsing",feeInfo.getThisUsing());
        map1.put("thisRelief",feeInfo.getThisRelief());
        map1.put("thisDue",feeInfo.getThisDue());
        map1.put("thisPaid",feeInfo.getThisPaid());
        map1.put("paymentState",feeInfo.getPaymentState());
        map1.put("paymentDeadLine",feeInfo.getPaymentDeadLine());
        map1.put("paymentTime",feeInfo.getPaymentTime());
        map1.put("isBilling",feeInfo.getIsBilling());
        map1.put("latePayment",feeInfo.getLatePayment());
        map1.put("latePaymentState",feeInfo.getLatePaymentState());
        map1.put("meterReader",feeInfo.getMeterReader());
        map1.put("reporter",feeInfo.getReporter());
        map2.put("cell","新数据");
        data.add(map1);
        data.add(map2);

        return data;
    }

    @Transactional
    public ImportRes importFeeExcel(List<Map<String, Object>> mapList) {
        ImportRes importRes = new ImportRes();
        try {
            for (Map<String, Object> one : mapList) {
                WaterFeeInfo fee = dao.selectById((String) one.get("id"));
                System.out.println(fee.getId());
                fee.setPaymentTime((String) one.get("paymentTime"));
                fee.setMeterReadTime(Util.formatToSqlDate((String) one.get("meterReadTime")));
                fee.setUserId((String) one.get("userId"));
                fee.setWaterMeterId((String) one.get("meterId"));
                fee.setPaymentState((String) one.get("paymentState"));
                fee.setThisRelief(Integer.valueOf((String) one.get("thisRelief")));
                fee.setThisPaid(Double.valueOf((String) one.get("thisPaid")));
                if (one.get("latePaymentState") != null && !((String) one.get("latePaymentState")).equals("")) {
                    fee.setLatePaymentState((String) one.get("latePaymentState"));
                }
                if (one.get("latePayment") != null && !((String) one.get("latePayment")).equals("")) {
                    fee.setLatePayment(Double.valueOf((String) one.get("latePayment")));
                }
                fee.setId(Integer.valueOf((String) one.get("id")));
                fee.setIsBilling((String) one.get("isBilling"));
                fee.setReporter((String) one.get("reporter"));

                UserInfo userInfo = dao.selectOneById(fee.getWaterMeterId());
                UserCategory userCat = ucdao.selectById(userInfo.getUserCategoryId());
                WaterInfo waterInfo = widao.selectById(fee.getWaterMeterId());


                fee.setThisUsing2(waterInfo.getMaxDegree());
                if (fee.getIsBilling().equals("已开")) {
                    fee.setThisDue(waterInfo.getMaxDegree(), userCat.getTaxWaterPrice());
                } else {
                    fee.setThisDue(waterInfo.getMaxDegree(), userCat.getWaterPrice());
                }
                dao.updateWaterFee(fee);
            }

            importRes.setCode("200");
            importRes.setMsg("更新成功");
        }catch (Exception e) {

            importRes.setCode("500");
            importRes.setMsg("更新失败");
            //e.printStackTrace();
        }


        return importRes;
    }

    public List<Map<String,Object>> exportFeeExcelModel(List<String> ids) {
        List<WaterFeeInfo> feeInfoList = dao.selectByIds(ids);
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (WaterFeeInfo one:feeInfoList){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("id",String.valueOf(one.getId()));
            map.put("userId",one.getUserId());
            map.put("meterId",one.getWaterMeterId());
            map.put("meterReadTime",one.getMeterReadTime());
            data.add(map);
        }
        return data;


    }

    public List<Map<String, Object>> exportFeeDelete(List<String> ids) {
        List<WaterFeeInfo> feeInfoList = dao.selectByIds(ids);
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (WaterFeeInfo feeInfo:feeInfoList){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("id",feeInfo.getId());
            map.put("userId",feeInfo.getUserId());
            map.put("meterId",feeInfo.getWaterMeterId());
            map.put("previousDegree",feeInfo.getPreviousDegree());
            map.put("thisDegree",feeInfo.getThisDegree());
            map.put("thisUsing",feeInfo.getThisUsing());
            map.put("thisRelief",feeInfo.getThisRelief());
            map.put("thisDue",feeInfo.getThisDue());
            map.put("thisPaid",feeInfo.getThisPaid());
            map.put("paymentState",feeInfo.getPaymentState());
            map.put("paymentDeadLine",feeInfo.getPaymentDeadLine());
            map.put("paymentTime",feeInfo.getPaymentTime());
            map.put("isBilling",feeInfo.getIsBilling());
            map.put("latePayment",feeInfo.getLatePayment());
            map.put("latePaymentState",feeInfo.getLatePaymentState());
            map.put("meterReader",feeInfo.getMeterReader());
            map.put("reporter",feeInfo.getReporter());
            data.add(map);
        }
        return data;
    }

    public ImportRes setProve(String id,String path) {
        WaterFeeInfo one = new WaterFeeInfo();
        one.setId(Integer.valueOf(id));
        one.setCertificate(path);
        int flag = dao.updateWaterFee(one);
        ImportRes importRes = new ImportRes();
        if(flag==0){
            importRes.setCode(ConstantString.UPDATE_FAILED);
            importRes.setMsg("上传失败");
        }else{
            importRes.setCode(ConstantString.UPDATE_SUCCESS);
            importRes.setMsg("上传成功");
        }
        return importRes;
    }

    public String getProve(String id){
        WaterFeeInfo one = dao.selectById(id);
        return one.getCertificate();
    }
}
