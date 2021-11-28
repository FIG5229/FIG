package com.embracesource.traffic.time.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.embracesource.traffic.time.dao.ViewTimeLjDao;
import com.embracesource.traffic.time.dao.ViewTimeLjcomeDao;
import com.embracesource.traffic.time.domain.ViewTimeLjDo;
import com.embracesource.traffic.time.domain.ViewTimeLjcomeDo;
import com.embracesource.traffic.time.dto.AccrateAllStationDTO;
import com.embracesource.traffic.time.dto.CoordinateDTO;
import com.embracesource.traffic.time.dto.TimeDTO;
import com.embracesource.traffic.time.service.ViewTimeLjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@DS(value = "pgsql")
public class ViewTimeLjServiceImpl implements ViewTimeLjService {

    private static Map<String, String> zdMap = new HashMap<>();

    static {
        zdMap.put("H", "上");
        zdMap.put("J", "兰");
        zdMap.put("O", "青");
        zdMap.put("B", "哈");
        zdMap.put("C", "呼");
        zdMap.put("V", "太");
        zdMap.put("F", "郑");
        zdMap.put("Z", "宁");
        zdMap.put("Q", "广");
        zdMap.put("Y", "西");
        zdMap.put("T", "沈");
        zdMap.put("R", "乌");
        zdMap.put("P", "京");
        zdMap.put("M", "昆");
        zdMap.put("G", "南");
        zdMap.put("N", "武");
        zdMap.put("K", "济");
        zdMap.put("W", "成");
    }

    @Autowired
    ViewTimeLjcomeDao viewTimeLjcomeDao;

    @Autowired
    ViewTimeLjDao viewTimeLjDao;

    @Override
    public ViewTimeLjcomeDo getLjAccrate(String id, String station, String ljName) {
        ViewTimeLjcomeDo data = viewTimeLjcomeDao.getLjAccrate(id, station, ljName);
        if (data == null) {
            return new ViewTimeLjcomeDo("00");
        } else {
            return viewTimeLjcomeDao.getLjAccrate(id, station, ljName);
        }
    }

    @Override
    public ViewTimeLjDo getAllLjAccrate(String id, String station) {
        ViewTimeLjDo data = null;
        try {
            data = viewTimeLjDao.getAllLjAccrate(id, station);
            if (data == null) {
                data = new ViewTimeLjDo("00");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public List<AccrateAllStationDTO> getAllStationAllLjAccrate(String id) {
        List<AccrateAllStationDTO> result = new ArrayList<AccrateAllStationDTO>();
        List<ViewTimeLjDo> list = viewTimeLjDao.getAllStationAllLjAccrate(id);
        if (!CollectionUtils.isEmpty(list)) {
            Set<String> stationSet = list.stream().map(ViewTimeLjDo::getStation).collect(Collectors.toSet());
            List<CoordinateDTO> coordinateDTOList;
            if (Integer.parseInt(id.trim()) < 13) {
                //卸车站
                coordinateDTOList = viewTimeLjDao.queryCoordinateXCZ(stationSet);
            } else {
                //编组站
                coordinateDTOList = viewTimeLjDao.queryCoordinateBZZ(stationSet);
            }
            Map<String, CoordinateDTO> coordinateDTOMap = coordinateDTOList.stream().collect(Collectors.toMap(CoordinateDTO::getDblm, coordinateDTO -> coordinateDTO));
            for (ViewTimeLjDo view : list) {
                //拿到该站的经度和纬度
                CoordinateDTO coordinate = coordinateDTOMap.get(view.getStation());
                if (coordinate == null) continue;
                //站对应路局有信息的加进去
                result.add(new AccrateAllStationDTO(view.getStation(), view.getStationName(), view.getLj(), view.getLjName(), view.getJAccrate(), coordinate.getLongitude(), coordinate.getLatitude()));
            }
        }
        return result;
    }

    @Override
    public List<TimeDTO> getLjAccrateList(String id, String station) {
        List<TimeDTO> timeDTOList = new ArrayList<TimeDTO>();
        List<ViewTimeLjcomeDo> ljAccrateList = viewTimeLjcomeDao.getLjAccrateList(id, station);


        if (ljAccrateList == null || ljAccrateList.size() == 0) {
            Iterator<String> iterator = zdMap.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next().trim();
                String value = zdMap.get(key);
                timeDTOList.add(new TimeDTO(key, value, "无"));
            }
        } else {
            for (ViewTimeLjcomeDo view : ljAccrateList) {
                timeDTOList.add(new TimeDTO(view.getLj(), view.getLjName(), view.getJAccrate()));
            }

            Iterator<String> iterator = zdMap.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next().trim();
                String value = zdMap.get(key);
                if (!ljAccrateList.contains(new ViewTimeLjcomeDo(key, value))) {
                    timeDTOList.add(new TimeDTO(key, value, "无"));
                }
            }

        }

        return timeDTOList;

    }
}
