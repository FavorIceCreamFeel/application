package com.smxr.application.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author ZhangRongFei
 * @date 2019/12/23 19:39
 * 须在idea安装Lombok插件
 */
@Data
@ApiModel(value = "后台首页")
public class AccueilDTO {
    /**
     * 用户总量
     */
    private Integer userNum;
    /**
     * 今日注册总量
     */
    private Integer userDayNum;
    /**
     * 订单申请总量
     */
    private Integer orderApplicantNum;
    /**
     * 订单审核总量
     */
    private Integer orderAudit;
}
