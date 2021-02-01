package com.ruoyi.common.utils;



/**
 * @author liujc
 * @create 2020-12-14 18:48:39
 **/
public class GenUtil {
    static  SnowflakeGenerate idWorker = new SnowflakeGenerate(1, 1);
    public static String generateFeedbackCode(){
        String ymd = DateUtils.dateTime().replaceAll("-", "");
        return "F" + ymd + idWorker.getSubId();
    }
    public static String generateToken(){
        return "TOKEN_" + idWorker.getSubId();
    }
}
