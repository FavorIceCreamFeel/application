package com.smxr.application.service;

import com.github.pagehelper.PageInfo;
import com.smxr.application.dto.AccueilDTO;
import com.smxr.application.pojo.User;

import java.util.List;

/**
 * @author ZhangRongFei
 * @date 2019/12/23 19:35
 */
public interface AccueilService {
    public AccueilDTO findAll();
    public PageInfo<User> showUserPage(int pageSize, int pageNumber);
}
