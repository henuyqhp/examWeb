package com.henu.util;

import com.henu.pojo.Exam;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {

    /**
     * 判断一个字符串是否全部为数字
     * @param str
     * @return
     */
    public static boolean isAllNumber(String str){
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isIP(String str){
        Pattern pattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
        Matcher matcher = pattern.matcher(str);
        if(matcher.matches()) {
            String s[] = str.split("\\.");
            if (Integer.parseInt(s[0]) < 255)
                if (Integer.parseInt(s[1]) < 255)
                    if (Integer.parseInt(s[2]) < 255)
                        if (Integer.parseInt(s[3]) < 255)
                            return true;
        }
        return false;
    }


    public static boolean ExamTimeIsInvi(Exam exam){
        return  new Date().getTime() -exam.getEndtime().getTime() <= 0 &&new Date().getTime() -exam.getStarttime().getTime() >= 0;
    }
    public static boolean ExamTimeCanRemove(Exam exam){
        return new Date().getTime() - exam.getEndtime().getTime() >=0;
    }
    public static void main(String[] args) {

        System.out.println(new Date().getTime());
        System.out.println(isAllNumber("123456"));
        System.out.println(isIP("192.168.1.1"));
        System.out.println(isIP("192.1681.1111"));
    }
}
