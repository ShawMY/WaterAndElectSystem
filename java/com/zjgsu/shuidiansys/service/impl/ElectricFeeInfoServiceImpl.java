package com.zjgsu.shuidiansys.service.impl;

import com.zjgsu.shuidiansys.common.ConstantString;
import com.zjgsu.shuidiansys.common.PageParam;
import com.zjgsu.shuidiansys.common.Util;
import com.zjgsu.shuidiansys.common.WordUtil;
import com.zjgsu.shuidiansys.dao.*;
import com.zjgsu.shuidiansys.pojo.*;
import com.zjgsu.shuidiansys.pojo.RESTful.*;
import com.zjgsu.shuidiansys.service.IElectricFeeInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.zjgsu.shuidiansys.common.WordUtil.createWord;

@Service
public class ElectricFeeInfoServiceImpl implements IElectricFeeInfoService {
    @Resource
    IElectricFeeInfoDao dao;
    @Resource
    IElectricInfoDao eidao;
    @Resource
    IUserInfoDao uidao;
    @Resource
    IElectricCopyDao ecdao;
    @Resource
    IUserCategoryDao ucdao;

    @Resource
    IMeterAlarmDao madao;
    public int getRowCountByPage(){return dao.getRowCountByPage();}

    public int getRowCountByQuery(ElectricFeeInfo electricInfo){return dao.getRowCountByQuery(electricInfo);}
    public GetElectricFeeListRes getElectricFeeList(PageParam pageParam) {
        int currPage = pageParam.getCurrPage();
        pageParam.setRowCount(getRowCountByPage());
        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", offset);
        params.put("size", size);

        List<ElectricFeeInfo> electricFeeInfoList = dao.selectByParams(params);

        GetElectricFeeListRes electricFeeListRes = new GetElectricFeeListRes();
        GetElectricFeeListRes.Content content = electricFeeListRes.new Content();
        List<GetElectricFeeListRes.Content.Info> infoList = new ArrayList<GetElectricFeeListRes.Content.Info>();
        electricFeeListRes.setCode(ConstantString.QUEARY_SUCCESS);
        electricFeeListRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");

        for(ElectricFeeInfo electricFeeInfo:electricFeeInfoList){
            GetElectricFeeListRes.Content.Info info = content.new Info();
            info.setCurrentFree(String.valueOf(electricFeeInfo.getThisRelief()));
            info.setCurrentNum(String.valueOf(electricFeeInfo.getThisDegree()));
            info.setCurrentPayable(String.valueOf(electricFeeInfo.getThisDue()));
            info.setCurrentPayed(String.valueOf(electricFeeInfo.getThisPaid()));
            info.setFinishPaymentTime(electricFeeInfo.getPaymentTime());
            info.setId(String.valueOf(electricFeeInfo.getId()));
            info.setIsInvoice(electricFeeInfo.getIsBilling());
            info.setMeterCode(electricFeeInfo.getElectricityMeterId());
            info.setOverdueFee(String.valueOf(electricFeeInfo.getLatePayment()));
            info.setOverdueFeeStatus(electricFeeInfo.getLatePaymentState());
            info.setPayDeadline(electricFeeInfo.getPaymentDeadLine());
            info.setPayStatus(electricFeeInfo.getPaymentState());
            info.setPreviousNum(String.valueOf(electricFeeInfo.getPreviousDegree()));
            info.setRecordTime(String.valueOf(electricFeeInfo.getMeterReadTime()));
            info.setUserCode(electricFeeInfo.getUserId());
            if(electricFeeInfo.getCertificate()!=null&&!electricFeeInfo.getCertificate().equals("")){
                info.setIsProve("true");
            }else{
                info.setIsProve("false");
            }
            infoList.add(info);
        }
        content.setInfo(infoList);
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        electricFeeListRes.setContent(content);

        return electricFeeListRes;
    }

    public QueryElectricFeeRes getElectricFeeListByQuery(QueryElectricFeeReq electricFeeReq) {
        int currPage = electricFeeReq.getPage();
        PageParam pageParam = new PageParam();
        pageParam.setCurrPage(currPage);

        // limit offset, size
        int offset = (currPage - 1) * PageParam.pageSize ; //起始条数
        int size = PageParam.pageSize;
        int lastset = offset+size;

        //System.out.println(electricFeeReq.getRecordTime1());



        // 通过电表地址查询ID列表

        ElectricInfo electricInfo = new ElectricInfo();
        electricInfo.setFirstAddress(electricFeeReq.getInstallAddress1());
        electricInfo.setSecondAddress(electricFeeReq.getInstallAddress2());
        electricInfo.setThirdAddress(electricFeeReq.getInstallAddress3());
        electricInfo.setFourthAddress(electricFeeReq.getInstallAddress4());
        electricInfo.setFifthAddress(electricFeeReq.getInstallAddress5());
        electricInfo.setSixthAddress(electricFeeReq.getInstallAddress6());


        List<ElectricInfo> electricInfos = eidao.selectAllByQuery(electricInfo);
        //System.out.println(electricInfos.size());

        //获取查询列表
        List<ElectricFeeInfo> electricFeeList = new ArrayList<ElectricFeeInfo>();
        for (ElectricInfo electric:electricInfos){
            ElectricFeeInfo electricFeeInfo = new ElectricFeeInfo();
            electricFeeInfo.setPaymentState(electricFeeReq.getStatus());
            electricFeeInfo.setElectricityMeterId(electric.getElectricityMeterId());
            electricFeeList.add(electricFeeInfo);
        }



        List<ElectricFeeInfo> electricFeeInfoList = new ArrayList<ElectricFeeInfo>();// 逐条查询获取返回列表
        for(ElectricFeeInfo electricFeeInfo:electricFeeList){
            //System.out.println(electricFeeInfo.getElectricityMeterId());
            Map<String, Object> params = new HashMap<String, Object>(); // 查询1
            params.put("beginTime",electricFeeReq.getRecordTime1());
            params.put("endTime",electricFeeReq.getRecordTime2());
            params.put("electric",electricFeeInfo);
            List<ElectricFeeInfo> one = dao.selectByQuery(params);
            if(one!=null) {
                //System.out.println("one!=null");
                electricFeeInfoList.addAll(one);
            }

        }
        pageParam.setRowCount(electricFeeInfoList.size());// 得到总条数
        System.out.println(lastset);
        if(lastset>electricFeeInfoList.size()){
            lastset=electricFeeInfoList.size();

        }
        //System.out.println(electricFeeInfoList.size());
        QueryElectricFeeRes electricFeeRes = new QueryElectricFeeRes();
        QueryElectricFeeRes.Content content = electricFeeRes.new Content();
        List<QueryElectricFeeRes.Content.Info> infoList = new ArrayList<QueryElectricFeeRes.Content.Info>();
        electricFeeRes.setCode(ConstantString.QUEARY_SUCCESS);
        electricFeeRes.setMsg("共查询到数据"+pageParam.getRowCount()+"条");
        for(int i=offset;i<lastset;i++){
            ElectricFeeInfo electricFeeInfo = electricFeeInfoList.get(i);
            QueryElectricFeeRes.Content.Info info = content.new Info();
            try {
                info.setCurrentFree(String.valueOf(electricFeeInfo.getThisRelief()));
                info.setCurrentNum(String.valueOf(electricFeeInfo.getThisDegree()));
                info.setCurrentPayable(String.valueOf(electricFeeInfo.getThisDue()));
                info.setCurrentPayed(String.valueOf(electricFeeInfo.getThisPaid()));
                info.setFinishPaymentTime(electricFeeInfo.getPaymentTime());
                info.setId(String.valueOf(electricFeeInfo.getId()));
                info.setIsInvoice(electricFeeInfo.getIsBilling());
                info.setMeterCode(electricFeeInfo.getElectricityMeterId());
                info.setOverdueFee(String.valueOf(electricFeeInfo.getLatePayment()));
                info.setOverdueFeeStatus(electricFeeInfo.getLatePaymentState());
                info.setPayDeadline(electricFeeInfo.getPaymentDeadLine());
                info.setPayStatus(electricFeeInfo.getPaymentState());
                info.setPreviousNum(String.valueOf(electricFeeInfo.getPreviousDegree()));
                info.setRecordTime(String.valueOf(electricFeeInfo.getMeterReadTime()));
                info.setUserCode(electricFeeInfo.getUserId());
                if(electricFeeInfo.getCertificate()!=null&&!electricFeeInfo.getCertificate().equals("")){
                    info.setIsProve("true");
                }else{
                    info.setIsProve("false");
                }
            }catch (Exception e){
                System.out.println(e);
            }
            infoList.add(info);
        }
        content.setInfo(infoList);
        content.setPage(pageParam.getCurrPage());
        content.setPageNum(pageParam.getTotalPage());
        electricFeeRes.setContent(content);

        return electricFeeRes;
    }

    public DeleteRes deleteElectricFee(DeleteReq electricFeeReq) {

        int deleteFlag = dao.delete(electricFeeReq.getIds());
        DeleteRes electricFeeRes = new DeleteRes();

        if(deleteFlag == 0){
            electricFeeRes.setCode(ConstantString.DELETE_FAILED);
            electricFeeRes.setMsg("删除数据失败");
        }else if(deleteFlag < electricFeeReq.getIds().size()){
            electricFeeRes.setCode(ConstantString.DELETE_PART_FAILED);
            electricFeeRes.setMsg("删除数据部分失败");
        }else{
            electricFeeRes.setCode(ConstantString.DELETE_SUCCESS);
            electricFeeRes.setMsg("删除数据成功");
        }
        return electricFeeRes;
    }

    public ChangeRes changeElectricFee(ChangeElectricFeeReq electricFeeReq) {

        ElectricFeeInfo electricFeeInfo = new ElectricFeeInfo();
        if(!electricFeeReq.getId().equals("")&&electricFeeReq.getId()!=null)
            electricFeeInfo.setId(Integer.valueOf(electricFeeReq.getId()));
        electricFeeInfo.setIsBilling(electricFeeReq.getIsInvoice());
        if(electricFeeReq.getOverdueFee()!=null&& !electricFeeReq.getOverdueFee().equals(""))
            electricFeeInfo.setLatePayment(Double.valueOf(electricFeeReq.getOverdueFee()));
        electricFeeInfo.setLatePaymentState(electricFeeReq.getOverdueFeeStatus());

        //日期转化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(electricFeeReq.getRecordTime());
            java.sql.Date meterReadTime = new java.sql.Date(date.getTime());
            electricFeeInfo.setMeterReadTime(meterReadTime);
        } catch (Exception e) {
            System.out.println(e);
        }
        electricFeeInfo.setPaymentDeadLine(electricFeeReq.getPayDeadline());
        electricFeeInfo.setPaymentState(electricFeeReq.getPayStatus());
        electricFeeInfo.setPaymentTime(electricFeeReq.getFinishPaymentTime());
        if(electricFeeReq.getPreviousNum()!=null&& !electricFeeReq.getPreviousNum().equals(""))
            electricFeeInfo.setPreviousDegree(Integer.valueOf(electricFeeReq.getPreviousNum()));
        if(electricFeeReq.getCurrentPayable()!=null&& !electricFeeReq.getCurrentPayable().equals(""))
            electricFeeInfo.setThisDue(Double.valueOf(electricFeeReq.getCurrentPayable()));
        if(electricFeeReq.getCurrentNum()!=null&& !electricFeeReq.getCurrentNum().equals(""))
            electricFeeInfo.setThisDegree(Integer.valueOf(electricFeeReq.getCurrentNum()));
        if(electricFeeReq.getCurrentPayed()!=null&& !electricFeeReq.getCurrentPayed().equals(""))
            electricFeeInfo.setThisPaid(Double.valueOf(electricFeeReq.getCurrentPayed()));
        if(electricFeeReq.getCurrentFree()!=null&& !electricFeeReq.getCurrentFree().equals(""))
            electricFeeInfo.setThisRelief(Integer.valueOf(electricFeeReq.getCurrentFree()));
        electricFeeInfo.setElectricityMeterId(electricFeeReq.getMeterCode());
        UserInfo userInfo = dao.selectOneById(electricFeeInfo.getElectricityMeterId());
        UserCategory userCat = ucdao.selectById(userInfo.getUserCategoryId());
        ElectricInfo electricInfo = eidao.selectById(electricFeeInfo.getElectricityMeterId());

        electricFeeInfo.setThisUsing2(electricInfo.getMaxDegree());
        System.out.println(electricInfo.getMaxDegree());
        if (electricFeeInfo.getIsBilling()!=null&&electricFeeInfo.getIsBilling().equals("已开")) {
            electricFeeInfo.setThisDue(electricInfo.getMaxDegree(), userCat.getTaxElectricityPrice());
        } else {
            electricFeeInfo.setThisDue(electricInfo.getMaxDegree(), userCat.getElectricityPrice());
        }
        int updateFlag = dao.update(electricFeeInfo);
        ChangeRes electricFeeRes = new ChangeRes();

        if(updateFlag == 0){
            electricFeeRes.setCode(ConstantString.UPDATE_FAILED);
            electricFeeRes.setMsg("更新数据失败");
        }else {
            electricFeeRes.setCode(ConstantString.INSERT_SUCCESS);
            electricFeeRes.setMsg("更新数据成功");

        }

        return electricFeeRes;
    }

    @Transactional
    public ImportRes importElectricCopy(List<Map<String, Object>> mapList) {
        ImportRes electricCopyRes = new ImportRes();
//        try{
            for (Map<String, Object> one : mapList) {
                ElectricFeeInfo electric = new ElectricFeeInfo();
                ElectricCopy electricCopy = new ElectricCopy();
                electric.setElectricityMeterId((String) one.get("electricMeterId"));
                UserInfo user = dao.selectOneById(electric.getElectricityMeterId());
                ElectricInfo electricInfo = eidao.selectById(electric.getElectricityMeterId());
                //System.out.println(one.get("electricMeterId"));
                UserCategory userCat = ucdao.selectById(user.getUserCategoryId());

                int thisDegree = Integer.valueOf((String) one.get("thisDegree"));
                int previousDegree =  Integer.valueOf((String) one.get("previousDegree"));
                java.sql.Date date = Util.formatToSqlDate((String) one.get("meterReadTime"));


                electric.setUserId(user.getUserId());
                electric.setThisDegree(Integer.valueOf((String) one.get("thisDegree")));
                electric.setPreviousDegree(Integer.valueOf((String)  one.get("previousDegree")));
                electric.setThisUsing2(electricInfo.getMaxDegree());



                // 获取去年与今年的本期示数
                Date date1 = Util.formatToUtilDate((String) one.get("meterReadTime"));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date1);
                int year =calendar.get(Calendar.YEAR);
                int month =calendar.get(Calendar.MONTH)+1;
                int day = calendar.get(Calendar.DATE);
                String thisTime1 = String.valueOf(year)+"-"+String.valueOf(month);
                ElectricFeeInfo info = dao.selectOneByDateLike(thisTime1,electric.getElectricityMeterId());
                double rate = 0;
                if(info==null) {
                    int month2 = month - 1;
                    if (month == 1) {
                        month2 = 12;
                    }
                    int year2 = year - 1;
                    String thisTime2 = String.valueOf(year) + "-" + String.format("%02d",month2);
                    ElectricFeeInfo info2 = dao.selectOneByDateLike(thisTime2,electric.getElectricityMeterId());
                    System.out.println(thisTime2);
                    //System.out.println("info2:"+info2.getWaterMeterId());
                    String lastTime1 = String.valueOf(year2) + "-" + String.format("%02d",month);
                    ElectricFeeInfo info3 = dao.selectOneByDateLike(lastTime1,electric.getElectricityMeterId());
                    //System.out.println("info3:"+info3.getWaterMeterId());
                    String lastTime2 = String.valueOf(year2) + "-" + String.format("%02d",month2);
                    ElectricFeeInfo info4 = dao.selectOneByDateLike(lastTime2,electric.getElectricityMeterId());
                    //System.out.println("info3:"+info3.getWaterMeterId());
                    if(info2!=null&&info3!=null&&info4!=null) {
                        int using1 = Util.getActualUsing(electricInfo.getMaxDegree(),info.getThisDegree(),info2.getThisDegree());
                        int using2 = Util.getActualUsing(electricInfo.getMaxDegree(),info3.getThisDegree(),info4.getThisDegree());
                        rate = (Math.abs(using1 - using2) * 1.0) / using2;
                    }
                }



                // 检测报警

                MeterAlarm ma = new MeterAlarm();
                System.out.println(electricInfo.getElectricityMeterId());
                if(Util.isNearlyFlip(electricInfo.getMaxDegree(),thisDegree)){
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



                electric.setThisDue(electricInfo.getMaxDegree(), userCat.getElectricityPrice());
                electric.setMeterReader((String) one.get("meterReader"));
                try {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                    java.util.Date utildate = df.parse((String) one.get("meterReadTime"));// new Date()为获取当前系统时间
                    java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
                    electric.setMeterReadTime(sqlDate);
                }catch (Exception e){
                    System.out.println(e);
                }

                electricCopy.setElectricityMeterId(electric.getElectricityMeterId());
                electricCopy.setPreviousDegree(electric.getThisDegree());
                electricCopy.setMeterReader(electric.getMeterReader());
                if(ecdao.getRowCountById(electric.getElectricityMeterId())==0){
                    electricCopy.setFirstAddress(electricInfo.getFirstAddress());
                    electricCopy.setSecondAddress(electricInfo.getSecondAddress());
                    electricCopy.setThirdAddress(electricInfo.getThirdAddress());
                    electricCopy.setFourthAddress(electricInfo.getFourthAddress());
                    electricCopy.setFifthAddress(electricInfo.getFifthAddress());
                    electricCopy.setSixthAddress(electricInfo.getSixthAddress());
                    ecdao.insert(electricCopy);
                }else if(ecdao.getRowCountById(electric.getElectricityMeterId())==1){
                    ecdao.update(electricCopy);
                }

                dao.insert(electric);
            }


            electricCopyRes.setCode("200");
            electricCopyRes.setMsg("导入成功");
//        }catch (Exception e){
//            System.out.println(e);
//            electricCopyRes.setCode("500");
//            electricCopyRes.setMsg("发生内部错误，请检查数据格式或检查此表是否已与用户绑定");
//        }

        return electricCopyRes;
    }

    public void exportElectricFeeInfo(List<String> ids) {

        // 获取ID对应的费用列表
        List<ElectricFeeInfo> electricFeeInfoList = dao.selectByIds(ids);
        FeeInfoFtl ftlModel = new FeeInfoFtl();
        Calendar calendar = Calendar.getInstance();
        ftlModel.nian2 = String.valueOf(calendar.get(Calendar.YEAR));
        ftlModel.yue3 = String.valueOf(calendar.get(Calendar.MONTH)+1);
        ftlModel.ri3 = String.valueOf(calendar.get(Calendar.DATE));
        ElectricFeeInfo first  = electricFeeInfoList.get(0);
        UserInfo user = dao.selectOneById(first.getElectricityMeterId());
        ElectricInfo electricInfo = eidao.selectById(first.getElectricityMeterId());
        ftlModel.huming = user.getUserName();
        ftlModel.dizhi = electricInfo.getFirstAddress()+electricInfo.getSecondAddress()+electricInfo.getThirdAddress()
                +electricInfo.getFourthAddress()+electricInfo.getFifthAddress()+electricInfo.getSixthAddress();
        UserCategory userCat = ucdao.selectById(user.getUserCategoryId());

        double jine = 0;
        for(int i=0;i<electricFeeInfoList.size();i++){
            if(electricFeeInfoList.get(i)!=null){
                ElectricFeeInfo one = electricFeeInfoList.get(i);
                switch (i){
                    case 0:{
                        ftlModel.biao1 = one.getElectricityMeterId();
                        ftlModel.shangqi1 = String.valueOf(one.getPreviousDegree());
                        ftlModel.benqi1 = String.valueOf(one.getThisDegree());
                        ftlModel.shiyong1 = String .valueOf(one.getThisUsing());
                        break;
                    }
                    case 1:{
                        ftlModel.biao2 = one.getElectricityMeterId();
                        ftlModel.shangqi2 = String.valueOf(one.getPreviousDegree());
                        ftlModel.benqi2 = String.valueOf(one.getThisDegree());
                        ftlModel.shiyong2 = String .valueOf(one.getThisUsing());
                        break;
                    }
                    case 2:{
                        ftlModel.biao3 = one.getElectricityMeterId();
                        ftlModel.shangqi3 = String.valueOf(one.getPreviousDegree());
                        ftlModel.benqi3 = String.valueOf(one.getThisDegree());
                        ftlModel.shiyong3 = String .valueOf(one.getThisUsing());
                        break;
                    }
                }
                Date date = new Date();
                one.setPaymentDeadLine(Util.getPaymentDate(date).toString());
                dao.update(one);
                System.out.println("update success!");
                jine = jine+one.getThisDue();
            }
        }
        ftlModel.jine = String.valueOf(jine);
        if(first.getIsBilling()!=null && first.getIsBilling().equals("已开")) {
            ftlModel.danjia = String.valueOf(userCat.getTaxElectricityPrice());
        }else{
            ftlModel.danjia = String.valueOf(userCat.getElectricityPrice());
        }
        String fileName = "浙江工商大学电费缴费单.doc";
        String filePath = ConstantString.WORD_FILE_PATH+fileName;
        OutputStream out=null;
        try {
            out = new FileOutputStream(filePath);
        }catch (IOException e){
            System.out.println(e);
        }
        Map<String,Object> map = WordUtil.matchFtl(ftlModel);
        createWord(out, "/ElectricFeeInfo.ftl", map);


    }

    public  List<Map<String,Object>> exportElectricFeeChange(String id) {
        ElectricFeeInfo feeInfo = dao.selectById(id);
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Map<String,Object> map1 = new HashMap<String, Object>();
        Map<String,Object> map2 = new HashMap<String, Object>();
        map1.put("cell","原数据");
        map1.put("userId",feeInfo.getUserId());
        map1.put("meterId",feeInfo.getElectricityMeterId());
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
                ElectricFeeInfo fee = dao.selectById((String) one.get("id"));
                fee.setPaymentTime((String) one.get("paymentTime"));
                fee.setMeterReadTime(Util.formatToSqlDate((String) one.get("meterReadTime")));
                fee.setUserId((String) one.get("userId"));
                fee.setElectricityMeterId((String) one.get("meterId"));
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

                UserInfo userInfo = dao.selectOneById(fee.getElectricityMeterId());
                UserCategory userCat = ucdao.selectById(userInfo.getUserCategoryId());
                ElectricInfo electricInfo = eidao.selectById(fee.getElectricityMeterId());

                fee.setThisUsing2(electricInfo.getMaxDegree());
                if (fee.getIsBilling().equals("已开")) {
                    fee.setThisDue(electricInfo.getMaxDegree(), userCat.getTaxElectricityPrice());
                } else {
                    fee.setThisDue(electricInfo.getMaxDegree(), userCat.getElectricityPrice());
                }
                dao.update(fee);
            }

            importRes.setCode("200");
            importRes.setMsg("更新成功");
        }catch (Exception e) {
            importRes.setCode("500");
            importRes.setMsg("更新失败");
        }


        return importRes;
    }

    public List<Map<String,Object>> exportFeeExcelModel(List<String> ids) {
        List<ElectricFeeInfo> feeInfoList = dao.selectByIds(ids);
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (ElectricFeeInfo one:feeInfoList){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("id",String.valueOf(one.getId()));
            map.put("userId",one.getUserId());
            map.put("meterId",one.getElectricityMeterId());
            map.put("meterReadTime",one.getMeterReadTime());
            data.add(map);
        }
        return data;


    }

    public List<Map<String, Object>> exportFeeDelete(List<String> ids) {
        List<ElectricFeeInfo> feeInfoList = dao.selectByIds(ids);
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (ElectricFeeInfo feeInfo:feeInfoList){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("id",feeInfo.getId());
            map.put("userId",feeInfo.getUserId());
            map.put("meterId",feeInfo.getElectricityMeterId());
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
        ElectricFeeInfo one = new ElectricFeeInfo();
        one.setId(Integer.valueOf(id));
        one.setCertificate(path);
        int flag = dao.update(one);
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
        ElectricFeeInfo one = dao.selectById(id);
        return one.getCertificate();
    }

}
