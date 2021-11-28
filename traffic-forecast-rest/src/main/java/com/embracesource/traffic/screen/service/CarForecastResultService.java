package com.embracesource.traffic.screen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.embracesource.traffic.screen.domain.CarForecastResultDO;
import com.embracesource.traffic.screen.dto.NonRealTimeCalculationDataDTO;

import java.util.List;
import java.util.Map;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-02-01 下午 03:41
 * @description：
 * @version:
 */
public interface CarForecastResultService extends IService<CarForecastResultDO> {
    NonRealTimeCalculationDataDTO getResults();

    Map<String, Long> getAbnormalPredictStatisticsPeray(List<String> before);

    Map<String, Long> getAbnormalPredictDataStatistics(List<String> before);
}
