package com.embracesource.traffic.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.admin.domain.SysUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-28 上午 10:35
 * @description：
 * @version:
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserDO> {
    SysUserDO getUserByName(@Param("userName") String userName);
}
