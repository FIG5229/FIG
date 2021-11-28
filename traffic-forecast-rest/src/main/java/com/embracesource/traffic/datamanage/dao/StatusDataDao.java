package com.embracesource.traffic.datamanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.datamanage.domain.StatusDataDO;
import com.embracesource.traffic.datamanage.dto.StatusDataResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ：tom.fu
 * @date ：Created in 2021/5/20
 * @description：状态数据下面列表dao
 * @version:
 */

@Mapper
public interface StatusDataDao extends BaseMapper<StatusDataDO> {


    List<StatusDataResponseDTO> getTimeCareInfoByPage(@Param("wagonNumber") String wagonNumber,
                                                      @Param("currentStand") String currentStand,
                                                      @Param("startingStand") String startingStand,
                                                      @Param("endStand") String endStand,
                                                      @Param("getPageSize") Integer getPageSize,
                                                      @Param("newPage") Integer newPage,
                                                      @Param("date") String date);

    List<StatusDataDO> getTimeCareInfo(@Param("wagonNumber") String wagonNumber,
                                       @Param("currentStand") String currentStand,
                                       @Param("startingStand") String startingStand,
                                       @Param("endStand") String endStand,
                                       @Param("date") String date);

    Integer getTimeCareInfoCount(@Param("wagonNumber") String wagonNumber,
                                       @Param("currentStand") String currentStand,
                                       @Param("startingStand") String startingStand,
                                       @Param("endStand") String endStand,
                                       @Param("date") String date);
}
