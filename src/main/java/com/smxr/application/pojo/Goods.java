package com.smxr.application.pojo;

/**
 * @author smxr
 * @date 2019/11/24
 * @time 1:15
 * 商品表
 */
public class Goods {
    private int goodsId;//商品id、编码
    private String goodsName;//商品名字
    private int goodsNum;//商品数量
    private String goodsMoney;//商品价格
    private String goodsDescription;//商品描述
    private String goods;//商品
    private int goodsStatus;//商品状态

    public Goods() {
    }

    public Goods(int goodsId, String goodsName, int goodsNum, String goodsMoney, String goodsDescription, String goods, int goodsStatus) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsNum = goodsNum;
        this.goodsMoney = goodsMoney;
        this.goodsDescription = goodsDescription;
        this.goods = goods;
        this.goodsStatus = goodsStatus;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsMoney() {
        return goodsMoney;
    }

    public void setGoodsMoney(String goodsMoney) {
        this.goodsMoney = goodsMoney;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public int getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(int goodsStatus) {
        this.goodsStatus = goodsStatus;
    }
}
