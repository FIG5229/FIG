package com.embracesource.traffic.base.utils;

import com.embracesource.traffic.screen.vo.DateVo;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.time.DateUtils.parseDate;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-25 下午 04:27
 * @description：
 * @version:
 */
public class DateUtil {
    public static String dateToMillisString(Date date) {
        if (null == date) {
            return System.currentTimeMillis() + "";
        }
        return date.getTime() + "";
    }

    public static final String FORMATDATE1 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMATDATE2 = "yyyy-MM-dd";
    public static final String FORMATDATE3 = "yyyyMMddHHmmss";
    public static final String FORMATDATE4 = "yyyyMMdd";
    public static final String FORMATDATE5 = "MM-dd";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    public static Date strToDate(String date, String format) {
        if (StringUtils.isEmpty(format)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(date);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String dateToString(Date date, String format) {
        if (StringUtils.isEmpty(format)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.format(date);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 获取上n个小时整点小时时间
     *
     * @param date
     * @return
     */
    public static Date getLastHourTime(Date date, int n) {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.HOUR_OF_DAY, ca.get(Calendar.HOUR_OF_DAY) - n);
        return ca.getTime();
    }

    /**
     * 获取当前时间的整点小时时间
     *
     * @param date
     * @return
     */
    public static Date getCurrHourTime(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        return ca.getTime();
    }

    /*
    * 获取当前日期
    *
    * 格式：yyyyMMdd
    * */
    public static String getNowDay(){
        //添加当前时间
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");//设置日期格式
        return dateFormat.format(now);
    }

    public static String dateFormat(Date date, String pattern){
        if (date == null)
            date = new Date();
        if (StringUtils.isBlank(pattern)){
            pattern = FORMATDATE1;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    public static String dateFormat(Date date){
        if (date == null)
            date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATDATE1);
        return sdf.format(date);
    }

    public static List<String> getBeforeMonth(int i) {
        if (i < 1) {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        List<String> months = new ArrayList<>(i);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        for (int j = 0; j < i; j++) {
            months.add(sdf.format(ca.getTime()));
            ca.set(Calendar.MONTH, ca.get(Calendar.MONTH) - 1);
        }
        return months;
    }

    public static List<String> getBeforeMonth() {
        return getBeforeMonth(11);
    }

    public static int getMonth(String date) {
        if (StringUtils.isEmpty(date) || date.length() < 6) {
            return 0;
        }
        Integer integer = 0;
        try {
            integer = Integer.valueOf(date.substring(4, 6));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return integer;

    }

    public static int getHour(String date) {
        if (StringUtils.isEmpty(date) || date.length() < 10) {
            return 0;
        }
        Integer integer = 0;
        try {
            integer = Integer.valueOf(date.substring(8, 10));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return integer;
    }

    public static List<String> getBeforeHour(int i,Object... pattern) {
        if (i < 1) {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.HOUR_OF_DAY, ca.get(Calendar.HOUR_OF_DAY));
        List<String> months = new ArrayList<>(i);
        SimpleDateFormat sdf = null;
        if (pattern != null && pattern.length > 0) {
            sdf = new SimpleDateFormat(pattern[0].toString());
        }else{
            sdf = new SimpleDateFormat("yyyyMMddHH");
        }

        for (int j = 0; j < i; j++) {
            months.add(sdf.format(ca.getTime()));
            ca.set(Calendar.HOUR_OF_DAY, ca.get(Calendar.HOUR_OF_DAY) - 1);
        }
        return months;
    }

    public static List<String> getBeforeHour_new(int i,Object... pattern) {
        if (i < 1) {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.HOUR_OF_DAY,-7);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.HOUR_OF_DAY, ca.get(Calendar.HOUR_OF_DAY));
        List<String> months = new ArrayList<>(i);
        SimpleDateFormat sdf = null;
        if (pattern != null && pattern.length > 0) {
            sdf = new SimpleDateFormat(pattern[0].toString());
        }else{
            sdf = new SimpleDateFormat("yyyyMMddHH");
        }

        for (int j = 0; j < i; j++) {
            months.add(sdf.format(ca.getTime()));
            ca.set(Calendar.HOUR_OF_DAY, ca.get(Calendar.HOUR_OF_DAY) + 1);
        }
        return months;
    }

    public static List<String> getBeforeHour() {
        return getBeforeHour(23);
    }


    public static DateVo getMonthBeginAndEnd() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        DateVo dateVo = new DateVo();
        dateVo.setCurrentMonthBegin(c.getTime());
        c.add(Calendar.MONTH, 1);
        dateVo.setCurrentMonthEnd(c.getTime());
        return dateVo;
    }

    public static DateVo getDayBeginAndEnd() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        DateVo dateVo = new DateVo();
        dateVo.setCurrentDayBegin(c.getTime());
        c.add(Calendar.DAY_OF_MONTH, 1);
        dateVo.setCurrentDayEnd(c.getTime());
        return dateVo;
    }

    public static DateVo getHourBeginAndEnd() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        DateVo dateVo = new DateVo();
        dateVo.setCurrentHourBegin(c.getTime());
        c.add(Calendar.HOUR, 1);
        dateVo.setCurrentHourEnd(c.getTime());
        return dateVo;
    }



    /**
     *  获取day天的零点时间
     * @param day   0：今天  1：明天  -1：昨天
     * @return
     */
    public static Date getZeroTime(int day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+day);
//        calendar.add();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.FORMATDATE1);
        String time = simpleDateFormat.format(zero);
        System.out.println(time);
        return zero;
    }

    public static Date getBeforDay(int day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+day);
        Date zero = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.FORMATDATE1);
        String time = simpleDateFormat.format(zero);
        System.out.println(time);
        return zero;
    }

    /**
     * 获取今天的零点时间
     * @return
     */
    public static Date getTodayZeroTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.FORMATDATE1);
        String time = simpleDateFormat.format(zero);
        return zero;
    }
    /**
     * 获取明天的零点时间
     * @return
     */
    public static Date getTomorrowZeroTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int month = calendar.get(Calendar.MONTH);
//        calendar.add(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.FORMATDATE1);
        String time = simpleDateFormat.format(zero);
        return zero;
    }
    public static void main(String[] args) throws ParseException {
//        System.out.println(getBeforeMonth(12));
//        getZeroTime(0);
        System.out.println(dateFormat(null,""));
    }

    public static String getBetweenMinutes(String time1, String time2) {
        if (StringUtils.isEmpty(time1) || StringUtils.isEmpty(time2)) return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.FORMATDATE1);
        try {
            return String.valueOf((simpleDateFormat.parse(time1).getTime() - simpleDateFormat.parse(time2).getTime()) / 60000);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String getBetweenMinutes2(String time1, String time2,String time3,String time4) {
        if (StringUtils.isEmpty(time1) || StringUtils.isEmpty(time2) || StringUtils.isEmpty(time3) || StringUtils.isEmpty(time4) ) return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.FORMATDATE1);
        try {

            long time = (simpleDateFormat.parse(time1).getTime() - simpleDateFormat.parse(time2).getTime()) - (simpleDateFormat.parse(time3).getTime() - simpleDateFormat.parse(time4).getTime());
            return String.valueOf(time / 60000);
        } catch (Exception ex) {
            return null;
        }
    }


    /**
     * 获取当前系统时间最近12月的年月（含当月）
     */
    public static List<String> getLatest12Month(){
        List<String> latest12Months = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1); //要先+1,才能把本月的算进去
        for(int i=0; i<12; i++){
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1); //逐次往前推1个月
            latest12Months.add(cal.get(Calendar.YEAR)+ "-" +fillZero(cal.get(Calendar.MONTH)+1));
        }
        return latest12Months;
    }

    /**
     * 格式化月份
     */
    public static String fillZero(int i){
        String month = "";
        if(i<10){
            month = "0" + i;
        }else{
            month = String.valueOf(i);
        }
        return month;
    }


}
