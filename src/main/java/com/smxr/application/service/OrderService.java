package com.smxr.application.service;

import com.smxr.application.pojo.Orders;

import java.util.List;

/**
 * @author ZhangRongFei
 * @date 2019/12/13 20:23
 */
public interface OrderService {
    public Boolean addOrder(Orders orders);
    public List<Orders> selectOrdersAllByPhone(String phone);
}
