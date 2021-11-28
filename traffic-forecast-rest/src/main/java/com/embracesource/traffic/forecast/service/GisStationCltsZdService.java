package com.embracesource.traffic.forecast.service;

import com.embracesource.traffic.forecast.domain.GisStationCltsZdDO;
import com.embracesource.traffic.forecast.dto.CityDTO;

import java.util.List;
import java.util.Map;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-06 上午 10:42
 * @description：经纬度服务
 * @version:
 */
public interface GisStationCltsZdService {

    GisStationCltsZdDO findByName(String name);

    List<GisStationCltsZdDO> findAllByNameIn(List<String> names);

    Map<String, GisStationCltsZdDO> findAllByDblmIn(List<String> names);

    List<List<CityDTO>> getCityDto(int length, int max, String startSatation, String currentStation, String endStation);
}
