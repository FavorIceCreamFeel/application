package com.smxr.application.service.impl;

import com.smxr.application.dao.OrdersDao;
import com.smxr.application.pojo.Orders;
import com.smxr.application.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ZhangRongFei
 * @date 2019/12/13 20:26
 */
@Service
public class OrderServiceImpl implements OrderService {
    public static Logger logger= LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrdersDao ordersDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addOrder(Orders orders) {
        if (orders == null) {
            logger.info("订单提交失败，请重试");
            return false;
        }
        orders.setOrderStatus(1);
        orders.setOrderTime(new Date());
        Boolean boo = ordersDao.addOrder(orders);
        if (boo)
            return true;
        return false;
    }
}
