package com.embracesource.traffic.freighttrafficcontrast.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.embracesource.traffic.freighttrafficcontrast.Entity.OfflineResult1;
import com.embracesource.traffic.freighttrafficcontrast.mapper.OfflineResultMapper1;
import org.springframework.stereotype.Service;

@Service
@DS("gp")
public class OfflineResultService1 extends ServiceImpl<OfflineResultMapper1, OfflineResult1> {

    public double getTlsj(String strStn,String curStn,String endStn){
        QueryWrapper<OfflineResult1> wrapper = new QueryWrapper<>();
        QueryWrapper<OfflineResult1> wrapper1 = new QueryWrapper<>();

        wrapper.lambda().eq(OfflineResult1::getStartStation,strStn)
                        .eq(OfflineResult1::getEndStation,endStn)
                        .eq(OfflineResult1::getCurrentStation,curStn)
                        .orderByDesc(OfflineResult1::getmNum).last("limit 1");
//                        .eq(OfflineResult::getnStation,nxtStn);
//
//
//        OfflineResult offlineResult = this.baseMapper.selectOne(wrapper);
//
//        int wNum = offlineResult.getwNum();
//        if(wNum !=0){
//            return offlineResult.getwTlMinutes();
//        }else {
//            return offlineResult.getaTlMinutes();
//        }




        OfflineResult1 offlineResult = this.baseMapper.selectOne(wrapper);

        if(offlineResult != null){

            int wNum = offlineResult.getmNum();

            if(wNum != 0){
                double v = offlineResult.getmTlMinutes();
                if (v==0){
                    return offlineResult.getaTlMinutes();
                }
                return v;
            }else {
                wrapper1.lambda().eq(OfflineResult1::getStartStation,strStn)
                        .eq(OfflineResult1::getEndStation,endStn)
                        .eq(OfflineResult1::getCurrentStation,curStn)
                        .orderByDesc(OfflineResult1::getaNum).last("limit 1");
                OfflineResult1 offlineResult1 = this.baseMapper.selectOne(wrapper1);

                return offlineResult1.getaTlMinutes();
            }

        }
        return 0;
    }

}
