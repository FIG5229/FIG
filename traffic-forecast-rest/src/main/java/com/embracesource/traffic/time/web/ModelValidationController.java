package com.embracesource.traffic.time.web;

import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.time.business.ModelValidationBiz;
import com.embracesource.traffic.time.domain.HitRate;
import com.embracesource.traffic.time.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@Api(value = "模型验证controller", tags = {"模型验证接口"})
@RestController
@RequestMapping("/model/validation/")
public class ModelValidationController {

    @Autowired
    ModelValidationBiz modelValidationBiz;

    /**
     * 模型验证-规则修正上面列表数据
     * @param modelValidationRequestDTO
     * @return
     */
    @ApiOperation("模型验证展示接口")
    @PostMapping("/getRuleAmendmentList")
    public Result<ModelValidationDTO> getRuleAmendmentList(@Valid ModelValidationRequestDTO modelValidationRequestDTO) {
        return modelValidationBiz.getModelValidation(modelValidationRequestDTO);
    }

    /**
     * 模型验证-规则检验-命中率  getHitRate
     */

    @ApiOperation("模型验证-命中率展示接口")
    @PostMapping("/getHitRateList")
    public Result<List<HitRate>> getHitRateList() {
        return modelValidationBiz.getHitRateList();
    }


    /**
     * 模型验证-规则检验-时间偏差加大区间柱状图
     */
    @ApiOperation("模型验证-区间柱状图展示接口")
    @PostMapping("/getSectionList")
    public Result<List<SectionResponseDTO>> getSectionList() {
        return modelValidationBiz.getSectionList();
    }


    /**
     * 模型验证-规则检验-时间偏差加大车站柱状图
     */
    @ApiOperation("模型验证-车站柱状图展示接口")
    @PostMapping("/getStationList")
    public Result<List<StationResponseDTO>> getStationList() {
        return modelValidationBiz.getStationList();
    }


    /**
     * 模型验证-规则检验-下面列表
     */
    @ApiOperation("模型验证-规则检验-下面列表展示接口")
    @PostMapping("/getBelowShowList")
    public Result<ModelValidationDTO> getBelowShowList() {
        return null;
    }


}
