package com.embracesource.traffic.time.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("xcz_yj_cnt")
public class XczYjCntDO {
    /**
     *
     */
    private String xcz;

    /**
     *
     */
    private Long yjDd;
}

