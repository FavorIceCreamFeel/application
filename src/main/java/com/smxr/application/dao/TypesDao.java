package com.smxr.application.dao;

import com.smxr.application.pojo.Types;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author smxr
 * @date 2019/12/18
 * @time 18:29
 * 一级分类dao
 */
@Component
@Mapper
public interface TypesDao {
    @Select("select *from types")
    public List<Types> selectTypesAll();
}
