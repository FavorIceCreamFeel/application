package com.smxr.application.dao;

import com.smxr.application.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author smxr
 * @date 2019/11/24
 * @time 1:45
 */
@Component
@Mapper
public interface UserDao {
    /**
     * 查询用户byPhoneNum
     * @param phoneNum
     * @return
     */
    @Select("select *from user where phoneNumber=#{param}")
    public User queryUserByPhoneNum(String phoneNum);
    @Select("select  powerSign from power where" +
            " powerId in (select distinct powerId from role_power where" +
            " roleId in (select roleId from userid_roleid where userId=#{param1}))")
    public List queryPowerStringByPhoneNum(String phoneNum);
    @Insert("insert into user values(#{userName},#{userPwd},#{userSex},#{userAge},#{phoneNumber},#{address},#{createTime})")
    public boolean insertUser(User user);
    public boolean updateUser(User user);
    /**
     *用户人数查询
     */
    @Select("select count(1) from user where phoneNumber=#{param}")
    public int selectUserByPhoneNumber(String phoneNumber);

}
