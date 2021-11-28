package com.embracesource.traffic.forecast.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.embracesource.traffic.forecast.domain.GisGaodeMapPyDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-10 上午 10:35
 * @description：
 * @version:
 */
@Mapper
public interface GisGaodeMapPyDao extends BaseMapper<GisGaodeMapPyDO> {
    List<String> queryAllNameByNameKey(@Param("key") String key);

    List<String> queryAllNameByPYKey(@Param("key") String key);

    List<String> queryAllNameByPYKeyTablePinYin(@Param("key") String key);

    List<String> queryAllNameByNameKeyTablePinYin(@Param("key") String key);

    List<String> queryAllNameByEndNameAndNameKeyTablePinYin(@Param("key") String key,
                                                            @Param("endName") String endName);

    List<String> queryAllNameByEndNameAndPYKeyTablePinYin(@Param("key") String key,
                                                          @Param("endName") String endName);


    List<String> queryAllEndNameByStartNameTablePinYin(@Param("startName") String startName);

    List<String> queryAllEndNameByNameKeyTablePinYin(@Param("key") String key);

    List<String> queryAllEndNameByPYKeyTablePinYin(@Param("key") String key);

    List<String> queryAllEndNameByStartNameAndNameTablePinYin(@Param("key") String key,
                                                              @Param("startName") String startName);

    List<String> queryAllEndNameByStartNameAndPinYinTablePinYin(@Param("key") String key,
                                                                @Param("startName") String startName);

}
