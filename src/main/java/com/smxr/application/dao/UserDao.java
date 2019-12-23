package com.smxr.application.dao;

import com.smxr.application.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
    @Select("select powerSign from power where" +
            " powerId in (select distinct powerId from role_power where" +
            " roleId in (select roleId from userid_roleid where userId=#{param1}))")
    public List<String> queryPowerStringByPhoneNum(long param1);
    @Insert("insert into user values(#{userName},#{userPwd},#{userSex},#{userAge},#{phoneNumber},#{address},#{createTime})")
    public boolean insertUser(User user);

    //修改密码
    @Update("update user set userPwd=#{userPwd} where userName=#{userName}")
    public boolean updateUser(User user);
    //旧密码校验
    @Select("select userPwd from user where userName=#{userName}")
    public String findUserPwd(String userName);
    /**
     *用户人数查询
     */
    @Select("select count(1) from user where phoneNumber=#{param}")
    public int selectUserByPhoneNumber(String phoneNumber);

    //用户总量
    @Select("select count(phoneNumber) from user")
    public Integer userNum();

    //今日注册人数
    @Select("select count(phoneNumber) from user where to_days(createTime) = to_days(now())")
    public Integer userDayNum();
}
