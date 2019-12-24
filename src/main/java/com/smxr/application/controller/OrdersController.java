package com.smxr.application.controller;

import com.smxr.application.pojo.Orders;
import com.smxr.application.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ZhangRongFei
 * @date 2019/12/13 20:15
 */
@Controller
@RequestMapping("/order")
public class OrdersController {
    private static Logger logger= LoggerFactory.getLogger(OrdersController.class);
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

    /**
     * 购物车展示
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/shows")
    public String showsGoods(HttpServletRequest request, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails){
            String userName = (String)request.getSession().getAttribute("userName");
            model.addAttribute("userName",userName);
            List<Orders> orders = orderService.selectOrdersAllByPhone(authentication.getName());
            logger.info("获取用户所有订单："+orders);
            model.addAttribute("orders",orders);
            logger.info("进入购物车！用户："+userName);
            return "show/shopcart";
        }else {
            return "show/shopcart";
        }
    }
}
