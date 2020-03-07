package com.smxr.application.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smxr.application.dao.GoodsDao;
import com.smxr.application.pojo.Goods;
import com.smxr.application.pojo.Orders;
import com.smxr.application.service.GoodsService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品service
 */
@Log
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

    @Override
    public PageInfo<Goods> queryGoodsPageInfo(int pageSize, int pageNumber) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Goods> goodsList = goodsDao.findGoodsAll();
        return new PageInfo<Goods>(goodsList);
    }
    /**
     * byID修改商品数量
     * @param goodsId
     * @param goodsNum
     * @return
     */
    @Override
    public boolean updateGoodsNumByID(int goodsId,int goodsNum) {
        return goodsDao.updateGoodsNumById(goodsId, goodsNum);
    }

    /**
     * 修改商品状态
     * @param goodsId
     * @return
     */
    @Override
    public boolean updateGoodsNumByID(int goodsId) {
        Goods goods = goodsDao.selectGoodsById(goodsId);
        int i=goods.getGoodsStatus();
        if (i==1)
            return goodsDao.updateGoodsStatusById(goodsId, 0);
        if (i==0)
            return goodsDao.updateGoodsStatusById(goodsId,1);
        return false;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert_Goods(Goods goods) {
        return goodsDao.insertGoods(goods);
    }

    @Override
    public Goods queryGoodsByName(String goodsName) {
        return goodsDao.selectGoodsByName(goodsName);
    }

}
