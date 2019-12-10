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

    @Select("select  o.orderId,o.goodsNum,o.goodsMoney,g.goodsUrl from  goods g  join  orders o on o.goodsId=g.goodsId")
    List<Orders> findAllOrder();

    //
}
