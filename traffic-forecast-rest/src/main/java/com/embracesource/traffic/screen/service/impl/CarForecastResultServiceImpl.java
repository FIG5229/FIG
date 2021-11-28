package com.embracesource.traffic.screen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.embracesource.traffic.base.config.Constant;
import com.embracesource.traffic.base.utils.JsonUtils;
import com.embracesource.traffic.screen.dao.CarForecastResultDao;
import com.embracesource.traffic.screen.domain.CarForecastResultDO;
import com.embracesource.traffic.screen.dto.NonRealTimeCalculationDataDTO;
import com.embracesource.traffic.screen.service.CarForecastResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-02-01 下午 03:41
 * @description：
 * @version:
 */
@Service
public class CarForecastResultServiceImpl extends ServiceImpl<CarForecastResultDao, CarForecastResultDO> implements CarForecastResultService {
    @Autowired
    CarForecastResultDao carForecastResultDao;

    @Override
    public NonRealTimeCalculationDataDTO getResults() {
        List<String> keys = Arrays.asList(Constant.PATHTESTS, Constant.RAILWAYADMINISTRATIONSTUBETRAFFIC, Constant.RAILWAYADMINISTRATIONSACCURACY,
                Constant.RAILWAYADMINISTRATIONSTOPREDICTSHOOT, Constant.TOTALINPUTERRORDATA, Constant.ABNORMALDISTRIBUTIONPREDICTIONSITE,
                Constant.AUDITINGSTATISTICALANOMALYDATA, Constant.RESIDENCETIMETRANSPORTGOODS, Constant.SITEPEERSABILITY,
                Constant.ABNORMALSITESTATISTICS, Constant.TOTALSTATISTICS);
        List<CarForecastResultDO> carForecastResultDOS = carForecastResultDao.queryCarForecastByKeysAndField(keys, null);
        if (CollectionUtils.isEmpty(carForecastResultDOS)) {
            return null;
        }
        NonRealTimeCalculationDataDTO nonRealTimeCalculationDataDTO = new NonRealTimeCalculationDataDTO();
        Map<String, List<CarForecastResultDO>> listMap = carForecastResultDOS.stream().collect(Collectors.groupingBy(CarForecastResultDO::getKey));
        //TODO 获取路径测试数据
        carForecastResultDOS = listMap.get(Constant.PATHTESTS);
        if (!CollectionUtils.isEmpty(carForecastResultDOS)) {
            CarForecastResultDO carForecastResultDO = carForecastResultDOS.get(0);
            nonRealTimeCalculationDataDTO.setPathTests(JsonUtils.jsonToMap(carForecastResultDO.getValue(), String.class, Float.class));
        }
        //TODO 获取路局管内车流数据
        carForecastResultDOS = listMap.get(Constant.RAILWAYADMINISTRATIONSTUBETRAFFIC);
        if (!CollectionUtils.isEmpty(carForecastResultDOS)) {
            CarForecastResultDO carForecastResultDO = carForecastResultDOS.get(0);
            nonRealTimeCalculationDataDTO.setRailwayAdministrationsTubeTraffic(JsonUtils.jsonToMap(carForecastResultDO.getValue(), String.class, Long.class));
        }
        //TODO 获取路局准确率数据
        carForecastResultDOS = listMap.get(Constant.RAILWAYADMINISTRATIONSACCURACY);
        if (!CollectionUtils.isEmpty(carForecastResultDOS)) {
            CarForecastResultDO carForecastResultDO = carForecastResultDOS.get(0);
            nonRealTimeCalculationDataDTO.setRailwayAdministrationsAccuracy(JsonUtils.jsonToMap(carForecastResultDO.getValue(), String.class, Float.class));
        }
        //TODO 获取路局命中率数据
        carForecastResultDOS = listMap.get(Constant.RAILWAYADMINISTRATIONSTOPREDICTSHOOT);
        if (!CollectionUtils.isEmpty(carForecastResultDOS)) {
            CarForecastResultDO carForecastResultDO = carForecastResultDOS.get(0);
            nonRealTimeCalculationDataDTO.setRailwayAdministrationsToPredictShoot(JsonUtils.jsonToMap(carForecastResultDO.getValue(), String.class, Float.class));
        }
        //TODO 获取输入数据总量/错误量数据
        carForecastResultDOS = listMap.get(Constant.TOTALINPUTERRORDATA);
        if (!CollectionUtils.isEmpty(carForecastResultDOS)) {
            CarForecastResultDO carForecastResultDO = carForecastResultDOS.get(0);
            nonRealTimeCalculationDataDTO.setTotalInputErrorData(Float.valueOf(carForecastResultDO.getValue()));
        }
        //TODO 获取异常预测站点分布
        carForecastResultDOS = listMap.get(Constant.ABNORMALDISTRIBUTIONPREDICTIONSITE);
        if (!CollectionUtils.isEmpty(carForecastResultDOS)) {
            CarForecastResultDO carForecastResultDO = carForecastResultDOS.get(0);
            nonRealTimeCalculationDataDTO.setAbnormalDistributionPredictionSite(JsonUtils.jsonToMap(carForecastResultDO.getValue(), String.class, Long.class));
        }
        //TODO 获取异常站点稽核统计
        carForecastResultDOS = listMap.get(Constant.AUDITINGSTATISTICALANOMALYDATA);
        if (!CollectionUtils.isEmpty(carForecastResultDOS)) {
            CarForecastResultDO carForecastResultDO = carForecastResultDOS.get(0);
            nonRealTimeCalculationDataDTO.setAuditingStatisticalAnomalyData(JsonUtils.jsonToMap(carForecastResultDO.getValue(), String.class, Float.class));
        }
        //TODO 获取运输货品停留时间
        carForecastResultDOS = listMap.get(Constant.RESIDENCETIMETRANSPORTGOODS);
        if (!CollectionUtils.isEmpty(carForecastResultDOS)) {
            CarForecastResultDO carForecastResultDO = carForecastResultDOS.get(0);
            nonRealTimeCalculationDataDTO.setResidenceTimeTransportGoods(JsonUtils.jsonToMap(carForecastResultDO.getValue(), String.class, Long.class));
        }
        //TODO 获取站点同行能力数据
        carForecastResultDOS = listMap.get(Constant.SITEPEERSABILITY);
        if (!CollectionUtils.isEmpty(carForecastResultDOS)) {
            CarForecastResultDO carForecastResultDO = carForecastResultDOS.get(0);
            nonRealTimeCalculationDataDTO.setSitePeersAbility(JsonUtils.jsonToMap(carForecastResultDO.getValue(), String.class, Float.class));
        }
        //TODO 获取异常站点统计数据
        carForecastResultDOS = listMap.get(Constant.ABNORMALSITESTATISTICS);
        if (!CollectionUtils.isEmpty(carForecastResultDOS)) {
            CarForecastResultDO carForecastResultDO = carForecastResultDOS.get(0);
            nonRealTimeCalculationDataDTO.setAbnormalSiteStatistics(JsonUtils.jsonToMap(carForecastResultDO.getValue(), String.class, Float.class));
        }
        //TODO 获取总量统计数据
        carForecastResultDOS = listMap.get(Constant.TOTALSTATISTICS);
        if (!CollectionUtils.isEmpty(carForecastResultDOS)) {
            CarForecastResultDO carForecastResultDO = carForecastResultDOS.get(0);
            nonRealTimeCalculationDataDTO.setTotalStatistics(JsonUtils.jsonToMap(carForecastResultDO.getValue(), String.class, Object.class));
        }
        return nonRealTimeCalculationDataDTO;
    }

    @Override
    public Map<String, Long> getAbnormalPredictStatisticsPeray(List<String> before) {
        if (CollectionUtils.isEmpty(before)) {
            return new HashMap<>(0);
        }
        List<CarForecastResultDO> carForecastResultDOList = carForecastResultDao.queryCarForecastByKeyAndFields(Constant.ABNORMALPREDICTSTATISTICSPERAY, before);
        if (CollectionUtils.isEmpty(carForecastResultDOList)) {
            return new HashMap<>(0);
        }
        Map<String, Long> result = new HashMap<>(carForecastResultDOList.size());
        carForecastResultDOList.forEach(carForecastResultDO -> {
            if (carForecastResultDO != null) {
                result.put(carForecastResultDO.getFeild(), Long.valueOf(carForecastResultDO.getValue()));
            }
        });
        return result;
    }

    @Override
    public Map<String, Long> getAbnormalPredictDataStatistics(List<String> before) {
        if (CollectionUtils.isEmpty(before)) {
            return new HashMap<>(0);
        }
        List<CarForecastResultDO> carForecastResultDOList = carForecastResultDao.queryCarForecastByKeyAndFields(Constant.ABNORMALPREDICTDATASTATISTICS, before);
        if (CollectionUtils.isEmpty(carForecastResultDOList)) {
            return new HashMap<>(0);
        }
        Map<String, Long> result = new HashMap<>(carForecastResultDOList.size());
        carForecastResultDOList.forEach(carForecastResultDO -> {
            if (carForecastResultDO != null) {
                result.put(carForecastResultDO.getFeild(), Long.valueOf(carForecastResultDO.getValue()));
            }
        });
        return result;
    }
}
