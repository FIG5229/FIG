package com.embracesource.traffic.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.admin.domain.SysUserRoleRelationDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-28 下午 03:04
 * @description：
 * @version:
 */
@Mapper
public interface SysUserRoleRelationDao extends BaseMapper<SysUserRoleRelationDO> {
    int deleteByUserIdAndRoleId(@Param("userId") String userId,
                                    @Param("roleId") String roleId);
}
