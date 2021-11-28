package com.embracesource.traffic.forecast.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.embracesource.traffic.forecast.dao.OfflineResultDao;
import com.embracesource.traffic.forecast.dto.OfflineResultVO;
import com.embracesource.traffic.forecast.service.OfflineResultService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-06 上午 10:55
 * @description：
 * @version:
 */
@Service
@DS("pgsql")
public class OfflineResultServiceImpl implements OfflineResultService {
    @Resource
    OfflineResultDao offlineResultDao;

    @Override
    public List<OfflineResultVO> queryAllByStartStationAndEndStationAndCurrentStation(String startStation, String currentStation, String endStation) {
        return offlineResultDao.queryAllByStartStationAndEndStationAndCurrentStation(startStation, currentStation, endStation);
    }
}
