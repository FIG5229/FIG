package com.embracesource.traffic.admin.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.embracesource.traffic.admin.dao.SysUserDao;
import com.embracesource.traffic.admin.domain.SysUserDO;
import com.embracesource.traffic.admin.service.UserService;
import com.embracesource.traffic.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-28 下午 03:22
 * @description：
 * @version:
 */
@Service
@DS(value = "pgsql")
public class UserServiceImpl implements UserService {
    @Autowired
    SysUserDao sysUserDao;

    @Override
    public Result createUser(SysUserDO sysUserDO) {
        sysUserDO.setId(UUID.randomUUID().toString().replace("-", ""));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        sysUserDO.setPassword(bCryptPasswordEncoder.encode(sysUserDO.getPassword()));
        sysUserDO.setCreateTime(new Date());
        if (sysUserDao.insert(sysUserDO) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @Override
    public Result deleteUser(String userId) {
        if (sysUserDao.deleteById(userId) > 0) {
            return Result.ok();
        }
        return Result.error();
    }
}
