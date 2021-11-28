package com.embracesource.traffic.forecast.service;

import java.util.List;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-10 上午 10:44
 * @description：
 * @version:
 */
public interface GisGaodeMapPyService {
    List<String> getListNames(String key, String endName);

    List<String> getListNamesByStartStation(String key, String startName);

}
