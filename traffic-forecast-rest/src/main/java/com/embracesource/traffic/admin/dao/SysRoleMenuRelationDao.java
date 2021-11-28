package com.embracesource.traffic.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.admin.domain.SysRoleMenuRelationDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-28 下午 02:48
 * @description：
 * @version:
 */
@Mapper
public interface SysRoleMenuRelationDao extends BaseMapper<SysRoleMenuRelationDO> {

    int deleteByRoleIdAndMenuId(@Param("roleId") String roleId,
                                @Param("menuId") String menuId);
}
