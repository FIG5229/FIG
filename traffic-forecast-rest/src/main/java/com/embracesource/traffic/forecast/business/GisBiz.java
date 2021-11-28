package com.embracesource.traffic.forecast.business;

import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.forecast.domain.GisStationCltsZdDO;
import com.embracesource.traffic.forecast.dto.*;
import com.embracesource.traffic.forecast.service.GisGaodeMapPyService;
import com.embracesource.traffic.forecast.service.GisStationCltsZdService;
import com.embracesource.traffic.forecast.service.OfflineLzjService;
import com.embracesource.traffic.forecast.service.OfflineResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-06 上午 10:56
 * @description：
 * @version:
 */
@Slf4j
@Service
public class GisBiz {

    private static final int MAX = 2;

    private static final int LENGTH = 20;

    private static final Double LON = 1.0;

    private static final Double LAT = 1.0;


    @Resource
    GisStationCltsZdService gisStationCltsZdService;

    @Resource
    OfflineResultService offlineResultService;

    @Resource
    OfflineLzjService offlineLzjService;

    @Resource
    GisGaodeMapPyService gisGaodeMapPyService;

    public Result<GISResultDTO> getGisInfo(SelectDTO selectDTO) {
        //获取出发到达当前站点数据信息
        GisStationCltsZdDO startStationClts = gisStationCltsZdService.findByName(selectDTO.getStart_station());
        if (startStationClts == null) {
            log.error("start={},current={},end={},无法找到起始站点", selectDTO.getStart_station(), selectDTO.getCurrent_station(), selectDTO.getEnd_station());
            return Result.deny("无法找到起始站点,请检查起点站名");
        }
        GisStationCltsZdDO currentStationClts;
        if (StringUtils.isEmpty(selectDTO.getCurrent_station()) || selectDTO.getCurrent_station().equals(selectDTO.getStart_station())) {
            currentStationClts = startStationClts;
        } else {
            currentStationClts = gisStationCltsZdService.findByName(selectDTO.getCurrent_station());
            if (currentStationClts == null) {
                log.error("start={},current={},end={},无法找到当前站点", selectDTO.getStart_station(), selectDTO.getCurrent_station(), selectDTO.getEnd_station());
                return Result.deny("无法找到当前站点,请检查站名");
            }
        }
        GisStationCltsZdDO endStationClts = gisStationCltsZdService.findByName(selectDTO.getEnd_station());
        if (endStationClts == null) {
            log.error("start={},current={},end={},无法找到终点站点", selectDTO.getStart_station(), selectDTO.getCurrent_station(), selectDTO.getEnd_station());
            return Result.deny("无法找到终点站点,请检查站名");
        }

        GISResultDTO gisResultDTO = new GISResultDTO();
        gisResultDTO.setStart_station(getCityDTO(startStationClts.getName(), startStationClts.getLongitude(), startStationClts.getLatitude(), startStationClts.getDblm(), null));
        gisResultDTO.setCurrent_station(getCityDTO(currentStationClts.getName(), currentStationClts.getLongitude(), currentStationClts.getLatitude(), currentStationClts.getDblm(), null));
        gisResultDTO.setEnd_station(getCityDTO(endStationClts.getName(), endStationClts.getLongitude(), endStationClts.getLatitude(), endStationClts.getDblm(), null));

        //查询所有站点
        List<List<CityDTO>> cityDoList = gisStationCltsZdService.getCityDto(LENGTH, MAX, startStationClts.getDblm(), currentStationClts.getDblm(), endStationClts.getDblm());
        if (CollectionUtils.isEmpty(cityDoList)) {
            log.error("start={},current={},end={},没有查询到线路信息", startStationClts.getDblm(), currentStationClts.getDblm(), endStationClts.getDblm());
            return Result.ok(gisResultDTO);
        }
        Set<String> smallStationKeys = new HashSet<>();
        List<RouteDTO> routeDTOS = new ArrayList<>();
        cityDoList.forEach(cityDTOS -> {
            RouteDTO routeDTO = new RouteDTO();
            routeDTO.setRoutes(cityDTOS);
            routeDTOS.add(routeDTO);
            smallStationKeys.addAll(packagingSite(cityDTOS));
        });
        routeDTOS.get(0).setOptimal(true);
        if (!CollectionUtils.isEmpty(smallStationKeys)) {
            //查询小站信息
            Map<String, List<CityDTO>> listCityDTOMap = offlineLzjService.getListCityDTOMap(smallStationKeys);
            if (!CollectionUtils.isEmpty(listCityDTOMap)) {
                //填充小站信息
                routeDTOS.forEach(routeDTO -> {
                    saveSite(routeDTO, listCityDTOMap);
                });
            }
        }
        gisResultDTO.setActual_route(routeDTOS);
        return Result.ok(gisResultDTO);
    }

    //填充小站信息
    private void saveSite(RouteDTO routeDTO, Map<String, List<CityDTO>> listCityDTOMap) {
        if (routeDTO != null) {
            List<CityDTO> cityDTOList = routeDTO.getRoutes();
            if (cityDTOList.size() > 1) {
                List<CityDTO> newCityDTOList = new ArrayList<>(cityDTOList.size());
                StringBuffer stringBuffer = new StringBuffer(cityDTOList.get(0).getCityCode());
                CityDTO cityDTO = cityDTOList.get(0);
                newCityDTOList.add(cityDTO);
                Double firstLatDouble;
                Double firstLonDouble;
                List<CityDTO> tempList;
                for (int i = 1, len = cityDTOList.size(); i < len; i++) {
                    firstLatDouble = new Double(cityDTO.getLat());
                    firstLonDouble = new Double(cityDTO.getLon());
                    cityDTO = cityDTOList.get(i);
                    stringBuffer.append(cityDTO.getCityCode());
                    tempList = listCityDTOMap.get(stringBuffer.toString());
                    if (!CollectionUtils.isEmpty(tempList)) {
                        newCityDTOList.addAll(cityDTOFilter(tempList, firstLonDouble, firstLatDouble, new Double(cityDTO.getLon()), new Double(cityDTO.getLat())));
                    }
                    newCityDTOList.add(cityDTO);
                    stringBuffer = new StringBuffer(cityDTO.getCityCode());
                }
                routeDTO.setRoutes(newCityDTOList);
            }
        }
    }

    /**
     * 偏离经纬度过滤
     */
    private List<CityDTO> cityDTOFilter(List<CityDTO> cityDTOS, Double lLon, Double lLat, Double rLon, Double rLat) {
        if (CollectionUtils.isEmpty(cityDTOS) || lLon == null || lLat == null || rLon == null || rLat == null) {
            return cityDTOS;
        }
        double maxLon = Math.max(lLon, rLon) + LON;
        double minLon = Math.min(lLon, rLon) - LON;
        double maxLat = Math.max(lLat, rLat) + LAT;
        double minLat = Math.min(lLat, rLat) - LAT;
        return cityDTOS.stream().filter(cityDTO -> filterLatAndLon(cityDTO, maxLat, minLat, maxLon, minLon)).collect(Collectors.toList());
    }

    private boolean filterLatAndLon(CityDTO cityDTO, double maxLat, double minLat, double maxLon, double minLon) {
        if (cityDTO == null) {
            return false;
        }
        if (StringUtils.isEmpty(cityDTO.getLat()) || StringUtils.isEmpty(cityDTO.getLon())) {
            return false;
        }
        Double latDouble = new Double(cityDTO.getLat());
        Double lonDouble = new Double(cityDTO.getLon());
        if (latDouble > maxLat || latDouble < minLat) {
            return false;
        }
        if (lonDouble > maxLon || lonDouble < minLon) {
            return false;
        }
        return true;
    }

    public Result<PercentageResultDTO> getPercentageInfo(SelectPercentageDTO selectPercentageDTO) {
        //查询当前站的信息
        if (selectPercentageDTO.getCurrentStation().equals(selectPercentageDTO.getEndStation())) {
            return Result.deny("当前是终点站,暂无下一站信息");
        }
        PercentageResultDTO percentageResultDTO = new PercentageResultDTO();
        List<OfflineResultVO> offlineResultVOS = offlineResultService.queryAllByStartStationAndEndStationAndCurrentStation(selectPercentageDTO.getStartStationEn(), selectPercentageDTO.getCurrentStation(), selectPercentageDTO.getEndStation());
        if (CollectionUtils.isEmpty(offlineResultVOS)) {
            return Result.deny("没有下一站信息,请检查当前站信息");
        }
        Long longStream = offlineResultVOS.stream().mapToLong(OfflineResultVO::getANum).sum();
        if (offlineResultVOS.size() > MAX) {
            offlineResultVOS = offlineResultVOS.stream().sorted(Comparator.comparing(OfflineResultVO::getANum).reversed()).collect(Collectors.toList());
            offlineResultVOS = offlineResultVOS.subList(0, MAX);
        }
        List<CityDTO> cityDTOList = new ArrayList<>();
        offlineResultVOS.forEach(offlineResultVO -> {
            cityDTOList.add(getCityDTO(offlineResultVO.getName(), offlineResultVO.getLongitude(), offlineResultVO.getLatitude(), offlineResultVO.getCurrentStation(), new Double(offlineResultVO.getANum()) / longStream));
        });
        percentageResultDTO.setRoutes(cityDTOList);
        return Result.ok(percentageResultDTO);
    }

    private CityDTO getCityDTO(String name, String lon, String lat, String dblm, Double percentage) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setCity(name);
        cityDTO.setLon(lon);
        cityDTO.setLat(lat);
        cityDTO.setCityCode(dblm);
        cityDTO.setPercentage(percentage);
        return cityDTO;
    }

    //封装小站信息
    private List<String> packagingSite(List<CityDTO> cityDTOList) {
        if (cityDTOList != null && cityDTOList.size() > 1) {
            List<String> keys = new ArrayList<>(cityDTOList.size() - 1);
            StringBuffer stringBuffer = new StringBuffer(cityDTOList.get(0).getCityCode());
            for (int i = 1, len = cityDTOList.size(); i < len; i++) {
                stringBuffer.append(cityDTOList.get(i).getCityCode());
                keys.add(stringBuffer.toString());
                stringBuffer = new StringBuffer(cityDTOList.get(i).getCityCode());
            }
            return keys;
        }
        return new ArrayList<>(0);
    }

    public Result<List<String>> selectKey(String key, String endName) {
        return Result.ok(gisGaodeMapPyService.getListNames(key, endName));
    }

    public Result<List<String>> selectEndKey(String key, String startName) {
        return Result.ok(gisGaodeMapPyService.getListNamesByStartStation(key, startName));
    }
}
