package com.embracesource.traffic.forecast.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.embracesource.traffic.forecast.dao.OfflineLzjDao;
import com.embracesource.traffic.forecast.domain.OfflineLzjDO;
import com.embracesource.traffic.forecast.dto.CityDTO;
import com.embracesource.traffic.forecast.dto.OfflineLzjDTO;
import com.embracesource.traffic.forecast.service.OfflineLzjService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-09 上午 09:38
 * @description：
 * @version:
 */
@Service
@DS("pgsql")
public class OfflineLzjServiceImpl implements OfflineLzjService {
    @Resource
    OfflineLzjDao offlineLzjDao;

    @Override
    public Map<String, List<CityDTO>> getListCityDTOMap(Set<String> keys) {
        List<OfflineLzjDO> offlineLzjDOS = offlineLzjDao.queryByCurrentStationAndNStation(new ArrayList<>(keys));
        Map<String, List<CityDTO>> resultMap = new HashMap<>();
        offlineLzjDOS.forEach(offlineLzjDO -> {
            resultMap.put(offlineLzjDO.getCurrentStation() + offlineLzjDO.getNStation(), getListCity(offlineLzjDO.getNodesLz()));
        });
        return resultMap;
    }

    private List<CityDTO> getListCity(String value) {
        if (StringUtils.isEmpty(value)) {
            return new ArrayList<>(0);
        }
        String[] split = value.split(";");
        List<CityDTO> cityDTOList = new ArrayList<>(split.length);
        CityDTO cityDTO;
        for (String group : split) {
            String[] split1 = group.split(",");
            if ("0".equals(split1[1]) || "0".equals(split1[2])) {
                continue;
            }
            cityDTO = new CityDTO();
            cityDTO.setCityCode(split1[0]);
            cityDTO.setLon(split1[1]);
            cityDTO.setLat(split1[2]);
            cityDTOList.add(cityDTO);
        }
        return cityDTOList;
    }
}
