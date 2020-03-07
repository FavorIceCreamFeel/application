package com.smxr.application.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smxr.application.dao.GoodsDao;
import com.smxr.application.dao.OrdersDao;
import com.smxr.application.pojo.Goods;
import com.smxr.application.pojo.Orders;
import com.smxr.application.service.GoodsService;
import com.smxr.application.service.OrderService;
import com.smxr.application.utils.ApplicationUtilsOne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZhangRongFei
 * @date 2019/12/13 20:26
 */
@Service
public class OrderServiceImpl implements OrderService {
    public static Logger logger= LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private GoodsDao goodsDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addOrder(Orders orders) {
        if (orders == null) {
            logger.info("订单提交失败，请重试"+ApplicationUtilsOne.getDateTime());
            return false;
        }
        Boolean boo = ordersDao.addOrder(orders);
        if (boo) {
            logger.info("订单提交成功！"+ ApplicationUtilsOne.getDateTime());
            return true;
        }
        return false;
    }

    /**
     * 查询所有订单ByPhone
     * @param phone
     * @return
     */
    @Override
    public List<Orders> selectOrdersAllByPhone(String phone) {
        return ordersDao.selectOrdersAllByPhone(phone);
    }

    /**
     * 处理购物车数据--->订单显示
     * @param phone
     * @return
     */
    @Override
    public HashMap<Orders, Goods> selectOrdersAll(String phone) {
        List<Orders> orders = ordersDao.selectOrdersAllByPhone(phone);
        HashMap<Orders, Goods> ordersGoodsHashMap = new HashMap<>();
        for (Orders order : orders) {
            Goods goods = goodsDao.selectGoodsById(order.getGoodsId());
            ordersGoodsHashMap.put(order, goods);
        }
        return ordersGoodsHashMap;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean orderApply(String phoneNum,int... OrderId) {
        for (int i : OrderId) {
            if (!ordersDao.updateOrderStatus(i))
                return false;
        }
        return true;
    }

    @Override
    public boolean deleteOrder(String phoneNum,int orderId) {
        return ordersDao.deleteOrder(phoneNum,orderId);
    }

    /**
     * 订单审批---1
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @Override
    public PageInfo<Orders> queryOrdersAll(int pageSize, int pageNumber) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Orders> orders = ordersDao.selectOrdersAll();
        return new PageInfo<Orders>(orders);
    }

    /**
     *订单完成
     * @param orderId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateOrdersStatus(int orderId) {
        return ordersDao.updateOrderStatusEnd(orderId);
    }

}
