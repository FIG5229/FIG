package com.embracesource.traffic.screen.web;

import com.embracesource.traffic.base.pageHelper.PageInfo;
import com.embracesource.traffic.base.utils.DateUtil;
import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.screen.business.DataHandleBiz;
import com.embracesource.traffic.screen.dto.CountDataDTO;
import com.embracesource.traffic.screen.dto.CountInfoDTO;
import com.embracesource.traffic.screen.dto.PredictCarResultDTO;
import com.embracesource.traffic.screen.dto.PredictCarResultRequestDTO;
import com.embracesource.traffic.screen.service.CarResultInfoHistoryService;
import com.embracesource.traffic.screen.service.CarTypeErrorCountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.embracesource.traffic.base.config.Constant.WEEK;

/**
 * @Author: daniel.liu
 * @Description:
 * @Date: create in 2021/5/19 16:05
 */

@Api(tags = "数据处理-预测数据")
@Controller
@RequestMapping("/dataHandle")
public class DataHandleController {

    @Autowired
    CarResultInfoHistoryService carResultInfoHistoryService;

    @Autowired
    DataHandleBiz dataHandleBiz;


    @ApiOperation(value = "数据处理量")
    @GetMapping(value = "/getDataHandlingCapacityDay")
    @ResponseBody
    public Result<CountDataDTO> getDataHandlingCapacity(){
        return  dataHandleBiz.getDataHandlingCapacity();
    }


    @ApiOperation(value = "异常数据量")
    @GetMapping(value = "/getAbnormalDataVolume")
    @ResponseBody
    public Result<List<CountInfoDTO>> getAbnormalDataVolume(){
        Result result= dataHandleBiz.getAbnormalDataVolume();
        return  result;
    }

    @ApiOperation(value = "预测失败量")
    @GetMapping(value = "/getPredictiveFailureAmount")
    @ResponseBody
    public Result<List<CountInfoDTO>> getPredictiveFailureAmount(){
        Result result= dataHandleBiz.getPredictiveFailureAmount();
        return result;
    }

    @ApiOperation(value = "预测数据列表")
    @GetMapping(value = "/findPredictList")
    @ResponseBody
    public Result<PageInfo<PredictCarResultDTO>> findPredictList(PredictCarResultRequestDTO requestDTO){
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.FORMATDATE1);
        /*七天前的时间*/
        Date starTime =DateUtil.getBeforDay(-WEEK);
        /*当前时间*/
        Date endTime =  DateUtil.getBeforDay(0);

        Result result= carResultInfoHistoryService.queryListPredict(starTime,endTime,requestDTO);
        return  result;
    }
}
