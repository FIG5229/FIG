package com.embracesource.traffic.base.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @Description: 获取用户
 **/
public class SecurityUserUtil {
    /**
     * @Description: 从认证中心里面获取当前用户权限信息
     **/
    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * @Description: 获取当前使用者的名称
     **/
    public static String getCurrentUserName() {
        User securitySysUser = getCurrentUser();
        if (null == securitySysUser) {
            return null;
        }
        return securitySysUser.getUsername();
    }
}
