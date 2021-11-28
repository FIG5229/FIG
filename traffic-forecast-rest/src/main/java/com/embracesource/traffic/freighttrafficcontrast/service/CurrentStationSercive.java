package com.embracesource.traffic.freighttrafficcontrast.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.embracesource.traffic.base.utils.StaticDataLoader;
import com.embracesource.traffic.freighttrafficcontrast.Entity.CarResultInfoHistory;
import com.embracesource.traffic.freighttrafficcontrast.mapper.CarResultMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.embracesource.traffic.base.utils.StaticDataLoader.ZMZD;


@Service
@DS("crate")
public class CurrentStationSercive extends ServiceImpl<CarResultMapper, CarResultInfoHistory> {



    public HashMap<String, List<String[]>> selectPathByCurrentStation(String currStn, String carNo, String startStn, String endStn,String startTime) throws ParseException {
        List<String[]> list = new ArrayList<>();
        String endTime = null;
        String zdz = null;
        String resultSource = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CarResultInfoHistory carResultInfoHistory= null;


        QueryWrapper<CarResultInfoHistory> wrapper = new QueryWrapper<>();
        QueryWrapper<CarResultInfoHistory> wrapper1 = new QueryWrapper<>();

        wrapper.lambda().eq(CarResultInfoHistory::getCarNo, carNo)
                .eq(CarResultInfoHistory::getStartStation, startStn)
                .eq(CarResultInfoHistory::getCurrentStation, currStn)
                .eq(CarResultInfoHistory::getEndStation, endStn)
                .ge(CarResultInfoHistory::getPathTime,startTime)
                .orderByDesc(CarResultInfoHistory::getPathTime);

        List<CarResultInfoHistory> carResultInfoHistories = this.baseMapper.selectList(wrapper);

        if (carResultInfoHistories.size() != 0) {

            for (CarResultInfoHistory car : carResultInfoHistories) {
                resultSource=car.getResultSource();
                if (resultSource.equals("1")){
                    carResultInfoHistory=car;
                    break;
                }
                carResultInfoHistory=car;
            }


            if (resultSource.equals("1")) {
                //SBF,2020-07-03 19:38:00,17,2020-07-03 20:37:00,LFF|LFF,2020-07-03 20:37:00,77,2020-07-03 22:33:00,HOK|HOK,2020-07-03 22:33:00,167,2020-07-04 04:11:00,XXF
                list = Arrays.asList(carResultInfoHistory.getNodesInfo().split("\\|")).stream().map((String s) -> {
                    String[] arr = s.split(",");
                    String[] arr1 = new String[6];
                    for (int i = 0; i < arr.length; i++) {
                        arr1[i] = arr[i];
                    }
                    return arr1;
                }).collect(Collectors.toList());
                endTime = list.get(list.size() - 1)[3];
                zdz = list.get(list.size() - 1)[4];

                for (int i = list.size() - 1; i >= 1; i--) {
                    list.get(i)[0] = ZMZD.get(list.get(i)[0]);
                    list.get(i)[3] = list.get(i - 1)[3];

                    long time1 = sdf.parse(list.get(i)[1]).getTime();
                    long time2 = sdf.parse(list.get(i)[3]).getTime();

                    String time3 = (time1 - time2) / (1000 * 60) + "";


                    list.get(i)[5] = time3;
                }
                list.get(0)[0] = ZMZD.get(list.get(0)[0]);
                list.get(0)[3] = "";
                list.get(0)[5] = "0";
                String[] e = {StaticDataLoader.ZMZD.get(zdz),"---------- --:--:--","--",endTime,"--","--"};
                list.add(e);


//                String[] strings = list.get(list.size() - 1);
//                zdz = strings[strings.length - 1];
            } else {
                //UBK,0,UBK,0,2007032302,2007032302|UCK,6,UCK,0,2007032312,2007032312|FLK,12,FLK,0,2007032333,2007032333|SIK,15,SIK,0,2007032359,2007032359|YIK,15,YIK,0,2007040025,2007040025|TTK,17,TTK,0,2007040055,2007040055|LMK,17,LMK,0,2007040125,2007040125|YPK,25,YPK,0,2007040208,2007040208|MZK,19,MZK,0,2007040241,2007040241|JCK,10,JCK,0,2007040258,2007040258|HIK,27,HIK,0,2007040345,2007040345|SUK,22,SUK,0,2007040423,2007040423|LRK,15,LRK,0,2007040449,2007040449|JYK,21,JYK,0,2007040526,2007040526|DVK,18,DVK,0,2007040557,2007040557|JUK,7,JUK,0,2007040609,2007040609|JJK,14,JJK,0,2007040633,2007040633|JIK,10,JIK,0,2007040650,2007040650|YKK,19,YKK,0,2007040723,2007040723|NWK,10,NWK,0,2007040740,2007040740|YZK,3,YZK,0,2007040745,2007040745|YDK,4,YDK,0,2007040752,2007040752
                list = Arrays.asList(carResultInfoHistory.getNodesInfo().split("\\|")).stream().map((String s) -> {
                    String[] arr = s.split(",");
                    String s1 = ZMZD.get(arr[0]);
                    String[] arr1 = {s1, arr[4], arr[1], arr[5], arr[2], "0"};
                    return arr1;
//                    return s;
                }).collect(Collectors.toList());
                endTime = list.get(list.size() - 1)[5];
                zdz = list.get(list.size() - 1)[0];
//                if(list.size()==1){
//                    list=null;
//                }
//                String[] strings = list.get(list.size() - 1);
//                list.remove(list.size() - 1);
//                zdz = strings[0];
            }
        } else {
            list = null;
        }

        HashMap<String, List<String[]>> map = new HashMap<>();
        map.put(zdz + "," + endTime + "," + resultSource, list);

        return map;
    }

}
