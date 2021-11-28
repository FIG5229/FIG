package com.embracesource.traffic.screen.service.impl;

import com.embracesource.traffic.base.utils.DateUtil;
import com.embracesource.traffic.screen.dao.CarTypeErrorCountDao;
import com.embracesource.traffic.screen.domain.CarErrorTypeDictDo;
import com.embracesource.traffic.screen.dto.CountDataDTO;
import com.embracesource.traffic.screen.dto.CountInfoDTO;
import com.embracesource.traffic.screen.service.CarErrorTypeDictService;
import com.embracesource.traffic.screen.service.CarTypeErrorCountService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.embracesource.traffic.base.config.Constant.WEEK;

/**
 * @Author: daniel.liu
 * @Description:
 * @Date: create in 2021/5/21 15:23
 */
@Service
public class CarTypeErrorCountImpl implements CarTypeErrorCountService {

    @Resource
    private CarTypeErrorCountDao carTypeErrorCountDao;

    @Autowired
    private CarErrorTypeDictService carErrorTypeDictService;

    /**
     * 输入数据错误量
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<CountInfoDTO> countAbnormalDataVolume(Date startTime,Date endTime) {
        List<CountInfoDTO> res = carTypeErrorCountDao.countAbnormalDataVolume(startTime,endTime);
        for (int i =0;i<WEEK;i++){
            Date date = DateUtil.getZeroTime(-i);
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.FORMATDATE1);
            String time = sdf.format(date);
            String day = time.substring(5,10);
            List<String> labelList = res.stream().map(CountInfoDTO::getLabel).collect(Collectors.toList());
            if (!labelList.contains(day)){
                CountInfoDTO dto = new CountInfoDTO();
                dto.setLabel(day);
                dto.setCount(0);
                res.add(i,dto);
            }
        }
/*        res.stream().sorted(new Comparator<CountInfoDTO>() {
            @Override
            public int compare(CountInfoDTO o1, CountInfoDTO o2) {
                try {
                    Date d1 = DateUtils.parseDate(o1.getLabel(), "MM-dd");
                    Date d2 = DateUtils.parseDate(o2.getLabel(), "MM-dd");
                    return d2.compareTo(d1);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });*/
        return res;
    }

    @Override
    public long countAbnormalDataVolumeByDay(Date startTime, Date endTime) {
        long res = carTypeErrorCountDao.countAbnormalDataVolumeByDay(startTime,endTime);
        return res;
    }

    @Override
    public long countTotalDataVolume() {
        return carTypeErrorCountDao.countTotalDataVolume();
    }

    @Override
    public List<CountInfoDTO> getExceptionalDataInspectionRules() {
        List<CountInfoDTO> list = carTypeErrorCountDao.countExceptionalDataInspectionRules();
        List<CarErrorTypeDictDo> dicts  = carErrorTypeDictService.list();
        /*nums的下标+1  对应字典的值（errorCode）*/
        int[] nums = new int[dicts.size()];
        /*遍历没一个结果，对标签进行拆分再遍历，拆分的标签与字典的值遍历，相同则把count添加到num中*/
        list.forEach(item -> {
            String[] split = item.getLabel().split(",");
            for (String s : split) {
                for (CarErrorTypeDictDo dict : dicts) {
                    if (s.equals(dict.getErrorCode())){
                        nums[Integer.valueOf(dict.getErrorCode())-1]+=item.getCount();
                    }
                }
            }
        });
        List<CountInfoDTO> res= new ArrayList<>();
        /*把计算的所有值放到结果集中，并添加标签名称*/
        for(int i =0;i<dicts.size();i++) {
            CountInfoDTO map=new CountInfoDTO();
            map.setCount(nums[i]);
            for (CarErrorTypeDictDo dict : dicts) {
                if (dict.getErrorCode().equals(String.valueOf((i+1)))){
                    map.setLabel(dict.getErrorContent());
                }
            }
            res.add(map);
        }
        return res;
    }


}
