package com.gd.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description : 基础工具类
 **/
public class ToolUtils {

    private ToolUtils(){}

    /**
    * @Description: 字符串为空
    * @Param: [str]
    * @return: boolean
    */
    public static boolean strIsNull(String str){
        if(str == null || str.trim().length() == 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * @Description: 字符串不为空
     * @Param: [str]
     * @return: boolean
     */
    public static boolean strIsNotNul(String str){
        if(strIsNull(str)){
            return false;
        }else{
            return true;
        }
    }

    // 判断数字正则表达式
    private static final Pattern pattern = Pattern.compile("[0-9]*");

    // 检查字符串是不是int类型
    public static boolean checkInt(String str) {
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        } else {
            return true;
        }
    }

    // 字符串转换为int类型
    public static Integer str2Int(String str) {
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return 0;
        } else {
            return Integer.parseInt(str);
        }
    }

    // 字符串转换为LocalDateTime
    public static LocalDateTime str2LocalDateTime(String dateStr) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(dateStr,df);
        return ldt;
    }

}
