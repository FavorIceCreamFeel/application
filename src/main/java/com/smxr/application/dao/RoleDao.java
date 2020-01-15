package com.smxr.application.dao;

import com.smxr.application.pojo.Power;
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

    /**
     * 查询所有角色
     * @return
     */
    @Select("select * from role")
    public List<Role> selectRoleAll();
    /**
     * 由角色id查询角色
     * @return
     */
    @Select("select * from role where roleId=#{roleId}")
    public Role selectRoleByRoleId(int roleId);

    /**
     * 查询所有资源字段
     * @return
     */
    @Select("select * from power")
    public List<Power> selectPowerAll();
    /**
     * 由字段id查询资源
     * @return
     */
    @Select("select * from power where powerId=#{powerId}")
    public Role selectPowerByPowerId(int powerId);

    /**
     * 由RoleId查询权限字段
     * @return
     */
    @Select("select * from power where powerId in (select distinct powerId from role_power where roleId=#{roleId})")
    public List<Power> selectPowerByRoleId(int roleId);

}
