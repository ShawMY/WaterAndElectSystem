package com.zjgsu.shuidiansys.common;

public class StringUtils {

    public static boolean isNotEmpty(String str){
        if (str!=null &&str!=""){
            return true;
        }else {
            return false;
        }
    }
}
