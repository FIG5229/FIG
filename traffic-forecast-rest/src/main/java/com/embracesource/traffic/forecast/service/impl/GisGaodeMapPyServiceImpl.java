package com.embracesource.traffic.forecast.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.embracesource.traffic.forecast.dao.GisGaodeMapPyDao;
import com.embracesource.traffic.forecast.service.GisGaodeMapPyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-10 上午 10:45
 * @description：
 * @version:
 */
@Service
@DS("pgsql")
public class GisGaodeMapPyServiceImpl implements GisGaodeMapPyService {
    @Resource
    GisGaodeMapPyDao gisGaodeMapPyDao;


    @Override
    public List<String> getListNames(String key, String endName) {
        if (StringUtils.isEmpty(endName)) {
            if (isChinese(key)) {
                return gisGaodeMapPyDao.queryAllNameByNameKeyTablePinYin(key);
            } else {
                return gisGaodeMapPyDao.queryAllNameByPYKeyTablePinYin(key.toLowerCase());
            }
        }
        if (isChinese(key)) {
            return gisGaodeMapPyDao.queryAllNameByEndNameAndNameKeyTablePinYin(key, endName);
        } else {
            return gisGaodeMapPyDao.queryAllNameByEndNameAndPYKeyTablePinYin(key.toLowerCase(), endName);
        }
    }

    @Override
    public List<String> getListNamesByStartStation(String key, String startName) {
        if (isChinese(key)) {
            if (StringUtils.isEmpty(startName)) {
                return gisGaodeMapPyDao.queryAllEndNameByNameKeyTablePinYin(key);
            }
            return gisGaodeMapPyDao.queryAllEndNameByStartNameAndNameTablePinYin(key, startName);
        }
        if (StringUtils.isEmpty(startName)) {
            return gisGaodeMapPyDao.queryAllEndNameByPYKeyTablePinYin(key.toLowerCase());
        }
        return gisGaodeMapPyDao.queryAllEndNameByStartNameAndPinYinTablePinYin(key.toLowerCase(), startName);
    }

    private boolean isChinese(String str) {
        if (str == null) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean isChinese(char c) {
        return c >= 0x4E00 && c <= 0x9FA5;
    }
}
