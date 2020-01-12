package com.smxr.application.controller;

import com.smxr.application.pojo.Role;
import com.smxr.application.pojo.User;
import com.smxr.application.service.RoleService;
import com.smxr.application.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ZhangRongFei
 * @date 2019/12/12 21:50
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger logger= LoggerFactory.getLogger(ZeroController.class);
    @Autowired
    private UserService userServer;
    @Autowired
    private RoleService roleService;

    /**
     * 修该用户信息
     * @param
     * @return
     */
//    public boolean updateUser(@RequestParam String phoneNumber,String userName,String userAge,String userSex,String address){
    @RequestMapping("/updateUser")
    public String updateUser(User user){
        logger.info("用户修改信息："+user.toString());
        boolean boo = userServer.updateUser(user);
        logger.info("修改结果："+boo);
        if (!boo){return "404";}
        return "redirect:/user/showUser";
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

    /**
     * 展示个人信息
     * @return
     */
    @RequestMapping("/showUser")
    @ApiOperation("个人信息展示")
    public String showUser(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();//这个name是手机号
        logger.info("进入个人信息页面:"+name);
        if (name==null||name.equals("")){return "404";}
        User user = userServer.queryUserByPhoneNumber(name);
        model.addAttribute("userInfo", user);
        logger.info("填充用户信息:"+user);
        List<Role> roles = roleService.selectRoleByPhoneNumber(Long.parseLong(name));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < roles.size(); i++) {
            if (i==roles.size()-1){
                stringBuilder.append(roles.get(i).getRoleName());
            }else {
                stringBuilder.append(roles.get(i).getRoleName());
                stringBuilder.append(",");
            }
        }
        model.addAttribute("RoleInfo", stringBuilder.toString());
        logger.info("填充用户角色信息："+stringBuilder.toString());
        return "manage/personInfo";
    }

    /**
     * 修改个人密码
     * @return
     */
    @RequestMapping("/upUser")
    @ApiOperation("密码修改展示")
    public String upUserShow(HttpServletRequest request,Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();//这个name是手机号
        logger.info("进入修改密码页面:"+name);
        if (name==null||name.equals("")){return "404";}
        Object userName = request.getSession().getAttribute("userName");
        if (userName==null){userName=name;}
        model.addAttribute("userName", userName);
        return "manage/changepwd";
    }
    @RequestMapping("/updateUserPwd")
    @ApiOperation("密码修改接口")
    public String updateUser(@RequestParam String passwordOld,@RequestParam String passwordOne,@RequestParam String passwordTwo){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();//这个name是手机号
        if (name==null||name.equals("")){return "404";}
        boolean boo = userServer.updateUserPwd(name, passwordOld, passwordOne, passwordTwo);
        if (boo){
            return "redirect:/accueil/index";
        }else {
            return "404";
        }
    }
}
