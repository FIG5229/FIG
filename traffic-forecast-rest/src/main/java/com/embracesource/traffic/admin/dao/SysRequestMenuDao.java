package com.embracesource.traffic.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.admin.domain.SysRequestMenuDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-28 上午 10:46
 * @description：
 * @version:
 */
@Mapper
public interface SysRequestMenuDao extends BaseMapper<SysRequestMenuDO> {

    List<SysRequestMenuDO> queryListByUserId(@Param("userId") String userId);

    List<SysRequestMenuDO> queryListByUserName(@Param("currentUserName") String currentUserName);
}
