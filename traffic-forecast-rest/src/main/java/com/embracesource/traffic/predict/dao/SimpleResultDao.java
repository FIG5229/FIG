package com.embracesource.traffic.predict.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: daniel.liu
 * @Date: create in 2021/8/12 8:45、
 * @Description:
 */
@Mapper
public interface SimpleResultDao {
   List<Map<String,Object>> countOffLineAccuracy();
}
