package com.embracesource.traffic.forecast.service;

import com.embracesource.traffic.forecast.dto.CityDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-09 上午 09:37
 * @description：
 * @version:
 */
public interface OfflineLzjService {
    Map<String, List<CityDTO>> getListCityDTOMap(Set<String> keys);
}
