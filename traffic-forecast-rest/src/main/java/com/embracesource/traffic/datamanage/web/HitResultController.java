package com.embracesource.traffic.datamanage.web;

import com.embracesource.traffic.base.pageHelper.PageInfo;
import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.datamanage.business.HitResultBiz;
import com.embracesource.traffic.datamanage.dto.HitResultRequestDTO;
import com.embracesource.traffic.datamanage.dto.HitResultResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: traffic-forecast
 * @description: 命中数据controller层
 * @author: kevin.jia
 * @create: 2021-05-24 09:52
 **/

@RestController
@Api(value = "命中数据controller", tags = {"命中数据接口"})
@RequestMapping("/hitResult")
public class HitResultController {

    @Autowired
    HitResultBiz hitResultBiz;

    @ApiOperation("命中数据展示接口")
    @RequestMapping(value = "/getHitResultList", method = RequestMethod.POST)
    public Result<PageInfo<HitResultResponseDTO>> getHitResultList(HitResultRequestDTO hitResultRequestDTO){
        return hitResultBiz.getHitResultList(hitResultRequestDTO);
    }
}
