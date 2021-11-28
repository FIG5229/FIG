package com.embracesource.traffic.datamanage.web;


import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.datamanage.business.StatusDataBiz;
import com.embracesource.traffic.datamanage.dto.StatusDataDTO;
import com.embracesource.traffic.datamanage.dto.StatusDataRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "状态数据controller", tags = {"状态数据接口"})
@RestController
@RequestMapping("/status/data/")
public class StatusDataController {

    @Autowired
    StatusDataBiz statusDataBiz;

    /**
     * 状态数据下面列表数据
     * @param statusDataRequestDTO
     * @return
     */
    @ApiOperation("状态数据展示接口")
    @PostMapping("/getStatusDataList")
    public Result<StatusDataDTO> getStatusDataList(@Valid StatusDataRequestDTO statusDataRequestDTO) {
        return statusDataBiz.getStatusDataList(statusDataRequestDTO);
    }


}

