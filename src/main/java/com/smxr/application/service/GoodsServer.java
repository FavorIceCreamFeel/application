package com.smxr.application.service;

import com.smxr.application.pojo.Goods;
import com.smxr.application.pojo.Orders;

import java.util.List;

public interface GoodsServer {

    List<Goods> findAll();
    List<Orders> findAllOrder();
}
