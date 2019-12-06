package com.smxr.application.controller;

import com.smxr.application.service.UserServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private UserServer userServer;
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

    /**
     * 手机号注册，未完成
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


        // 此处用申请通过的模板id
        String templateId = "xxx";
        // 模板参数对应的json格式数据,例如模板为您的验证码为${p1},请于${p2}时间登陆到我们的服务器
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("p1", "123");
        jsonObject.put("p2", "20180816");
        String params = jsonObject.toJSONString();
        String mobile = "xx";
        Map<String, String> datas = buildParam(mobile, templateId, params);
        String result = HttpClient4Utils.sendPost(httpClient, API_URL, datas, Consts.UTF_8);
        System.out.println("result = [" + result + "]");

//        private static Map<String, String> buildParam(String mobile, String templateId, String params) throws IOException {
            Map map = new HashMap<String, String>();
            map.put("businessId", BUSINESSID);
            map.put("timestamp", String.valueOf(System.currentTimeMillis()));
            map.put("version", "v2");
            map.put("templateId", templateId);
            map.put("mobile", mobile);
            // 国际短信对应的国际编码(非国际短信接入请注释掉该行代码)
            // map.put("internationalCode", "对应的国家编码");
            map.put("paramType", "json");
            map.put("params", params);
            map.put("nonce", UUID.randomUUID().toString().replace("-", ""));
            map.put("secretId", SECRETID);
            String sign = SignatureUtils.genSignature(SECRETKEY, map);
            map.put("signature", sign);
            return map;
//        }
    }
}
