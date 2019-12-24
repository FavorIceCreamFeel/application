package com.smxr.application.service;

import com.smxr.application.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author smxr
 * @date 2019/11/24
 * @time 1:37
 */
public interface UserServer extends UserDetailsService {
    public boolean insertUser(User user);
    public boolean updateUser(User user);
    public boolean selectUserByPhoneNumber(String phoneNumber);
    public User queryUserByPhoneNumber(String phoneNumber);
    public String findUserPwd(String userName,String userPwd);
}
