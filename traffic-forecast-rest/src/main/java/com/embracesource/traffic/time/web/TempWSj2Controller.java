package com.embracesource.traffic.time.web;

import com.embracesource.traffic.base.utils.DateUtil;
import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.time.domain.TempWSj2DO;
import com.embracesource.traffic.time.dto.TempWSj2ResponseDTO;
import com.embracesource.traffic.time.service.TempWSj2Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "时间态势controller", tags = {"时间态势接口"})
@RestController
@RequestMapping("/TempWSj2")
public class TempWSj2Controller {

    @Autowired
    TempWSj2Service tempWSj2Service;

    @ApiOperation("获取区间时间统计数量")
    @RequestMapping(value = "/selectQjTime", method = RequestMethod.POST)
    public Result selectQjTime(){
        return Result.ok(tempWSj2Service.selectQjTime());
    }

    @ApiOperation("获取停留时间统计数量")
    @RequestMapping(value = "/selectTlTime", method = RequestMethod.POST)
    public Result selectTlTime(){
        return Result.ok(tempWSj2Service.selectTlTime());
    }

    @ApiOperation("获取近一周时间分布")
    @RequestMapping(value = "/selectLastWeek", method = RequestMethod.POST)
    public Result selectLastWeek(){
        return Result.ok(tempWSj2Service.selectLastWeek());
    }

    @ApiOperation("列表数据")
    @RequestMapping(value = "/selectAllMessage", method = RequestMethod.POST)
    public Result selectAllMessage(){
        return Result.ok(tempWSj2Service.selectAllMessage());
    }



}
