package com.embracesource.traffic.screen.web;

import com.embracesource.traffic.base.utils.DateUtil;
import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.predict.service.AHitDistinct2Service;
import com.embracesource.traffic.screen.business.ScreenPartyOneBiz;
import com.embracesource.traffic.screen.dto.*;
import com.embracesource.traffic.predict.service.AHitDistinctService;
import com.embracesource.traffic.screen.service.CarTypeErrorCountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-25 下午 05:11
 * @description：
 * @version:
 */
@Api(tags = "车辆数据统计接口/大屏展示接口")
@RestController
@RequestMapping("/TrainsData")
public class TrainsRunDataController {
    @Autowired
    ScreenPartyOneBiz screenPartyOneBiz;

    @Autowired
    private AHitDistinct2Service aHitDistinct2Service;
    AHitDistinctService aHitDistinctService;
    @Autowired
    private CarTypeErrorCountService carTypeErrorCountService;

    /******************************第一页接口******************************/

    @ApiOperation(value = "今日运行车次数据接口以及地图展示数据")
    @RequestMapping(value = "/getTrainsRunDayData", method = RequestMethod.GET)
    public Result<TrainsRunDayDataDTO> getTrainsRunDayData() {
        return screenPartyOneBiz.getTrainsRunDayData();
    }


    @ApiOperation(value = "准确率统计-月（最后月份）+ 小时统计预测数（最后小时数据）")
    @RequestMapping(value = "/getAccurateStatisticsPerMonthAndHoursStatisticsForecast", method = RequestMethod.GET)
    public Result<AccurateAndHourForecastDTO> getAccurateStatisticsPerMonthAndHoursStatisticsForecast() {
        return screenPartyOneBiz.getAccurateStatisticsPerMonthAndHoursStatisticsForecast();
    }

    /**
     * 路局管内车流
     * 路局预测命中率
     * 路局预测准确率
     * @param
     * @return
     */
    @ApiOperation(value = "路径测试;路局管内车流;路局预测命中率;路局预测准确率")
    @RequestMapping(value = "/getLeftInfo", method = RequestMethod.GET)
    public Result<LeftCarInfoDTO> countLeftRailway(){

        return screenPartyOneBiz.countLeftRailway();
    }
    @ApiOperation(value = "输入数据总量/错误量;异常预测统计/天")
    @RequestMapping(value = "/getRightInputDataAndException", method = RequestMethod.GET)
    public Result<RightInputDataAndExcept> countRightInputDataAndException(){
        return screenPartyOneBiz.countRightInputDataAndException();
    }

    @ApiOperation(value = "异常预测站点分布")
    @RequestMapping(value = "/getDistributionAnomalyPredictionSitesTop", method = RequestMethod.GET)
    public Result<List<CountInfoDTO>> getDistributionAnomalyPredictionSitesTop(){

        return Result.ok(screenPartyOneBiz.getDistributionAnomalyPredictionSitesTop());
    }

    @ApiOperation(value = "异常数据稽核统计")
    @RequestMapping(value = "/getExceptionalDataInspectionRules", method = RequestMethod.GET)
    public Result<List<CountInfoDTO>> getExceptionalDataInspectionRules(){

        return Result.ok(carTypeErrorCountService.getExceptionalDataInspectionRules());
    }

    @ApiOperation(value = "弹出层车辆整体情况")
    @RequestMapping(value = "/getVehicleAllStatus", method = RequestMethod.POST)
    public Result<List<VehicleRunningStatusDTO>> getVehicleAllStatus(@Valid VehicleAllStatusDTO vehicleAllStatusDTO) {
        return screenPartyOneBiz.getVehicleAllStatus(vehicleAllStatusDTO);
    }

    @ApiOperation(value = "非实时计算数据-无数据单位")
    @RequestMapping(value = "/getNonRealTimeCalculationData", method = RequestMethod.GET)
    public Result<NonRealTimeCalculationDataDTO> getNonRealTimeCalculationData() {
        return screenPartyOneBiz.getNonRealTimeCalculationData();
    }

    @ApiOperation(value = "非实时计算数据-数据单位 天")
    @RequestMapping(value = "/getNonRealTimeCalculationDataPerDay", method = RequestMethod.GET)
    public Result<NonRealTimeCalculationDataPerDayDTO> getNonRealTimeCalculationDataPerDay() {
        return screenPartyOneBiz.getNonRealTimeCalculationDataPerDay();
    }


    /******************************第二页接口******************************/

    //@ApiOperation(value = "实时模型输入数量+实时模型预测数量+实时模型异常数量")
    //@RequestMapping(value = "/getInputAndPredictAndErrorData", method = RequestMethod.GET)
    //因执行时间过长，拆分为下面三个方法
    public Result<InputAndPredictAndErrorDataDTO> getInputAndPredictAndErrorData() {
        return screenPartyOneBiz.getInputAndPredictAndErrorData();
    }
    @ApiOperation(value = "实时模型输入数量")
    @RequestMapping(value = "/getRealTimeInputData", method = RequestMethod.GET)
    @ApiResponse(code = 200,  message = "success:[{num:23,time:'10:00'}....]")
    public Result<List<Map<String,Object>>> getRealTimeInputData() {
        return screenPartyOneBiz.getRealTimeInputData();
    }
    @ApiOperation(value = "实时模型预测数量")
    @RequestMapping(value = "/getRealTimeAndPredictData", method = RequestMethod.GET)
    @ApiResponse(code = 200,  message = "success:[{num:23,time:'10:00'}....]")
    public Result<List<Map<String,Object>>> getRealTimeAndPredictData() {
        return screenPartyOneBiz.getRealTimeAndPredictData();
    }
    @ApiOperation(value = "实时模型异常数量")
    @RequestMapping(value = "/getRealTimeAndErrorData", method = RequestMethod.GET)
    @ApiResponse(code = 200,  message = "success:[{num:23,time:'10:00'}....]")
    public Result<List<Map<String,Object>>> getRealTimeAndErrorData() {
        return screenPartyOneBiz.getRealTimeAndErrorData();
    }


    @ApiOperation(value = "车辆运行情况")
    @RequestMapping(value = "/getVehicleRunningStatus", method = RequestMethod.GET)
    public Result<List<VehicleRunningStatusNewDTO>> getVehicleRunningStatus(@RequestParam(value = "获取条数", required = false) Integer topK) {
        if (topK == null || topK == 0) {
            topK = 1000;
        }
//        return screenPartyOneBiz.getVehicleRunningStatus(topK);
        return screenPartyOneBiz.getVehicleRunningStatus_new(topK);
    }

    /******************************第二页接口******************************/


    @ApiOperation(value = "运输货品停留时间（TOP10）、站点通行能力（TOP10）、站点异常统计")
    @RequestMapping(value = "/getBigScreenTwoLeftInfo" ,method = RequestMethod.GET)
    public Result<BigScreenTwoLeftResponseDTO> getBigScreenTwoLeftInfo(){
        return screenPartyOneBiz.getBigScreenTwoLeftInfo();
    }


    //@ApiOperation(value = "运输货品停留时间（TOP10）")
    //@RequestMapping(value = "/getTempWSj2TopTen" ,method = RequestMethod.GET)
    public Result<List<TempWSj2TopTenResponseDTO>> getTempWSj2TopTen(){
        return screenPartyOneBiz.getTempWSj2TopTen();
    }

    @ApiOperation(value = "异常预测数据量统计")
    @RequestMapping(value = "/getUnusualForecastDataNumList" ,method = RequestMethod.GET)
    public Result<List<UnusualForecastDataNumResponseDTO>> getUnusualForecastDataNumList(){
        return screenPartyOneBiz.getUnusualForecastDataNumList();
    }

    //@ApiOperation(value = "站点通行能力（TOP10）")
    //@RequestMapping(value = "/getSitTrafficTopTen" ,method = RequestMethod.GET)
    public Result<List<SitTrafficTopTenResponseDTO>> getSitTrafficTopTen(){
        return screenPartyOneBiz.getSitTrafficTopTen();
    }

    //@ApiOperation(value = "站点异常统计")
    //@RequestMapping(value = "/getSiteAbnormalNumList" ,method = RequestMethod.GET)
    public Result<List<SiteAbnormalStatisticsResponseDTO>> getSiteAbnormalNumList(){
        return screenPartyOneBiz.getSiteAbnormalNumList();
    }

    @ApiOperation(value = "总量统计")
    @RequestMapping(value = "/getTotalStatisticsList" ,method = RequestMethod.GET)
    public Result<List<TotalStatisticsResponseDTO>> getTotalStatisticsList(){
        return screenPartyOneBiz.getTotalStatisticsList();
    }
}
