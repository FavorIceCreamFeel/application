package com.smxr.application.controller;


import com.smxr.application.pojo.Goods;
import com.smxr.application.pojo.Orders;
import com.smxr.application.service.GoodsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/Goods")
public class GoodsController {
    private static Logger logger= LoggerFactory.getLogger(GoodsController.class);
    @Autowired
    private GoodsServer goodsServer;

    //商品查询
    @RequestMapping("/goodList")
    public String findAll(Model model){
        List<Goods> goodsList = goodsServer.findAll();

        model.addAttribute("goodsList",goodsList);

        return "index";
    }
    //订单查询
    @RequestMapping("/orderList")
    public String findAllOrder(Model model){
        List<Orders> allOrder = goodsServer.findAllOrder();
        model.addAttribute("orderList",allOrder);
        return "show/shopcart";
    }
    /**
     * 商品展示
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/showGoods")
    public String showGoods(HttpServletRequest request, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails){
            String userName = (String)request.getSession().getAttribute("userName");

            model.addAttribute("userName",userName);
            logger.info("获取用户所有订单：");
//            model.addAttribute("orders",);
            logger.info("进入购物车！用户："+userName);
            return "show/shopcart";
        }else {
            return "show/shopcart";
        }
    }

}
