package com.smxr.application.service.impl;

import com.smxr.application.dao.GoodsTypeDao;
import com.smxr.application.dao.TypesDao;
import com.smxr.application.pojo.GoodsType;
import com.smxr.application.pojo.Types;
import com.smxr.application.service.GoodsTypeService;
import lombok.extern.java.Log;
import org.apache.ibatis.javassist.runtime.Desc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author smxr
 * @date 2019/12/18
 * @time 18:36
 * 处理商品分类
 */
@Log
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Autowired
    private GoodsTypeDao goodsTypeDao;
    @Autowired
    private TypesDao typesDao;
    /**
     * 获取首页分类一级二级信息
     */
    public HashMap<String,ArrayList<GoodsType>> queryGoodsTypes(){
        List<Types> types = typesDao.selectTypesAll();
        HashMap<String, ArrayList<GoodsType>> goodsTypeHashMap = new HashMap<>();
        for (Types type : types) {
            List<GoodsType> goodsTypeList = goodsTypeDao.selectGoodsTypeByTypeId(type.getTypeId());
            ArrayList<GoodsType> stringArrayList = new ArrayList<>(goodsTypeList);
            goodsTypeHashMap.put(type.getTypeName(),stringArrayList);
        }
        return goodsTypeHashMap;
    }

    /**
     * 查询所有的二级分类
     * @return
     */
    @Override
    public List<GoodsType> queryGoodsTypeAll() {
        return goodsTypeDao.selectGoodsTypeAll();
    }

    /**
     * 查询所有的一级分类
     * @return
     */
    @Override
    public List<Types> queryTypesAll() {
        return typesDao.selectTypesAll();
    }

    @Override
    public boolean delTypesByID(int typesId) {
        log.info("删除一级分类："+typesId);
        return typesDao.delTypesById(typesId);
    }

    @Override
    public boolean delGoodsTypesByID(int GoodsTypesId) {
        log.info("删除二级分类："+GoodsTypesId);
        return goodsTypeDao.delGoodsTypesById(GoodsTypesId);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertGoodsTypes(GoodsType goodsType) {
        return goodsTypeDao.insertGoodsTypes(goodsType);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertTypes(Types types) {
        return typesDao.insertTypes(types);
    }

}
