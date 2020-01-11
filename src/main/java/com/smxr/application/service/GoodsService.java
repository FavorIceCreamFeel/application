package com.smxr.application.service;

import com.smxr.application.pojo.Goods;
import com.smxr.application.pojo.Orders;

import java.util.List;

public interface GoodsService {

    List<Goods> findAll();
    List<Orders> findAllOrder();
    Goods queryGoodsById(int goodsId);
    List<Goods> selectGoodsByType(int goodsTypeId);
}
