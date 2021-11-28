package com.embracesource.traffic.freighttrafficcontrast.service;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.embracesource.traffic.freighttrafficcontrast.Entity.HitResult;
import com.embracesource.traffic.freighttrafficcontrast.Entity.PgCarResultInfoHistory;
import com.embracesource.traffic.freighttrafficcontrast.mapper.HitResultMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@DS("Crate")
public class IsHitService extends ServiceImpl<HitResultMapper, HitResult> {

    public String getHitResult(PgCarResultInfoHistory car, String starttime) throws ParseException {
        String isHit="";
        QueryWrapper<HitResult> wrapper = new QueryWrapper<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
        Date date1 = sdf.parse(starttime);
        long date1Time = date1.getTime();


        wrapper.lambda().eq(HitResult::getCarNo,car.getCarNo())
                        .eq(HitResult::getStartStation,car.getStartStation())
                        .eq(HitResult::getEndStation,car.getEndStation())
                        .eq(HitResult::getCurrentStation,car.getCurrentStation())
                        .ge(HitResult::getPathTime, String.valueOf(date1Time));

        List<HitResult> hitResults = this.baseMapper.selectList(wrapper);


        if(hitResults.size()==0){

            return "3";
        }else {
            for (HitResult hit :hitResults) {
                isHit = hit.getIsHit();
                if (isHit.equals("1")){
                    return  "1";
                }
            }
        }




        return isHit;

    }
}
