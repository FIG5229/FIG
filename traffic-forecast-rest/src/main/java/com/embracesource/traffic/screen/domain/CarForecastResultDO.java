package com.embracesource.traffic.screen.domain;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description: 
 * @Date: Created inMon Feb 01 14:33:06 CST 2021
 * @Author: wangshimin
 * @Modified By:
 */
@Data
@TableName(value ="car_forcast_result" )
public class CarForecastResultDO  implements Serializable {

	private static final long serialVersionUID =  3931441157635601740L;

   	@TableField(value = "key" )
	private String key;

   	@TableField(value = "feild" )
	private String feild;

   	@TableField(value = "value" )
	private String value;

}
