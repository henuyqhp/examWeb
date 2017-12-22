package com.henu.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期转换类
 */
public class DateTimeUtil {

    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String STANDARD_FORMAT2 = "yyyy-MM-dd HH:mm";
    /**
     * 字符串转Date
     * @param dateTimeStr
     * @return
     */
    public static Date strToDate(String dateTimeStr){
        Matcher m = Pattern.compile("[a-zA-Z]").matcher(dateTimeStr);
        if (m.find()){
            dateTimeStr = dateTimeStr.replace(m.group()," ");
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }
    /**
     * 字符串转Date
     * @param dateTimeStr
     * @return
     */
    public static Date strToDate2(String dateTimeStr){
        Matcher m = Pattern.compile("[a-zA-Z]").matcher(dateTimeStr);
        if (m.find()){
            dateTimeStr = dateTimeStr.replace(m.group()," ");
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT2);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }
    /**
     * Date转字符串
     * @param date
     * @return
     */
    public static String dateToStr(Date date){
        if (date == null)
            return StringUtils.EMPTY;
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }

    /**
     * 两个日期想减 判断中间是否还有时间
     * @param start
     * @param end
     * @return
     */
    public static boolean subtraction(Date start,Date end){
        return end.getTime() - start.getTime() > 0 ;
    }
    public static boolean subtraction(Date time){
        return  time.getTime() - new Date().getTime()> 0 ;
    }
    public static void main(String[] args) {
        System.out.println(DateTimeUtil.dateToStr(new Date()));
        System.out.println(DateTimeUtil.strToDate("2017-11-01T22:22"));
        String a = "2017-11-01T22:22";


    }
}
