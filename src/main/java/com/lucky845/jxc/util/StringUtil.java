package com.lucky845.jxc.util;

/**
 * @description 字符串工具类
 */
public class StringUtil {

    /**
     * 判断是否是空
     * @param str

     */
    public static boolean isEmpty(String str){
        return str == null || "".equals(str.trim());
    }

    /**
     * 判断是否不是空
     * @param str

     */
    public static boolean isNotEmpty(String str){
        return (str != null) && !"".equals(str.trim());
    }

}
