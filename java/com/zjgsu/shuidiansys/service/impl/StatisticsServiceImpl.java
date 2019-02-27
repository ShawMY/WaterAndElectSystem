package com.zjgsu.shuidiansys.service.impl;

import com.zjgsu.shuidiansys.common.ConstantString;
import com.zjgsu.shuidiansys.dao.IElectricFeeInfoDao;
import com.zjgsu.shuidiansys.dao.IElectricInfoDao;
import com.zjgsu.shuidiansys.dao.IWaterFeeInfoDao;
import com.zjgsu.shuidiansys.pojo.ElectricFeeInfo;
import com.zjgsu.shuidiansys.pojo.ElectricInfo;
import com.zjgsu.shuidiansys.pojo.Statistics.TopSecondCmp;
import com.zjgsu.shuidiansys.pojo.WaterFeeInfo;
import com.zjgsu.shuidiansys.service.IStatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class StatisticsServiceImpl implements IStatisticsService {

    @Resource
    IWaterFeeInfoDao wfdao;
    @Resource
    IElectricFeeInfoDao efdao;
    @Resource
    IElectricInfoDao eidao;

    public List<Map<String, Object>> getEYearTopSecondCompare(String year) {
        List<ElectricFeeInfo> feeInfos = efdao.selectByDateLike(year);
        TopSecondCmp jsg = new TopSecondCmp();
        TopSecondCmp ypz = new TopSecondCmp();
        TopSecondCmp qjw = new TopSecondCmp();
        for(ElectricFeeInfo one : feeInfos){
            ElectricInfo electricInfo = eidao.selectById(one.getElectricityMeterId());
            if(electricInfo.getSecondAddress().equals(ConstantString.AREA_NAME_J)){
                if(electricInfo.getLevel().equals("一级")){
                    jsg.setTopUse(one.getThisUsing());
                    jsg.setPaymentPrice(one.getThisPaid());
                }
                if(electricInfo.getLevel().equals("二级")){
                    jsg.setSecondUse(one.getThisUsing());
                    jsg.setRecylePrice(one.getThisPaid());
                }
            }
            if(electricInfo.getSecondAddress().equals(ConstantString.AREA_NAME_Y)){
                if(electricInfo.getLevel().equals("一级")){
                    ypz.setTopUse(one.getThisUsing());
                    ypz.setPaymentPrice(one.getThisPaid());
                }
                if(electricInfo.getLevel().equals("二级")){
                    ypz.setSecondUse(one.getThisUsing());
                    ypz.setRecylePrice(one.getThisPaid());
                }
            }
            if(electricInfo.getSecondAddress().equals(ConstantString.AREA_NAME_Q)){
                if(electricInfo.getLevel().equals("一级")){
                    qjw.setTopUse(one.getThisUsing());
                    qjw.setPaymentPrice(one.getThisPaid());
                }
                if(electricInfo.getLevel().equals("二级")){
                    qjw.setSecondUse(one.getThisUsing());
                    qjw.setRecylePrice(one.getThisPaid());
                }
            }
        }
        jsg.setUseDif();
        jsg.setPaymentDif();
        ypz.setUseDif();
        ypz.setPaymentDif();
        qjw.setUseDif();
        qjw.setPaymentDif();
        List<Map<String,Object>> maps = new ArrayList<Map<String, Object>>();
        maps.add(topCmpMatcher(jsg,ConstantString.AREA_NAME_J));
        maps.add(topCmpMatcher(ypz,ConstantString.AREA_NAME_Y));
        maps.add(topCmpMatcher(qjw,ConstantString.AREA_NAME_Q));

        return maps;
    }

    public Map<String,Object> topCmpMatcher(TopSecondCmp one,String name){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("area",name);
        map.put("paymentDif",one.getPaymentDif());
        map.put("topUse",one.getTopUse());
        map.put("paymentPrice",one.getPaymentPrice());
        map.put("secondUse",one.getSecondUse());
        map.put("recylePrice",one.getRecylePrice());
        map.put("useDif",one.getUseDif());

        return map;


    }
}
