package com.embracesource.traffic.admin.service;

import com.embracesource.traffic.admin.domain.SysUserDO;
import com.embracesource.traffic.base.utils.Result;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-28 下午 03:22
 * @description：
 * @version:
 */
public interface UserService {

    Result createUser(SysUserDO sysUserDO);

    Result deleteUser(String userId);
}
