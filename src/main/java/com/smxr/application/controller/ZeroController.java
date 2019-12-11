package com.smxr.application.controller;

import com.smxr.application.pojo.PhoneCode;
import com.smxr.application.pojo.User;
import com.smxr.application.service.UserServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 手机号注册
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
//    if (signUp.equals("null")||signUp.equals("")){return false;}
//    if (phone.equals("null")||phone.equals("")){return false;}
//    if (number.equals("null")||number.equals("")){return false;}
        User user = new User("root", number, "男", 0,phone, "", "");
        System.out.println(user.toString());
        boolean b = userServer.insertUser(user);
        System.out.println(b);
        return b+"";
    }

}
