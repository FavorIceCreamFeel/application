package com.smxr.application.service;

import com.smxr.application.pojo.GoodsType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author smxr
 * @date 2019/12/18
 * @time 18:35
 */
public interface GoodsTypeService {
    public HashMap<String, ArrayList<GoodsType>> queryGoodsTypes();
}
