package com.smxr.application.service.impl;

import com.smxr.application.dao.GoodsDao;
import com.smxr.application.pojo.Goods;
import com.smxr.application.pojo.Orders;
import com.smxr.application.service.GoodsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServerImpl implements GoodsServer {

    @Autowired
    private GoodsDao goodsDao;


    public List<Goods> findAll() {
        return goodsDao.findAll();
    }

    public List<Orders> findAllOrder() {
        return goodsDao.findAllOrder();
    }
}
