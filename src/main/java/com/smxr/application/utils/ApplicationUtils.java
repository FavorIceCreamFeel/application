package com.smxr.application.utils;

import com.smxr.application.pojo.PhoneCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author smxr
 * @date 2019/12/2
 * @time 23:02
 * 工具类
 */
@Component
public class ApplicationUtils {
    @Autowired
    private PhoneCode phoneCode;
    private static Map<String,Long> map=new HashMap<>();

    public static Map<String, Long> getMap() {
        return map;
    }

    /**
     * 获取指定长度的随机字符串
     */
    public static String getRandomString(int length){
        String str="0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<length;i++) {
            int i1 = random.nextInt(str.length());
            stringBuilder.append(str.charAt(i1));
        }
        return stringBuilder.toString();
    }
    /**
     * 生成签名信息
     * @param secretKey 产品私钥
     * @param params 接口请求参数名和参数值map，不包括signature参数名
     * @return
     */
    private String genSignature(String secretKey, Map<String, String> params){
        // 1. 参数名按照ASCII码表升序排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 2. 按照排序拼接参数名与参数值
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append(params.get(key));
        }
        // 3. 将secretKey拼接到最后
        sb.append(secretKey);

        // 4. MD5是128位长度的摘要算法，转换为十六进制之后长度为32字符
        return DigestUtils.md5DigestAsHex(sb.toString().getBytes(StandardCharsets.UTF_8));
    }
    @Scheduled(cron = "0 0 12 * * ? ")
    private void smxr(){
        if (map.size()!=0){
            for (Map.Entry<String, Long> entry : map.entrySet()) {
                long currentTimeMillis = System.currentTimeMillis();
                if(currentTimeMillis-entry.getValue()>=60000){
                    map.remove(entry.getKey());
                }
            }
        }
    }
}
