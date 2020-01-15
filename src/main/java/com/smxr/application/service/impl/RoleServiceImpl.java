package com.smxr.application.service.impl;

import com.smxr.application.dao.RoleDao;
import com.smxr.application.pojo.Power;
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
    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<Role> queryRoleAll() {
        return roleDao.selectRoleAll();
    }
    /**
     * 查询所有资源字段
     * @return
     */
    @Override
    public List<Power> queryPowerAll() {
        return roleDao.selectPowerAll();
    }
    /**
     * 由RoleId查询权限字段
     * @return
     */
    @Override
    public List<Power> queryPowerByRoleId(int roleId) {
        return roleDao.selectPowerByRoleId(roleId);
    }
}
