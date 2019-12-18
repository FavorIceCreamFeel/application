package com.smxr.application.dao;

import com.smxr.application.pojo.GoodsType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author smxr
 * @date 2019/12/18
 * @time 18:29
 * 二级分类dao
 */
@Component
@Mapper
public interface GoodsTypeDao {
    @Select("select *from goodstype where typeId=#{typeId}")
    public List<GoodsType> selectGoodsTypeByTypeId(int typeId);

}
