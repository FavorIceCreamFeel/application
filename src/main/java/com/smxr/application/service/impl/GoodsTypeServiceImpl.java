package com.smxr.application.service.impl;

import com.smxr.application.dao.GoodsTypeDao;
import com.smxr.application.dao.TypesDao;
import com.smxr.application.pojo.GoodsType;
import com.smxr.application.pojo.Types;
import com.smxr.application.service.GoodsTypeService;
import org.apache.ibatis.javassist.runtime.Desc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author smxr
 * @date 2019/12/18
 * @time 18:36
 * 处理分类
 */
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

}
