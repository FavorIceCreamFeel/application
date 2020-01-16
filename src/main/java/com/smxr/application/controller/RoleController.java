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
import org.springframework.web.bind.annotation.RequestParam;

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
        log.info("获取角色列表："+roles.toString());
        return "manage/role";
    }

    @RequestMapping("/showPower")
    @ApiOperation("资源列表权限字段")
    public String showPower(Model model, @RequestParam(required = false,value = "roleId",defaultValue ="0")int roleId) {
        if (roleId!=0){
            List<Power> powers = roleService.queryPowerByRoleId(roleId);
            model.addAttribute("powerList",powers);//资源添加
            log.info("单个用户权限字段展示列表："+powers.toString());
        }else {
            List<Power> powers = roleService.queryPowerAll();
            if (powers == null) {
                return "404";
            }
            model.addAttribute("powerList",powers);//资源添加
            log.info("展示所有权限字段列表："+powers.toString());
        }
        model.addAttribute("roles","资源");//展示文本
        model.addAttribute("Power",1);//分辨数据
        return "manage/role";
    }

    @RequestMapping("/showInsertRole")
    @ApiOperation("创建角色页面跳转")
    public String showInsertRole(Model model) {
        List<Power> powers = roleService.queryPowerAll();
        model.addAttribute("powerList",powers);
        log.info("获取资源列表："+powers.toString());
        return "manage/insertrole";
    }

    @RequestMapping("/insertRole")
    @ApiOperation("创建角色")
    public String insertRole(Model model,Role role,int...power) {
        if (role==null)
            return "404";
        boolean boo = roleService.insertRole(role, power);
        if (!boo){
            log.info("创建失败："+role.getRoleName());
            return "404";
        }
        return "redirect:/role/showRole";
    }

    @RequestMapping("/updateRoleStatus")
    @ApiOperation("角色状态修改")
    public String updateRoleStatus(@RequestParam int roleStatus,@RequestParam int roleId) {
        if (roleStatus==0){
            log.info("角色状态修改:"+roleId+"------>"+roleStatus);
            if (roleService.updateRole(new Role(roleId, roleStatus)))
                return "redirect:/role/showRole";
        }
        if (roleStatus==1){
            log.info("角色状态修改:"+roleId+"------>"+roleStatus);
            if (roleService.updateRole(new Role(roleId,roleStatus)))
                return "redirect:/role/showRole";
        }
        return "404";
    }

}
