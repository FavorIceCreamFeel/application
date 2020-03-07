package com.smxr.application.service;

import com.smxr.application.pojo.ShoppingTrolley;

/**
 * @author ZhangRongFei
 * @date 2019/12/13 20:23
 */
public interface ShoppingTrolleyService {
    public ShoppingTrolley findByOrderId(Integer orderId, Integer goodsNum);
}
