package com.zjgsu.shuidiansys.common;

import net.sourceforge.pinyin4j.PinyinHelper;

public class PinyinUtils {

    //判断是否为汉字
    public static boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }

    //获取首字符
    public static String getFirstChar(String str){
        return str.substring(0,1);
    }

    //获取中文首字母
    public static String getPinYinHeadChar(String str) {

        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

    public static String getFistCode(String str){
        String firstc  = getFirstChar(str);
        boolean flag = isChineseChar(str.charAt(0));
        //System.out.println(flag);
        if(flag){
            return getPinYinHeadChar(firstc).toUpperCase();
        }else{
            return firstc;
        }
    }
}
