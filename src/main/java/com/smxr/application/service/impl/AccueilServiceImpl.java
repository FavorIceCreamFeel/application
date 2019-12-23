package com.smxr.application.service.impl;

import com.smxr.application.dao.OrdersDao;
import com.smxr.application.dao.UserDao;
import com.smxr.application.dto.AccueilDTO;
import com.smxr.application.pojo.Orders;
import com.smxr.application.pojo.User;
import com.smxr.application.service.AccueilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZhangRongFei
 * @date 2019/12/23 19:36
 */
@Service
public class AccueilServiceImpl implements AccueilService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrdersDao ordersDao;
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
}
