package com.smxr.application.service;

import com.github.pagehelper.PageInfo;
import com.smxr.application.pojo.Goods;
import com.smxr.application.pojo.Orders;

import java.util.List;

public interface GoodsService {

    List<Goods> findAll();
    List<Orders> findAllOrder();
    Goods queryGoodsById(int goodsId);
    List<Goods> selectGoodsByType(int goodsTypeId);
    PageInfo<Goods> queryGoodsPageInfo(int pageSize, int pageNumber);
    boolean updateGoodsNumByID(int goodsId,int goodsNum);
    boolean updateGoodsNumByID(int goodsId);
    boolean insert_Goods(Goods goods);
    Goods queryGoodsByName(String goodsName);
}
