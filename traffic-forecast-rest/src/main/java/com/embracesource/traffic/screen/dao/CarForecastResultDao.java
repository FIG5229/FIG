package com.embracesource.traffic.screen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.screen.domain.CarForecastResultDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-02-01 下午 02:33
 * @description：
 * @version:
 */
@Mapper
public interface CarForecastResultDao extends BaseMapper<CarForecastResultDO> {
    CarForecastResultDO getCarForecastByKeyAndField(@Param("key") String key,
                                                    @Param("field") String field);

    List<CarForecastResultDO> queryCarForecastByKeyAndFields(@Param("key") String key,
                                                             @Param("fields") List<String> fields);


    List<CarForecastResultDO> queryCarForecastByKeysAndField(@Param("keys") List<String> key,
                                                             @Param("field") String fields);

}
