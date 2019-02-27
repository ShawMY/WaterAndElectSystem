package com.zjgsu.shuidiansys.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstantString {
	
	public static String LOGIN_SUCCESS = "200";
	
	public static String LOGIN_FAILED = "500";

	public static String QUEARY_SUCCESS = "200";

	public static String QUEARY_FAILED = "500";

	public static String UPDATE_SUCCESS = "200";

	public static String UPDATE_FAILED = "500";

	public static String DELETE_PART_FAILED = "450";

	public static String DELETE_FAILED = "500";

	public static String DELETE_SUCCESS = "200";

	public static String INSERT_PART_FAILED = "450";

	public static String INSERT_FAILED = "500";

	public static String INSERT_SUCCESS = "200";

	public static int PAGE_SIZE = 3;

	public static List<String> LEVELS = new ArrayList<String>(Arrays.asList("一级","二级","三级","四级","五级","六级"));

	public static String WORD_FILE_PATH = "./res/";
	public static String PIC_WATER_FILE_PATH = "./res/pic/water/";
	public static String PIC_ELECTRIC_FILE_PATH = "./res/pic/electric/";

	public static String ORIGIN_PWD = "123456";

	public static int MAX_IMAGE_SIZE = 1024*10;

	// 报警异常
	public static String ALARM_NEARLY_FLIP = "剩余刻度不足百分之一";
	public static String ALARM_ALREADY_FLIP = "翻表报警";
	public static String ALARM_UNUSUAL_USE = "用量异常（超出或低于去年本期用量的百分之五十）";
	// 报警状态
	public static String ALARM_HASREADING = "已处理";
	public static String ALARM_NOREADING = "未处理";

	// 区域名称
	public static String AREA_NAME_J="金沙港";
	public static String AREA_NAME_Y="玉屏洲";
	public static String AREA_NAME_Q="钱江湾";

}
