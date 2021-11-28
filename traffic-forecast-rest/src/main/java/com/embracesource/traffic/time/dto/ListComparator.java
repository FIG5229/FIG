package com.embracesource.traffic.time.dto;

import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

/**
 * .
 * User: huqiaomei
 * Date: 2021/3/18 9:59
 * Description:
 */
public class ListComparator implements Comparator {
    FastDateFormat instance = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    @Override
    public int compare(Object o1, Object o2) {

        //列表部分
        if((o1 instanceof CarDTO) && (o2 instanceof CarDTO)){
            CarDTO obj1 = (CarDTO)o1;
            CarDTO obj2 = (CarDTO)o2;
            try{
                //按照报文时间降序
                //这个时间是不可能为空的
                if((obj1.getReportTime()!=null && !obj1.getReportTime().equals("")) && (obj2.getReportTime()!=null && !obj2.getReportTime().equals(""))){
                    Date data1 = instance.parse(obj1.getReportTime());
                    Date data2 = instance.parse(obj2.getReportTime());

                    if(data1.before(data2)){
                        return 1;
                    }else if(data1.after(data2)){
                        return -1; //更晚的时间
                    }else{
                        return 0; //相等
                    }
                }
            }catch (ParseException e){
                e.printStackTrace();
            }
        }

        //弹窗部分
        else if((o1 instanceof ForecastCarDTO) && (o2 instanceof ForecastCarDTO)){
            ForecastCarDTO obj1 = (ForecastCarDTO)o1;
            ForecastCarDTO obj2 = (ForecastCarDTO)o2;

            //升序，先按照实际到达时间，然后按照预计到达时间
            try {
                    String sjD1 = "";
                    String sjD2 = "";

                    if(obj1.getSjDdTime()==null || obj1.getSjDdTime().equals("")){
                        sjD1 = obj1.getSjCfTime();
                    }else{
                        sjD1 = obj1.getSjDdTime();
                    }

                    if(obj2.getSjDdTime()==null || obj2.getSjDdTime().equals("")){
                        sjD2 = obj2.getSjCfTime();
                    }else{
                        sjD2 = obj2.getSjDdTime();
                    }

                    Date data1 = instance.parse(sjD1);
                    Date data2 = instance.parse(sjD2);
                    if(data1.before(data2)){
                        return -1;
                    }else if(data1.after(data2)){
                        return 1; //更晚的时间
                    }else{
                        return 0; //相等
                    }

            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        return 0;
    }
}
