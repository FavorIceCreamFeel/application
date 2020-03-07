package com.smxr.application.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author smxr
 * @date 2020/1/15
 * @time 14:22
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Power {
    private int powerId;//权限id
    private String powerName;//权限名字
    private String powerDescribe;//权限描述
    private String powerSign;//权限字段
}
