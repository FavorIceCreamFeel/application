package com.smxr.application.controller;

import com.smxr.application.pojo.Orders;
import com.smxr.application.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ZhangRongFei
 * @date 2019/12/13 20:15
 */
@Controller
@RequestMapping("/order")
public class OrdersController {
    @Autowired
    private OrderService orderService;
    /**
     * 提交订单
     * @param orders
     * @return
     */
    @RequestMapping("/addOrder")
    public Boolean addOrder(Orders orders){
        return orderService.addOrder(orders);
    }
}
