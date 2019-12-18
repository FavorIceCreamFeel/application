package com.smxr.application.service.impl;

import com.smxr.application.dao.GoodsTypeDao;
import com.smxr.application.dao.TypesDao;
import com.smxr.application.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author smxr
 * @date 2019/12/18
 * @time 18:36
 * 处理分类
 */
public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Autowired
    private GoodsTypeDao goodsTypeDao;
    @Autowired
    private TypesDao typesDao;

}
