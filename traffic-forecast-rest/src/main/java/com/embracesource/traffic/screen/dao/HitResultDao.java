package com.embracesource.traffic.screen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.screen.domain.HitResult;
import com.embracesource.traffic.screen.dto.TotalStatisticsResponseDTO;
import com.embracesource.traffic.screen.dto.CountInfoDTO;
import com.embracesource.traffic.screen.dto.TrainsErrorSiteDTO;
import com.embracesource.traffic.datamanage.dto.HitResultRequestDTO;
import com.embracesource.traffic.datamanage.dto.HitResultResponseDTO;
import com.embracesource.traffic.screen.dto.UnusualForecastDataNumResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-25 下午 04:15
 * @description：
 * @version:
 */
@Mapper
public interface HitResultDao extends BaseMapper<HitResult> {
    Long countAmount(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<TrainsErrorSiteDTO> queryErrorListTopTenByStartAndEnd(@Param("startTime") String startTime, @Param("endTime") String endTime);

    Long countErrorAmount(@Param("startTime") String startTime, @Param("endTime") String endTime);
    List<CountInfoDTO> countErrorAmountBeta(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<HitResultResponseDTO> getHitResultList(@Param("hitResultRequestDTO") HitResultRequestDTO hitResultRequestDTO, @Param("startIndex") int startIndex, @Param("pageSize") int endIndex);

    //获取最近12个月异常预测数据量统计
    List<UnusualForecastDataNumResponseDTO> getUnusualForecastDataNumList(List<String> latest12Month);

    List<UnusualForecastDataNumResponseDTO> getUnusualForecastDataNumList_new();

    //获取前八个小时（包含当前小时）实时模型输入数量
    List<Map<String, Object>> getRealTimeModelInputNumber(@Param("beforeHour") List<String> beforeHour);

    Map<String, Object> getRealTimeModelInputNumber_new(@Param("now") String now, @Param("after") String after, @Param("format") String format);

    //获取前八个小时（包含当前小时）实时模型预测数量
    List<Map<String, Object>> getRealTimeModelPredictsNumber(@Param("beforeHour") List<String> beforeHour);
    Map<String, Object> getRealTimeModelPredictsNumber_new(@Param("now") String now, @Param("after") String after, @Param("format") String format);

    //获取前八个小时（包含当前小时）实时模型预测异常数量
    List<Map<String, Object>> getRealTimeModelErrorNumber(@Param("beforeHour") List<String> beforeHour);
    Map<String, Object> getRealTimeModelErrorNumber_new(@Param("now")String now, @Param("after")String after, @Param("format")String format);

    int getHitResultListCount(HitResultRequestDTO hitResultRequestDTO);
    //获取前八个小时（包含当前小时）实时模型输入数量根据时间段
    List<Map<String, Object>> getRealTimeModelInputNumberByTimeSlot(@Param("beforeTime") String beforeTime, @Param("nowTime")String nowTime);
    //获取前八个小时（包含当前小时）实时模型预测数量根据时间段
    List<Map<String, Object>> getRealTimeModelPredictsByTimeSlot(@Param("beforeTime")String beforeTime, @Param("nowTime")String nowTime);
    //获取前八个小时（包含当前小时）实时模型预测异常根据时间段
    List<Map<String, Object>> getRealTimeModelErrorNumberByTimeSlot(@Param("beforeTime")String beforeTime, @Param("nowTime")String nowTime);
    //总量统计
    List<TotalStatisticsResponseDTO> getTotalStatisticsList();
}
