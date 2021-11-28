package com.embracesource.traffic.forecast.dto;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author wangshimin
 * @Date 2020-11-06 10:50:24
 */

@Data
public class OfflineResultDTO implements Serializable {

    private static final long serialVersionUID = 160027706991629L;

    private String startStation;

    private String currentStation;

    private String endStation;

    private String nStation;

    private Long aNum;

    private String name;

    private String longitude;

    private String latitude;

    private List<OfflineResultDTO> next = new ArrayList<>();

    private List<OfflineResultDTO> pre = new ArrayList<>();


    public void addNext(List<OfflineResultDTO> item, int max) {
        if (!CollectionUtils.isEmpty(item)) {
            next.addAll(item);
            next = next.stream().sorted(Comparator.comparing(OfflineResultDTO::getANum).reversed())
                    .collect(Collectors.toList())
                    .subList(0, Math.min(next.size(), max));
        }
    }

    public void addPre(OfflineResultDTO item, int max) {
        if (item != null) {
            pre.add(item);
            pre = pre.stream().sorted(Comparator.comparing(OfflineResultDTO::getANum).reversed())
                    .collect(Collectors.toList())
                    .subList(0, Math.min(pre.size(), max));
        }
    }

}
