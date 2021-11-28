package com.embracesource.traffic.screen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.screen.domain.AjbDisposeAllDO;
import com.embracesource.traffic.screen.dto.SiteAbnormalStatisticsResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AjbDisposeAllDao extends BaseMapper<AjbDisposeAllDO> {
    int getTotalNum();

    List<SiteAbnormalStatisticsResponseDTO> getSiteAbnormalNumList(@Param("totalNum") int totalNum);
}