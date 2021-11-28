package com.embracesource.traffic.time.service;

import com.embracesource.traffic.time.domain.ViewTimeLjDo;
import com.embracesource.traffic.time.domain.ViewTimeLjcomeDo;
import com.embracesource.traffic.time.dto.AccrateAllStationDTO;
import com.embracesource.traffic.time.dto.TimeDTO;

import java.util.List;

public interface ViewTimeLjService {
    ViewTimeLjcomeDo getLjAccrate(String id, String station, String ljName);
    ViewTimeLjDo getAllLjAccrate(String id, String station);
    List<AccrateAllStationDTO> getAllStationAllLjAccrate(String id);
    List<TimeDTO> getLjAccrateList(String id, String station);
}
