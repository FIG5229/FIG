package com.embracesource.traffic.screen.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.embracesource.traffic.screen.dto.SiteAbnormalStatisticsResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.embracesource.traffic.screen.domain.AjbDisposeAllDO;
import com.embracesource.traffic.screen.dao.AjbDisposeAllDao;
import com.embracesource.traffic.screen.service.AjbDisposeAllService;

import java.util.List;

@Service
@DS("clickhouse")
public class AjbDisposeAllServiceImpl extends ServiceImpl<AjbDisposeAllDao, AjbDisposeAllDO> implements AjbDisposeAllService{

    @Autowired
    AjbDisposeAllDao ajbDisposeAllDao;

    //站点异常统计
    @Override
    public List<SiteAbnormalStatisticsResponseDTO> getSiteAbnormalNumList() {
        int totalNum = ajbDisposeAllDao.getTotalNum();
        List<SiteAbnormalStatisticsResponseDTO> list = ajbDisposeAllDao.getSiteAbnormalNumList(totalNum);
        return list;
    }
}
