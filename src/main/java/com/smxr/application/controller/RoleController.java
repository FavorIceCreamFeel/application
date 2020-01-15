package com.smxr.application.controller;

import com.smxr.application.pojo.Power;
import com.smxr.application.pojo.Role;
import com.smxr.application.service.RoleService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * @author smxr
 * @date 2020/1/15
 * @time 11:01
 */
@Log
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/showRole")
    @ApiOperation("角色列表")
    public String showRole(Model model) {
        List<Role> roles = roleService.queryRoleAll();
        if (roles==null){
            return "404";
        }
        model.addAttribute("roleList",roles);
        model.addAttribute("roles","角色");
        model.addAttribute("Role",1);
//        model.addAttribute("Power",1);
        log.info("获取角色列表："+roles.toString());
        return "manage/role";
    }
    @RequestMapping("/showPower")
    @ApiOperation("资源列表")
    public String showPower(Model model) {
        List<Power> powers = roleService.queryPowerAll();
        if (powers==null){
            return "404";
        }
        model.addAttribute("powerList",powers);
        model.addAttribute("roles","资源");
        model.addAttribute("Power",1);
//        model.addAttribute("Role",0);
        log.info("获取资源列表："+powers.toString());
        return "manage/role";
    }
}
