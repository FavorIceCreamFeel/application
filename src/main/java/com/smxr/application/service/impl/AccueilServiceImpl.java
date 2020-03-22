package com.smxr.application.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smxr.application.dao.OrdersDao;
import com.smxr.application.dao.UserDao;
import com.smxr.application.dto.AccueilDTO;
import com.smxr.application.pojo.Orders;
import com.smxr.application.pojo.User;
import com.smxr.application.service.AccueilService;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangRongFei
 * @date 2019/12/23 19:36
 */
@Log
@Service
public class AccueilServiceImpl implements
        AccueilService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrdersDao ordersDao;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AccueilDTO findAll() {
        Integer userNum = userDao.userNum();
        if (userNum == null){
            userNum = 0;
        }
        Integer userDayNum = userDao.userDayNum();
        if (userDayNum == null){
            userDayNum = 0;
        }
        //待审核(申请支付)
        Integer orderStatus = 1;
        Integer orderApplicantNum = ordersDao.orderApplicantNum(orderStatus);
        if (orderApplicantNum == null){
            orderApplicantNum = 0;
        }
        //审核通过(支付成功)
        orderStatus = 2;
        Integer orderAudit = ordersDao.orderAudit(orderStatus);
        if (orderAudit == null){
            orderAudit = 0;
        }

        AccueilDTO accueilDTO = new AccueilDTO();
        accueilDTO.setUserNum(userNum);
        accueilDTO.setUserDayNum(userDayNum);
        accueilDTO.setOrderApplicantNum(orderApplicantNum);
        accueilDTO.setOrderAudit(orderAudit);
        return accueilDTO;
    }

    @Override
    public PageInfo<User> showUserPage(int pageSize,int pageNumber) {
//        第一个参数表示当前页数pageNumber，第二个表示页长pageSize
        PageHelper.startPage(pageNumber,pageSize);
        List<User> userAllList = userDao.findUserAll();
        return new PageInfo<User>(userAllList);
    }

}
