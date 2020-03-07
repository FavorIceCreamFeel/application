package com.smxr.application.service;

import com.smxr.application.pojo.GoodsType;
import com.smxr.application.pojo.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author smxr
 * @date 2019/12/18
 * @time 18:35
 */
public interface GoodsTypeService {
    public HashMap<String, ArrayList<GoodsType>> queryGoodsTypes();
    List<GoodsType> queryGoodsTypeAll();
    List<Types> queryTypesAll();
    boolean delTypesByID(int typesId);
    boolean delGoodsTypesByID(int GoodsTypesId);
    boolean insertGoodsTypes(GoodsType goodsType);
    boolean insertTypes(Types types);
}
