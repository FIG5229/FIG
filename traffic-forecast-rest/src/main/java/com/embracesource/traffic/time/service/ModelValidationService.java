package com.embracesource.traffic.time.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.embracesource.traffic.screen.domain.HitResult;
import com.embracesource.traffic.time.domain.HitRate;
import com.embracesource.traffic.time.domain.ModelValidationDO;
import com.embracesource.traffic.time.dto.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface ModelValidationService extends IService<HitRate> {

    List<ModelValidationResponseDTO> getRuleAmendmentList(String currentStand, String startingStand, String endStand, String pageSize, String page,String sort);


    Integer getRuleAmendmentCount(String currentStand, String startingStand, String endStand, String pageSize);

    List<HitRateNotHitResponseDTO> getCarNo(String date);

    List<SectionResponseDTO> getSectionList(String date);

    List<StationResponseDTO> getStationList(String date);

    List<HitResult> getNotHitList(HitRateNotHitResponseDTO hitRateNotHitResponseDTO);

    List<Map<String, Object>> getCountHitList(String date);

    List<HitResult> getNotHitAllList(String date);

    void deleteAll();

    List<HitRate> seleceHitRatelist();
}
