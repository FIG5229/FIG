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

@Service
@DS("crate")
public class LoadCrateService extends ServiceImpl<CarResultMapper, CarResultInfoHistory> {


    public HashMap<String, List<String[]>> generatePath(String CarNo,String startStn,String endStn) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String[]> list = new ArrayList<>();
        List<String[]> newList = new ArrayList<>();


        String endTime = null;
        String resultSource = null;

        QueryWrapper<CarResultInfoHistory> wrapper = new QueryWrapper<>();
        QueryWrapper<CarResultInfoHistory> wrapper1 = new QueryWrapper<>();


        wrapper1.lambda().eq(CarResultInfoHistory::getCarNo,CarNo)
                .eq(CarResultInfoHistory::getStartStation,startStn)
                .eq(CarResultInfoHistory::getEndStation,endStn)
//                .eq(CarResultInfoHistory::getCurrentStation,startStation)
                .ne(CarResultInfoHistory::getPathTime,"null")
                .orderByDesc(CarResultInfoHistory::getPathTime).last("limit 1");



        CarResultInfoHistory carResultInfoHistory = this.baseMapper.selectOne(wrapper1);
        if(!(carResultInfoHistory==null)) {
            resultSource = carResultInfoHistory.getResultSource();
//        String nodesInfo = carResultInfoHistory.getNodesInfo();
//
//        builder.append(carResultInfoHistory.getNodesInfo());
//        List<String[]> list = Arrays.asList(carResultInfoHistory.getNodesInfo().split("\\|")).stream().map((String s) -> {
//            String[] arr = s.split(",");
//            System.out.println(arr.toString());
//            return arr;
//        }).collect(Collectors.toList());


            if ("1".equals(resultSource)) {
                //SBF,2020-07-03 19:38:00,17,2020-07-03 20:37:00,LFF|LFF,2020-07-03 20:37:00,77,2020-07-03 22:33:00,HOK|HOK,2020-07-03 22:33:00,167,2020-07-04 04:11:00,XXF
                list = Arrays.asList(carResultInfoHistory.getNodesInfo().split("\\|")).stream().map((String s) -> {
                    String[] arr = s.split(",");
                    String[] arr1 = new String[6];
                    for (int i = 0; i < arr.length ; i++) {
                        arr1[i]=arr[i];
                    }
                    return arr1;
                }).collect(Collectors.toList());
                endTime = list.get(list.size()-1)[3];
                endStn = list.get(list.size()-1)[4];
//                String[] strings;
//                String[] strings1;
//
//
//                for (int i = 0; i < list.size(); i++) {
//                    if (i==0){
//                        strings = list.get(i);
//                        String s = ZMZD.get(strings[0]);
//                        String[] strings2={s,"",strings[2],strings[1],""};
//                        newList.add(strings2);
//
//                    }else {
//                        strings = list.get(i);
//                        strings1 = list.get(i - 1);
//                        String s = ZMZD.get(strings[0]);
//                        long time1 = sdf.parse(strings1[3]).getTime();
//                        long time2 = sdf.parse(strings[1]).getTime();
//                        String[] strings2={s,strings1[3],strings[2],strings[1],(time2-time1)/(1000*60)+""};
//                        newList.add(strings2);
//                    }
//                }


                for (int i = list.size()-1;i>=1;i--){
                    list.get(i)[0]= StaticDataLoader.ZMZD.get(list.get(i)[0]);
                    list.get(i)[3]=list.get(i-1)[3];

                    long time1 = sdf.parse(list.get(i)[1]).getTime();
                    long time2 = sdf.parse(list.get(i)[3]).getTime();

                    String time3 = (time1 - time2) / (1000 * 60) + "";


                    list.get(i)[5]=time3;
                }

                String[] e = {StaticDataLoader.ZMZD.get(endStn),"---------- --:--:--","--",endTime,"--","--"};
                list.add(e);


                list.get(0)[0]= StaticDataLoader.ZMZD.get(list.get(0)[0]);
                list.get(0)[3]="";
                list.get(0)[5]="0";




//                String[] strings = list.get(list.size() - 1);
//                zdz = strings[strings.length - 1];
            } else {

                //UBK,0,UBK,0,2007032302,2007032302|UCK,6,UCK,0,2007032312,2007032312|FLK,12,FLK,0,2007032333,2007032333|SIK,15,SIK,0,2007032359,2007032359|YIK,15,YIK,0,2007040025,2007040025|TTK,17,TTK,0,2007040055,2007040055|LMK,17,LMK,0,2007040125,2007040125|YPK,25,YPK,0,2007040208,2007040208|MZK,19,MZK,0,2007040241,2007040241|JCK,10,JCK,0,2007040258,2007040258|HIK,27,HIK,0,2007040345,2007040345|SUK,22,SUK,0,2007040423,2007040423|LRK,15,LRK,0,2007040449,2007040449|JYK,21,JYK,0,2007040526,2007040526|DVK,18,DVK,0,2007040557,2007040557|JUK,7,JUK,0,2007040609,2007040609|JJK,14,JJK,0,2007040633,2007040633|JIK,10,JIK,0,2007040650,2007040650|YKK,19,YKK,0,2007040723,2007040723|NWK,10,NWK,0,2007040740,2007040740|YZK,3,YZK,0,2007040745,2007040745|YDK,4,YDK,0,2007040752,2007040752
                list = Arrays.asList(carResultInfoHistory.getNodesInfo().split("\\|")).stream().map((String s) -> {
                    String[] arr = s.split(",");
                    String s1 = StaticDataLoader.ZMZD.get(arr[0]);
                    String[] arr1 = {s1, arr[4], arr[1], arr[5],arr[2],"0"};
                    return arr1;
//                    return s;
                }).collect(Collectors.toList());

                endTime = list.get(list.size()-1)[5];

//                if(list.size()==1){
//                    list=null;
//                }

//                String[] strings = list.get(list.size() - 1);
//                list.remove(list.size() - 1);
//                zdz = strings[0];
            }
        }

        HashMap<String, List<String[]>>map = new HashMap<>();
        map.put(endTime+","+resultSource,list);

        return map;
    }

}
