package com.embracesource.traffic.screen.business;

import com.embracesource.traffic.base.utils.DateUtil;
import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.predict.service.AHitDistinct2Service;
import com.embracesource.traffic.predict.service.AHitDistinct3Service;
import com.embracesource.traffic.predict.service.AHitDistinctService;
import com.embracesource.traffic.predict.service.SimpleResultService;
import com.embracesource.traffic.screen.dto.*;
import com.embracesource.traffic.screen.service.*;
import com.embracesource.traffic.screen.vo.DateVo;
import com.embracesource.traffic.time.service.TempWSj2Service;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-25 下午 03:33
 * @description：
 * @version:
 */
@Service
public class ScreenPartyOneBiz {
    @Autowired
    CarResultInfoHistoryService carResultInfoHistoryService;
    @Autowired
    HitResultService hitResultService;
    @Autowired
    CarForecastResultService carForecastResultService;
    @Autowired
    TempWSj2Service tempWSj2Service;

    @Autowired
    AHitDistinctService aHitDistinctService;

    @Autowired
    private CarTypeErrorCountService carTypeErrorCountService;

    @Autowired
    private AjbDisposeAllService ajbDisposeAllService;

    @Autowired
    private AHitDistinct2Service aHitDistinct2Service;

    @Autowired
    private AHitDistinct3Service aHitDistinct3Service;

    @Autowired
    private HitAndAccuracyService hitAndAccuracyService;

    @Autowired
    private SimpleResultService simpleResultService;

    public Result<TrainsRunDayDataDTO> getTrainsRunDayData() {
        TrainsRunDayDataDTO trainsRunDayDataDTO = new TrainsRunDayDataDTO();
        long carNoByNowDayDate = carResultInfoHistoryService.countCarNoByNowDayDate(null);
        trainsRunDayDataDTO.setNowDayAmount(carNoByNowDayDate);
        if (carNoByNowDayDate == 0) {
            return Result.ok(trainsRunDayDataDTO);
        }
        //TODO 获取地图数据

        return Result.ok(trainsRunDayDataDTO);
    }


    public Result<AccurateAndHourForecastDTO> getAccurateStatisticsPerMonthAndHoursStatisticsForecast() {
        AccurateAndHourForecastDTO accurateAndHourForecastDTO = new AccurateAndHourForecastDTO();
        //获取准确率统计-月
        Long startTime  = DateUtil.getZeroTime(-30).getTime();
        Long endTime = DateUtil.getLastHourTime(new Date(),1).getTime();
        /*统计每天的准确率*/
//        aHitDistinct2Service.countOffLineAccuracyDay(startTime,endTime);
//        List<Map<String, Object>> accountList = aHitDistinct2Service.countOffLineAccuracyDay(startTime,endTime);
//        List<Map<String, Object>> accountList = aHitDistinct2Service.countOffLineAccuracyDay(startTime,endTime);
        List<Map<String, Object>> accountList = simpleResultService.countOffLineAccuracyDay();
        accurateAndHourForecastDTO.setAccountList(accountList);
        //获取小时预测数量
        Date startDate = DateUtil.getLastHourTime(null,24);
        Date endDate = DateUtil.getLastHourTime(null,-1);
        List<CountInfoDTO> hourForecastList = carResultInfoHistoryService.getHourForecastList(DateUtil.dateFormat(startDate,DateUtil.FORMATDATE1),DateUtil.dateFormat(endDate,DateUtil.FORMATDATE1));
        accurateAndHourForecastDTO.setHourForecastList(hourForecastList);
        return Result.ok(accurateAndHourForecastDTO);
    }


    public Result<InputAndPredictAndErrorDataDTO> getInputAndPredictAndErrorData() {
        InputAndPredictAndErrorDataDTO inputAndPredictAndErrorDataDTO = new InputAndPredictAndErrorDataDTO();
        //按照小时计算  总共计算8小时  需先获取之前7小时  在计算当前时间
        //List<String> beforeHour_old = DateUtil.getBeforeHour(7);
        List<String> beforeHour = DateUtil.getBeforeHour(8,"yyyy-MM-dd HH:00");
        DateVo hourBeginAndEnd = DateUtil.getHourBeginAndEnd();
        // 获取实时模型输入数量
        inputAndPredictAndErrorDataDTO.setRealTimeModelInputNumber(
                hitResultService.getRealTimeModelInputNumber(beforeHour,
                        hourBeginAndEnd.getCurrentHourBegin(), hourBeginAndEnd.getCurrentHourEnd()));
        //TODO 获取实时模型预测数量
        inputAndPredictAndErrorDataDTO.setRealTimeModelPredicts(hitResultService.getRealTimeModelPredicts(beforeHour,
                hourBeginAndEnd.getCurrentHourBegin(), hourBeginAndEnd.getCurrentHourEnd()));
        //TODO 获取实时模型异常数量
        inputAndPredictAndErrorDataDTO.setRealTimeModelErrorNumber(hitResultService.getRealTimeModelErrorNumber(beforeHour,
                hourBeginAndEnd.getCurrentHourBegin(), hourBeginAndEnd.getCurrentHourEnd()));
        return Result.ok(inputAndPredictAndErrorDataDTO);
    }

    //实时模型输入数量
    public Result getRealTimeInputData() {
        //按照小时计算  总共计算8小时  需先获取之前7小时  在计算当前时间
//        List<String> beforeHour = DateUtil.getBeforeHour(8,"yyyy-MM-dd HH:00");
//        List<String> beforeHour = DateUtil.getBeforeHour_new(8,"yyyy-MM-dd HH:00");
//        DateVo hourBeginAndEnd = DateUtil.getHourBeginAndEnd();
        // 获取实时模型输入数量
//        List<Map<String,Object>> resultList = hitResultService.getRealTimeModelInputNumber(beforeHour,
//                hourBeginAndEnd.getCurrentHourBegin(), hourBeginAndEnd.getCurrentHourEnd());
        List<Map<String,Object>> resultList = new ArrayList<>();
        //第二版
        /*for (String dateStr : beforeHour) {
            Calendar calendar = Calendar.getInstance();
            Date date = DateUtil.strToDate(dateStr, "yyyy-MM-dd HH:00");
            calendar.setTime(date);
            long now =calendar.getTimeInMillis();
            calendar.add(Calendar.HOUR,1);
            long after =calendar.getTimeInMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:00");
            String format = sdf.format(date);
            Map<String,Object> map = hitResultService.getRealTimeModelInputNumber_new(String.valueOf(now), String.valueOf(after),format);
            resultList.add(map);
        }*/
        //第三版
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,1);
        String nowTime = simpleDateFormat.format(calendar.getTime());
        calendar.add(Calendar.HOUR,-8);
        String beforeTime = simpleDateFormat.format(calendar.getTime());
        resultList =  hitResultService.getRealTimeModelInputNumberByTimeSlot(beforeTime,nowTime);
        resultList.stream().forEach(item -> {
            item.put("time",item.get("time").toString().substring(11));
        });
        return Result.ok(resultList);
    }
    //实时模型预测数量
    public Result getRealTimeAndPredictData() {
        //按照小时计算  总共计算8小时  需先获取之前7小时  在计算当前时间
        List<String> beforeHour = DateUtil.getBeforeHour_new(8,"yyyy-MM-dd HH:00");
//        DateVo hourBeginAndEnd = DateUtil.getHourBeginAndEnd();
        // 获取实时模型预测数量
//        List<Map<String,Object>> resultList = hitResultService.getRealTimeModelPredicts(beforeHour,
//                hourBeginAndEnd.getCurrentHourBegin(), hourBeginAndEnd.getCurrentHourEnd());
        List<Map<String,Object>> resultList = new ArrayList<>();
        //第二版
       /* for (String dateStr : beforeHour) {
            Calendar calendar = Calendar.getInstance();
            Date date = DateUtil.strToDate(dateStr, "yyyy-MM-dd HH:00");
            calendar.setTime(date);
            long now =calendar.getTimeInMillis();
            calendar.add(Calendar.HOUR,1);
            long after =calendar.getTimeInMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:00");
            String format = sdf.format(date);
            Map<String,Object> map = hitResultService.getRealTimeModelPredicts_new(String.valueOf(now), String.valueOf(after),format);
            resultList.add(map);
        }*/
        //第三版
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.add(Calendar.HOUR,1);
        String nowTime = calendar.getTimeInMillis()+"";
        calendar.add(Calendar.HOUR,-8);
        String beforeTime = calendar.getTimeInMillis()+"";
        resultList =  hitResultService.getRealTimeModelPredictsByTimeSlot(beforeTime.trim(),nowTime.trim());
        resultList.stream().forEach(item -> {
            item.put("time",item.get("time").toString().substring(11));
        });
        return Result.ok(resultList);
    }
    //实时模型异常数量
    public Result getRealTimeAndErrorData() {
        //按照小时计算  总共计算8小时  需先获取之前7小时  在计算当前时间
        List<String> beforeHour = DateUtil.getBeforeHour_new(8,"yyyy-MM-dd HH:00");
//        DateVo hourBeginAndEnd = DateUtil.getHourBeginAndEnd();
        // 获取实时模型异常数量
        /*List<Map<String,Object>> resultList = hitResultService.getRealTimeModelErrorNumber(beforeHour,
                hourBeginAndEnd.getCurrentHourBegin(), hourBeginAndEnd.getCurrentHourEnd());*/
        List<Map<String,Object>> resultList = new ArrayList<>();
        //第二版
        /*for (String dateStr : beforeHour) {
            Calendar calendar = Calendar.getInstance();
            Date date = DateUtil.strToDate(dateStr, "yyyy-MM-dd HH:00");
            calendar.setTime(date);
            long now =calendar.getTimeInMillis();
            calendar.add(Calendar.HOUR,1);
            long after =calendar.getTimeInMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:00");
            String format = sdf.format(date);
            Map<String,Object> map = hitResultService.getRealTimeModelErrorNumber_new(String.valueOf(now), String.valueOf(after),format);
            resultList.add(map);
        }*/
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.add(Calendar.HOUR,1);
        String nowTime = calendar.getTimeInMillis()+"";
        calendar.add(Calendar.HOUR,-8);
        String beforeTime = calendar.getTimeInMillis()+"";
        resultList = hitResultService.getRealTimeModelErrorNumberByTimeSlot(beforeTime, nowTime);
        resultList.stream().forEach(item -> {
            item.put("time",item.get("time").toString().substring(11));
        });
        return Result.ok(resultList);
    }
    //车辆运行情况
    public Result<List<VehicleRunningStatusNewDTO>> getVehicleRunningStatus_new(int topk) {
        List<VehicleRunningStatusNewDTO> list = carResultInfoHistoryService.getRunStatus_new(topk);
        if (CollectionUtils.isEmpty(list)) {
            return Result.ok();
        }
        mergeVehicleDeptment_new(list);
        return Result.ok(list);
    }

    public Result<List<VehicleRunningStatusNewDTO>> getVehicleRunningStatus_new2(int topk) {
        List<VehicleRunningStatusNewDTO> list = carResultInfoHistoryService.getRunStatus_new(topk);
        if (CollectionUtils.isEmpty(list)) {
            return Result.ok();
        }
        mergeVehicleDeptment_new(list);
        return Result.ok(list);
    }
    public Result<List<VehicleRunningStatusDTO>> getVehicleRunningStatus(int topk) {
        List<VehicleRunningStatusDTO> list = carResultInfoHistoryService.getRunStatus(topk);
        //TODO 封装数据
        if (CollectionUtils.isEmpty(list)) {
            return Result.ok();
        }
        mergeVehicleDeptment(list);
        return Result.ok(list);
    }

    public Result<List<VehicleRunningStatusDTO>> getVehicleAllStatus(VehicleAllStatusDTO vehicleAllStatusDTO) {
        List<VehicleRunningStatusDTO> list = carResultInfoHistoryService.getRunAllStatus(vehicleAllStatusDTO.getCarNo(),
                vehicleAllStatusDTO.getStartStation(), vehicleAllStatusDTO.getEndStation(),
                vehicleAllStatusDTO.getGoodsType());
        //TODO 封装数据
        if (CollectionUtils.isEmpty(list)) {
            return Result.ok();
        }
        mergeVehicleDeptment(list);
        return Result.ok(list);
    }

    private void mergeVehicleDeptment(List<VehicleRunningStatusDTO> list) {
        list.forEach(vehicleRunningStatusDTO -> {
            String expectedDepartureTime = vehicleRunningStatusDTO.getEstimatedTimeArrival();
            if (!StringUtils.isEmpty(expectedDepartureTime)) {
                String[] split = expectedDepartureTime.split("\\|");
                if (split != null && split.length > 0) {
                    String str = split[0];
                    String[] strings = str.split(",");
                    if (strings != null && strings.length > 2) {
                        vehicleRunningStatusDTO.setExpectedDepartureTime(strings[1]);
                    }
                }
            }
        });
    }

    private void mergeVehicleDeptment_new(List<VehicleRunningStatusNewDTO> list) {
        list.forEach(vehicleRunningStatusDTO -> {
            String expectedDepartureTime = vehicleRunningStatusDTO.getEstimatedTimeArrival();
            if (!StringUtils.isEmpty(expectedDepartureTime)) {
                String[] split = expectedDepartureTime.split("\\|");
                if (split != null && split.length > 0) {
                    String str = split[0];
                    String[] strings = str.split(",");
                    if (strings != null && strings.length > 2) {
                        vehicleRunningStatusDTO.setExpectedDepartureTime(strings[1]);
                    }
                    //到达时间
                    String str2 = split[split.length-1];
                    String[] strings2 = str2.split(",");
                    if (strings2 != null && strings2.length > 2) {
                        vehicleRunningStatusDTO.setEstimatedTimeArrival(strings2[strings2.length-2]);
                    }
                }
                String runStatus = "停站中";
                Long residueTime = 0L;//已经运行时间
                Long totalTime = 0L; //总时长
                double runRatio = 0.0;
                try {
                    Long reportTime =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vehicleRunningStatusDTO.getReportTime()).getTime();
                    Long chufaTime =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vehicleRunningStatusDTO.getExpectedDepartureTime()).getTime();
                    Long daodaTime =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vehicleRunningStatusDTO.getEstimatedTimeArrival()).getTime();
                    totalTime = daodaTime - chufaTime;
                    if(reportTime>=chufaTime && reportTime<daodaTime){
                        runStatus = "在途中";
                        residueTime = reportTime - chufaTime;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    runRatio = (residueTime*1.0/totalTime)*100;
                }catch (Exception e){
                    e.printStackTrace();
                    runRatio = 0;
                }
                vehicleRunningStatusDTO.setRunRatio(runRatio);
                vehicleRunningStatusDTO.setRunStatus(runStatus);
                vehicleRunningStatusDTO.setTotalTime(totalTime);
                vehicleRunningStatusDTO.setRunTime(residueTime);
            }
        });
    }

    public Result<NonRealTimeCalculationDataDTO> getNonRealTimeCalculationData() {
        //TODO 获取历史数据
        NonRealTimeCalculationDataDTO nonRealTimeCalculationDataDTO = carForecastResultService.getResults();
        return Result.ok(nonRealTimeCalculationDataDTO);
    }

    public Result<NonRealTimeCalculationDataPerDayDTO> getNonRealTimeCalculationDataPerDay() {
        NonRealTimeCalculationDataPerDayDTO nonRealTimeCalculationDataPerDayDTO = new NonRealTimeCalculationDataPerDayDTO();
        //TODO 获取异常统计数据-天 数据
        List<String> before = DateUtil.getBeforeHour(8);
        nonRealTimeCalculationDataPerDayDTO.setAbnormalPredictDataStatistics(carForecastResultService.getAbnormalPredictStatisticsPeray(before));
        //TODO 获取异异常数据统计量数据
        before = DateUtil.getBeforeMonth(12);
        nonRealTimeCalculationDataPerDayDTO.setAbnormalPredictDataStatistics(carForecastResultService.getAbnormalPredictDataStatistics(before));
        return Result.ok(nonRealTimeCalculationDataPerDayDTO);
    }

    /*查询大屏左侧数据*/
    public Result countLeftRailway() {
        LeftCarInfoDTO leftCarInfoDTO = new LeftCarInfoDTO();
        Date startDate = DateUtil.getLastHourTime(new Date(), 72);
        Date onLineDate = DateUtil.getLastHourTime(new Date(), 0);
        String s = DateUtil.dateFormat(onLineDate, DateUtil.FORMATDATE1);
        Date endDate = new Date();
        Long startTime= startDate.getTime();
        Long onLineStartTime= startDate.getTime();
        Long endTime=endDate.getTime();

        /**
         * 路径测试  命中率 准确率改为直接查询数据库中计算好的数据
         */
        /*查询命中率 离线*/
//        Map<String,Long> hitRateMap = aHitDistinct2Service.countOffLineHitRate(startTime,endTime);
        /*long offLineHitRateIsCount = 0;
        long offLineHitRateAllCount = 0;
        if (hitRateMap != null){
            if ( hitRateMap.get("is_count") != null){
                offLineHitRateIsCount = hitRateMap.get("is_count");
            }
            if (hitRateMap.get("all_count") != null){
                offLineHitRateAllCount = hitRateMap.get("all_count");
            }
        }*/

        /*查询准确率 离线*/
//        Map<String,Long> accuracyMap = aHitDistinct2Service.countOffLineAccuracy(startTime,endTime);
      /*  long offLineAccuracyIsCount =0;
        long offLineAccuracyAllCount = 0;
        if (accuracyMap != null){
            if (accuracyMap.get("is_count") != null){
                offLineAccuracyIsCount  = accuracyMap.get("is_count");
            }
            if (accuracyMap.get("all_count") != null){
                offLineAccuracyAllCount = accuracyMap.get("all_count");
            }
        }*/

        /**
         * 暂时取消实时查询
         */
        /*实时查询命中率*/
//        Map<String,Long> onLineHitRateMap = aHitDistinctService.countPredictHitRate(onLineDate,endDate);
//        long onLineHitRateIsCount = 0;
//        long onLineHitRateAllCount = 0;
 /*       if (onLineHitRateMap != null){
            if (onLineHitRateMap.get("is_count") != null){
                onLineHitRateIsCount = onLineHitRateMap.get("is_count");
            }
            if (onLineHitRateMap.get("all_count") != null){
                onLineHitRateAllCount = onLineHitRateMap.get("all_count");
            }
        }*/

        /*实时查询准确率*/
//        Map<String,Long> onLineAccuracyMap = aHitDistinctService.countPredictAccuracy(onLineDate,endDate);
//        long onLineAccuracyIsCount = 0;
//        long onLineAccuracyAllCount = 0;
       /* if (onLineAccuracyMap != null){
            if (onLineAccuracyMap.get("is_count") != null){
                onLineAccuracyIsCount = onLineAccuracyMap.get("is_count");
            }
            if (onLineAccuracyMap.get("all_count") !=null ){
                onLineAccuracyAllCount = onLineAccuracyMap.get("all_count");
            }
        }*/



        Object hitRate = null;
        Object accuracy = null;
//        if (offLineHitRateAllCount == 0){
//            offLineHitRateAllCount = 1;
//        }
//        if (offLineAccuracyAllCount == 0){
//            offLineAccuracyAllCount =1;
//        }
//        hitRate =  (((float)(offLineHitRateIsCount +onLineHitRateIsCount)/(float) (offLineHitRateAllCount+onLineHitRateAllCount)));
//        accuracy =  ((float)(offLineAccuracyIsCount+onLineAccuracyIsCount)/(float)(offLineAccuracyAllCount+onLineAccuracyAllCount));
//        leftCarInfoDTO.setHitRate(hitRate);
        /**/
        Date startHit = new Date();
        List<Map<String,Object>> hitAndAccuracy = hitAndAccuracyService.findHitAndAccuracy();
        Date endHit = new Date();
        long hit = endHit.getTime() - startHit.getTime();
        System.out.println("左侧大屏信息--路径测试--》"+hit);
        for (Map<String,Object> item : hitAndAccuracy) {
            if (item.get("label").equals("hit")){
                hitRate = item.get("count");
            }
            if (item.get("label").equals("accuracy")){
                accuracy = item.get("count");
            }
        }
        leftCarInfoDTO.setHitRate(hitRate);
        leftCarInfoDTO.setAccuracy(accuracy);
        /*查询路局管内车流*/
        Date startTraffic= new Date();
        leftCarInfoDTO.setTraffic(carResultInfoHistoryService.getCanalTraffic());
        Date endTraffic = new Date();
        long traffic = endTraffic.getTime() - startTraffic.getTime();
        System.out.println("左侧大屏信息--查询路局管内车流--》"+traffic);
        /*查询路局预测命中率*/

        /*查询路局预测命中率 离线*/
        Date startRailway= new Date();
        List<Map<String, Object>>  offLineHitRateRailway = aHitDistinct3Service.countOffLinePredictHitRate(startTime,endTime);
        Date endRailway= new Date();
        long railway = endRailway.getTime() - startRailway.getTime();
        System.out.println("左侧大屏信息--查询路局管内车流--》"+railway);
//        List<Map<String, Object>> onLine = aHitDistinctService.countPredictHitRate();
        List<Map<String, Object>> hitRateResult = new ArrayList<>();
//        onLine.stream().forEach(map -> {
//
//        });
        leftCarInfoDTO.setHitRateRailway(offLineHitRateRailway);
        /*查询路局预测准确率 离线*/
        Date startAccuracy= new Date();
        List<Map<String, Object>>  offLineAccuracyRailway = aHitDistinct3Service.countOffLinePredictAccuracy(startTime,endTime);
        Date endAccuracy= new Date();
        long Accuracy = endAccuracy.getTime() - startAccuracy.getTime();
        System.out.println("左侧大屏信息--查询路局预测准确率--》"+Accuracy);
        /*查询路局预测准确率 实时*/

        leftCarInfoDTO.setAccuracyRailway(offLineAccuracyRailway);
        /*命中率为离线加实时*/
//        leftCarInfoDTO.setHitRate(onLineHitRate+Float.valueOf(offLineHitRate.toString()));
//        leftCarInfoDTO.setAccuracy(Float.valueOf(offLineAccuracy.toString())+onLineAccuracy);
        return Result.ok(leftCarInfoDTO);
    }

    /**
     * 统计
     *  输入数据总量/错误量
     *  异常预测统计/天
     * @return
     */
    public Result countRightInputDataAndException(){
        Date startDate = DateUtil.getLastHourTime(new Date(),24);
        Date endDate = new Date();
        Long startTime=startDate.getTime();
        Long endTime=endDate.getTime();
        RightInputDataAndExcept inputDataAndExcept = new RightInputDataAndExcept();
        /*输入数据错误量*/
        long inputDataError = carTypeErrorCountService.countAbnormalDataVolumeByDay(DateUtil.getZeroTime(0),new Date());
        /*输入数据总量*/
        long inputDataTotal = carTypeErrorCountService.countTotalDataVolume();
        inputDataAndExcept.setErrorInputData(inputDataError);
        inputDataAndExcept.setTotalInputData(inputDataTotal);
        /*异常预测统计错误的*/
        /*此部分暂时去掉实时数据*/
//        Long predictExceptError = aHitDistinctService.countOnLinePredictNum(startDate,endDate,"0");
        Long offLinePredictExceptError = aHitDistinct2Service.countOffLinePredictNum(startTime,endTime,"0");
        /*异常预测统计正确的*/
//        Long predictExceptCorrect = aHitDistinctService.countOnLinePredictNum(startDate,endDate,"1");
        Long offLinePredictExceptCorrect = aHitDistinct2Service.countOffLinePredictNum(startTime,endTime,"1");
        inputDataAndExcept.setPredictCorrectNum(offLinePredictExceptCorrect);
        inputDataAndExcept.setPreDictErrorNum(offLinePredictExceptError);
        return Result.ok(inputDataAndExcept);
    }

    //第二页大屏，左半部分接口集
    public Result<BigScreenTwoLeftResponseDTO> getBigScreenTwoLeftInfo() {
        BigScreenTwoLeftResponseDTO bigScreenTwoLeftResponseDTO = new BigScreenTwoLeftResponseDTO();
        StringBuffer message = null;
        try {
            //运输货品停留时间（TOP10）
            bigScreenTwoLeftResponseDTO.setTempWSj2TopTenList(tempWSj2Service.getTempWSj2TopTen());
        }catch (Exception e){
            e.printStackTrace();
            if(message==null){
                message = new StringBuffer("运输货品停留时间（TOP10）接口调用发生错误，错误信息："+e.toString());
            }else{
                message.append("运输货品停留时间（TOP10）接口调用发生错误，错误信息："+e.toString());
            }
        }
       /* try {
            //异常预测数据量统计
            List<String> latest12Month = DateUtil.getLatest12Month();
            //bigScreenTwoLeftResponseDTO.setUnusualForecastDataNumList(hitResultService.getUnusualForecastDataNumList(latest12Month));
        }catch (Exception e){
            e.printStackTrace();
            if(message==null){
                message = new StringBuffer("异常预测数据量统计接口调用发生错误，错误信息："+e.toString());
            }else{
                message.append("异常预测数据量统计接口调用发生错误，错误信息："+e.toString());
            }
        }*/
        try {
            //站点通行能力（TOP10）
            bigScreenTwoLeftResponseDTO.setSitTrafficTopTenList(carResultInfoHistoryService.getSitTrafficTopTen());
        }catch (Exception e){
            e.printStackTrace();
            if(message==null){
                message = new StringBuffer("站点通行能力（TOP10）接口调用发生错误，错误信息："+e.toString());
            }else{
                message.append("站点通行能力（TOP10）接口调用发生错误，错误信息："+e.toString());
            }
        }
        try {
            //站点异常统计
            bigScreenTwoLeftResponseDTO.setSiteAbnormalStatisticsList(ajbDisposeAllService.getSiteAbnormalNumList());
        }catch (Exception e){
            e.printStackTrace();
            if(message==null){
                message = new StringBuffer("站点异常统计接口调用发生错误，错误信息："+e.toString());
            }else{
                message.append("站点异常统计接口调用发生错误，错误信息："+e.toString());
            }
        }
        String messageStr = "调用成功";
        if(message!=null){
            messageStr = message.toString();
        }
        return Result.ok(messageStr,bigScreenTwoLeftResponseDTO);
    }

    //运输货品停留时间（TOP10）
    public Result<List<TempWSj2TopTenResponseDTO>> getTempWSj2TopTen() {
        List<TempWSj2TopTenResponseDTO> list = tempWSj2Service.getTempWSj2TopTen();
        return Result.ok(list);
    }

    //异常预测数据量统计
    public Result<List<UnusualForecastDataNumResponseDTO>> getUnusualForecastDataNumList() {
        List<String> latest12Month = DateUtil.getLatest12Month();
//        List<UnusualForecastDataNumResponseDTO> list = hitResultService.getUnusualForecastDataNumList(latest12Month);
        List<UnusualForecastDataNumResponseDTO> list = hitResultService.getUnusualForecastDataNumList_new();
        return Result.ok(list);
    }

    //站点通行能力（TOP10）
    public Result<List<SitTrafficTopTenResponseDTO>> getSitTrafficTopTen() {
        List<SitTrafficTopTenResponseDTO> list = carResultInfoHistoryService.getSitTrafficTopTen();
        return Result.ok(list);
    }

    //站点异常统计
    public Result<List<SiteAbnormalStatisticsResponseDTO>> getSiteAbnormalNumList() {
        List<SiteAbnormalStatisticsResponseDTO> list = ajbDisposeAllService.getSiteAbnormalNumList();
        return Result.ok(list);
    }


    /**
     * 异常预测站点分布
     * @return
     */
    public List<CountInfoDTO> getDistributionAnomalyPredictionSitesTop() {
        Long startTime= DateUtil.getZeroTime(0).getTime();
        Long endTime=new Date().getTime();
        return aHitDistinct2Service.getOffLineDistributionAnomalyPredictionSitesTop(startTime,endTime);
    }
    /**
     * 总量统计
     */
    public Result<List<TotalStatisticsResponseDTO>> getTotalStatisticsList() {
        List<TotalStatisticsResponseDTO> resultList = hitResultService.getTotalStatisticsList();
        return Result.ok(resultList);
    }
}
