package com.smxr.application.controller;

import com.smxr.application.dto.AccueilDTO;
import com.smxr.application.service.AccueilService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZhangRongFei
 * @date 2019/12/23 19:34
 */
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
}