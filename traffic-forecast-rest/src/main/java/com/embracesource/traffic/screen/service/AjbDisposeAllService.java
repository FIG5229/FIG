package com.embracesource.traffic.screen.service;

import com.embracesource.traffic.screen.domain.AjbDisposeAllDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.embracesource.traffic.screen.dto.SiteAbnormalStatisticsResponseDTO;

import java.util.List;

public interface AjbDisposeAllService extends IService<AjbDisposeAllDO>{

    //站点异常统计
    List<SiteAbnormalStatisticsResponseDTO> getSiteAbnormalNumList();
}
