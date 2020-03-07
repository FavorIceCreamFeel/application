package com.smxr.application.controller;

import com.github.pagehelper.PageInfo;
import com.smxr.application.dto.AccueilDTO;
import com.smxr.application.pojo.*;
import com.smxr.application.service.*;
import com.smxr.application.utils.ApplicationUtilsOne;
import com.sun.org.apache.xpath.internal.operations.Mod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

/**
 * @author ZhangRongFei
 * @date 2019/12/23 19:34
 * 后台管理
 */
@Log
@Controller
@RequestMapping("/accueil")
public class AccueilController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsTypeService goodsTypeService;
    @Autowired
    private AccueilService accueilService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private OrderService orderService;

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

//    @RequestMapping("/showOrder")
//    @ApiOperation("订单列表")
//    public String showOrder() {
//        return "user_table";
//    }

    /**
     * 展示用户列表
     * @return
     */
    @RequestMapping("/showUserList")
    @ApiOperation("展示用户列表视图跳转")
    public String showUserList(@RequestParam(required = false,value = "pageSize",defaultValue = "10") Integer pageSize,
                               @RequestParam(required = false,value = "pageNumber",defaultValue ="1") Integer pageNumber,
                               Model model){
        if (pageSize<10){
            pageSize=10;
        }
        if (pageNumber<1){
            pageNumber=1;
        }
        PageInfo<User> userPageInfo = accueilService.showUserPage(pageSize, pageNumber);
        List<User> list = userPageInfo.getList();
        log.info("第"+pageNumber+"页，共"+pageSize+"用户");
        log.info(userPageInfo.toString());
        model.addAttribute("PageInfo",userPageInfo);
        model.addAttribute("userList",list);
        return "manage/user_table";
    }
    @RequestMapping("updateUserPwd")
    @ApiOperation("修改用户密码")
    public String updateUserPwd(HttpServletRequest request,Model model){
        String phoneNumber = request.getParameter("PhoneNumber");
        if (phoneNumber==null || phoneNumber.equals(""))
            return "404";
        User user = userService.queryUserByPhoneNumber(phoneNumber);
        if (user==null)
            return "404";
        user.setUserPwd("");
        user.setAddress("");
        model.addAttribute("User",user);
        return "manage/user_changepwd";
    }
    @RequestMapping("update_UserPwd")
    @ApiOperation("密码修改接口")
    public String update_UserPwd(@RequestParam String PhoneNumber,@RequestParam String passwordOne,@RequestParam String passwordTwo,Model model){
        boolean boo = userService.updateUserPwd(PhoneNumber, passwordOne, passwordTwo);
        if (!boo)
            return "404";
        return "manage/main";
    }
    @RequestMapping("/addRole")
    @ApiOperation("添加角色--》显示用户的角色")
    public String showAddRole(@RequestParam String PhoneNumber,Model model){
        log.info("修改"+PhoneNumber+"用户角色");
        User user = userService.queryUserByPhoneNumber(PhoneNumber);
        List<Role> roles = roleService.selectRoleByPhoneNumber(Long.parseLong(PhoneNumber));
        log.info("用户已有角色："+roles.toString());
        List<Role> roles1 = roleService.selectRole_ByPhoneNumber(Long.parseLong(PhoneNumber));
        log.info("未拥有角色："+roles1);
        model.addAttribute("PhoneNumber", user.getPhoneNumber());
        model.addAttribute("UserName", user.getUserName());
        model.addAttribute("rolesList",roles);
        model.addAttribute("rolesNotList",roles1);
        return "manage/add_role";
    }
    @RequestMapping("/add_Role")
    @ApiOperation("给用户添加角色接口")
    public String add_Role(@RequestParam String PhoneNumber,Model model,int...rolesList){
        log.info("修改"+PhoneNumber+"用户角色");
        boolean boo = roleService.insertRoleId_userId(PhoneNumber, rolesList);
        log.info("修改："+boo);
        if (!boo)
            return "404";
        return "manage/main";
    }
    @RequestMapping("show_Goods")
    @ApiOperation("商品页面显示")
    public String addGoods_show(@RequestParam(required = false,value = "pageSize",defaultValue = "10") Integer pageSize,
                                @RequestParam(required = false,value = "pageNumber",defaultValue ="1") Integer pageNumber,
                                Model model){
        if (pageSize<10){
            pageSize=10;
        }
        if (pageNumber<1){
            pageNumber=1;
        }
        PageInfo<Goods> goodsPageInfo = goodsService.queryGoodsPageInfo(pageSize, pageNumber);
        model.addAttribute("PageInfo",goodsPageInfo);
        return "manage/goods_table";
    }
    @ResponseBody
    @RequestMapping("/updateGoodsNum")
    @ApiOperation("修改商品数量")
    public boolean updateGoodsNum(@RequestParam Integer goodsId,@RequestParam Integer goodsNum){
        log.info("修改商品："+goodsId+"的数量："+goodsNum);
        return goodsService.updateGoodsNumByID(goodsId, goodsNum);
    }
    @RequestMapping("/updateGoodsStatus")
    @ApiOperation("修改商品状态")
    @ResponseBody
    public boolean updateGoodsStatus(@RequestParam Integer goodsId){
        log.info("修改商品："+goodsId+"的状态");
        return goodsService.updateGoodsNumByID(goodsId);
    }
    @RequestMapping("/addGoodsShow")
    @ApiOperation("显示添加商品的页面")
    public String addGoods_show(Model model){
//        List<Types> types = goodsTypeService.queryTypesAll();
        List<GoodsType> goodsTypes = goodsTypeService.queryGoodsTypeAll();
//        model.addAttribute("Types", types);
        model.addAttribute("goodsTypes", goodsTypes);
        return "manage/add_goods";
    }
    @RequestMapping("/one_GoodsTypes")
    @ApiOperation("商品一级分类")
    public String one_GoodsTypes(Model model){
        List<Types> types = goodsTypeService.queryTypesAll();//一级分类
        model.addAttribute("types",types);
        model.addAttribute("oneTypes",1 );
        model.addAttribute("text", "一级分类");
        return "manage/types";
    }
    @RequestMapping("/two_GoodsTypes")
    @ApiOperation("商品二级分类")
    public String two_GoodsTypes(Model model){
        List<GoodsType> goodsTypes = goodsTypeService.queryGoodsTypeAll();//二级分类
        model.addAttribute("goodsTypes", goodsTypes);
        model.addAttribute("twoTypes",1 );
        model.addAttribute("text", "二级分类");
        return "manage/types";
    }
    @RequestMapping("/deleteOneGoodsTypes")
    @ApiOperation("删除商品一级分类")
    @ResponseBody
    public boolean deleteOneGoodsTypes(Model model,@RequestParam Integer typeId){
        return goodsTypeService.delTypesByID(typeId);
    }
    @RequestMapping("/deleteTwoGoodsTypes")
    @ApiOperation("删除商品二级分类")
    @ResponseBody
    public boolean deleteTwoGoodsTypes(Model model,@RequestParam Integer goodsTypeId){
        return goodsTypeService.delGoodsTypesByID(goodsTypeId);
    }
    @RequestMapping("/InsertOneGoodsTypes")
    @ApiOperation("创建商品一级分类")
    @ResponseBody
    public boolean InsertOneGoodsTypes(Model model,@RequestParam String typeName){
        return goodsTypeService.insertTypes(new Types(typeName, ApplicationUtilsOne.getDateTime()));
    }
    @RequestMapping("/InsertTwoGoodsTypes")
    @ApiOperation("创建商品二级分类")
    public String InsertTwoGoodsTypes(Model model){
        List<Types> types = goodsTypeService.queryTypesAll();
        model.addAttribute("types", types);
        return "manage/add_types";
    }
    @RequestMapping("/InsertGoodsTypes")
    @ApiOperation("创建商品二级分类")
    public String InsertGoodsTypes(Model model,@RequestParam String goodsTypeName,@RequestParam Integer typeId){
        boolean b = goodsTypeService.insertGoodsTypes(new GoodsType(goodsTypeName, typeId, ApplicationUtilsOne.getDateTime()));
        if (b)
            return "redirect:/accueil/two_GoodsTypes";
        else
            return "404";
    }

    @RequestMapping("/orderShow")
    @ApiOperation("订单审批显示")
    public String orderShow(@RequestParam(required = false,value = "pageSize",defaultValue = "10") Integer pageSize,
                            @RequestParam(required = false,value = "pageNumber",defaultValue ="1") Integer pageNumber,
                            Model model){
        PageInfo<Orders> ordersPageInfo = orderService.queryOrdersAll(pageSize, pageNumber);
        model.addAttribute("PageInfo", ordersPageInfo);
        return "manage/orders_table";
    }
    @RequestMapping(value = "/updateOrdersStatus",method = RequestMethod.POST)
    @ApiOperation("订单完成")
    @ResponseBody
    public boolean updateOrdersStatus(@RequestParam Integer orderId){
        return orderService.updateOrdersStatus(orderId);
    }
}
