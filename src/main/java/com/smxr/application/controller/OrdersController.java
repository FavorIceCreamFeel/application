package com.smxr.application.controller;

import com.smxr.application.pojo.Goods;
import com.smxr.application.pojo.Orders;
import com.smxr.application.service.OrderService;
import com.smxr.application.utils.ApplicationUtilsOne;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
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
     * @param
     * @return
     */
    @RequestMapping(value = "/addOrder",method = RequestMethod.POST)
    @ResponseBody
    public Boolean addOrder(@RequestParam Integer goodsId, @RequestParam Integer goodsNum, @RequestParam double goodsMoney){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String phoneNum = authentication.getName();
        assert phoneNum != null;
        Orders orders = new Orders();
        orders.setOrderTime(ApplicationUtilsOne.getDateTime());
        orders.setGoodsId(goodsId);
        orders.setGoodsMoney(BigDecimal.valueOf(goodsMoney));
        orders.setOrderUser(phoneNum);
        orders.setGoodsNum(goodsNum);
        orders.setOrderStatus(0);
        logger.info("用户:"+phoneNum+"添加订单开始");
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
            HashMap<Orders, Goods> ordersGoodsHashMap = orderService.selectOrdersAll(authentication.getName());
            logger.info("获取用户所有订单："+authentication.getName());
            model.addAttribute("ordersGoodsHashMap",ordersGoodsHashMap);
            logger.info("进入购物车！用户："+userName);
            return "show/shopcart";
        }else {
            return "show/shopcart";
        }
    }
    @RequestMapping("orderApply")
    @ApiOperation("申请结算")
    public String apply(@RequestParam int...OrderId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String phoneNum = authentication.getName();
        assert phoneNum != null;
        boolean boo = orderService.orderApply(phoneNum, OrderId);
        if (boo)
            return "redirect:/order/shows";
        else
            return "404";
    }
    @RequestMapping(value = "/delOrder",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("删除订单")
    public boolean delOrder(@RequestParam Integer OrderId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String phoneNum = authentication.getName();
        assert phoneNum != null;
        return orderService.deleteOrder(phoneNum, OrderId);
    }
}
