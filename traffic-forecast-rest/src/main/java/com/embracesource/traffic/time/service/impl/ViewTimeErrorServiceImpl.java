package com.embracesource.traffic.time.service.impl;
import com.embracesource.traffic.time.domain.ViewTimePageNumDo;
import org.apache.commons.lang3.time.FastDateFormat;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.embracesource.traffic.time.dao.ViewTimeErrorResultDao;
import com.embracesource.traffic.time.domain.ViewTimeErrorResultDo;
import com.embracesource.traffic.time.dto.AccrateDTO;
import com.embracesource.traffic.time.dto.GetCountDTO;
import com.embracesource.traffic.time.dto.TimeAccrateDTO;
import com.embracesource.traffic.time.service.ViewTimeErrorService;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
@DS(value = "pgsql")
public class ViewTimeErrorServiceImpl implements ViewTimeErrorService {

    @Autowired
    ViewTimeErrorResultDao viewTimeErrorResultDao;

    @Override
    public AccrateDTO getAccrate(TimeAccrateDTO timeAccrateDTO) {
        Map<String,String> ids = getId(timeAccrateDTO.getTime1(),timeAccrateDTO.getTime2(),timeAccrateDTO.getType());
        ViewTimeErrorResultDo accrateA = viewTimeErrorResultDao.getAccrate(ids.keySet().iterator().next());
        ViewTimeErrorResultDo accrateB = viewTimeErrorResultDao.getAccrate(ids.get(ids.keySet().iterator().next()));
        if(accrateA == null && accrateB == null){
            return new AccrateDTO("0","0");
        }else{
            return new AccrateDTO(accrateA.getAccrate(),accrateB.getAccrate());
        }
    }

    @Override
    public Map<String,String> getId(String time1, String time2, String type) {
        Map<String,String> ids = new HashMap<String,String>();

        //卸车站
        if(type.trim().equals("1")){
            if(time1.trim().equals("12") && time2.trim().equals("1") ){
                ids.put("1","2");
            }
            if(time1.trim().equals("12") && time2.trim().equals("2") ){
                ids.put("3","4");
            }
            if(time1.trim().equals("12") && time2.trim().equals("3") ){
                ids.put("5","6");
            }


            if(time1.trim().equals("24") && time2.trim().equals("1") ){
                ids.put("7","8");
            }
            if(time1.trim().equals("24") && time2.trim().equals("2") ){
                ids.put("9","10");
            }
            if(time1.trim().equals("24") && time2.trim().equals("3") ){
                ids.put("11","12");
            }
        }

        //编组站
        if(type.trim().equals("0")){
            if(time1.trim().equals("12") && time2.trim().equals("1") ){
                ids.put("13","14");
            }
            if(time1.trim().equals("12") && time2.trim().equals("2") ){
                ids.put("15","16");
            }
            if(time1.trim().equals("12") && time2.trim().equals("3") ){
                ids.put("17","18");
            }


            if(time1.trim().equals("24") && time2.trim().equals("1") ){
                ids.put("19","20");
            }
            if(time1.trim().equals("24") && time2.trim().equals("2") ){
                ids.put("21","22");
            }
            if(time1.trim().equals("24") && time2.trim().equals("3") ){
                ids.put("23","24");
            }
        }

        return ids;
    }

    @Override
    public String getId(String time1,String time2,String flag,String type){
        String id = "";
        //卸车站
        if(type.trim().equals("1")){
            if(time1.trim().equals("12") && time2.trim().equals("1") ){
                if(flag.trim().equals("1") ){
                    id = "1";
                }
                if(flag.trim().equals("0") ){
                    id = "2";
                }
            }
            if(time1.trim().equals("12") && time2.trim().equals("2") ){
                if(flag.trim().equals("1") ){
                    id = "3";
                }
                if(flag.trim().equals("0") ){
                    id = "4";
                }
            }
            if(time1.trim().equals("12") && time2.trim().equals("3") ){
                if(flag.trim().equals("1") ){
                    id = "5";
                }
                if(flag.trim().equals("0") ){
                    id = "6";
                }
            }


            if(time1.trim().equals("24") && time2.trim().equals("1") ){
                if(flag.trim().equals("1") ){
                    id = "7";
                }
                if(flag.trim().equals("0") ){
                    id = "8";
                }
            }
            if(time1.trim().equals("24") && time2.trim().equals("2") ){
                if(flag.trim().equals("1") ){
                    id = "9";
                }
                if(flag.trim().equals("0") ){
                    id = "10";
                }
            }
            if(time1.trim().equals("24") && time2.trim().equals("3") ){
                if(flag.trim().equals("1") ){
                    id = "11";
                }
                if(flag.trim().equals("0") ){
                    id = "12";
                }
            }
        }

        //编组站
        if(type.trim().equals("0")){
            if(time1.trim().equals("12") && time2.trim().equals("1") ){
                if(flag.trim().equals("1") ){
                    id = "13";
                }
                if(flag.trim().equals("0") ){
                    id = "14";
                }
            }
            if(time1.trim().equals("12") && time2.trim().equals("2") ){
                if(flag.trim().equals("1") ){
                    id = "15";
                }
                if(flag.trim().equals("0") ){
                    id = "16";
                }
            }
            if(time1.trim().equals("12") && time2.trim().equals("3") ){
                if(flag.trim().equals("1") ){
                    id = "17";
                }
                if(flag.trim().equals("0") ){
                    id = "18";
                }
            }


            if(time1.trim().equals("24") && time2.trim().equals("1") ){
                if(flag.trim().equals("1") ){
                    id = "19";
                }
                if(flag.trim().equals("0") ){
                    id = "20";
                }
            }
            if(time1.trim().equals("24") && time2.trim().equals("2") ){
                if(flag.trim().equals("1") ){
                    id = "21";
                }
                if(flag.trim().equals("0") ){
                    id = "22";
                }
            }
            if(time1.trim().equals("24") && time2.trim().equals("3") ){
                if(flag.trim().equals("1") ){
                    id = "23";
                }
                if(flag.trim().equals("0") ){
                    id = "24";
                }
            }
        }
        return id;
    }

    @Override
    public GetCountDTO getCarCount() {
        ViewTimePageNumDo count = viewTimeErrorResultDao.getCount();
        //System.out.println("数量"+count.getDayCarNum()+","+count.getDayCfNum()+","+count.getDayDdNum());
        return new GetCountDTO(count.getDayCarNum(),count.getDayCfNum(),count.getDayDdNum());
        
        
//        FastDateFormat instance = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
//        String nowDate = instance.format(new Date());
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        Date zero = calendar.getTime();
//        String zeroDate = instance.format(zero);
//        Long carCount = 0L;
//        Long leaveCount = 0L;
//        Long arriveCount = 0L;
////        System.out.println("zeroDate:"+zeroDate+",Timestamp.valueOf(zeroDate):"+Timestamp.valueOf(zeroDate));
////        System.out.println("nowDate:"+nowDate+",Timestamp.valueOf(nowDate):"+Timestamp.valueOf(nowDate));
//         carCount = viewTimeErrorResultDao.getCarCount( Timestamp.valueOf(nowDate),Timestamp.valueOf(zeroDate));
//         leaveCount = viewTimeErrorResultDao.getLeaveCount(Timestamp.valueOf(nowDate),Timestamp.valueOf(zeroDate));
//         arriveCount = viewTimeErrorResultDao.getArriveCount(Timestamp.valueOf(nowDate),Timestamp.valueOf(zeroDate));
//            if(carCount == null || carCount==0L){
//                carCount = 0L;
//            }
//            if(leaveCount == null || leaveCount==0L){
//                leaveCount = 0L;
//            }
//            if(arriveCount == null || arriveCount==0L){
//                arriveCount = 0L;
//            }
//        return new GetCountDTO(carCount,leaveCount,arriveCount);
    }

}
