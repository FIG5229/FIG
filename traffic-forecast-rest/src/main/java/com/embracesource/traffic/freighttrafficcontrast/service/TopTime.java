package com.embracesource.traffic.freighttrafficcontrast.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.embracesource.traffic.freighttrafficcontrast.Entity.ZToptest;
import com.embracesource.traffic.freighttrafficcontrast.mapper.ZToptestMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.embracesource.traffic.base.utils.StaticDataLoader.ZMZD;


@Service
@DS("gp")
public class TopTime extends ServiceImpl<ZToptestMapper, ZToptest> {

    public List<ZToptest> getTopTlsj(String number) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd HH:mm");

        QueryWrapper<ZToptest> wrapper = new QueryWrapper<ZToptest>();

        wrapper.lambda().orderByDesc(ZToptest::getTlMinutes).last("limit "+number);

        List<ZToptest> zToptests = this.baseMapper.selectList(wrapper);

        for (int i = 0; i < zToptests.size() ; i++) {
            ZToptest zToptest = zToptests.get(i);

            String tlMinutes = zToptest.getTlMinutes();
            String dayHour = getDayHour(tlMinutes);
            zToptest.setTlMinutes(dayHour);

            String startStation = zToptest.getStartStation();
            String newStart = ZMZD.get(startStation);
            zToptest.setStartStation(newStart);

            String currentStation = zToptest.getCurrentStation();
            String newCurrent = ZMZD.get(currentStation);
            zToptest.setCurrentStation(newCurrent);

            String endStation = zToptest.getEndStation();
            String newEnd = ZMZD.get(endStation);
            zToptest.setEndStation(newEnd);


            String pathTimeDd = zToptest.getPathTimeDd();
            Date dd1 = sdf.parse(pathTimeDd);
            String dd2 = sdf1.format(dd1);
            zToptest.setPathTimeDd(dd2);

            String pathTimeCf = zToptest.getPathTimeCf();
            Date cf1 = sdf.parse(pathTimeCf);
            String cf2 = sdf1.format(cf1);
            zToptest.setPathTimeCf(cf2);

            zToptests.set(i,zToptest);
        }
        return zToptests;
    }


    public List<ZToptest> getTopTlerr(String number) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd HH:mm");

        QueryWrapper<ZToptest> wrapper = new QueryWrapper<ZToptest>();

        wrapper.lambda().ne(ZToptest::getTlMinutes,0).orderByDesc(ZToptest::getError).last("limit "+number);

        List<ZToptest> zToptests = this.baseMapper.selectList(wrapper);

        for (int i = 0; i < zToptests.size() ; i++) {
            ZToptest zToptest = zToptests.get(i);

            String tlMinutes = zToptest.getTlMinutes();
            String dayHour = getDayHour(tlMinutes);
            zToptest.setTlMinutes(dayHour);

            String error = zToptest.getError();
            String dayHour1 = getDayHour(error);
            zToptest.setError(dayHour1);

            String startStation = zToptest.getStartStation();
            String newStart = ZMZD.get(startStation);
            zToptest.setStartStation(newStart);

            String currentStation = zToptest.getCurrentStation();
            String newCurrent = ZMZD.get(currentStation);
            zToptest.setCurrentStation(newCurrent);

            String endStation = zToptest.getEndStation();
            String newEnd = ZMZD.get(endStation);
            zToptest.setEndStation(newEnd);


            zToptests.set(i,zToptest);
        }
        return zToptests;
    }

    private String getDayHour(String tlMinutes) {
//        System.out.println(tlMinutes);
        long l = Long.parseLong(tlMinutes);

        long day = l / (60 * 24);
        long hour = l % (60 * 24) / 60;
        long minutes = l % 60;

        return day+"天"+hour+"小时"+minutes+"分";
    }
}
