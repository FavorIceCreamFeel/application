package com.smxr.application.controller;

import com.smxr.application.pojo.ShoppingTrolley;
import com.smxr.application.service.ShoppingTrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZhangRongFei
 * @date 2019/12/13 20:15
 */
@Controller
@RequestMapping("/shoppingTrolley")
@ResponseBody
public class ShoppingTrolleyController {
    @Autowired
    private ShoppingTrolleyService shoppingTrolleyService;
    /**
     * 异步请求校验并计算订单金额，返回购物车商品数量、金钱
     * @param shoppingTrolleyId
     * @return
     */
    @RequestMapping("/findByOrderId")
    @ResponseBody
    public ShoppingTrolley findByOrderId(@RequestParam Integer shoppingTrolleyId, @RequestParam Integer goodsNum){
        return shoppingTrolleyService.findByOrderId(shoppingTrolleyId, goodsNum);
    }
}
