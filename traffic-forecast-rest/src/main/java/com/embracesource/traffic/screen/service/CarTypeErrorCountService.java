package com.embracesource.traffic.screen.service;


import com.embracesource.traffic.screen.dto.CountInfoDTO;

import java.util.Date;
import java.util.List;

public interface CarTypeErrorCountService {

    /**
     * 获取异常数据量（输入数据错误量）
     * @return
     */
    List<CountInfoDTO> countAbnormalDataVolume(Date startTime,Date endTime);
    long countAbnormalDataVolumeByDay(Date startTime,Date endTime);

    long countTotalDataVolume();

    List<CountInfoDTO> getExceptionalDataInspectionRules();
}
