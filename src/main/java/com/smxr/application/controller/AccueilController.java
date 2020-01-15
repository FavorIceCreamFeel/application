package com.smxr.application.controller;

import com.smxr.application.dto.AccueilDTO;
import com.smxr.application.service.AccueilService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * @author ZhangRongFei
 * @date 2019/12/23 19:34
 */
@Log
@Controller
@RequestMapping("/accueil")
public class AccueilController {

    @Autowired
    AccueilService accueilService;

    @RequestMapping("/findAll")
    @ResponseBody
    @ApiOperation("后台首页")
    public AccueilDTO findAll() {
        return accueilService.findAll();
    }

    @RequestMapping("/index")
    @ApiOperation("后台首页")
    public String index(HttpServletRequest request, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        HttpSession session = request.getSession();
        for (GrantedAuthority authority : authorities) {
            if (authority.toString().equals("Role")){
                log.info("拥有权限：权限管理====>>true");
                    session.setAttribute("Role", authority.toString());
            }
           /* if (authority.toString().equals("")){
                log.info("拥有权限：用户管理====>>true");
                session.setAttribute("",authority.toString());
            }
            if (authority.toString().equals("Role")){
                log.info("拥有权限：审核管理====>>true");
                session.setAttribute("Role",authority.toString());
            }
            if (authority.toString().equals("Role")){
                log.info("拥有权限：商品管理====>>true");
                session.setAttribute("Role",authority.toString());
            }*/
        }
        return "manage/main";
    }

    @RequestMapping("/showOrder")
    @ApiOperation("订单列表")
    public String showOrder() {
        return "manage/table";
    }
}
