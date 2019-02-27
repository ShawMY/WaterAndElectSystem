package com.zjgsu.shuidiansys.common;

public class ExcelModel {

    String[] excelHeader = {"ID", "国家", "商品编号", "名称", "分类", "创建日期"};
    String[] ds_titles = {"id", "country", "sn", "name", "productCategory", "createdDate"};

    // 水表抄表导出模型
    public static String[] WATER_COPY_HEADER = {"水表编号","水表地址","上期读数","本期读数","抄表日期","抄表员"};
    public static String[] WATER_COPY_DS_TITLES = {"waterMeterId","installAddress","previousDegree","thisDegree","meterReadTime","meterReader"};
    public static int[] WATER_COPY_DS_FORMAT = {1, 1, 1, 1, 1, 1};

    // 一级水表导出模型
    public static String[] TOP_WATER_HEADER = {"序号","户名","表号","位置","户号","上期示数","本期示数","结算水量","实际用量","单价","实际金额","备注","日期"};
    public static String[] TOP_WATER_DS_TITLES = {"number","userName","waterMeterId","location","userNumber","previousDegree","thisDegree","nominalUsage","actualUsing","price","actualPrice","remarks","date"};
    public static int[] TOP_WATER_DS_FORMAT = {1, 1, 1, 1, 1, 1,1,1,1,1,1,1,1};

    // 一级水表修改模型
    public static String[] TOP_WATER_CHANGE_HEADER = {"","序号","户名","表号","位置","户号","上期示数","本期示数","结算水量","实际用量","单价","实际金额","备注","日期"};
    public static String[] TOP_WATER_CHANGE_DS_TITLES = {"cell","number","userName","waterMeterId","location","userNumber","previousDegree","thisDegree","nominalUsage","actualUsing","price","actualPrice","remarks","date"};
    public static int[] TOP_WATER_CHANGE_DS_FORMAT = {1,1, 1, 1, 1, 1, 1,1,1,1,1,1,1,1};

    // 一级电表导出模型
    public static String[] TOP_ELECTRIC_HEADER = {"序号","户名","表号","位置","户号","上期示数","本期示数","倍率","铜铁损","实际用量","单价","调整金额","实际金额","日期"};
    public static String[] TOP_ELECTRIC_DS_TITLES = {"number","userName","electricMeterId","location","userNumber","previousDegree","thisDegree","magnification","copperLoss","actualUsing","price","adjustPrice","actualPrice","date"};
    public static int[] TOP_ELECTRIC_DS_FORMAT = {1, 1, 1, 1, 1, 1,1,1,1,1,1,1,1,1};

    // 一级电表修改模型
    public static String[] TOP_ELECTRIC_CHANGE_HEADER = {"","序号","户名","表号","位置","户号","上期示数","本期示数","倍率","铜铁损","实际用量","单价","调整金额","实际金额","日期"};
    public static String[] TOP_ELECTRIC_CHANGE_DS_TITLES = {"cell","number","userName","electricMeterId","location","userNumber","previousDegree","thisDegree","magnification","copperLoss","actualUsing","price","adjustPrice","actualPrice","date"};
    public static int[] TOP_ELECTRIC_CHANGE_DS_FORMAT = {1, 1, 1, 1, 1, 1,1,1,1,1,1,1,1,1,1};

    // 电表抄表导出模型
    public static String[] ELECTRIC_COPY_HEADER = {"电表编号","电表地址","上期读数","本期读数","抄表日期","抄表员"};
    public static String[] ELECTRIC_COPY_DS_TITLES = {"electricMeterId","installAddress","previousDegree","thisDegree","meterReadTime","meterReader"};
    public static int[] ELECTRIC_COPY_DS_FORMAT = {1, 1, 1, 1, 1, 1};

    // 费用修改导出模型
    public static String[] TABLE_HEADER = {"","用户编号","表记编号","抄表日期","上期度数","本期度数","本期用量","本期免除","本期应缴","本期已缴","缴费状态","缴费截止日期","缴清日期","是否开票","滞纳金","滞纳金状态","抄表员","录入员"};
    public static String[] TABLE_DS_TITLES = {"cell","userId","meterId","meterReadTime","previousDegree","thisDegree","thisUsing","thisRelief","thisDue","thisPaid","paymentState","paymentDeadLine","paymentTime","isBilling","latePayment","latePaymentState","meterReader","reporter"};
    public static int[] TABLE_DS_FORMAT = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

    // 费用删除导出模型
    public static String[] TABLE_DLETE_HEADER = {"费用编号","用户编号","表记编号","抄表日期","上期度数","本期度数","本期用量","本期免除","本期应缴","本期已缴","缴费状态","缴费截止日期","缴清日期","是否开票","滞纳金","滞纳金状态","抄表员","录入员"};
    public static String[] TABLE_DLETE_DS_TITLES = {"id","userId","meterId","meterReadTime","previousDegree","thisDegree","thisUsing","thisRelief","thisDue","thisPaid","paymentState","paymentDeadLine","paymentTime","isBilling","latePayment","latePaymentState","meterReader","reporter"};
    public static int[] TABLE_DLETE_DS_FORMAT = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

    // 费用更新导出模型
    public static String[] TABLE_FEE_HEADER = {"费用编号","用户编号","表记编号","抄表日期","本期免除","本期已缴","缴费状态","缴清日期","是否开票","滞纳金","滞纳金状态","录入员"};
    public static String[] TABLE_FEE_DS_TITLES = {"id","userId","meterId","meterReadTime","thisRelief","thisPaid","paymentState","paymentTime","isBilling","latePayment","latePaymentState","reporter"};
    public static int[] TABLE_FEE_DS_FORMAT = {1,1,1,1,1,1,1,1,1,1,1,1};

    // 各生活园区一级电表支付与二级表回收对比
    public static String[] TOP_SECOND_CMP_HEADER = {"园区","一级表实际支付实用数","支付金额","二级表回收实用数","回收金额","实用数差额","金额差额"};
    public static String[]TOP_SECOND_CMP_DS_TITLES = {"area","topUse","paymentPrice","secondUse","recylePrice","useDif","paymentDif"};
    public static int[] TOP_SECOND_CMP_DS_FORMAT = {1,1,1,1,1,1,1};
}
