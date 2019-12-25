package com.smxr.application.dao;


import com.smxr.application.pojo.Goods;
import com.smxr.application.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper
public interface GoodsDao {

    //查询所有商品
    @Select("select * from goodtype left join goods on goodtype.goodsTypeId=goods.goodTypeId")
    List<Goods> findAll();

    //订单查询

    @Select("select  o.orderId,o.goodsNum,o.goodsMoney,g.goodsUrl from  goods  join  orders o on o.goodsId=g.goodsId")
    List<Orders> findAllOrder();

    /**
     * 由订单号查询订单
     * @return
     */
    @Select("select *from goods where goodsId=#{goodsId}")
    Goods selectGoodsById(int goodsId);

    /**
     * 由商品类型查找商品
     * @param goodsTypeId
     * @return
     */
    @Select("select * from goods where goodsTypeId=#{goodsTypeId}")
    List<Goods> selectGoodsByType(int goodsTypeId);
}
