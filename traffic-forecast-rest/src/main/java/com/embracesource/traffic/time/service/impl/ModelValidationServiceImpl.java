package com.embracesource.traffic.time.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.embracesource.traffic.screen.domain.HitResult;
import com.embracesource.traffic.time.dao.ModelValidationDao;
import com.embracesource.traffic.time.domain.HitRate;
import com.embracesource.traffic.time.domain.ModelValidationDO;
import com.embracesource.traffic.time.dto.*;
import com.embracesource.traffic.time.service.ModelValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@DS(value = "pgsql")
public class ModelValidationServiceImpl extends ServiceImpl<ModelValidationDao, HitRate> implements ModelValidationService {

    @Autowired
    ModelValidationDao modelValidationDao;

    @Override
    public List<ModelValidationResponseDTO> getRuleAmendmentList(String currentStand, String startingStand, String endStand, String pageSize, String page,String sort) {
        List<ModelValidationResponseDTO> modelValidationResponseDTOS = new ArrayList<ModelValidationResponseDTO>();
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
        String orderSort = "";
        if(!"".equals(sort) && sort != null && "0".equals(sort)){
            orderSort = "ASC";
        }else if(!"".equals(sort) && sort != null && "1".equals(sort)){
            orderSort = "DESC";
        }
        modelValidationResponseDTOS = modelValidationDao.getTimeCareInfoByPage(currentStand, startingStand ,endStand, getPageSize, newPage,orderSort);

        return modelValidationResponseDTOS;
    }

    @Override
    public Integer getRuleAmendmentCount(String currentStand, String startingStand, String endStand, String pageSize) {
        Integer data = 0;
        Integer count = modelValidationDao.getTimeCareInfo(currentStand, startingStand, endStand);
        if (count == null || count == 0) {
            data = 0;
        }else{
            data = count;
        }

        return data;

    }

    @Override
    public List<HitRateNotHitResponseDTO> getCarNo(String date) {
        return modelValidationDao.getCarNo(date);
    }

    @Override
    public List<SectionResponseDTO> getSectionList(String date) {
        return modelValidationDao.getSectionList(date);
    }

    @Override
    public List<StationResponseDTO> getStationList(String date) {
        return modelValidationDao.getStationList(date);
    }

    @Override
    public List<HitResult> getNotHitList(HitRateNotHitResponseDTO hitRateNotHitResponseDTO) {
        return modelValidationDao.getNotHitList(hitRateNotHitResponseDTO.getCarNo(),hitRateNotHitResponseDTO.getStartStation(),hitRateNotHitResponseDTO.getEndStation());
    }

    @Override
    public List<Map<String, Object>> getCountHitList(String date) {
        return modelValidationDao.getCountHitList(date);
    }

    @Override
    public List<HitResult> getNotHitAllList(String date) {
        return modelValidationDao.getNotHitAllList(date);
    }

    @Transactional
    @Override
    public void deleteAll() {
        modelValidationDao.deleteAll();
    }

    @Override
    public List<HitRate> seleceHitRatelist() {
        return modelValidationDao.seleceHitRatelist();
    }


}
