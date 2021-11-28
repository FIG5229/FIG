package com.embracesource.traffic.screen.business;

import com.embracesource.traffic.base.utils.DateUtil;
import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.screen.dto.CountDataDTO;
import com.embracesource.traffic.screen.dto.CountInfoDTO;
import com.embracesource.traffic.screen.service.CarResultInfoHistoryService;
import com.embracesource.traffic.screen.service.CarTypeErrorCountService;
import com.embracesource.traffic.screen.service.HitResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.embracesource.traffic.base.config.Constant.WEEK;

/**
 * @Author: daniel.liu
 * @Description:
 * @Date: create in 2021/5/20 17:44
 */
@Service
public class DataHandleBiz {

    @Autowired
    CarResultInfoHistoryService carResultInfoHistoryService;

    @Autowired
    HitResultService hitResultService;

    @Autowired
    CarTypeErrorCountService carTypeErrorCountService;




    /**
     * 数据处理量
     * @return
     */
    public Result getDataHandlingCapacity(){
        Date startTime = DateUtil.getZeroTime(-7);
        Date endTime = DateUtil.getZeroTime(1);
        List<CountDataDTO> res = carResultInfoHistoryService.countDataHandlingCapacityDay(startTime,endTime);
        return Result.ok(res);
    }

    /**
     * 异常数据量
     */
    public Result getAbnormalDataVolume(){
        Date startTime = DateUtil.getZeroTime(-7);
        Date endTime = DateUtil.getZeroTime(1);
        List<CountInfoDTO> res = carTypeErrorCountService.countAbnormalDataVolume(startTime, endTime);
        return Result.ok(res);
    }

    /**
     * 预测失败量
     * @return
     */
    public Result getPredictiveFailureAmount(){
        Date startTime = DateUtil.getZeroTime(-7);
        Date endTime = DateUtil.getZeroTime(1);
        List<CountInfoDTO> res = hitResultService.getPredictiveFailureAmount(startTime,endTime);
        return Result.ok(res);
    }
}
