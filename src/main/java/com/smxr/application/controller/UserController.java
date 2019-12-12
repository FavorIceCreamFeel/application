package com.smxr.application.controller;

import com.smxr.application.pojo.User;
import com.smxr.application.service.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZhangRongFei
 * @date 2019/12/12 21:50
 */
@Controller
@RequestMapping("/index")
public class UserController {
    @Autowired
    private UserServer userServer;

    /**
     * 修该用户信息
     * @param user
     * @return
     */
    @RequestMapping("/updateUser")
    public boolean updateUser(User user){
        boolean boo = userServer.updateUser(user);
        return boo;
    }

    /**
     * 旧密码校验
     * @param userName
     * @return
     */
    @RequestMapping("/findUserPwd")
    @ResponseBody
    public String findUserPwd(@RequestParam String userName,String userPwd){
        String pwd = userServer.findUserPwd(userName,userPwd);
        return pwd;
    }
}
