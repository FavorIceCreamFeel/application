package com.smxr.application.dao;

import com.smxr.application.pojo.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author ZhangRongFei
 * @date 2019/12/13 20:24
 */
@Component
@Mapper
public interface OrdersDao {
    @Insert("insert into orders values(null,#{goodsId},#{goodsMoney},#{goodsNum},#{orderTime},#{orderStatus})")
    public Boolean addOrder(Orders orders);
}
