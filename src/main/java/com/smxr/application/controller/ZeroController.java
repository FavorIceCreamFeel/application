package com.smxr.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.*;

/**
 * @author smxr
 * @date 2019/11/25
 * @time 22:28
 */
@Controller
@RequestMapping("/zero")
public class ZeroController {
    private static Logger logger=LoggerFactory.getLogger(ZeroController.class);
    @RequestMapping("/index")
    public String indexPage(){
        return "index";
    }
    @RequestMapping("/signUp")
    public String signUpPage(Model model,String signUp){
        if (signUp.equals(""))
            return "404";
        model.addAttribute("signUp", signUp);
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
}
