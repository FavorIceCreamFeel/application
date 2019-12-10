package com.smxr.application.pojo;

/**
 * @author smxr
 * @date 2019/11/24
 * @time 1:24
 * 订单表
 */
public class Orders {
    private int orderId;//订单id
    private int goodsId;//订单商品id
    private String goodsMoney;//订单商品价格
    private int goodsNum;//订单商品数量
    private String orderTime;//订单创建时间
    private int orderStatus;//订单状态（0未支付，1申请支付中，2已经支付）

    public Orders() {
    }

    public Orders(int orderId, int goodsId, String goodsMoney, int goodsNum, String orderTime, int orderStatus) {
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.goodsMoney = goodsMoney;
        this.goodsNum = goodsNum;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsMoney() {
        return goodsMoney;
    }

    public void setGoodsMoney(String goodsMoney) {
        this.goodsMoney = goodsMoney;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }
}
