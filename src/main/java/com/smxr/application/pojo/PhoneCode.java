package com.smxr.application.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import sun.net.www.http.HttpClient;

import java.lang.annotation.Target;

/**
 * @author smxr
 * @date 2019/12/5
 * @time 22:50
 * 手机验证码配置
 */
@Component
public class PhoneCode {
    @Value("${phoneCode.businessId}")
    private String businessId;
    @Value("${phoneCode.secretId}")
    private String secretId;
    @Value("${phoneCode.secretKey}")
    private String secretKey;
    @Value("${phoneCode.API_URl}")
    private String API_URl;
    public String getBusinessId() {
        return businessId;
    }
    public String getSecretId() {
        return secretId;
    }
    public String getSecretKey() {
        return secretKey;
    }

    public String getAPI_URl() {
        return API_URl;
    }

    @Override
    public String toString() {
        return "PhoneCode{" +
                "businessId='" + businessId + '\'' +
                ", secretId='" + secretId + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", API_URl='" + API_URl + '\'' +
                '}';
    }
}
