package com.embracesource.traffic.datamanage.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.embracesource.traffic.datamanage.dao.StatusDataDao;
import com.embracesource.traffic.datamanage.domain.StatusDataDO;
import com.embracesource.traffic.datamanage.dto.StatusDataResponseDTO;
import com.embracesource.traffic.datamanage.service.StatusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@Service
@DS(value = "pgsql")
public class StatusDataServiceImpl implements StatusDataService {

    @Autowired
    StatusDataDao statusDataDao;

    @Override
    public List<StatusDataResponseDTO> getStatusDataList(String wagonNumber, String currentStand, String startingStand, String endStand, String pageSize, String page,String date) {
        List<StatusDataResponseDTO> statusDataResponseDTOS = new ArrayList<StatusDataResponseDTO>();
        Integer getPageSize = null;
        Integer newPage = 0;
        if(!"".equals(pageSize) && pageSize != null && !"".equals(page) && page != null) {
            getPageSize = Integer.parseInt(pageSize);

            Integer page_t = (Integer.parseInt(page) - 1);
            if (page_t == 0L) {
                newPage = 0;
            } else {
                newPage = page_t * getPageSize;
            }
        }else {
            newPage = null;
        }
        statusDataResponseDTOS = statusDataDao.getTimeCareInfoByPage(wagonNumber, currentStand, startingStand ,endStand, getPageSize, newPage,date);

        return statusDataResponseDTOS;
    }

    @Override
    public Integer getStatusDataCount(String wagonNumber, String currentStand, String startingStand, String endStand, String pageSize,String date) {
        Integer data = 0;
        BigInteger t = new BigInteger("0");
        BigInteger allCount = new BigInteger("0");
        Integer count = statusDataDao.getTimeCareInfoCount(wagonNumber, currentStand, startingStand, endStand,date);

        if (count == null || count == 0) {
            data = 0;
        }else{
            data = count;
        }

        return data;
    }
}
