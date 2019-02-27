package com.zjgsu.shuidiansys.test;


import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import static com.zjgsu.shuidiansys.common.WordUtil.createWord;

public class Test {
    public static void main(String[] args) {
        String outputFile = "E:\\TestWord\\test.doc";
        System.out.println("123456");
        OutputStream out=null;
        try {

             out = new FileOutputStream(outputFile);
        }catch (IOException e){
            System.out.println(e);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("nian1", "2018");
        map.put("yue1", "10");
        map.put("ri1", "10");
        map.put("nian2", "2018");
        map.put("yue2", "10");
        map.put("ri2", "11");
        map.put("yue3", "10");
        map.put("ri3", "11");
        map.put("huming", "admin");
        map.put("dizhi", "admin");
        map.put("biao1", "123");
        map.put("biao2", "");
        map.put("biao3", "");
        map.put("shangqi1", "105");
        map.put("shangqi2", "");
        map.put("shangqi3", "");
        map.put("benqi1", "200");
        map.put("benqi2", "");
        map.put("benqi3","");
        map.put("shiyong1","95");
        map.put("shiyong2","");
        map.put("shiyong3","");
        map.put("danjia","1.0");
        map.put("jine","95.0");
        map.put("beizhu","");





    }

    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    private static List<Method> getAllMethods(Object obj){
        List<Method> methods = new ArrayList<Method>();
        Class<?> clazz = obj.getClass();
        while(!clazz.getName().equals("java.lang.Object")){
            methods.addAll(Arrays.asList(clazz.getDeclaredMethods()));
            clazz = clazz.getSuperclass();
        }
        return methods;
    }

    public  static Object matchMapWithClass(Map<String,Object> map,Object object){
        List<Method> methods = getAllMethods(object);
        for(String key:map.keySet()) {
            for (Method m : methods) {
                String methodName = m.getName();
                String member = toLowerCaseFirstOne(methodName.substring(3));
                //System.out.println(member);
                if (methodName.startsWith("set")&&member.equals(key)) {
                    System.out.println(member);
                    try {

                        m.invoke(object,map.get(key));
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
            }
        }
        return object;
    }

    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
