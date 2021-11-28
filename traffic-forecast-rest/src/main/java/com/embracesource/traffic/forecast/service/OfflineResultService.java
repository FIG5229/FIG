package com.embracesource.traffic.forecast.service;

import com.embracesource.traffic.forecast.dto.OfflineResultVO;

import java.util.List;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-06 上午 10:53
 * @description：
 * @version:
 */
public interface OfflineResultService {

    List<OfflineResultVO> queryAllByStartStationAndEndStationAndCurrentStation(String startStation, String currentStation, String endStation);


}
