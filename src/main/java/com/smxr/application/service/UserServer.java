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
    public int selectUserByPhoneNumber(String phoneNumber);
}
