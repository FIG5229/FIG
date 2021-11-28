package com.embracesource.traffic.freighttrafficcontrast.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.embracesource.traffic.freighttrafficcontrast.Entity.PgCarResultInfoHistory;
import com.embracesource.traffic.freighttrafficcontrast.mapper.PgCarResultMapper;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@DS("crate")
public class LoadPgService extends ServiceImpl<PgCarResultMapper, PgCarResultInfoHistory> {

//    @Autowired
//    private CarResultInfoHistory carResultInfoHistory
    @Autowired
    private IsHitService is_hit;

    @Autowired
    private OfflineResultService1 offlineResultService1;

    private final Logger logger = LoggerFactory.getLogger(LoadPgService.class);

/*

    public LinkedHashMap<String, String> getAllPath(String carNo,String startStn,String endStn) throws Exception{
        List<String> realPath = new ArrayList<>();
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("StartTime",null);
        String dqz = null;
        String lcdd = null;
        String lccf = null;
        String chsb = null;
        String isHit = null;
        String tlsj = null;
        String yjdd= null;
        String yjcf=null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        QueryWrapper<PgCarResultInfoHistory> wrapper1 = new QueryWrapper<>();
        QueryWrapper<PgCarResultInfoHistory> wrapper2 = new QueryWrapper<>();


        wrapper1.lambda()
                .eq(PgCarResultInfoHistory::getCarNo,carNo)
                .ne(PgCarResultInfoHistory::getReportType,"XCDD")
                .ne(PgCarResultInfoHistory::getPathTime,"null")
                .and(c -> c.ne(PgCarResultInfoHistory::getStartStation,startStn)
                        .or().
                                ne(PgCarResultInfoHistory::getEndStation,endStn))
                .orderByDesc(PgCarResultInfoHistory::getPathTime);
//                            .eq(PgCarResultInfoHistory::getStartStation,startStn)
//                            .eq(PgCarResultInfoHistory::getEndStation,endStn)
//                            .eq(PgCarResultInfoHistory::getCurrentStation,startStn)



        List<PgCarResultInfoHistory> pgCarResultInfoHistories = this.baseMapper.selectList(wrapper1);
        PgCarResultInfoHistory startTime = null;
        if(pgCarResultInfoHistories.size()!=0){
             startTime =pgCarResultInfoHistories.get(0);
        }

        if(startTime==null){
            return map;
        } else {
            String starttime = startTime.getPathTime();
            map.put("StartTime",starttime);
            System.out.println(starttime);
            wrapper2.lambda().eq(PgCarResultInfoHistory::getCarNo,carNo)
                    .eq(PgCarResultInfoHistory::getStartStation,startStn)
                    .eq(PgCarResultInfoHistory::getEndStation,endStn)
                    .gt(PgCarResultInfoHistory::getPathTime,starttime)
                    .ne(PgCarResultInfoHistory::getPathTime,"null")
                    .orderByAsc(PgCarResultInfoHistory::getPathTime);
            List<PgCarResultInfoHistory> cars = this.baseMapper.selectList(wrapper2);

            for (PgCarResultInfoHistory car:cars) {
//                System.out.println("-----"+System.currentTimeMillis()+"--------");
                if(!car.getCurrentStation().equals(dqz)){
                    dqz = car.getCurrentStation();
                    lccf=null;
                    lcdd=null;
                    chsb=null;
                    tlsj=null;
                    isHit=is_hit.getHitResult(car,starttime);
                }

//                System.out.println("+++++"+System.currentTimeMillis()+"++++++");

                if("1".equals(isHit) && tlsj==null){
//                    Double tlsj1 = offlineResultService.getTlsj(startStn,dqz,endStn);
                    List<String> nodes_info = Arrays.asList(car.getNodesInfo().replaceAll(",", "|").split("\\|"));
                    int ddIndex = nodes_info.indexOf(dqz);
                    int fcIndex = nodes_info.lastIndexOf(dqz);
                    if(ddIndex > 0){
                        yjdd=nodes_info.get(ddIndex-1);
                    }
                    if(fcIndex < nodes_info.size()-1){
                        yjcf=nodes_info.get(fcIndex+1);
                    }
                    if (StringUtil.isNotEmpty(yjdd)){

                    }
                    if (StringUtil.isNotEmpty(yjcf) && StringUtil.isNotEmpty(yjdd)){

                        Date cfTime = sdf.parse(yjcf);

                        Date ddTime = sdf.parse(yjdd);

                        long l = (cfTime.getTime() - ddTime.getTime()) / (1000 * 60);
                        tlsj=l+"";
                    }
                }

                if("LCCF".equals(car.getReportType()) || "ZCBG".equals(car.getReportType())){
                    lccf=car.getPathTime();
                }
                if("LCDD".equals(car.getReportType())  || "XCBG".equals(car.getReportType())){
                    lcdd=car.getPathTime();
                }
                if("CHSB".equals(car.getReportType())){
                    chsb=car.getPathTime();
                }

                map.put(dqz,lccf+","+lcdd+","+chsb+","+isHit+","+tlsj);

                if(dqz.equals(endStn)){
                    break;
                }
            }

//            List<String> collect = map.keySet().stream().map(new Function<String, String>() {
//                @Override
//                public String apply(String s) {
//                    return s + "," + map.get(s);
//                }
//            }).collect(Collectors.toList());
//
//            for(int i=0,size=collect.size()-1;i<size;i++){
//                String [] arr=collect.get(i).split(",");
//                if(arr[4].equals("1")){
//
//                    Double tlsj1 = offlineResultService.getTlsj(startStn, arr[0], endStn,collect.get(i+1).split(",")[0]);
//                    map.put(arr[0],arr[1]+","+arr[2]+","+arr[3]+","+arr[4]+","+tlsj1);
////                    collect.add(i,collect.get(i)+tlsj1);
//
//                }else{
//                    continue;
//                }
//            }

        }

        return map;
    }
*/

    public LinkedHashMap<String, String> getAllPath(String carNo,String startStn,String endStn) throws Exception{
        List<String> realPath = new ArrayList<>();
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("StartTime",null);
        String dqz = null;
        String lcdd = null;
        String lccf = null;
        String chsb = null;
        String isHit = null;
        String tlsj = null;
        String yjcf=null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyMMddHHmm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd HH:mm");
        QueryWrapper<PgCarResultInfoHistory> wrapper1 = new QueryWrapper<>();
        QueryWrapper<PgCarResultInfoHistory> wrapper2 = new QueryWrapper<>();


        wrapper1.lambda().eq(PgCarResultInfoHistory::getCarNo,carNo)
                .ne(PgCarResultInfoHistory::getReportType,"XCDD")
                .ne(PgCarResultInfoHistory::getPathTime,"null")
                .and(c -> c.ne(PgCarResultInfoHistory::getStartStation,startStn).or().ne(PgCarResultInfoHistory::getEndStation,endStn))
                .orderByDesc(PgCarResultInfoHistory::getPathTime);
//                            .eq(PgCarResultInfoHistory::getStartStation,startStn)
//                            .eq(PgCarResultInfoHistory::getEndStation,endStn)
//                            .eq(PgCarResultInfoHistory::getCurrentStation,startStn)



        List<PgCarResultInfoHistory> pgCarResultInfoHistories = this.baseMapper.selectList(wrapper1);
        PgCarResultInfoHistory startTime = null;
        if(pgCarResultInfoHistories.size()!=0){
            startTime =pgCarResultInfoHistories.get(0);
        }

        if(startTime==null){
            return map;
        } else {
            String starttime = startTime.getPathTime();
            map.put("StartTime",starttime);
            logger.info(starttime);
            wrapper2.lambda().eq(PgCarResultInfoHistory::getCarNo,carNo)
                    .eq(PgCarResultInfoHistory::getStartStation,startStn)
                    .eq(PgCarResultInfoHistory::getEndStation,endStn)
                    .gt(PgCarResultInfoHistory::getPathTime,starttime)
                    .ne(PgCarResultInfoHistory::getPathTime,"null")
                    .orderByAsc(PgCarResultInfoHistory::getPathTime);
            List<PgCarResultInfoHistory> cars = this.baseMapper.selectList(wrapper2);

            for (PgCarResultInfoHistory car:cars) {
//                System.out.println("-----"+System.currentTimeMillis()+"--------");
                if(!car.getCurrentStation().equals(dqz)){
                    dqz = car.getCurrentStation();
                    lccf=null;
                    lcdd=null;
                    tlsj=null;
                    isHit=is_hit.getHitResult(car,starttime);
                }

//                System.out.println("+++++"+System.currentTimeMillis()+"++++++");

//                if(isHit.equals("1") && tlsj==null){
                if(car.getResultSource().equals("1") && car.getReportType().equals("LCDD")){
//                    System.out.println(dqz);
//                    System.out.println(car.getNodesInfo());
//                    Double tlsj1 = offlineResultService.getTlsj(startStn,dqz,endStn);
                    List<String> nodes_info = Arrays.asList(car.getNodesInfo().replaceAll(",", "|").split("\\|"));

                    int fcIndex = nodes_info.lastIndexOf(dqz);
                    if(fcIndex < nodes_info.size()-1){
                        yjcf=nodes_info.get(fcIndex+1);
                        logger.info(yjcf);
                    }
                    Date cfTime = sdf.parse(yjcf);
                    Date ddTime = sdf1.parse(car.getPathTime());



                    long l = (cfTime.getTime() - ddTime.getTime()) / (1000 * 60);
                    tlsj=l+"";
                    chsb=tlsj;
                }else if(car.getResultSource().equals("0")){
                    chsb="兰州径路推算";

                }
//                System.out.println("*****"+System.currentTimeMillis()+"*********");

                if(car.getReportType().equals("LCCF") || car.getReportType().equals("ZCBG")){
                    lccf=car.getPathTime();
                }
                if(car.getReportType().equals("LCDD")  || car.getReportType().equals("XCBG")){
                    lcdd=car.getPathTime();
                }
//                if(car.getReportType().equals("CHSB")){
//                    chsb=car.getPathTime();
//                }

                map.put(dqz,lccf+","+lcdd+","+chsb+","+isHit+","+tlsj);

                if(dqz.equals(endStn)){
                    break;
                }
            }

//            List<String> collect = map.keySet().stream().map(new Function<String, String>() {
//                @Override
//                public String apply(String s) {
//                    return s + "," + map.get(s);
//                }
//            }).collect(Collectors.toList());
//
//            for(int i=0,size=collect.size()-1;i<size;i++){
//                String [] arr=collect.get(i).split(",");
//                if(arr[4].equals("1")){
//
//                    Double tlsj1 = offlineResultService.getTlsj(startStn, arr[0], endStn,collect.get(i+1).split(",")[0]);
//                    map.put(arr[0],arr[1]+","+arr[2]+","+arr[3]+","+arr[4]+","+tlsj1);
////                    collect.add(i,collect.get(i)+tlsj1);
//
//                }else{
//                    continue;
//                }
//            }

        }

        return map;
    }

    public HashMap<String,String> getStartAndEnd(String carNo) {

        HashMap<String, String> startAndEnd = new HashMap<>();
        QueryWrapper<PgCarResultInfoHistory> wrapper = new QueryWrapper<>();

        wrapper.lambda().eq(PgCarResultInfoHistory::getCarNo,carNo)
                .ne(PgCarResultInfoHistory::getPathTime,"null")
                .orderByDesc(PgCarResultInfoHistory::getPathTime);


        List<PgCarResultInfoHistory> pgCarResultInfoHistories = this.baseMapper.selectList(wrapper);

        if (pgCarResultInfoHistories.size()==0){
            return startAndEnd;
        }

        PgCarResultInfoHistory carResultInfoHistory = pgCarResultInfoHistories.get(0);

        String startStation = carResultInfoHistory.getStartStation();
        String endStation = carResultInfoHistory.getEndStation();

        startAndEnd.put("start",startStation);
        startAndEnd.put("end",endStation);

        return startAndEnd;

    }




    public LinkedHashMap<String, String> getAllPath1(String carNo,String startStn,String endStn) throws Exception{
        List<String> realPath = new ArrayList<>();
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("StartTime",null);
        String dqz = null;
        String lcdd = null;
        String lccf = null;
        String chsb = null;
        String isHit = null;
        String tlsj = null;
        QueryWrapper<PgCarResultInfoHistory> wrapper1 = new QueryWrapper<>();
        QueryWrapper<PgCarResultInfoHistory> wrapper2 = new QueryWrapper<>();


        wrapper1.lambda().eq(PgCarResultInfoHistory::getCarNo,carNo)
                .ne(PgCarResultInfoHistory::getReportType,"XCDD")
                .ne(PgCarResultInfoHistory::getPathTime,"null")
                .and(c -> c.ne(PgCarResultInfoHistory::getStartStation,startStn).or().ne(PgCarResultInfoHistory::getEndStation,endStn))
                .orderByDesc(PgCarResultInfoHistory::getPathTime);
//                            .eq(PgCarResultInfoHistory::getStartStation,startStn)
//                            .eq(PgCarResultInfoHistory::getEndStation,endStn)
//                            .eq(PgCarResultInfoHistory::getCurrentStation,startStn)



        List<PgCarResultInfoHistory> pgCarResultInfoHistories = this.baseMapper.selectList(wrapper1);
        PgCarResultInfoHistory startTime = null;
        if(pgCarResultInfoHistories.size()!=0){
            startTime =pgCarResultInfoHistories.get(0);
        }

        if(startTime==null){
            return map;
        } else {
            String starttime = startTime.getPathTime();
            map.put("StartTime",starttime);
            System.out.println(starttime);
            wrapper2.lambda().eq(PgCarResultInfoHistory::getCarNo,carNo)
                    .eq(PgCarResultInfoHistory::getStartStation,startStn)
                    .eq(PgCarResultInfoHistory::getEndStation,endStn)
                    .gt(PgCarResultInfoHistory::getPathTime,starttime)
                    .ne(PgCarResultInfoHistory::getPathTime,"null")
                    .orderByAsc(PgCarResultInfoHistory::getPathTime);
            List<PgCarResultInfoHistory> cars = this.baseMapper.selectList(wrapper2);

            for (PgCarResultInfoHistory car:cars) {
//                System.out.println("-----"+System.currentTimeMillis()+"--------");
                if(!car.getCurrentStation().equals(dqz)){
                    dqz = car.getCurrentStation();
                    lccf=null;
                    lcdd=null;
                    chsb=null;
                    tlsj=null;
                    isHit=is_hit.getHitResult(car,starttime);
                }

//                System.out.println("+++++"+System.currentTimeMillis()+"++++++");

                if(isHit.equals("1") && tlsj==null){
                    Double tlsj1 = offlineResultService1.getTlsj(startStn,dqz,endStn);
                    tlsj=tlsj1+"";
                }
//                System.out.println("*****"+System.currentTimeMillis()+"*********");

                if(car.getReportType().equals("LCCF") || car.getReportType().equals("ZCBG")){
                    lccf=car.getPathTime();
                }
                if(car.getReportType().equals("LCDD")  || car.getReportType().equals("XCBG")){
                    lcdd=car.getPathTime();
                }
                /*if(car.getReportType().equals("CHSB")){
                    chsb=car.getPathTime();
                }*/

                map.put(dqz,lccf+","+lcdd+","+chsb+","+isHit+","+tlsj);

                if(dqz.equals(endStn)){
                    break;
                }
            }

//            List<String> collect = map.keySet().stream().map(new Function<String, String>() {
//                @Override
//                public String apply(String s) {
//                    return s + "," + map.get(s);
//                }
//            }).collect(Collectors.toList());
//
//            for(int i=0,size=collect.size()-1;i<size;i++){
//                String [] arr=collect.get(i).split(",");
//                if(arr[4].equals("1")){
//
//                    Double tlsj1 = offlineResultService.getTlsj(startStn, arr[0], endStn,collect.get(i+1).split(",")[0]);
//                    map.put(arr[0],arr[1]+","+arr[2]+","+arr[3]+","+arr[4]+","+tlsj1);
////                    collect.add(i,collect.get(i)+tlsj1);
//
//                }else{
//                    continue;
//                }
//            }

        }

        return map;
    }
}
