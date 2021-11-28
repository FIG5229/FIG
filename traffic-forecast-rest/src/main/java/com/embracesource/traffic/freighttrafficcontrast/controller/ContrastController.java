package com.embracesource.traffic.freighttrafficcontrast.controller;

import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.freighttrafficcontrast.Entity.*;
import com.embracesource.traffic.freighttrafficcontrast.service.CurrentStationSercive;
import com.embracesource.traffic.freighttrafficcontrast.service.LoadCrateService;
import com.embracesource.traffic.freighttrafficcontrast.service.LoadPgService;
import com.embracesource.traffic.freighttrafficcontrast.service.TopTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.embracesource.traffic.base.utils.StaticDataLoader.*;

@Api(value = "推算验证接口", tags = {"推算验证"})
@RestController
@RequestMapping("/ContrastController")
public class ContrastController {

    @Autowired
    private LoadPgService loadPg;

    @Autowired
    private LoadCrateService loadCrate;

    @Autowired
    private CurrentStationSercive currentStation;

    @Autowired
    private TopTime topTime;


    //初始加载两条路径
    @ApiOperation("初始加载路径")
    @RequestMapping(value = "/contrast",method = RequestMethod.POST)
    public Result contrast(@RequestParam(value = "car_no",defaultValue = "") String car_no) throws Exception {

        List<String[]> allPath1 =new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd HH:mm");
        LinkedHashMap<String, String> realPath =new LinkedHashMap<>();
        List<ContrastAllPathResponseDTO> allPath =new ArrayList<>();
        List<ContrastAllPath1ResponseDTO> allPath1List =new ArrayList<>();

        String endTime = null;
        String next = null;
        String resultSource=null;
        String start = null;
        String end = null;
        int mileage = 0;
        StartAndEndStation startAndEndStation = new StartAndEndStation();


        if(StringUtils.isNotEmpty(car_no)){
            //获取最近一次货运的始终站点信息，并转换为汉字
            HashMap<String, String> startAndEnd = loadPg.getStartAndEnd(car_no);
            start = startAndEnd.get("start");
            end = startAndEnd.get("end");
            startAndEndStation.setStartDM(start);
            startAndEndStation.setEndDM(end);
            startAndEndStation.setStart(ZMZD.get(start));
            startAndEndStation.setEnd(ZMZD.get(end));

            //处理真实路线
            if(start!=null&&end!=null ){
            realPath = loadPg.getAllPath(car_no,start,end);
            String startTime = realPath.get("StartTime");
            startAndEndStation.setStartTime(startTime);
            realPath.remove("StartTime");
            //形式为（站：出发时间，到达时间，车号识别，是否命中）
            Iterator<String> iterator1 = realPath.keySet().iterator();
            while(iterator1.hasNext()){
                next = iterator1.next();
                String[] split = realPath.get(next).split(",");
                String cf = split[0];
                String dd = split[1];
                String chsb = split[2];
                String ishit = split[3];
                String tlsj = split[4];
                Long time1=0L;
                Long time2=0L;
                Long time3=0L;
                Double error=0.0;

                String zm = ZMZD.get(next);
                String ljdm = ZMLJ.get(next);
                String lj = LJZD.get(ljdm);

                if(!"null".equals(cf)){
                    time1 = sdf.parse(split[0]).getTime();
                    cf = sdf1.format(sdf.parse(split[0]));
                }else{
                    cf = null;
                }
                if(!"null".equals(dd)){
                    time2 = sdf.parse(split[1]).getTime();
                    dd=sdf1.format(sdf.parse(split[1]));
                }else{
                    dd = null;
                }
                /*if(!"null".equals(chsb)){
                    chsb=sdf1.format(sdf.parse(split[2]));
                }else{
                    chsb = null;
                }*/

                if(time1!=0 && time2!=0){
                    time3 = (time1 - time2)/(1000*60);
                    if(!"null".equals(tlsj) && !next.equals(start) && !next.equals(end)){
                        double v = Double.parseDouble(tlsj);
                        error = time3 - v ;
                    }
                }
                ContrastAllPathResponseDTO allPathResponseDTO = new ContrastAllPathResponseDTO();
                allPathResponseDTO.setZm(zm);
                allPathResponseDTO.setDd(dd);
                allPathResponseDTO.setCf(cf);
                allPathResponseDTO.setChsb(chsb);
                allPathResponseDTO.setIshit(ishit);
                allPathResponseDTO.setTime3(time3);
                allPathResponseDTO.setLj(lj);
                allPathResponseDTO.setError(error);
                if ("null".equals(tlsj)){
                    tlsj = null;
                }
                allPathResponseDTO.setTlsj(tlsj);
                allPathResponseDTO.setCurrentStation(next);

                allPath.add(allPathResponseDTO);
            }
           // start_station = allPath.get(1)[0];


            //处理预测路线
            HashMap<String, List<String[]>> map = loadCrate.generatePath(car_no, start, end);
            Iterator<String> iterator = map.keySet().iterator();
            //形式为（到终点站时间，谁预测的：({[本站]，[出发时间],[里程],[到达时间],[下一站],[停留时间]}),…………)
            while (iterator.hasNext()){
                    endTime=iterator.next();
                }
                allPath1 = map.get(endTime);
                mileage = getMileage(allPath1);
                resultSource=endTime.split(",")[1];
                startAndEndStation.setEndTime(endTime.split(",")[0]);
                startAndEndStation.setMileage(mileage);
            }
        }
        for (int i = 0; i < allPath1.size(); i++){
            String[] strings = allPath1.get(i);

            String value0 = strings[0];
            String value1 = strings[1];
            String value2 = strings[2];
            String value3 = strings[3];
            ContrastAllPath1ResponseDTO conAllPath1 = new ContrastAllPath1ResponseDTO();
            conAllPath1.setYjlj(value0);
            conAllPath1.setYjcf(value1);
            conAllPath1.setLc(value2);
            conAllPath1.setYjdd(value3);
            allPath1List.add(conAllPath1);
        }


        ContrastResponseDTO contrastResponseDTO = new ContrastResponseDTO();
        contrastResponseDTO.setAllPath(allPath);
        contrastResponseDTO.setAllPath1(allPath1List);
        contrastResponseDTO.setResultSource(resultSource);
        contrastResponseDTO.setCar_no(car_no);
        contrastResponseDTO.setStartAndEndStation(startAndEndStation);
        contrastResponseDTO.setMileage(mileage);

        return Result.ok(contrastResponseDTO);

    }

    //获得剩余里程
    private int getMileage(List<String[]> path) {
        int mileage = 0;
        if(path!=null){
        for (String[] s:path) {
            if(s[2].equals("") || s[2].equals("--")){s[2]="0";}
            int i = Integer.parseInt(s[2].trim());
            mileage +=i ;
        }}

        return mileage;
    }

    //获取点击站点的预测路径
    @ApiOperation("获取点击站点的预测路径")
    @RequestMapping(value = "/getNodesInfo",method = RequestMethod.POST)
    public Result getNodesInfo(@RequestParam(value = "Current_station",defaultValue = "") String Current_station,
                        @RequestParam(value = "carNo",defaultValue = "") String carNo,
                        @RequestParam(value = "startStation",defaultValue = "") String startStation,
                        @RequestParam(value = "endStation",defaultValue = "") String endStation,
                        @RequestParam(value = "StartTime",defaultValue = "") String startTime,
                        Model model) throws ParseException {

        String resultSource = null;
        String endTime = null;
        StartAndEndStation startAndEndStation = new StartAndEndStation();
        ContrastResponseDTO contrastResponseDTO = new ContrastResponseDTO();

        HashMap<String, List<String[]>> map = currentStation.selectPathByCurrentStation(Current_station, carNo, startStation, endStation,startTime);
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){endTime=iterator.next();}
        List<String[]> strings = map.get(endTime);

        List<ContrastOffline1ResponseDTO> offline1List = new ArrayList<>();
        if(strings != null){

            for (int s = 0; s < strings.size(); s++){
                String[] stringList = strings.get(s);
                String location = stringList[0];
                String time = stringList[1];
                String num = stringList[2];
                String time2 = stringList[3];
                String code = stringList[4];
                String num2 = stringList[5];
                ContrastOffline1ResponseDTO contrastOffline1ResponseDTO = new ContrastOffline1ResponseDTO();
                contrastOffline1ResponseDTO.setYjlj(location);
                contrastOffline1ResponseDTO.setYjcf(time);
                contrastOffline1ResponseDTO.setLc(num);
                contrastOffline1ResponseDTO.setYjdd(time2);
                contrastOffline1ResponseDTO.setCode(code);
                contrastOffline1ResponseDTO.setNum2(num2);
                offline1List.add(contrastOffline1ResponseDTO);

            }

            String endStation1 = endTime.split(",")[0];
            int mileage = getMileage(strings);

            if(endStation.equals(endStation1)){
                startAndEndStation.setMileage(mileage);
                startAndEndStation.setEndDM(endStation);
                startAndEndStation.setEnd(ZMZD.get(endStation));
                startAndEndStation.setEndTime(endTime.split(",")[1]);
            }
            contrastResponseDTO.setResultSource(endTime.split(",")[2]);
            contrastResponseDTO.setMileage(mileage);
        }
        contrastResponseDTO.setOffline1(offline1List);
        contrastResponseDTO.setStartAndEndStation(startAndEndStation);
        return Result.ok(contrastResponseDTO);
    }


/*
    @RequestMapping(value = "/tlsjTop10",method = RequestMethod.POST)
    public String getTlsj(@RequestParam(value = "number",defaultValue = "10") String number,Model model) throws ParseException {
        System.out.println(new Date() + "-helloTL");
        String numeric = isNumeric(number);
        List<ZToptest> topTlsj = topTime.getTopTlsj(numeric);

        model.addAttribute("topList",topTlsj);

        return "topsj";
    }

    @RequestMapping(value = "/tlerrTop10",method = RequestMethod.POST)
    public String getTlerr(@RequestParam(value = "number",defaultValue = "10") String number,Model model) throws ParseException {
        System.out.println(new Date() + "-helloErr");
        String numeric = isNumeric(number);
        List<ZToptest> topTlsj = topTime.getTopTlerr(numeric);

        model.addAttribute("topList",topTlsj);

        return "toperr";
    }

    @RequestMapping(value = "/contrast1",method = RequestMethod.POST)
    public String contrast1(@RequestParam(value = "car_no",defaultValue = "") String car_no,Model model) throws Exception {

        List<String[]> allPath1 =new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd HH:mm");
        LinkedHashMap<String, String> realPath =new LinkedHashMap<>();
        List<String[]> allPath =new ArrayList<>();

        System.out.println(sdf1.format(new Date())+"||"+car_no);

//        List<String[]> allPath1 =new ArrayList<>();
        String endTime = null;
        String next = null;
        String resultSource=null;
        String start = null;
        String end = null;
        int mileage = 0;
        StartAndEndStation startAndEndStation = new StartAndEndStation();


        if(!car_no.equals("")){
            //获取最近一次货运的始终站点信息，并转换为汉字
            HashMap<String, String> startAndEnd = loadPg.getStartAndEnd(car_no);
            start = startAndEnd.get("start");
            end = startAndEnd.get("end");
            startAndEndStation.setStartDM(start);
            startAndEndStation.setEndDM(end);
            startAndEndStation.setStart(ZMZD.get(start));
            startAndEndStation.setEnd(ZMZD.get(end));

            //处理真实路线
            if(start!=null&&end!=null ){
                realPath = loadPg.getAllPath1(car_no,start,end);
                String startTime = realPath.get("StartTime");
                startAndEndStation.setStartTime(startTime);
                realPath.remove("StartTime");
                //形式为（站：出发时间，到达时间，车号识别，是否命中）
                Iterator<String> iterator1 = realPath.keySet().iterator();
                while(iterator1.hasNext()){
                    next = iterator1.next();
                    String[] split = realPath.get(next).split(",");
                    String cf = split[0];
                    String dd = split[1];
                    String chsb = split[2];
                    String ishit = split[3];
                    String tlsj = split[4];
                    Long time1=0L;
                    Long time2=0L;
                    Long time3=0L;
                    Double error=0.0;

                    String zm = ZMZD.get(next);
                    String ljdm = ZMLJ.get(next);
                    String lj = LJZD.get(ljdm);

                    if(!cf.equals("null")){
                        time1 = sdf.parse(split[0]).getTime();
                        cf = sdf1.format(sdf.parse(split[0]));
                    }
                    if(!dd.equals("null")){
                        time2 = sdf.parse(split[1]).getTime();
//                    System.out.println(time1);
                        dd=sdf1.format(sdf.parse(split[1]));
                    }
                    if(!chsb.equals("null")){
                        chsb=sdf1.format(sdf.parse(split[2]));
                    }

                    if(time1!=0 && time2!=0){
                        time3 = (time1 - time2)/(1000*60);
                        if(!tlsj.equals("null") && !next.equals(start) && !next.equals(end)){
                            double v = Double.parseDouble(tlsj);
                            error = time3 - v ;
                        }
                    }


                    //每一站加入路径allpath中
                    String[] strings = {zm,dd,cf,chsb,ishit,time3+"",lj,error+"",next};
                    allPath.add(strings);
                }
                // start_station = allPath.get(1)[0];




                //处理预测路线
                HashMap<String, List<String[]>> map = loadCrate.generatePath(car_no, start, end);
                Iterator<String> iterator = map.keySet().iterator();
                //形式为（到终点站时间，谁预测的：({[本站]，[出发时间],[里程],[到达时间],[下一站],[停留时间]}),…………)
                while (iterator.hasNext()){endTime=iterator.next();}
                allPath1 = map.get(endTime);
                mileage = getMileage(allPath1);
                resultSource=endTime.split(",")[1];
                startAndEndStation.setEndTime(endTime.split(",")[0]);
                startAndEndStation.setMileage(mileage);
            }
        }

        model.addAttribute("offline",allPath);
        model.addAttribute("offline1",allPath1);
        model.addAttribute("resultSource",resultSource);
        model.addAttribute("carNo",car_no);
        model.addAttribute("startandend", startAndEndStation);
        model.addAttribute("mileage",mileage);
//        model.addAttribute("endStation",end1);

        return "show1";

    }


    public String isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");

        Matcher matcher = pattern.matcher(str);
        if(!matcher.matches()){
            return "10";
        }

        return str;

    }
*/
}
