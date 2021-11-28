package com.embracesource.traffic.admin.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.embracesource.traffic.admin.dao.SysRequestMenuDao;
import com.embracesource.traffic.admin.dao.SysUserDao;
import com.embracesource.traffic.admin.domain.SysRequestMenuDO;
import com.embracesource.traffic.admin.domain.SysUserDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-28 上午 08:54
 * @description：
 * @version:
 */
@Service
@Transactional
@DS(value = "pgsql")
public class HrService implements UserDetailsService {
    @Autowired
    SysUserDao sysUserDao;

    @Autowired
    SysRequestMenuDao sysRequestMenuDao;

    /**
     * @Description: 实现了UserDetailsService接口中的loadUserByUsername方法
     * 执行登录,构建Authentication对象必须的信息,
     * 如果用户不存在，则抛出UsernameNotFoundException异常
     **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username)) {
            throw new RuntimeException("用户不能为空");
        }
        //根据用户名查询用户
        SysUserDO sysUserDO = sysUserDao.getUserByName(username);
        if (sysUserDO == null) {
            throw new RuntimeException("用户不存在");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (sysUserDO != null) {
            //获取该用户所拥有的权限
            List<SysRequestMenuDO> sysPermissions = sysRequestMenuDao.queryListByUserId(sysUserDO.getId());
            // 声明用户授权
            sysPermissions.forEach(sysPermission -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getUrl());
                grantedAuthorities.add(grantedAuthority);
            });
        }
        return new User(sysUserDO.getId(), sysUserDO.getPassword(), checkString(sysUserDO.getEnabled()), checkString(sysUserDO.getNotExpired()), checkString(sysUserDO.getCredentialsNotExpired()), checkString(sysUserDO.getAccountNotLocked()), grantedAuthorities);
    }

    private boolean checkString(String check) {
        //暂时字段存空 不进行校验
        /*if (StringUtils.isEmpty(check) || "0".equals(check)) {
            return false;
        }*/
        return true;
    }
}