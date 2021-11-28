package com.embracesource.traffic.forecast.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.embracesource.traffic.forecast.dao.GisStationCltsZdDao;
import com.embracesource.traffic.forecast.dao.OfflineResultDao;
import com.embracesource.traffic.forecast.domain.GisStationCltsZdDO;
import com.embracesource.traffic.forecast.dto.CityDTO;
import com.embracesource.traffic.forecast.dto.OfflineResultDTO;
import com.embracesource.traffic.forecast.dto.OfflineResultVO;
import com.embracesource.traffic.forecast.service.GisStationCltsZdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-06 上午 10:54
 * @description：
 * @version:
 */
@Slf4j
@Service
@DS("pgsql")
public class GisStationCltsZdServiceImpl implements GisStationCltsZdService {
    @Resource
    GisStationCltsZdDao gisStationCltsZdDao;

    @Resource
    OfflineResultDao offlineResultDao;

    @Override
    public GisStationCltsZdDO findByName(String name) {
        return gisStationCltsZdDao.findByName(name);
    }

    @Override
    public List<GisStationCltsZdDO> findAllByNameIn(List<String> names) {
        return gisStationCltsZdDao.findAllByNameIn(names);
    }

    @Override
    public Map<String, GisStationCltsZdDO> findAllByDblmIn(List<String> names) {
        List<GisStationCltsZdDO> allByDblmIn = gisStationCltsZdDao.findAllByDblmIn(names);
        return allByDblmIn.stream().collect(Collectors.toMap(GisStationCltsZdDO::getDblm, GisStationCltsZdDO -> GisStationCltsZdDO));
    }

    @Override
    public List<List<CityDTO>> getCityDto(int length, int max, String startSatation, String currentStation, String endStation) {
        List<OfflineResultVO> offlineResultDOS = offlineResultDao.queryAllByStartStationAndEndStation(startSatation, endStation);
        List<OfflineResultDTO> collect = offlineResultDOS.stream().map(map -> {
            OfflineResultDTO offlineResultDTO = new OfflineResultDTO();
            BeanUtils.copyProperties(map, offlineResultDTO);
            return offlineResultDTO;
        }).collect(Collectors.toList());
        Map<String, List<OfflineResultDTO>> map = collect.stream().collect(Collectors.groupingBy(OfflineResultDTO::getCurrentStation));

        collect.forEach(item -> {
            String nStation = item.getNStation();
            List<OfflineResultDTO> offlineResultDTOS = map.get(nStation);
            if (!CollectionUtils.isEmpty(offlineResultDTOS)) {
                // 添加next
                item.addNext(offlineResultDTOS, max);
                // 添加pre
                offlineResultDTOS.forEach(ns -> {
                    ns.addPre(item, max);
                });
            }
        });

        // 中断结尾
        List<OfflineResultDTO> stations = map.get(endStation);
        if (CollectionUtils.isEmpty(stations)) {
            log.error("start={},current={},end={},查不到结尾数据", startSatation, currentStation, endStation);
            //return null;
        } else {
            stations.forEach(end -> {
                end.setNext(null);
            });
        }
        // 中断起点
        stations = map.get(startSatation);
        if (CollectionUtils.isEmpty(stations)) {
            log.error("start={},current={},end={},查不到起点数据", startSatation, currentStation, endStation);
            //return null;
        } else {
            stations.forEach(start -> {
                start.setPre(null);
            });
        }
        List<OfflineResultDTO> current = map.get(currentStation);
        if (CollectionUtils.isEmpty(current)) {
            log.error("start={},current={},end={},查不到当前站点数据", startSatation, currentStation, endStation);
            return null;
        }
        current = current.stream().sorted(Comparator.comparing(OfflineResultDTO::getANum).reversed())
                .collect(Collectors.toList())
                .subList(0, Math.min(current.size(), max));

        //获取当前站以后的数据
        List<List<CityDTO>> answerASC = new ArrayList<>();
        for (OfflineResultDTO offlineResultDTO : current) {
            deepParseResultAsc(offlineResultDTO, new ArrayList<>(), answerASC, length);
        }
        //获取当前站之前的数据
        List<List<CityDTO>> answerDESC = new ArrayList<>();
        deepParseResultDesc(current.get(0), new ArrayList<>(), answerDESC, length);

        if (CollectionUtils.isEmpty(answerDESC)) {
            return answerASC;
        } else if (CollectionUtils.isEmpty(answerDESC)) {
            return answerASC;
        } else {
            List<List<CityDTO>> answer = new ArrayList<>();
            List<CityDTO> cityDTOListAll;
            for (List<CityDTO> cityDTOList : answerDESC) {
                for (List<CityDTO> cityDTOS : answerASC) {
                    cityDTOListAll = new ArrayList<>();
                    cityDTOListAll.addAll(cityDTOList);
                    cityDTOListAll.addAll(cityDTOS);
                    answer.add(cityDTOListAll);
                }
            }
            return answer;
        }
    }

    //从前向后遍历
    private void deepParseResultAsc(OfflineResultDTO item, ArrayList<CityDTO> result, List<List<CityDTO>> answer, int max) {
        if (answer.size() == max) {
            // 结果已经完成 强行终止
            return;
        }
        // item 转 vo
        CityDTO cityDTO = new CityDTO();
        cityDTO.setCity(item.getName());
        cityDTO.setCityCode(item.getCurrentStation());
        cityDTO.setLon(item.getLongitude());
        cityDTO.setLat(item.getLatitude());
        // 去重
        long count = result.stream()
                .filter(i -> i.getCityCode().equals(item.getCurrentStation())).count();
        if (count == 0) {
            result.add(cityDTO);
        }
        List<OfflineResultDTO> next = item.getNext();
        if (next == null || next.size() == 0) {
            // 找到一个结果
            answer.add(result);
        } else {
            for (OfflineResultDTO offlineResultDTO : next) {
                deepParseResultAsc(offlineResultDTO, new ArrayList<>(result), answer, max);
            }
        }
    }

    //从后向前遍历
    private void deepParseResultDesc(OfflineResultDTO item, ArrayList<CityDTO> result, List<List<CityDTO>> answer, int max) {
        if (answer.size() == max) {
            // 结果已经完成 强行终止
            return;
        }
        // item 转 vo
        CityDTO cityDTO = new CityDTO();
        cityDTO.setCity(item.getName());
        cityDTO.setCityCode(item.getCurrentStation());
        cityDTO.setLon(item.getLongitude());
        cityDTO.setLat(item.getLatitude());
        // 去重
        long count = result.stream()
                .filter(i -> i.getCityCode().equals(item.getCurrentStation())).count();
        if (count == 0) {
            result.add(cityDTO);
        }
        List<OfflineResultDTO> pre = item.getPre();
        if (pre == null || pre.size() == 0) {
            // 找到一个结果
            result.remove(0);
            if (!CollectionUtils.isEmpty(result)) {
                Collections.reverse(result);
                answer.add(result);
            }
        } else {
            for (OfflineResultDTO offlineResultDTO : pre) {
                deepParseResultDesc(offlineResultDTO, new ArrayList<>(result), answer, max);
            }
        }
    }
}
