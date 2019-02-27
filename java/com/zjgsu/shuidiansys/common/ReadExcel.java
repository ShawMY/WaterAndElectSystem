package com.zjgsu.shuidiansys.common;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadExcel {
    public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
    public static final String EMPTY = "";
    public static final String POINT = ".";
    public static SimpleDateFormat sdf =   new SimpleDateFormat("yyyy/MM/dd");
    /**
     * 获得path的后缀名
     * @param path
     * @return
     */
    public static String getPostfix(String path){

        if(path==null || EMPTY.equals(path.trim())){
            return EMPTY;
        }
        if(path.contains(POINT)){
            return path.substring(path.lastIndexOf(POINT)+1,path.length());
        }
        return EMPTY;
    }
    /**
     * 单元格格式
     * @param hssfCell
     * @return
     */
    @SuppressWarnings({ "static-access", "deprecation" })
    public static String getHValue(HSSFCell hssfCell){
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            String cellValue = "";
            if(HSSFDateUtil.isCellDateFormatted(hssfCell)){
                Date date = HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue());
                cellValue = sdf.format(date);
            }else{
                DecimalFormat df = new DecimalFormat("#.##");
                cellValue = df.format(hssfCell.getNumericCellValue());
                String strArr = cellValue.substring(cellValue.lastIndexOf(POINT)+1,cellValue.length());
                if(strArr.equals("00")){
                    cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
                }
            }
            return cellValue;
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }

    public int totalRows; //sheet中总行数
    public static int totalCells; //每一行总单元格数
    /**
     * read the Excel .xlsx,.xls
     * @param file jsp中的上传文件
     * @return
     * @throws IOException
     */
    public List<ArrayList<String>> readExcel(MultipartFile file) throws IOException {
        if(file==null||EMPTY.equals(file.getOriginalFilename().trim())){
            return null;
        }else{
            String postfix = getPostfix(file.getOriginalFilename());
            if(!EMPTY.equals(postfix)){
                if(OFFICE_EXCEL_2003_POSTFIX.equals(postfix)){
                    return readXls(file);
                } else{
                    return null;
                }
            }
        }
        return null;
    }
    /**
     * read the Excel 2003-2007 .xls
     * @param file
     * @return
     * @throws IOException
     */
    public List<ArrayList<String>> readXls(MultipartFile file){
        List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        // IO流读取文件
        InputStream input = null;
        HSSFWorkbook wb = null;
        ArrayList<String> rowList = null;
        try {
            input = file.getInputStream();
            // 创建文档
            wb = new HSSFWorkbook(input);
            //读取sheet(页)
            for(int numSheet=0;numSheet<wb.getNumberOfSheets();numSheet++){
                HSSFSheet hssfSheet = wb.getSheetAt(numSheet);
                if(hssfSheet == null){
                    continue;
                }
                totalRows = hssfSheet.getLastRowNum();
                //读取Row,从第二行开始
                for(int rowNum = 1;rowNum <= totalRows;rowNum++){
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if(hssfRow!=null){
                        rowList = new ArrayList<String>();
                        totalCells = hssfRow.getLastCellNum();
                        //读取列，从第一列开始
                        for(short c=0;c<=totalCells+1;c++){
                            HSSFCell cell = hssfRow.getCell(c);
                            if(cell==null){
                                rowList.add(EMPTY);
                                continue;
                            }
                            rowList.add(getHValue(cell).trim());
                        }
                        list.add(rowList);
                    }
                }
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
