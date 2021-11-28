package com.embracesource.traffic.time.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("xcz_sj_cnt")
public class XczSjCntDO {
    /**
     *
     */
    private String xcz;

    /**
     *
     */
    private Long sjDd;
}

