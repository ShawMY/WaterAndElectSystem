package com.zjgsu.shuidiansys.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Util {

    public static List<Integer> listStringToInteger(List<String> sList){
        List<Integer> iList = new ArrayList<Integer>();
        for(String one:sList){
            iList.add(Integer.valueOf(one));
        }
        return iList;
    }

    //获取年月号
    public static String getCurrentYearAndMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        return sdf.format(date).substring(0,6);
    }

    public static java.sql.Date getPaymentDate(Date date){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = new Date(date.getTime() + 7 * 24 * 60 * 60 * 1000);
        java.sql.Date sqlDate = new java.sql.Date(date2.getTime());
        return sqlDate;

    }

    public static java.sql.Date formatToSqlDate(String date){
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            java.util.Date utilDate = df.parse(date);// new Date()为获取当前系统时间
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            return sqlDate;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Date formatToUtilDate(String date){
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            java.util.Date utilDate = df.parse(date);// new Date()为获取当前系统时间
            return utilDate;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 检测是否触发翻表报警
    public static boolean isNearlyFlip(int maxDegree, int thisDegree){
        double miniUsing = maxDegree*0.01;
//        System.out.println(miniUsing);
//        System.out.println(maxDegree);
//        System.out.println(thisDegree);
        if(miniUsing>(maxDegree-thisDegree)){
            System.out.println(maxDegree-thisDegree);
            return true;
        }else{
            return false;
        }
    }

    public static int getActualUsing(int maxDegree,int thisDegree,int lastDegree){
        if(thisDegree<lastDegree){
            return (maxDegree+thisDegree)-lastDegree;
        }else{
            return thisDegree-lastDegree;
        }
    }

}
