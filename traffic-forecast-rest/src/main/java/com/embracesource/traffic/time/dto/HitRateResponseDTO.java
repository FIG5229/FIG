package com.embracesource.traffic.time.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ：tom.fu
 * @date ：Created in 2021/5/20
 * @description：模型分析规则校验命中率
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("模型分析规则校验命中率出参实体集合")
public class HitRateResponseDTO {

    List<HitRateResponseListDTO> hitRateResponseListDTOList;


}
