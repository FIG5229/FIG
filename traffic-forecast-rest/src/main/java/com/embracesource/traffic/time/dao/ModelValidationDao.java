package com.embracesource.traffic.time.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.screen.domain.HitResult;
import com.embracesource.traffic.time.domain.HitRate;
import com.embracesource.traffic.time.domain.ModelValidationDO;
import com.embracesource.traffic.time.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ModelValidationDao extends BaseMapper<HitRate> {


    List<ModelValidationResponseDTO> getTimeCareInfoByPage(@Param("currentStand") String currentStand,
                                                           @Param("startingStand") String startingStand,
                                                           @Param("endStand") String endStand,
                                                           @Param("getPageSize") Integer getPageSize,
                                                           @Param("newPage") Integer newPage,
                                                           @Param("orderSort") String orderSort);

    Integer getTimeCareInfo(@Param("currentStand") String currentStand,
                                            @Param("startingStand") String startingStand,
                                            @Param("endStand") String endStand);

    List<HitRateNotHitResponseDTO> getCarNo(String date);

    List<SectionResponseDTO> getSectionList(String date);

    List<StationResponseDTO> getStationList(String date);

    List<HitResult> getNotHitList(@Param("carNo") String carNo,
                                  @Param("startingStand") String startingStand,
                                  @Param("endStand") String endStand);

    List<Map<String, Object>> getCountHitList(String date);

    List<HitResult> getNotHitAllList(String date);

    void deleteAll();

    List<HitRate> seleceHitRatelist();

}
