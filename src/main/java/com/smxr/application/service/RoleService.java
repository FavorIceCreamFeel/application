package com.smxr.application.service;

import com.github.pagehelper.PageInfo;
import com.smxr.application.pojo.Power;
import com.smxr.application.pojo.Role;

import java.math.BigInteger;
import java.util.List;

/**
 * @author smxr
 * @date 2020/1/11
 * @time 16:33
 */
public interface RoleService {
    public List<Role> selectRoleByPhoneNumber(long phoneNumber);
    public List<Role> selectRole_ByPhoneNumber(long phoneNumber);
    public List<Role> queryRoleAll();
    public PageInfo<Role> queryRolePageInfo(int pageSize, int pageNumber);
    public List<Power> queryPowerAll();
    public List<Power> queryPowerByRoleId(int roleId);
    public boolean insertRole(Role role,int[] power);
    public boolean updateRole(Role role);
    public boolean insertRoleId_userId(String PhoneNumber, int...rolesList);
}
