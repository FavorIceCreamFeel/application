package com.smxr.application.controller;

import com.smxr.application.pojo.Goods;
import com.smxr.application.service.GoodsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author smxr
 * @date 2020/2/4
 * @time 10:28
 */
@Log
@Controller
@ResponseBody
public class UploadController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/fileUpload")
    @ApiOperation("图片上传接口")
    public String fileUpload(HttpServletRequest request, Model model, @RequestParam MultipartFile file, Goods goods){
        if (file.isEmpty())
            return "404";
        if (goods.getGoodsName().equals("")||goods.getGoodsName()==null)
            return "404";
        Goods goods1 = goodsService.queryGoodsByName(goods.getGoodsName());
        if (goods1!=null) {
                log.info("名字重复：====>上传失败");
                return "404";
        }
        String originalFilename = file.getOriginalFilename();//得到文件名
        assert originalFilename != null;
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String replace = UUID.randomUUID().toString().replace("-", "");
        String fileName=replace+suffixName;//新文件名
//        String realPath = request.getSession().getServletContext().getRealPath("/upload/");
        String realPath="E://upload//";
//        target/classes/static
        log.info("上传文件名:"+fileName+"---上传文件路径:"+realPath);
        File realFile =  new File(realPath,fileName);
        if(!realFile.getParentFile().exists()){
            realFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(realFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        处理商品添加
        goods.setGoodsUrl("/upload/"+fileName);
        log.info("商品添加："+goods.toString());
        boolean boo = goodsService.insert_Goods(goods);
        if (!boo)
            return "404";
        log.info("===>添加完成");
        return "redirect:/accueil/show_Goods";
    }
}
