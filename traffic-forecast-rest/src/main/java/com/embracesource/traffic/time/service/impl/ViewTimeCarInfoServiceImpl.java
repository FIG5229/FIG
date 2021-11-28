package com.embracesource.traffic.time.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.embracesource.traffic.base.utils.DateUtil;
import com.embracesource.traffic.time.dao.ViewTempHitResultDao;
import com.embracesource.traffic.time.dao.ViewTimeCarInfoDao;
import com.embracesource.traffic.time.dao.XczSjCntDao;
import com.embracesource.traffic.time.dao.XczYjCntDao;
import com.embracesource.traffic.time.domain.ViewTempHitResultDO;
import com.embracesource.traffic.time.domain.ViewTimeCarInfoDo;
import com.embracesource.traffic.time.dto.*;
import com.embracesource.traffic.time.service.ViewTimeCarInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
@DS(value = "pgsql")
public class ViewTimeCarInfoServiceImpl implements ViewTimeCarInfoService {

    @Autowired
    ViewTimeCarInfoDao viewTimeCarInfoDao;

    @Autowired
    ViewTempHitResultDao viewTempHitResultDao;

    @Autowired
    XczYjCntDao xczYjCntDao;

    @Autowired
    XczSjCntDao xczSjCntDao;

    private List<CarDTO> getCars(List<ViewTimeCarInfoDo> timeCareInfo) {
        List<CarDTO> cars = new ArrayList<CarDTO>();
        if (timeCareInfo != null && timeCareInfo.size() > 0) {
            for (ViewTimeCarInfoDo timecar : timeCareInfo) {
                Long time = Long.parseLong(timecar.getReportTime());
                FastDateFormat instance = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(time);
                String format = instance.format(calendar.getTime());
                String repoType = "";
                String flag = "";
                if(timecar.getReportType()==null || timecar.getReportType().trim().equals("")){
                    repoType = "";
                }else{
                    repoType = timecar.getReportType();
                }
                if(timecar.getIsWeekForecast()==null || timecar.getIsWeekForecast().trim().equals("")){
                    flag = "";
                }else{
                    flag = timecar.getIsWeekForecast();
                }


                cars.add(new CarDTO(timecar.getHitId(), timecar.getSjbj(), timecar.getCarNo(), timecar.getStartStation(), timecar.getCurrentStation(), timecar.getEndStation(), timecar.getYjCfTime(), timecar.getYjDdTime(), format,repoType,flag));
            }

        }

        return cars;
    }

//    @Override
//    public List<CarDTO> getTimeCareInfo(String lj, String ljName, String currentStation, String time) {
//        List<ViewTimeCarInfoDo> timeCareInfo = viewTimeCarInfoDao.getTimeCareInfo(lj, ljName, currentStation, time);
//        return getCars(timeCareInfo);
//
//    }

    @Override
    public List<CarDTO> getTimeCareInfoByPage(String lj, String ljName, String currentStation, String pageSize, String page, String time) {
        List<ViewTimeCarInfoDo> timeCareInfo = new ArrayList<ViewTimeCarInfoDo>();
        Integer getPageSize = Integer.parseInt(pageSize);

        Integer newPage = 0;
        Integer page_t = (Integer.parseInt(page) - 1);
        if (page_t == 0L) {
            newPage = 0;
        } else {
            newPage = page_t * getPageSize;
        }
        timeCareInfo = viewTimeCarInfoDao.getTimeCareInfoByPage(lj, ljName, currentStation, getPageSize, newPage, time);

        return getCars(timeCareInfo);
    }

    //修改：原来返回总页数，现在返回总条数
    @Override
    public Integer getTimeCareInfoPageCount(String lj, String ljName, String currentStation, String pageSize, String time) {
        Integer data = 0;
        BigInteger t = new BigInteger("0");
        BigInteger allCount = new BigInteger("0");
        List<ViewTimeCarInfoDo> list = viewTimeCarInfoDao.getTimeCareInfo(lj, ljName, currentStation, time);

        if (list == null || list.size() == 0) {
            data = 0;
        }else{
            data = list.size();
        }

        return data;

    }

    private String getTime(String t_time){
        if(t_time==null || "".equals(t_time)){
            return null;
        }else{
            return t_time;
        }
    }

    @Override
    public ForcastCarInfoDTO getforecastCar(String hitId, String ttime) {
        ForcastCarInfoDTO dto = new ForcastCarInfoDTO();
        try{



        List<ForecastCarDTO> list = new ArrayList<>();

        String t_yjCfTime = "";
        String t_yjDdTime = "";
        String t_sjDdTime = "";
        String t_sjCfTime = "";
        String t_errorTime = "";

        ForecastStringDTO forecastString = viewTimeCarInfoDao.getforecastCar(hitId);

        //1=站点   计划出发时间  里程  计划到达时间  下一站
        //0=当前站 里程 x 预计出发时间  预计到达时间（0的情况这两个时间直接用时间戳，并且不需要计算误差）
        String nodes = forecastString.getNodesInfo();
        String resultSource = forecastString.getResultSource();
        //System.out.println("数据1："+nodes+","+resultSource);

        List<ViewTimeCarInfoDo> infoList = viewTimeCarInfoDao.getCarInfo(hitId, ttime);
        //System.out.println("大小："+infoList.size());
        if (infoList != null && infoList.size() > 0) {
            ViewTimeCarInfoDo info = infoList.get(0);

            //TODO 获取实际时间
            Map<String, String> sjDdMap = new HashMap<>();
            Map<String, String> sjDdCHSBMap = new HashMap<>();
            Map<String, String> sjCfMap = new HashMap<>();
            //getSjTime(sjDdMap, sjCfMap, ttime, info);
            getSjTime(sjDdMap, sjCfMap,sjDdCHSBMap, ttime, info);
            //获取实际时间结束

            //当前时间
            Long time = Long.parseLong(info.getReportTime());
            FastDateFormat instance = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time);
            String format = instance.format(calendar.getTime());//报文时间=当前时间

            if (info.getLybj().trim().equals("编组站")) {
                if (nodes != null) {
                    if (!nodes.trim().equals("") && !nodes.trim().equals(" ")) {
                        String[] split = nodes.split("\\|");
                        if (split != null && split.length > 0) {
                            if(resultSource.trim().equals("1")){
                                //加入当前的
                                //根据报文类型
                                // 到达报文LCDD：弹窗中第一条的预计到达时间置空；
                                // 出发报文LCCF/装车报文ZCBG：弹窗中第一条预计出发、预计到达、实际到达时间都置空；
                                // 其他类型报文不处理
                                if(info.getReportType().trim().equals("LCDD") || info.getReportType().trim().equals("CHSB")){
                                    t_yjCfTime = info.getYjCfTime();
                                    t_yjDdTime = "";

                                    if(info.getReportType().trim().equals("LCDD")){
                                        t_sjDdTime = dateString(sjDdMap.get(info.getCurrentStationCode()));
                                    }

                                    if(info.getReportType().trim().equals("CHSB")){
                                        if(sjDdMap.get(info.getCurrentStationCode())==null || sjDdMap.get(info.getCurrentStationCode()).equals("")){
                                            t_sjDdTime = dateString(sjDdCHSBMap.get(info.getCurrentStationCode()));
                                        }else{
                                            t_sjDdTime = DateUtil.dateToString(new Date(Long.valueOf(sjDdMap.get(info.getCurrentStationCode()))), DateUtil.FORMATDATE1);
                                        }
                                    }
                                    //t_sjDdTime = dateString(sjDdMap.get(info.getCurrentStationCode()));
                                    t_sjCfTime = dateString(sjCfMap.get(info.getCurrentStationCode()));

                                    //原来：预计发车时间-实际发车时间
                                    //this.errorTime = DateUtil.getBetweenMinutes(yjCfTime, sjCfTime);
                                    //现在：(预计发车-预计到达)-(实际发车-实际到达)  误差值：分钟

                                    //改：到达报文的第一条，误差值：如果这三个时间存在：(预计发车-实际到达)-(实际发车-实际到达)
                                    //t_errorTime = DateUtil.getBetweenMinutes2(t_yjCfTime,t_yjDdTime,t_sjCfTime,t_sjDdTime);
                                    if((t_yjCfTime!=null && !t_yjCfTime.equals("")) && (t_sjCfTime!=null && !t_sjCfTime.equals("")) && (t_sjDdTime!=null && !t_sjDdTime.equals(""))){
                                        t_errorTime = DateUtil.getBetweenMinutes2(getTime(t_yjCfTime),getTime(t_sjDdTime),getTime(t_sjCfTime),getTime(t_sjDdTime));
                                    }else{
                                        t_errorTime = DateUtil.getBetweenMinutes2(getTime(t_yjCfTime),getTime(t_yjDdTime),getTime(t_sjCfTime),getTime(t_sjDdTime));
                                    }

                                    list.add(new ForecastCarDTO(info.getCurrentStation(), format, t_yjCfTime, t_yjDdTime,
                                            t_sjDdTime, t_sjCfTime,t_errorTime));
                                }else if(info.getReportType().trim().equals("LCCF") || info.getReportType().trim().equals("ZCBG")){
                                    t_yjCfTime = "";
                                    t_yjDdTime = "";
                                    t_sjDdTime = "";
                                    t_sjCfTime = dateString(sjCfMap.get(info.getCurrentStationCode()));
                                    t_errorTime = DateUtil.getBetweenMinutes2(getTime(t_yjCfTime),getTime(t_yjDdTime),getTime(t_sjCfTime),getTime(t_sjDdTime));
                                    list.add(new ForecastCarDTO(info.getCurrentStation(), format, t_yjCfTime, t_yjDdTime,
                                            t_sjDdTime, t_sjCfTime,t_errorTime));
                                }

                                //加入预测的，不加第一条
                                for (int i = 0; i < split.length; i++) {
                                    //1=站点   计划出发时间  里程  计划到达时间  下一站
                                    list = getForcast("0","1",i,split,list,sjDdMap,sjCfMap,format,info);
                                }
                            }
                            else if(resultSource.trim().equals("0")){
                                list = getForcast("0","0",1,split,list,sjDdMap,sjCfMap,format,info);
                            }
                        }
                    }
                }

                dto = new ForcastCarInfoDTO(info.getCarNo(), info.getStartStation(), info.getEndStation(), info.getCurrentStation(), info.getYjCfTime(), info.getYjDdTime(), list);

            } else { //卸车站

                //这种情况应该不会出现了，所以这里先不考虑了
                if (info.getCurrentStation().trim().equals(info.getEndStation().trim())) {
                    //只需要写入当前站
//                    list.add(new ForecastCarDTO(info.getCurrentStation(), format, info.getYjCfTime(), info.getYjDdTime(),
//                            dateString(sjDdMap.get(info.getCurrentStationCode())), dateString(sjCfMap.get(info.getCurrentStationCode()))));
                    dto = new ForcastCarInfoDTO(info.getCarNo(), info.getStartStation(), info.getEndStation(), info.getCurrentStation(), info.getYjCfTime(), info.getYjDdTime(), list);
                } else {

                    if (nodes != null) {
                        if (!nodes.trim().equals("") && !nodes.trim().equals(" ")) {
                            String[] split = nodes.split("\\|");
                            if (split != null && split.length > 0) {
                                if(resultSource.trim().equals("1")){

                                    //System.out.println("进来了："+split.length);

                                    for (int i = 0; i < split.length; i++) {
                                        //1=站点   计划出发时间  里程  计划到达时间  下一站
                                        if (i == 0) {
                                            String s = split[i];
                                            String[] split1 = s.split(",");
                                            if (split1.length == 5) {
                                                if(info.getReportType().trim().equals("LCDD") || info.getReportType().trim().equals("CHSB")){
                                                    t_yjCfTime = split1[1];
                                                    t_yjDdTime = "";

                                                    if(info.getReportType().trim().equals("LCDD")){
                                                        t_sjDdTime = dateString(sjDdMap.get(split1[0]));
                                                    }

                                                    if(info.getReportType().trim().equals("CHSB")){
                                                        if(sjDdMap.get(split1[0])==null || sjDdMap.get(split1[0]).equals("")){
                                                            t_sjDdTime = dateString(sjDdCHSBMap.get(split1[0]));
                                                        }else{
                                                            t_sjDdTime = DateUtil.dateToString(new Date(Long.valueOf(sjDdMap.get(split1[0]))), DateUtil.FORMATDATE1);
                                                        }
                                                    }
                                                    //t_sjDdTime = dateString(sjDdMap.get(split1[0]));
                                                    t_sjCfTime = dateString(sjCfMap.get(split1[0]));

                                                    if((t_yjCfTime!=null && !t_yjCfTime.equals("")) && (t_sjCfTime!=null && !t_sjCfTime.equals("")) && (t_sjDdTime!=null && !t_sjDdTime.equals(""))){
                                                        t_errorTime = DateUtil.getBetweenMinutes2(getTime(t_yjCfTime),getTime(t_sjDdTime),getTime(t_sjCfTime),getTime(t_sjDdTime));
                                                    }else{
                                                        t_errorTime = DateUtil.getBetweenMinutes2(getTime(t_yjCfTime),getTime(t_yjDdTime),getTime(t_sjCfTime),getTime(t_sjDdTime));
                                                    }

                                                    list.add(new ForecastCarDTO(viewTimeCarInfoDao.getStationName(split1[0]), format, t_yjCfTime, t_yjDdTime,
                                                            t_sjDdTime, t_sjCfTime,t_errorTime));
                                                }else if(info.getReportType().trim().equals("LCCF") || info.getReportType().trim().equals("ZCBG")){
                                                    t_yjCfTime = "";
                                                    t_yjDdTime = "";
                                                    t_sjDdTime = "";
                                                    t_sjCfTime = dateString(sjCfMap.get(split1[0]));
                                                    t_errorTime = DateUtil.getBetweenMinutes2(getTime(t_yjCfTime),getTime(t_yjDdTime),getTime(t_sjCfTime),getTime(t_sjDdTime));

                                                    list.add(new ForecastCarDTO(viewTimeCarInfoDao.getStationName(split1[0]), format, t_yjCfTime, t_yjDdTime,
                                                            t_sjDdTime, t_sjCfTime,t_errorTime));
                                                }

                                            }
                                            list = getForcast("1","1",i,split,list,sjDdMap,sjCfMap,format,info);

                                        } else {
                                            list = getForcast("1","1",i,split,list,sjDdMap,sjCfMap,format,info);
                                        }
                                    }
                                }else if(resultSource.trim().equals("0")){
                                    list = getForcast("1","0",1,split,list,sjDdMap,sjCfMap,format,info);
                                }

                            }
                        }
                    }

                    dto = new ForcastCarInfoDTO(info.getCarNo(), info.getStartStation(), info.getEndStation(), info.getCurrentStation(), info.getYjCfTime(), info.getYjDdTime(), list);
                }
            }


            //先暂时不管CHSB
            //预测站信息和实际表里拿到的剩余站，按照实际到达时间排序
            List<ForecastCarDTO> forecastList = dto.getList();//预测的：有序
//            for(int i=0;i<forecastList.size();i++){
//                System.out.println("原来的有序："+i+"="+forecastList.get(i).getCurrentStation());
//            }

            List<ForecastCarDTO> sjList = new ArrayList<ForecastCarDTO>(); //实际有序
            List<String> tmpStation = new ArrayList<String>();
            Iterator<String> sjCfIterator = sjCfMap.keySet().iterator();
            Iterator<String> sjDdIterator = sjDdMap.keySet().iterator();
            Iterator<String> sjDdCHSBIterator = sjDdCHSBMap.keySet().iterator();
            while (sjCfIterator.hasNext()){
                String trim = sjCfIterator.next().trim();
                if(!tmpStation.contains(trim)){
                    tmpStation.add(trim);
                    //System.out.println("出发站："+trim);
                }
            }
            while(sjDdIterator.hasNext()){
                String trim = sjDdIterator.next().trim();
                if(!tmpStation.contains(trim)){
                    tmpStation.add(trim);
                    //System.out.println("到达站："+trim);
                }
            }
            while(sjDdCHSBIterator.hasNext()){
                String trim = sjDdCHSBIterator.next().trim();
                if(!tmpStation.contains(trim)){
                    tmpStation.add(trim);
                    //System.out.println("车号识别站："+trim);
                }
            }

            if(tmpStation.size()>0){
                for(String s:tmpStation){
                    String sjCfTime = dateString(sjCfMap.get(s));
                    String sjDdTime = dateString(sjDdMap.get(s));
                    if(sjDdTime==null || sjDdTime.trim().equals("")){
                        sjDdTime = dateString(sjDdCHSBMap.get(s));
                    }
                    sjList.add(new ForecastCarDTO(viewTimeCarInfoDao.getStationName(s.trim()),null,null,null,sjDdTime,sjCfTime,null));
                }
                Collections.sort(sjList, new ListComparator());

//                for(int i=0;i<sjList.size();i++){
//                    System.out.println("排序后的实际站数据：i="+i+",currentStation="+sjList.get(i).getCurrentStation()+",sjDdTime="+sjList.get(i).getSjDdTime()+",sjCfTime="+sjList.get(i).getSjCfTime());
//                }

                //sjList中多出来的站有序插入到forecastList中
                //在谁后不能确定，在谁前可以确定
                String tx = "";
                for(int x = 0;x<sjList.size();x++){
                    ForecastCarDTO forecastCarDTO = sjList.get(x);
                    if(!forecastList.contains(forecastCarDTO)){
                        for(int y = x;y<sjList.size();y++){
                            if(forecastList.contains(sjList.get(y))){
                                tx = sjList.get(y).getCurrentStation();
                                break;
                            }
                        }

                       //System.out.println("tx的值："+tx);
                        if(!tx.equals("")){
                            int index = forecastList.indexOf(new ForecastCarDTO(tx,null,null,null,null,null,null));
                            //System.out.println("index的值："+index);
                            forecastList.add(index,forecastCarDTO);
                        }
                    }

                    tx = "";
                }
                dto.setList(forecastList);
//                for(int i=0;i<forecastList.size();i++){
//                    System.out.println("新的数据有序：currentStation="+forecastList.get(i).getCurrentStation()+",sjDdTime="+forecastList.get(i).getSjDdTime()+",sjCfTime="+forecastList.get(i).getSjCfTime());
//                }
            }

        }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dto;

    }

    /**
     *
     * @param type 编组站0，卸车站1
     * @param resultSource 0 1
     * @param i 0 非0
     * @param split
     * @param list
     * @param sjDdMap 实际到达集合
     * @param sjCfMap 实际出发集合
     * @param format 当前数据的时间
     * @param info 当前数据
     * @return
     */
    private List<ForecastCarDTO> getForcast(String type,String resultSource,int i,String[] split,List<ForecastCarDTO> list,Map<String, String> sjDdMap,Map<String, String> sjCfMap,String format,ViewTimeCarInfoDo info){
        String t_yjCfTime = "";
        String t_yjDdTime = "";
        String t_sjDdTime = "";
        String t_sjCfTime = "";
        String t_errorTime = "";

        if(type.equals("0") || type.equals("1")){
            if(resultSource.equals("1")){
                if(i==0){
                    String s = split[i];
                    String[] split1 = s.split(",");
                    if (split1.length == 5) {
                        //不加了，会重复
                        //list.add(new ForecastCarDTO(viewTimeCarInfoDao.getStationName(split1[0]),format,split1[1],""));
                    }
                    if (viewTimeCarInfoDao.getStationName(split1[4].trim()).equals(info.getEndStation().trim())) {
                        t_yjCfTime = "";
                        t_yjDdTime = split1[3];
                        t_sjDdTime = dateString(sjDdMap.get(split1[4].trim()));
                        t_sjCfTime = dateString(sjCfMap.get(split1[4].trim()));
                        t_errorTime = DateUtil.getBetweenMinutes2(getTime(t_yjCfTime),getTime(t_yjDdTime),getTime(t_sjCfTime),getTime(t_sjDdTime));

                        //卸车站：最后一站的实际出发时间置空（resultSource=0/1设置）
                        if(type.equals("1")){
                            list.add(new ForecastCarDTO(viewTimeCarInfoDao.getStationName(split1[4].trim()), format, t_yjCfTime, t_yjDdTime,
                                    t_sjDdTime,null,t_errorTime));
                        }else{
                            list.add(new ForecastCarDTO(viewTimeCarInfoDao.getStationName(split1[4].trim()), format, t_yjCfTime, t_yjDdTime,
                                    t_sjDdTime,t_sjCfTime,t_errorTime));
                        }

                    }


                }else{
                    String s1 = split[i - 1];
                    String s2 = split[i];
                    String[] split1 = s1.split(",");
                    String[] split2 = s2.split(",");
                    if (split1.length == 5 && split2.length == 5) {
                        t_yjCfTime = split2[1];
                        t_yjDdTime = split1[3];
                        t_sjDdTime = dateString(sjDdMap.get(split2[0]));
                        t_sjCfTime = dateString(sjCfMap.get(split2[0]));
                        t_errorTime = DateUtil.getBetweenMinutes2(getTime(t_yjCfTime),getTime(t_yjDdTime),getTime(t_sjCfTime),getTime(t_sjDdTime));

                        list.add(new ForecastCarDTO(viewTimeCarInfoDao.getStationName(split2[0]), format, t_yjCfTime, t_yjDdTime,
                                t_sjDdTime, t_sjCfTime,t_errorTime));
                    }
                    if (viewTimeCarInfoDao.getStationName(split2[4].trim()).equals(info.getEndStation().trim())) {
                        t_yjCfTime = "";
                        t_yjDdTime = split2[3];
                        t_sjDdTime = dateString(sjDdMap.get(split2[4].trim()));
                        t_sjCfTime = dateString(sjCfMap.get(split2[4].trim()));
                        t_errorTime = DateUtil.getBetweenMinutes2(getTime(t_yjCfTime),getTime(t_yjDdTime),getTime(t_sjCfTime),getTime(t_sjDdTime));

                        if(type.equals("1")){
                            list.add(new ForecastCarDTO(viewTimeCarInfoDao.getStationName(split2[4].trim()), format, t_yjCfTime, t_yjDdTime,
                                    t_sjDdTime, null,t_errorTime));
                        }else{
                            list.add(new ForecastCarDTO(viewTimeCarInfoDao.getStationName(split2[4].trim()), format, t_yjCfTime, t_yjDdTime,
                                    t_sjDdTime, t_sjCfTime,t_errorTime));
                        }


                    }
                }
            }else if(resultSource.equals("0")){//现在应该是没有resultSource=0的情况了
                //不加入当前的
                //加入预测的，包括第一条(预计出发时间，预计到达时间都是从这一条里取)
                for (int j = 0; j < split.length; j++) {
                    //0=当前站 里程 当前站 x 预计出发时间  预计到达时间（0的情况这两个时间直接用时间戳，并且不需要计算误差）
                    String s = split[j];
                    String[] split1 = s.split(",");

                    //resultSource=0的情况先不考虑第一条是否需要判断预计出发时间、预计到达时间的情况
                    //卸车站：最后一站的实际出发时间置空（resultSource=0/1设置）
                    if(type.equals("1") && j==(split.length-1)){
                        list.add(new ForecastCarDTO(viewTimeCarInfoDao.getStationName(split1[0]), format, split1[4], split1[5],
                                dateString(sjDdMap.get(split1[0])), null,null));
                    }else{
                        list.add(new ForecastCarDTO(viewTimeCarInfoDao.getStationName(split1[0]), format, split1[4], split1[5],
                                dateString(sjDdMap.get(split1[0])), dateString(sjCfMap.get(split1[0])),null));
                    }

                }

            }
        }

        return list;
    }

    @Override
    public HeavyVehiclesResponseDTO getHeavyVehiclesByStation(String type, String station) {
        HeavyVehiclesResponseDTO heavyVehiclesResponseDTO = new HeavyVehiclesResponseDTO();
        if (!"1".equals(type)) {
            heavyVehiclesResponseDTO.setActualAmount(0L);
            heavyVehiclesResponseDTO.setExpectAmount(0L);
            return heavyVehiclesResponseDTO;
        }
        //获取预计数据
        Long amount = xczYjCntDao.getAmountByStation(station);
        heavyVehiclesResponseDTO.setExpectAmount(amount == null ? 0L : amount);
        //获取实际数据
        amount = xczSjCntDao.getAmountByStation(station);
        heavyVehiclesResponseDTO.setActualAmount(amount == null ? 0L : amount);
        return heavyVehiclesResponseDTO;
    }

    //获取map集合
    private void getSjTime(Map<String, String> sjDdMap, Map<String, String> sjCfMap, Map<String, String> sjDdCHSBMap,String ttime, ViewTimeCarInfoDo info) {
        if (info == null) return;
        List<ViewTempHitResultDO> viewTempHitResultDOS = viewTempHitResultDao.queryHitResultDOByTableNameAndVid(getTabNameByTime(ttime), info.getVId());
        if (CollectionUtils.isEmpty(viewTempHitResultDOS)) return;
        Map<String, List<ViewTempHitResultDO>> viewByReportTypeMap = viewTempHitResultDOS.stream().collect(Collectors.groupingBy(ViewTempHitResultDO::getReportType));
        //获取到达时间 LCDD
        List<ViewTempHitResultDO> viewTempHitResultDOSDD = viewByReportTypeMap.get("LCDD");
        if (!CollectionUtils.isEmpty(viewTempHitResultDOSDD)) {
            //sjDdMap.putAll(viewTempHitResultDOSDD.stream().collect(Collectors.toMap(ViewTempHitResultDO::getCurrentStation, ViewTempHitResultDO::getReportTime, (key1, key2) -> key2)));
            sjDdMap.putAll(viewTempHitResultDOSDD.stream().collect(Collectors.toMap(ViewTempHitResultDO::getCurrentStation, ViewTempHitResultDO::getPathTime, (key1, key2) -> key2)));
        }


        //获取到达时间 CHSB
        List<ViewTempHitResultDO> viewTempHitResultDOSCHSB = viewByReportTypeMap.get("CHSB");
        if (!CollectionUtils.isEmpty(viewTempHitResultDOSCHSB)) {
            sjDdCHSBMap.putAll(viewTempHitResultDOSCHSB.stream().collect(Collectors.toMap(ViewTempHitResultDO::getCurrentStation, ViewTempHitResultDO::getPathTime, (key1, key2) -> key2)));
        }

        //获取出发时间 LCCF
        List<ViewTempHitResultDO> viewTempHitResultDOSCF = viewByReportTypeMap.get("LCCF");
        if (!CollectionUtils.isEmpty(viewTempHitResultDOSCF)) {
            //sjCfMap.putAll(viewTempHitResultDOSCF.stream().collect(Collectors.toMap(ViewTempHitResultDO::getCurrentStation, ViewTempHitResultDO::getReportTime, (key1, key2) -> key2)));
            sjCfMap.putAll(viewTempHitResultDOSCF.stream().collect(Collectors.toMap(ViewTempHitResultDO::getCurrentStation, ViewTempHitResultDO::getPathTime, (key1, key2) -> key2)));
        }


    }

    //根据时间类型选择数据库表名称
    private String getTabNameByTime(String ttime) {
        if (!StringUtils.isEmpty(ttime)) {
            //卸车站12时 123 (码 1-6) 查询表view_temp_hit_result_12_lj
            if ("1".equals(ttime) || "2".equals(ttime) || "3".equals(ttime) ||
                    "4".equals(ttime) || "5".equals(ttime) || "6".equals(ttime)) {
                return "view_temp_hit_result_12_lj";
            }
            //卸车站24时 123 (码 7-12) 查询表view_temp_hit_result_24_lj
            if ("7".equals(ttime) || "8".equals(ttime) || "9".equals(ttime) ||
                    "10".equals(ttime) || "11".equals(ttime) || "12".equals(ttime)) {
                return "view_temp_hit_result_24_lj";
            }
            //编组站12时 123 (码 13-18) 查询表view_temp_hit_result_12_lj_bzz
            if ("13".equals(ttime) || "14".equals(ttime) || "15".equals(ttime) ||
                    "16".equals(ttime) || "17".equals(ttime) || "18".equals(ttime)) {
                return "view_temp_hit_result_12_lj_bzz";
            }
            //编组站24时 123 (码 19-24) 查询表view_temp_hit_result_24_lj_bzz
            if ("19".equals(ttime) || "20".equals(ttime) || "21".equals(ttime) ||
                    "22".equals(ttime) || "23".equals(ttime) || "24".equals(ttime)) {
                return "view_temp_hit_result_24_lj_bzz";
            }
        }
        return null;
    }

    private String dateString(String currentTimeMillis) {
        return StringUtils.isEmpty(currentTimeMillis) ? null : DateUtil.dateToString(new Date(Long.valueOf(currentTimeMillis)), DateUtil.FORMATDATE1);
    }
}
