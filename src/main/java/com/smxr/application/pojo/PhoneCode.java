package com.smxr.application.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.annotation.Target;

/**
 * @author smxr
 * @date 2019/12/5
 * @time 22:50
 * 手机验证码配置
 */
@Component
@ConfigurationProperties(prefix = "phonecode")
public class PhoneCode {
    private String businessId;//网易短信业务ID
    private String secretId;//产品密钥ID，产品标识
    private String secretKey;//产品私有密钥，服务端生成签名信息使用
    private String API_URl;//请求路径
    private String templateId;//短信模板id

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAPI_URl() {
        return API_URl;
    }

    public void setAPI_URl(String API_URl) {
        this.API_URl = API_URl;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @Override
    public String toString() {
        return "PhoneCode{" +
                "businessId='" + businessId + '\'' +
                ", secretId='" + secretId + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", API_URl='" + API_URl + '\'' +
                ", templateId='" + templateId + '\'' +
                '}';
    }
}
