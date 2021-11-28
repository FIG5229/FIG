package com.embracesource.traffic.datamanage.business;

import com.embracesource.traffic.base.utils.OneWeekUtil;
import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.datamanage.dto.StatusDataDTO;
import com.embracesource.traffic.datamanage.dto.StatusDataRequestDTO;
import com.embracesource.traffic.datamanage.dto.StatusDataResponseDTO;
import com.embracesource.traffic.datamanage.service.StatusDataService;
import com.embracesource.traffic.time.business.ModelValidationBiz;
import com.embracesource.traffic.time.dto.ListComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author ：tom.fu
 * @date ：Created in 2021/5/20
 * @description:
 * @version:
 */

@Service
public class StatusDataBiz {

    @Autowired
    StatusDataService statusDataService;

    /**
     * 状态数据下面列表数据
     * @param statusDataRequestDTO
     * @return
     */
    public Result<StatusDataDTO> getStatusDataList(@Valid StatusDataRequestDTO statusDataRequestDTO) {

        String date = OneWeekUtil.lastWeek();

        List<StatusDataResponseDTO> list = statusDataService.getStatusDataList(statusDataRequestDTO.getWagonNumber(),statusDataRequestDTO.getCurrentStand(),statusDataRequestDTO.getStartingStand(),statusDataRequestDTO.getEndStand(),statusDataRequestDTO.getPageSize(), statusDataRequestDTO.getPage(),date);
        //Collections.sort(list, new ListComparator());
        Integer pageCount = statusDataService.getStatusDataCount(statusDataRequestDTO.getWagonNumber(),statusDataRequestDTO.getCurrentStand(),statusDataRequestDTO.getStartingStand(),statusDataRequestDTO.getEndStand(),statusDataRequestDTO.getPageSize(),date);
        if ((list == null || list.size() == 0) && (pageCount == null || pageCount == 0)) {
            return Result.ok(new StatusDataDTO(0, null));
        } else {
            return Result.ok(new StatusDataDTO(pageCount, list));
        }
    }




}
