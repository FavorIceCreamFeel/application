package com.smxr.application.controller;

import com.smxr.application.pojo.Goods;
import com.smxr.application.pojo.GoodsType;
import com.smxr.application.pojo.PhoneCode;
import com.smxr.application.pojo.User;
import com.smxr.application.service.GoodsService;
import com.smxr.application.service.GoodsTypeService;
import com.smxr.application.service.UserService;
import com.smxr.application.utils.ApplicationUtils;
import com.smxr.application.utils.ApplicationUtilsOne;
import com.smxr.application.utils.CCPRestSDK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    private UserService userServer;
    @Autowired
    private PhoneCode phoneCode;
    @Autowired
    private ApplicationUtils applicationUtils;
    @Autowired
    private GoodsTypeService goodsTypeService;
    @Autowired
    private GoodsService goodsServer;
    /**
     * 登录成功后跳转到首页
     * @return
     */
    @RequestMapping("/index")
    public String indexPage(HttpServletRequest request,Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() instanceof UserDetails){
            String name = authentication.getName();
            User user = userServer.queryUserByPhoneNumber(name);
            if (user==null)
                return "index";
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.toString().equals("Role1")){
                    logger.info("后台权限判断：true");
                    model.addAttribute("power",authority.toString());
                }
            }
        model.addAttribute("userName",user.getUserName());
        request.getSession().setAttribute("userName",user.getUserName());
        }
        //登录数据填充处
        HashMap<String, ArrayList<GoodsType>> stringArrayListHashMap = goodsTypeService.queryGoodsTypes();
        model.addAttribute("goodsTypeList",stringArrayListHashMap);
        logger.info("商品分类填充完成");
        String goodsTypeId = request.getQueryString();
        int i;
        if (goodsTypeId==null){
            logger.info("获取默认分类商品="+1);
            i=1;
        }else {
            String substring = goodsTypeId.substring(goodsTypeId.lastIndexOf("=") + 1);
            i=Integer.parseInt(substring);
            logger.info("获取分类商品="+i);
        }
        List<Goods> goods = goodsServer.selectGoodsByType(i);
        model.addAttribute("goodsList",goods);
        logger.info("商品填充完成");
        return "index";
    }
    @RequestMapping("/login")
    public String signInPage(@RequestParam(required = false,value = "signUp",defaultValue = "null")String signUp){
        if (signUp.equals("signUp")){
            return "login";
        }
        return "redirect:/zero/index";
    }
    @RequestMapping("/reLogin")
    public String signInPage(){
        return "login";
    }
    @RequestMapping("/signUp")
    public String signUpPage(Model model,String signUp){
        if (signUp.equals(""))
            return "404";
        model.addAttribute("signUp", signUp);
        model.addAttribute("addUser", 1);
        return "login";
    }
    @RequestMapping("/err")
    public String err_404(){
        return "404";
    }
    /**
     * 商品展示
     * @param goodsId
     * @param model
     * @return
     */
    @RequestMapping(value = "/showGoods")
    public String showGoods(@RequestParam(required = true,value = "goodsId")int goodsId, Model model){
        Goods goods = goodsServer.queryGoodsById(goodsId);
        if (goods==null){
            return "404";
        }
        model.addAttribute("goods",goods);
        return "show/details";
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
    public boolean addUser(@RequestParam(required = true,value = "phone")String phone,
                          @RequestParam(required = true,value = "number")String number,
                          String signUp){
        if (signUp.equals("null")||signUp.equals("")){return false;}
        if (phone.equals("null")||phone.equals("")){return false;}
        if (number.equals("null")||number.equals("")){return false;}
        Map<String, Long> codeMap = ApplicationUtils.getMap();
        Long aLong = codeMap.get(number);
        if (aLong==null){return false;}
        logger.info("map的时间戳："+aLong);
        logger.info("前端传来code："+number);
        boolean b=false;
        //是否超过60秒
        if (System.currentTimeMillis()-aLong<=60000){
            logger.info("时间对比："+(System.currentTimeMillis()-aLong)+"");
            User user = new User();
            user.setUserName(phone);//默认名字是手机号
            user.setUserPwd(number);//默认密码是code
            user.setPhoneNumber(phone);
            user.setUserAge(0);//默认年龄
            user.setCreateTime(ApplicationUtilsOne.getDateTime());//创建时间
             b = userServer.insertUser(user);//插入用户，返回Boolean值
            return b;
        }
        return false;
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
            HashMap<String, Object> result = null;
            CCPRestSDK restAPI = new CCPRestSDK();
            restAPI.init("app.cloopen.com", "8883");
            // 初始化服务器地址和端口，生产环境配置成app.cloopen.com，端口是8883.
            restAPI.setAccount(phoneCode.getAccount_SID(),phoneCode.getAuth_token());
            // 初始化主账号名称和主账号令牌，登陆云通讯网站后，可在控制首页中看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN。
            restAPI.setAppId(phoneCode.getAppID());
            // 请使用管理控制台中已创建应用的APPID。
            String randomString = ApplicationUtils.getRandomString(6);
            Map<String, Long> map = ApplicationUtils.getMap();
            boolean boo=true;
            while (boo){
                if (map.size()!=0){
                    if (map.get(randomString)!=null){
                        randomString=ApplicationUtils.getRandomString(6);
                    }else {
                        boo=false;
                    }
                }else {
                    boo=false;
                } }
            result = restAPI.sendTemplateSMS(phone,phoneCode.getTemplateId() ,new String[]{randomString,"1"});
            if("000000".equals(result.get("statusCode"))){
                map.put(randomString,System.currentTimeMillis());
                logger.info("放入时间戳:"+randomString);
                HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
                Set<String> keySet = data.keySet();
                for(String key:keySet){
                    Object object = data.get(key);
                    logger.info(key +" = "+object);
                }
                return true;
            }else{
                //异常返回输出错误码和错误信息
                logger.info("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
                return false;
            }
        }else {
            return false;
        }
    }


}
