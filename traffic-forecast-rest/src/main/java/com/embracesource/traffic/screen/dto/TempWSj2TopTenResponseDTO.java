package com.embracesource.traffic.screen.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value ="运输货品停留时间（TOP10）" )
public class TempWSj2TopTenResponseDTO implements Serializable {

    private static final long serialVersionUID = 6218981850449545678L;

    @ApiModelProperty(value = "货物类型")
    private String goodsType;

    @ApiModelProperty(value = "停留时间")
    private String tlMinutes;

}
