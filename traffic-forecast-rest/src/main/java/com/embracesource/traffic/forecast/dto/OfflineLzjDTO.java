package com.embracesource.traffic.forecast.dto;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-09 上午 09:24
 * @description：
 * @version:
 */
@Data
public class OfflineLzjDTO {
    private String key;
    private List<CityDTO> cityDTOS;

    public OfflineLzjDTO(String currentStation, String nStation, String value) {
        this.key = currentStation + nStation;
        if (!StringUtils.isEmpty(value)) {
            String[] split = value.split(";");
            cityDTOS = new ArrayList<>(split.length);
            CityDTO cityDTO;
            for (String str : split) {
                if (!StringUtils.isEmpty(str)) {
                    String[] strSplit = str.split(",");
                    if (strSplit[1] == "0" || strSplit[2] == "0") {
                        continue;
                    }
                    cityDTO = new CityDTO();
                    cityDTO.setCityCode(strSplit[0]);
                    cityDTO.setLon(strSplit[1]);
                    cityDTO.setLat(strSplit[2]);
                    cityDTOS.add(cityDTO);
                }
            }
        }
    }

}
