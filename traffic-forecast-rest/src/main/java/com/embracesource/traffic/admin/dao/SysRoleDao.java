package com.embracesource.traffic.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.admin.domain.SysRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-28 下午 02:59
 * @description：
 * @version:
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleDO> {
    List<SysRoleDO> queryListByUserId(@Param("userId") String userId);
}
