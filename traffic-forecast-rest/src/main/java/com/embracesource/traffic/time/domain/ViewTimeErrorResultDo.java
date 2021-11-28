package com.embracesource.traffic.time.domain;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value ="view_time_error_result" )
public class ViewTimeErrorResultDo implements Serializable {
    @TableField(value = "id" )
    private String id;

    @TableField(value = "accrate" )
    private String accrate;

    @TableField(value = "create_time" )
    private Date createTime;
}
