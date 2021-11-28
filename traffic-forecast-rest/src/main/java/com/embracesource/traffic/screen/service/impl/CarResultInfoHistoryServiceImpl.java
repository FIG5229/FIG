package com.embracesource.traffic.screen.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.embracesource.traffic.base.pageHelper.PageInfo;
import com.embracesource.traffic.base.utils.DateUtil;
import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.screen.dao.CarResultInfoHistoryDao;
import com.embracesource.traffic.screen.domain.CarResultInfoHistory;
import com.embracesource.traffic.screen.dto.*;
import com.embracesource.traffic.screen.service.CarResultInfoHistoryService;
import com.embracesource.traffic.screen.vo.DateVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.embracesource.traffic.base.config.Constant.WEEK;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-25 下午 03:27
 * @description：
 * @version:
 */
@Service
//@DS("clickhouse")
public class CarResultInfoHistoryServiceImpl implements CarResultInfoHistoryService {

    @Resource
    private CarResultInfoHistoryDao carResultInfoHistoryDao;

    @Override
    public long countByDayDate(Date day) {
        Calendar c = Calendar.getInstance();
        if (null != day) {
            c.setTime(day);
        }
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date startTime = c.getTime();
        c.add(Calendar.DATE, 1);
        Date endTime = c.getTime();
        return carResultInfoHistoryDao.countByStartDateAndEndDate(startTime, endTime);
    }

    @Override
    public List<CarResultInfoHistory> queryListByDayDate(Date day) {
        Calendar c = Calendar.getInstance();
        if (null != day) {
            c.setTime(day);
        }
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date startTime = c.getTime();
        c.add(Calendar.DATE, 1);
        Date endTime = c.getTime();
        return carResultInfoHistoryDao.queryListByNowDayDate(startTime, endTime);
    }

    @Override
    public long countCarNoByNowDayDate(Date day) {
        Calendar c = Calendar.getInstance();
        if (null != day) {
            c.setTime(day);
        }
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date startTime = c.getTime();
        c.add(Calendar.DATE, 1);
        Date endTime = c.getTime();
        return carResultInfoHistoryDao.countCarNoByNowDayDate(startTime, endTime);
    }

    @Override
    public List<CountInfoDTO> getHourForecastList(String startDate, String endDate) {
        //TODO 获取历史小时预测数
        return carResultInfoHistoryDao.getHourForecastList(startDate,endDate);

    }

    @Override
    public Map<Integer, Long> getHourForecastMap() {
        //TODO 获取历史小时预测数
        List<String> beforeHour = DateUtil.getBeforeHour();

        //获取实时当前时间的预测数
        DateVo hourBeginAndEnd = DateUtil.getHourBeginAndEnd();
        Map<Integer, Long> hourForecastMap = new HashMap<>(24);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hourBeginAndEnd.getCurrentHourBegin());
        hourForecastMap.put(calendar.get(Calendar.HOUR_OF_DAY), carResultInfoHistoryDao.countPredictsByStartDateAndEndDate(hourBeginAndEnd.getCurrentHourBegin(), hourBeginAndEnd.getCurrentHourEnd()));
        return hourForecastMap;
    }

    @Override
    public Map<Integer, Long> getRealTimeModelInputNumber(List<String> beforeHour, Date currentHourBegin, Date currentHourEnd) {
        Map<Integer, Long> result = new HashMap<>(8);
        //TODO 获取历史数据

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentHourBegin);
        result.put(calendar.get(Calendar.HOUR_OF_DAY), carResultInfoHistoryDao.countByStartDateAndEndDate(currentHourBegin, currentHourEnd));
        return result;
    }

    @Override
    public Map<Integer, Long> getRealTimeModelPredicts(List<String> beforeHour, Date currentHourBegin, Date currentHourEnd) {
        Map<Integer, Long> result = new HashMap<>(8);
        //TODO 获取历史数据

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentHourBegin);
        result.put(calendar.get(Calendar.HOUR_OF_DAY), carResultInfoHistoryDao.countPredictsByStartDateAndEndDate(currentHourBegin, currentHourEnd));
        return result;
    }

    @Override
    public List<VehicleRunningStatusNewDTO> getRunStatus_new(int topk) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY,-1);
        String format = sdf.format(calendar.getTime());
//        return carResultInfoHistoryDao.queryListByTopK_new(topk);
        return carResultInfoHistoryDao.queryListByTopK_new2(topk,format);
    }

    @Override
    public List<VehicleRunningStatusDTO> getRunStatus(int topk) {
        return carResultInfoHistoryDao.queryListByTopK(topk);
    }

    @Override
    public List<VehicleRunningStatusDTO> getRunAllStatus(String carNo, String startStation, String endStation, String goodsType) {
        return carResultInfoHistoryDao.queryListByCarNoAndStartAndEndAndGoodType(carNo, startStation, endStation, goodsType);
    }

    @Override
    public  List<CountDataDTO> countDataHandlingCapacityDay(Date startTime,Date endTime) {
        List<CountDataDTO> res = new ArrayList<>();
        List<DataCountInfoDTO> list = carResultInfoHistoryDao.countDataHandlingCapacityDay(startTime,endTime,"LCDD");
        List<CountInfoDTO> LCDD = new ArrayList<>();
        List<CountInfoDTO> LCCF = new ArrayList<>();
        List<CountInfoDTO> CHSB = new ArrayList<>();
        list.stream().forEach(dataCountInfoDTO -> {
            if (dataCountInfoDTO.getReportType().equals("LCDD")){
                LCCF.add(dataCountInfoDTO);
            }
            if (dataCountInfoDTO.getReportType().equals("LCCF")){
                LCDD.add(dataCountInfoDTO);
            }
            if (dataCountInfoDTO.getReportType().equals("CHSB")){
                CHSB.add(dataCountInfoDTO);
            }
        });

        for (int i =0;i<WEEK;i++){
            CountDataDTO countDataDTO = new CountDataDTO();

            Date date = DateUtil.getZeroTime(-i);
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.FORMATDATE1);
            String time = sdf.format(date);
            String day = time.substring(5,10);
            countDataDTO.setLabel(day);
            countDataDTO.setDate(date);

            List<String> lcdd = LCDD.stream().map(CountInfoDTO::getLabel).collect(Collectors.toList());
            if (!lcdd.contains(day)){
                countDataDTO.setLcdd(0);
            }else {
                Optional<CountInfoDTO> countInfo = LCDD.stream().filter(countInfoDTO -> countInfoDTO.getLabel().equals(day)).findFirst();
                if (countInfo.isPresent()){
                    countDataDTO.setLcdd(countInfo.get().getCount());
                }
            }
            List<String> lccf = LCCF.stream().map(CountInfoDTO::getLabel).collect(Collectors.toList());
            if (!lccf.contains(day)){
                countDataDTO.setLcdd(0);
            }else {
                Optional<CountInfoDTO> countInfo = LCCF.stream().filter(countInfoDTO -> countInfoDTO.getLabel().equals(day)).findFirst();
                if (countInfo.isPresent()){
                    countDataDTO.setLccf(countInfo.get().getCount());
                }
            }
            List<String> chsb = CHSB.stream().map(CountInfoDTO::getLabel).collect(Collectors.toList());
            if (!chsb.contains(day)){
                countDataDTO.setLcdd(0);
            }else {
                Optional<CountInfoDTO> countInfo = CHSB.stream().filter(countInfoDTO -> countInfoDTO.getLabel().equals(day)).findFirst();
                if (countInfo.isPresent()){
                    countDataDTO.setChsb(countInfo.get().getCount());
                }
            }
            res.add(i,countDataDTO);

        }

        /*List<CountDataDTO> i = res.stream().sorted(new Comparator<CountDataDTO>() {
            @Override
            public int compare(CountDataDTO o1, CountDataDTO o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        }).collect(Collectors.toList());*/
        return res;
    }

    @Override
    public Result queryListPredict(Date startTime, Date endTime, PredictCarResultRequestDTO requestDTO) {

        requestDTO.defaultParam();
        PageHelper.startPage(requestDTO.getPageNum(),requestDTO.getPageSize());
        List<PredictCarResultDTO> list = carResultInfoHistoryDao.queryListPredict(requestDTO,startTime,endTime);
        PageInfo<PredictCarResultDTO> pageInfo= new PageInfo(list);
        return Result.ok(pageInfo);
    }

    @Override
    public List<Map<String, Object>> getCanalTraffic() {

        return carResultInfoHistoryDao.countCanalTraffic();
    }

    //站点通行能力（TOP10）
    @Override
    public List<SitTrafficTopTenResponseDTO> getSitTrafficTopTen() {
        int totalNum = carResultInfoHistoryDao.getSitTrafficToday();
        return carResultInfoHistoryDao.getSitTrafficTopTen(totalNum);
    }

}
