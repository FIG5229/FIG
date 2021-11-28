package com.embracesource.traffic.screen.dao;


import com.embracesource.traffic.screen.dto.CountInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface CarTypeErrorCountDao {
    /*输入数据错误量*/
    List<CountInfoDTO> countAbnormalDataVolume(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
    long countAbnormalDataVolumeByDay(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*输入数据总量*/
    long countTotalDataVolume();

    List<CountInfoDTO> countExceptionalDataInspectionRules();
}
