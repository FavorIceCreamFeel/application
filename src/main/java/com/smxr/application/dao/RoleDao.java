package com.smxr.application.dao;

import com.smxr.application.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author smxr
 * @date 2020/1/11
 * @time 16:27
 * 角色，权限字段
 */
@Mapper
@Component
public interface RoleDao {
    /**
     * 查询角色《----手机号/
     * @param phoneNumber
     * @return
     */
    @Select("select * from role where roleId in (select roleId from userid_roleid where userId=#{phoneNumber})")
    public List<Role> selectRoleByPhoneNumber(long phoneNumber);
}
