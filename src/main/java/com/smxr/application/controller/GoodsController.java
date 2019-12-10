package com.smxr.application.controller;


import com.smxr.application.pojo.Goods;
import com.smxr.application.pojo.Orders;
import com.smxr.application.service.GoodsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/index")
public class GoodsController {

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
}
