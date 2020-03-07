package com.smxr.application.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smxr.application.dao.RoleDao;
import com.smxr.application.dao.UserDao;
import com.smxr.application.pojo.Power;
import com.smxr.application.pojo.Role;
import com.smxr.application.pojo.User;
import com.smxr.application.service.RoleService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * @author smxr
 * @date 2020/1/11
 * @time 16:35
 */
@Log
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;

    /**
     * 查询角色《----手机号
     * @param phoneNumber
     * @return
     */
    @Override
    public List<Role> selectRoleByPhoneNumber(long phoneNumber) {
        return roleDao.selectRoleByPhoneNumber(phoneNumber);
    }

    @Override
    public List<Role> selectRole_ByPhoneNumber(long phoneNumber) {
        return roleDao.selectRole_ByPhoneNumber(phoneNumber);
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
    * 分页显示角色
    */
    @Override
    public PageInfo<Role> queryRolePageInfo(int pageSize, int pageNumber) {
        PageHelper.startPage(pageNumber,pageSize);
        List<Role> roles = roleDao.selectRoleAll();
        return new PageInfo<Role>(roles);
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

    /**
     * 创建Role
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertRole(Role role,int[] power) {
        if (roleDao.insertRole(role)){
            log.info("创建角色："+role.toString());
            if (role.getRoleName()!=null && !role.getRoleName().equals("")) {
                Role role1 = roleDao.selectRoleByRoleName(role.getRoleName());
                for (int i : power) {
                    log.info("关联权限："+role.getRoleName()+"==========>>"+i);
                    if (!roleDao.insertrole_power(role1.getRoleId(),i))
                        return false;
                }
                log.info("创建完成：true");
                return true;
            }
        }
        return false;

    }

    /**
     * 修改Role
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    /**
     * 用户--》添加角色
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertRoleId_userId(String PhoneNumber, int...rolesList) {
        if (userDao.queryUserByPhoneNum(PhoneNumber) != null) {
            for (int i : rolesList) {
                roleDao.insertRoleId_userId(Long.parseLong(PhoneNumber), i);
            }
            return true;
        }
        return false;
    }


}
