package com.smxr.application.dao;

import com.smxr.application.pojo.User;
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
    @Select("select *from user where phoneNum=#{param}")
    public User queryUserByPhoneNum(int phoneNum);
    @Select("select  powerSign from power where" +
            " powerId in (select distinct powerId from role_power where" +
            " roleId in (select roleId from user_role where userId=#{param1}))")
    public List queryPowerStringByPhoneNum(int phoneNum);
    public boolean insertUser(User user);
    public boolean updateUser(User user);
    public User selectUserByPhoneNumber(int phoneNumber);

}
