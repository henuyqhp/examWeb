package com.henu.util;

import com.google.common.collect.Lists;
import com.henu.dao.StudentMapper;
import com.henu.exception.FileTypeUnCheck;
import com.henu.pojo.Student;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtil {
    private static final int SHEETNUM = 10;


    public static void main(String[] args) {
//        ArrayList<ArrayList<Object>> a = readExcel2007(new File("C:\\Users\\syl\\Desktop\\test.xlsx"),2);
//        for (int i =0;i<a.size();i++)
//        {
//            for (int j = 0;j<a.get(i).size();j++){
//                System.out.print(a.get(i).get(j) + " ");
//            }
//            System.out.println();
//        }


//        try {
//            Map map = getMap("序号:id,学号:sno,姓名:sname,密码:spass,可用:enable,班级:classnum");
////            List list = readXls(new File("C:\\Users\\syl\\Desktop\\test.xlsx"),map,"com.henu.pojo.Student");
////            List<Student> students = list;
//
////            System.out.println(list.size());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (FileTypeUnCheck fileTypeUnCheck) {
//            fileTypeUnCheck.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
    }


    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);


    /**
     * 根据字符串生成相应的map
     * @param keyValue
     * @return
     */
    public static Map<String, String> getMap(String keyValue) {
        Map<String, String> map = new HashMap<String, String>();
        if (keyValue != null) {
            String[] str = keyValue.split(",");
            for (int i = 0; i < str.length; i++) {
                String[] str2 = str[i].split(":");
                map.put(str2[0], str2[1]);
            }
        }
        return map;
    }

    /**
     * 通过给定的属性去读取excel文件里面的数据
     * @param multipartFile
     * @param map
     * @param classPath
     * @return
     * @throws ClassNotFoundException
     * @throws FileTypeUnCheck
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchFieldException
     */
    public static List<Object> readXls(MultipartFile multipartFile, Map map, String classPath) throws ClassNotFoundException, FileTypeUnCheck, IOException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> demo = null;
        Object obj = null;

        Set keySet = map.keySet();
        List<Object> list = Lists.newArrayList();
        demo = Class.forName(classPath);
        InputStream is = multipartFile.getInputStream();
        Workbook workbook = null;

        System.out.println(multipartFile.getOriginalFilename()+"++++++");
        if (multipartFile.getOriginalFilename().endsWith("xlsx")){
            workbook = new XSSFWorkbook(is);
        } else if (multipartFile.getOriginalFilename().endsWith("xls")){
            workbook = new HSSFWorkbook(is);
        }else {
            logger.error("文件类型错误，不是xlsx或者xls格式");
            throw new FileTypeUnCheck("文件类型错误，不是xlsx或者xls格式");
        }

        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets();sheetNum++){
            int rowNum_x = -1;

            Map<String, Integer> cellmap = new HashMap<String, Integer>();
            Sheet sheet = workbook.getSheetAt(sheetNum);
            logger.info("一个excel文件第{}个sheet，共{}行，{}列",sheetNum+1,sheet.getLastRowNum(),sheet.getRow(0).getLastCellNum());
            lableBreak:  for (int i = sheet.getFirstRowNum();i<= sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);
                if (row == null)
                    continue;
                boolean flag = false;
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    try {
                        if (row.getCell(j) != null && !("").equals(row.getCell(j).toString().trim())) {
                            flag = true;
                        }
                    }catch (Exception e){
                        logger.error("单元格异常:{}",e);
                        e.printStackTrace();
                    }
                }
                if (!flag)
                    continue;
                if ( rowNum_x == -1 ){
                    for (int j = 0;j<row.getLastCellNum();j++){
                        Cell cell = row.getCell(j);
                        if (cell == null)
                            continue;
                        String cellValue = "";
                        cellValue = cell.getStringCellValue().trim();
                        Iterator iterator = keySet.iterator();
                        while (iterator.hasNext()){
                            Object key = iterator.next();
                            if (!("").equals(cellValue) && key.equals(cellValue)){
                                rowNum_x = i;
                                cellmap.put(map.get(key).toString(),j);
                            }
                        }

                        if (rowNum_x == -1){
                            logger.error("[ExcelUtil Error Message]:没有找到对应的字段或者对应字段行上面含有不为空白的行");
                            break lableBreak;
                        }
                    }
                }else {
                    obj = demo.newInstance();
                    Iterator iterator = keySet.iterator();
                    while (iterator.hasNext()){
                        Object key = iterator.next();
                        Integer cellNum_x = cellmap.get(map.get(key).toString());
                        if (cellNum_x == null || row.getCell(cellNum_x) == null){
                            continue ;
                        }
                        String attr = map.get(key).toString();
                        Class<?> attrType = demo.getDeclaredField(attr).getType();
                        Cell cell = row.getCell(cellNum_x);
                        getVallue(cell,obj,attr,attrType);
                    }
                    list.add(obj);
                }
            }
        }
        return list;
    }

    /**
     * 得到excel列的值
     * @param cell 列
     * @param obj 表格对应的类
     * @param attr 类的属性
     * @param attrType 属性的类型
     */
    public static void getVallue (Cell cell,Object obj,String attr,Class attrType){
        Object val = null;
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            val = cell.getBooleanCellValue();

        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if(attrType==String.class){
                    val = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                }else{
                    val = DateTimeUtil.strToDate(sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue())));
                }
            } else {
                if(attrType==String.class){
                    val = Double.toString(cell.getNumericCellValue());
                }else if(attrType==Float.class){
                    val = (float)cell.getNumericCellValue();
                }else if(attrType==int.class){
                    val = (int)cell.getNumericCellValue();
                }else if(attrType == Integer.class){
                    val = (int)cell.getNumericCellValue();
                }
                else{
                    val = cell.getNumericCellValue();
                }
            }

        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            val = cell.getStringCellValue();
        }

        setter(obj, attr, val, attrType);
    }

    /**
     * 反射的setter方法给属性赋值
     * @param obj 具体的类
     * @param att 类的属性
     * @param value 属性的值
     * @param type 属性的类型
     */
    public static void setter(Object obj, String att, Object value,
                              Class<?> type) {
        try {
            Method method = obj.getClass().getMethod("set" + toUpperCaseFirstOne(att), type);
            method.invoke(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 将属性的第一个字母转换为小写
     * @param s 属性
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder())
                    .append(Character.toLowerCase(s.charAt(0)))
                    .append(s.substring(1)).toString();
    }
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder())
                    .append(Character.toUpperCase(s.charAt(0)))
                    .append(s.substring(1)).toString();
    }
    /**
     * 我自己写的 但是只能直接通过get(i).get(j)去读出对应坐标的单元格值
     * 不能根据数据库和pojo去取出想要的数据 不是太好
     * @param file
     * @return
     */
    public static ArrayList<ArrayList<Object>> readExcel(File file){
        if (file == null)
            return null;
        return readExcel2007(file,0);

    }

    /**
     * 我自己写的 但是只能直接通过get(i).get(j)去读出对应坐标的单元格值
     * 不能根据数据库和pojo去取出想要的数据 不是太好
     * 弃用
     * @param file excel文件
     * @param sheetNum 第几个表格
     * @return
     */
    @Deprecated
    private static ArrayList<ArrayList<Object>> readExcel2007(File file,int sheetNum){
        ArrayList<ArrayList<Object>> rowList = new ArrayList<>();
        ArrayList<Object> colList;
        InputStream is = null;
        Workbook wb = null;
        Sheet sheet;
        Cell cell;
        Row row;
        Object value = null;
        try {
            is = new FileInputStream(file);
            wb = new XSSFWorkbook(is);
            sheet = wb.getSheetAt(sheetNum);
            for (int i=sheet.getFirstRowNum();i<sheet.getPhysicalNumberOfRows();i++){
                row = sheet.getRow(i);
                colList = new ArrayList<>();
                if (row == null){
                    if (i != sheet.getPhysicalNumberOfRows())
                        rowList.add(colList);
                    continue;
                }
                for (int j = row.getFirstCellNum();j<row.getLastCellNum();j++){
                    cell = row.getCell(j);
                    if (cell == null ){
                        if (j != row.getLastCellNum())
                            colList.add("");
                        continue;
                    }
                    cell.setCellType(CellType.STRING);
                    value = cell.getStringCellValue();
                    colList.add(value);
                }
                rowList.add(colList);
            }
            logger.info("读取到的Excel数据为：{}",rowList);
            return rowList;
        }catch (Exception e){
            logger.error("读取Excel发生异常:{}",e);
        }finally {
            try {
                wb.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return rowList;
    }
}
