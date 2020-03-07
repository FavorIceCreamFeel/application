package com.smxr.application.service;

import com.github.pagehelper.PageInfo;
import com.smxr.application.pojo.Goods;
import com.smxr.application.pojo.Orders;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

/**
 * @author ZhangRongFei
 * @date 2019/12/13 20:23
 */
public interface OrderService {
    public Boolean addOrder(Orders orders);
    public List<Orders> selectOrdersAllByPhone(String phone);
    public HashMap<Orders, Goods> selectOrdersAll(String phone);
    boolean orderApply(String phoneNum,int...OrderId);
    boolean deleteOrder(String phoneNum,int orderId);
    PageInfo<Orders> queryOrdersAll(int pageSize, int pageNumber);
    boolean updateOrdersStatus(int orderId);
}
