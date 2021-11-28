package com.embracesource.traffic.forecast.web;

import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.forecast.business.GisBiz;
import com.embracesource.traffic.forecast.dto.GISResultDTO;
import com.embracesource.traffic.forecast.dto.PercentageResultDTO;
import com.embracesource.traffic.forecast.dto.SelectDTO;
import com.embracesource.traffic.forecast.dto.SelectPercentageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "GIS接口")
@RequestMapping("/GIS")
public class ForecastController {
    @Resource
    GisBiz gisBiz;

    @ApiOperation("查询地图数据")
    @RequestMapping(value = "/selectGISInfo", method = RequestMethod.POST)
    public Result<GISResultDTO> selectGISInfo(@Valid SelectDTO selectDTO) {
        return gisBiz.getGisInfo(selectDTO);
    }

    @ApiOperation("查询百分比列表数据")
    @RequestMapping(value = "/selectPercentageInfo", method = RequestMethod.POST)
    public Result<PercentageResultDTO> selectPercentageInfo(@Valid SelectPercentageDTO selectPercentageDTO) {
        return gisBiz.getPercentageInfo(selectPercentageDTO);
    }

    @ApiOperation("查询起始站点名称")
    @RequestMapping(value = "/selectKey", method = RequestMethod.POST)
    public Result<List<String>> selectKey(@RequestParam(value = "key", required = true) String key,
                                          @RequestParam(value = "endName", required = false) String endName) {
        return gisBiz.selectKey(key, endName);
    }

    @ApiOperation("查询终点站点名称")
    @RequestMapping(value = "/selectEndKey", method = RequestMethod.POST)
    public Result<List<String>> selectEndKey(@RequestParam(value = "key", required = true) String key,
                                             @RequestParam(value = "startName", required = false) String startName) {
        return gisBiz.selectEndKey(key, startName);
    }


}
