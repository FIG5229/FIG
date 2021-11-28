package com.embracesource.traffic.time.web;

import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.time.domain.ViewTimeLjDo;
import com.embracesource.traffic.time.dto.*;
import com.embracesource.traffic.time.service.ViewTimeCarInfoService;
import com.embracesource.traffic.time.service.ViewTimeErrorService;
import com.embracesource.traffic.time.service.ViewTimeLjService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@Api(value = "时间验证controller", tags = {"时间验证接口"})
@RestController
@RequestMapping("/time")
public class ViewTimeController {

    @Autowired
    private ViewTimeErrorService viewTimeErrorService;

    @Autowired
    private ViewTimeLjService viewTimeLjService;

    @Autowired
    ViewTimeCarInfoService viewTimeCarInfoService;

    @ApiOperation("获取日车辆数、日出发数、日到达数")
    @RequestMapping(value = "/getCarCount", method = RequestMethod.GET)
    public Result<GetCountDTO> getCarCount() {
        GetCountDTO getCount = viewTimeErrorService.getCarCount();
        return Result.ok(getCount);
    }

    @ApiOperation("获取准确率和异常率")
    @RequestMapping(value = "/getAccrate", method = RequestMethod.POST)
    public Result<AccrateDTO> getAccrate(TimeAccrateDTO timeAccrateDTO) {
        AccrateDTO accrate = viewTimeErrorService.getAccrate(timeAccrateDTO);
        if (accrate.getAccrateA().equals("0") && accrate.getAccrateB().equals("0")) {
            return Result.ok();
        } else {
            return Result.ok(accrate);
        }
    }

    @ApiOperation("某车站全路局比率")
    @RequestMapping(value = "/getAllLjAccrate", method = RequestMethod.POST)
    public Result getAllLjAccrate(AccrateStationDTO accrateStation) {
        String id = viewTimeErrorService.getId(accrateStation.getTime1(), accrateStation.getTime2(), accrateStation.getFlag(), accrateStation.getType());
        ViewTimeLjDo ljAccrate = viewTimeLjService.getAllLjAccrate(id, accrateStation.getStation());
        if (ljAccrate.getId().equals("00")) {
            return Result.ok("无");
        } else {
            return Result.ok(ljAccrate.getJAccrate());
        }
    }

    @ApiOperation("全站全路局比率")
    @RequestMapping(value = "/getAllStationAllLjAccrate", method = RequestMethod.POST)
    public Result<List<AccrateAllStationDTO>> getAllStationAllLjAccrate(AllTimeDTO allTimeDTO) {
        String id = viewTimeErrorService.getId(allTimeDTO.getTime1(), allTimeDTO.getTime2(), allTimeDTO.getFlag(), allTimeDTO.getType());
        List<AccrateAllStationDTO> list = viewTimeLjService.getAllStationAllLjAccrate(id);
        if (list == null || list.size() == 0) {
            return Result.ok();
        } else {
            return Result.ok(list);
        }
    }

    @ApiOperation("车站各路局比率")
    @RequestMapping(value = "/getLjAccrateList", method = RequestMethod.POST)
    public Result<List<TimeDTO>> getLjAccrateList(AccrateStationDTO accrateStation) {
        String id = viewTimeErrorService.getId(accrateStation.getTime1(), accrateStation.getTime2(), accrateStation.getFlag(), accrateStation.getType());
        List<TimeDTO> ljAccrateList = viewTimeLjService.getLjAccrateList(id, accrateStation.getStation());
        if (ljAccrateList == null || ljAccrateList.size() == 0) {
            return Result.ok();
        } else {
            return Result.ok(ljAccrateList);
        }
    }

    @ApiOperation("某路局车辆详细信息-分页")
    @RequestMapping(value = "/getTimeCareInfoByPage", method = RequestMethod.POST)
    public Result<PageDTO> getTimeCareInfoByPage(CarPageDTO carPageDTO) {
        String id = viewTimeErrorService.getId(carPageDTO.getTime1(), carPageDTO.getTime2(), carPageDTO.getFlag(), carPageDTO.getType());
        List<CarDTO> list = viewTimeCarInfoService.getTimeCareInfoByPage(carPageDTO.getLj(), carPageDTO.getLjName(), carPageDTO.getCurrentStation(), carPageDTO.getPageSize(), carPageDTO.getPage(), id);
        Collections.sort(list, new ListComparator());
        Integer pageCount = viewTimeCarInfoService.getTimeCareInfoPageCount(carPageDTO.getLj(), carPageDTO.getLjName(), carPageDTO.getCurrentStation(), carPageDTO.getPageSize(), id);
        if ((list == null || list.size() == 0) && (pageCount == null || pageCount == 0)) {
            return Result.ok(new PageDTO(0, null));
        } else {
            return Result.ok(new PageDTO(pageCount, list));
        }
    }


    @ApiOperation("当前站车辆详细信息")
    @RequestMapping(value = "/getCareInfoByStation", method = RequestMethod.POST)
    public Result<ForcastCarInfoDTO> getCareInfoByStation(PageTimeDTO pageTimeDTO) {
        String id = viewTimeErrorService.getId(pageTimeDTO.getTime1(), pageTimeDTO.getTime2(), pageTimeDTO.getFlag(), pageTimeDTO.getType());
        ForcastCarInfoDTO dto = viewTimeCarInfoService.getforecastCar(pageTimeDTO.getHitId(), id);
        return Result.ok(dto);
    }

// （上面是当前）
//编组站：当前+后面的预测(不要预测的第一条)
//卸车站：1）if 当前站=终点站，当前
//        2）否则：后面的预测

    @ApiOperation("获取站点重车数据")
    @RequestMapping(value = "/getHeavyVehiclesByStation", method = RequestMethod.POST)
    public Result<HeavyVehiclesResponseDTO> getHeavyVehiclesByStation(HeavyVehiclesRequestDTO heavyVehiclesRequestDTO) {
        HeavyVehiclesResponseDTO heavyVehiclesByStation = viewTimeCarInfoService.getHeavyVehiclesByStation(heavyVehiclesRequestDTO.getType(),
                heavyVehiclesRequestDTO.getStation());
        return Result.ok(heavyVehiclesByStation);
    }
}
