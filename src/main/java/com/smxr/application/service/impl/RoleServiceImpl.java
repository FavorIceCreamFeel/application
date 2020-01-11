package com.smxr.application.service.impl;

import com.smxr.application.dao.RoleDao;
import com.smxr.application.pojo.Role;
import com.smxr.application.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author smxr
 * @date 2020/1/11
 * @time 16:35
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /**
     * 查询角色《----手机号
     * @param phoneNumber
     * @return
     */
    @Override
    public List<Role> selectRoleByPhoneNumber(long phoneNumber) {
        return roleDao.selectRoleByPhoneNumber(phoneNumber);
    }
}
