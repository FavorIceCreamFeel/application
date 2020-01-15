package com.smxr.application.service;

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
    public List<Role> queryRoleAll();
    public List<Power> queryPowerAll();
    public List<Power> queryPowerByRoleId(int roleId);
}
