package com.smxr.application.service.impl;

import com.smxr.application.dao.UserDao;
import com.smxr.application.pojo.User;
import com.smxr.application.service.UserServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author smxr
 * @date 2019/11/24
 * @time 1:38
 */
@Service
public class UserServerImpl implements UserServer {
    public static Logger logger=LoggerFactory.getLogger(UserServerImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 用户权限授权
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("用户账号："+s);
        if (s==null) return null;
        if (s.equals("")) return null;
        User user = userDao.queryUserByPhoneNum(s);
        logger.info("用户："+user);
        if (user==null) return null;
        StringBuilder stringBuilder = new StringBuilder();
        List powerStringList = userDao.queryPowerStringByPhoneNum(user.getPhoneNumber());
        for (int i = 0; i <powerStringList.size() ; i++) {
            if (i==powerStringList.size()-1){
                stringBuilder.append(powerStringList.get(i));
            }else {
                stringBuilder.append(powerStringList.get(i));
                stringBuilder.append(",");
            }
        }
        logger.info("用户权限集："+stringBuilder.toString());
        return new org.springframework.security.core.userdetails.User(user.getPhoneNumber()+"", user.getUserPwd(), AuthorityUtils.commaSeparatedStringToAuthorityList(stringBuilder.toString()));
    }

    /**
     * 添加user
     * @param user
     * @return
     */
    @Override
    public boolean insertUser(User user) {
        logger.info("开始添加用户："+user);
        User user1 = userDao.queryUserByPhoneNum(user.getPhoneNumber());
        if (user1!=null){
            logger.info("用户已存在！注册失败");
            return false;
        }
        user.setUserPwd(passwordEncoder.encode(user.getUserPwd()));
        boolean boo = userDao.insertUser(user);
        if (boo)
            logger.info("添加用户成功："+user);
        else
            logger.info("添加用户失败："+user);
        return boo;
    }

    /**
     * 修改user
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(User user) {
        logger.info("开始修改用户："+user);
        if (user==null)  return false;
        boolean boo = userDao.updateUser(user);
        if (boo)
            logger.info("修改用户成功："+user);
        else
            logger.info("修改用户失败："+user);
        return boo;
    }

    /**
     * 查询用户数量userByPhoneNumber
     * @param phoneNumber
     * @return
     */
    @Override
    public int selectUserByPhoneNumber(String phoneNumber) {
        logger.info("开始查询用户："+phoneNumber);
        return userDao.selectUserByPhoneNumber(phoneNumber);
    }

}
