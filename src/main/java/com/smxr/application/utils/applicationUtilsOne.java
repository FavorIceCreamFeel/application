package com.smxr.application.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author smxr
 * @date 2019/12/11
 * @time 22:40
 * 工具类1号
 */
public class applicationUtilsOne {
    /**
     * 获取格式化时间
     * @return
     */
    public static String getDateTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }
}
