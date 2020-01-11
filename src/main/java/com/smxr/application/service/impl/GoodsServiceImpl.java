package com.smxr.application.service.impl;

import com.smxr.application.dao.GoodsDao;
import com.smxr.application.pojo.Goods;
import com.smxr.application.pojo.Orders;
import com.smxr.application.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品service
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    public List<Goods> findAll() {
        return goodsDao.findAll();
    }

    public List<Orders> findAllOrder() {
        return goodsDao.findAllOrder();
    }

    @Override
    public Goods queryGoodsById(int goodsId) {
        return goodsDao.selectGoodsById(goodsId);
    }

    @Override
    public List<Goods> selectGoodsByType(int goodsTypeId) {
        return goodsDao.selectGoodsByType(goodsTypeId);
    }
}