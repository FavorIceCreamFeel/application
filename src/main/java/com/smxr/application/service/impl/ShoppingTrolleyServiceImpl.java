package com.smxr.application.service.impl;

import com.smxr.application.dao.ShoppingTrolleyDao;
import com.smxr.application.pojo.ShoppingTrolley;
import com.smxr.application.service.ShoppingTrolleyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author ZhangRongFei
 * @date 2019/12/13 21:05
 */
@Service
public class ShoppingTrolleyServiceImpl implements ShoppingTrolleyService {
    public static Logger logger= LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private ShoppingTrolleyDao shoppingTrolleyDao;

    /**
     * 查询购物车订单信息，根据用户填写的数量计算实时金额
     * @param shoppingTrolleyId
     * @param goodsNum
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ShoppingTrolley findByOrderId(Integer shoppingTrolleyId, Integer goodsNum) {
        if (shoppingTrolleyId == null) {
            logger.info("订单编号不能为空，请重试");
            return new ShoppingTrolley();
        }
        if (goodsNum == null){
            logger.info("购买数量不能为空，请重试");
            return new ShoppingTrolley();
        }

        ShoppingTrolley shoppingTrolley = shoppingTrolleyDao.findByOrderId(shoppingTrolleyId);
        //计算金额
        BigDecimal goodsMoney = shoppingTrolley.getGoodsMoney();
        BigDecimal sumMoney = goodsMoney.multiply(BigDecimal.valueOf(goodsNum));

        shoppingTrolley.setGoodsMoney(sumMoney);
        shoppingTrolley.setGoodsNum(goodsNum);
        return shoppingTrolley;
    }
}
