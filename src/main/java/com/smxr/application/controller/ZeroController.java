package com.smxr.application.controller;

import com.smxr.application.Application;
import com.smxr.application.pojo.PhoneCode;
import com.smxr.application.pojo.User;
import com.smxr.application.service.UserServer;
import com.smxr.application.utils.applicationUtils;
import com.smxr.application.utils.applicationUtilsOne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

import static javafx.scene.input.KeyCode.L;

/**
 * @author smxr
 * @date 2019/11/25
 * @time 22:28
 * 对外开放接口
 */
@Controller
@RequestMapping("/zero")
public class ZeroController {
    private static Logger logger=LoggerFactory.getLogger(ZeroController.class);
    @Autowired
    private UserServer userServer;
    @Autowired
    private applicationUtils applicationUtil;

    /**
     * 登录成功后跳转到首页
     * @return
     */
    @RequestMapping("/index")
    public String indexPage(){

        return "404";
    }
    @RequestMapping("/signUp")
    public String signUpPage(Model model,String signUp){
        if (signUp.equals(""))
            return "404";
        model.addAttribute("signUp", signUp);
        model.addAttribute("addUser", 1);
        return "login";
    }
    @RequestMapping("/login")
    public String signInPage(){
        return "login";
    }
    @RequestMapping("/err")
    public String err_404(){
        return "404";
    }

    /**
     * 手机号注册接口
     * @param phone
     * @param number
     * @param signUp
     * @return
     */
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestParam(required = true,value = "phone")String phone,
                          @RequestParam(required = true,value = "number")String number,
                          String signUp){
    if (signUp.equals("null")||signUp.equals("")){return "404";}
    if (phone.equals("null")||phone.equals("")){return "404";}
    if (number.equals("null")||number.equals("")){return "404";}
    User user = new User();
    user.setUserName(phone);
    user.setUserPwd(number);
    user.setPhoneNumber(phone);
    user.setUserAge(0);
    user.setCreateTime(applicationUtilsOne.getDateTime());
    boolean b = userServer.insertUser(user);
    if(b){return "login";}else {return "404";}
    }

    /**
     * 发送短信验证码接口
     * @param phone
     * @return
     */
    @RequestMapping(value = "/phoneCode",method = RequestMethod.POST)
    @ResponseBody
    public boolean sendPhoneCode(@RequestParam(value = "phone") String phone){
        if (phone.equals("null")||phone.equals("")){return false;}
        if (userServer.selectUserByPhoneNumber(phone)){
//            applicationUtil.
            return true;
        }else {
            return false;
        }
    }

}
