package com.smxr.application.utils;

import org.springframework.scheduling.annotation.Scheduled;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author smxr
 * @date 2019/12/11
 * @time 22:40
 * 工具类1号
 */
public class ApplicationUtilsOne {

    /**
     * 获取格式化时间
     * @return
     */
    public static String getDateTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }
    /**
     * 获取格式化时间
     * parse Date
     * @return
     */
    public static String getDateTime(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
