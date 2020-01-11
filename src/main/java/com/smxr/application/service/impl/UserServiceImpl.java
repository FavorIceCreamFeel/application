package com.smxr.application.service.impl;

import com.smxr.application.dao.UserDao;
import com.smxr.application.pojo.User;
import com.smxr.application.service.UserService;
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
public class UserServiceImpl implements UserService {
    public static Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
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
        if (s==null){ return null;}
        if (s.equals("")){ return null;}
        User user = userDao.queryUserByPhoneNum(s);
        logger.info("用户："+user);
        if (user==null){ return null;}
        StringBuilder stringBuilder = new StringBuilder();
        List powerStringList = userDao.queryPowerStringByPhoneNum(Long.parseLong(s));
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
        if (user==null)
            return false;
        String userPwd = user.getUserPwd();
        String encode = passwordEncoder.encode(userPwd);
        user.setUserPwd(encode);
        boolean boo = userDao.updateUser(user);
        if (boo) {
            logger.info("修改用户成功：" + user);
            return boo;
        }else{
            logger.info("修改用户失败："+user);
            return boo;
        }
    }

    /**
     * 查询用户是否存在
     * @param phoneNumber
     * @return
     */
    @Override
    public boolean selectUserByPhoneNumber(String phoneNumber) {
        logger.info("开始查询用户："+phoneNumber);
        return userDao.queryUserByPhoneNum(phoneNumber) == null;
    }

    @Override
    public User queryUserByPhoneNumber(String phoneNumber) {
        return userDao.queryUserByPhoneNum(phoneNumber);
    }

    /**
     * 旧密码校验
     * @param userName
     * @return
     */
    @Override
    public String findUserPwd(String userName,String userPwd) {
        if (userName == null)
            return "用户名不能为空，请重试";
        if(userPwd == null)
            return "旧密码不能为空，请重试";
        String pwd = userDao.findUserPwd(userName);

        if (pwd == null) {
            logger.info("用户" + userName + "密码查询失败，请重试");
            return "用户密码查询失败，请重试";
        }else {
            boolean matches = passwordEncoder.matches(userPwd, pwd);
            if (!matches) {
                logger.info("原始密码错误，请重试");
                return "原始密码错误，请重试";
            }
            return userPwd;
        }
    }

}
