package com.embracesource.traffic.screen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: daniel.liu
 * @Date: create in 2021/8/12 16:08、
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataCountInfoDTO extends CountInfoDTO{
    private String reportType;
}
