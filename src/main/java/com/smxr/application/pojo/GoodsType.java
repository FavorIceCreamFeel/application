package com.smxr.application.pojo;

/**
 * @author smxr
 * @date 2019/12/7
 * @time 16:23
 * 商品分类表
 */
public class GoodsType {
    private int goodsTypeId;//分类id
    private int goodsTypeName;//分类名字
    private int goodsTypeTime;//分类创建时间

    public GoodsType() {
    }

    public GoodsType(int goodsTypeId, int goodsTypeName, int goodsTypeTime) {
        this.goodsTypeId = goodsTypeId;
        this.goodsTypeName = goodsTypeName;
        this.goodsTypeTime = goodsTypeTime;
    }

    public int getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(int goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public int getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(int goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public int getGoodsTypeTime() {
        return goodsTypeTime;
    }

    public void setGoodsTypeTime(int goodsTypeTime) {
        this.goodsTypeTime = goodsTypeTime;
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "goodsTypeId=" + goodsTypeId +
                ", goodsTypeName=" + goodsTypeName +
                ", goodsTypeTime=" + goodsTypeTime +
                '}';
    }
}
