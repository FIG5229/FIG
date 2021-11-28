package com.embracesource.traffic.freighttrafficcontrast.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.embracesource.traffic.freighttrafficcontrast.Entity.OfflineResult;
import com.embracesource.traffic.freighttrafficcontrast.mapper.OfflineResultMapper;
import org.springframework.stereotype.Service;

@Service
@DS("gp")
public class OfflineResService extends ServiceImpl<OfflineResultMapper, OfflineResult> {

    public double getTlsj(String strStn,String curStn,String endStn){
        QueryWrapper<OfflineResult> wrapper = new QueryWrapper<>();
        QueryWrapper<OfflineResult> wrapper1 = new QueryWrapper<>();

        wrapper.lambda().eq(OfflineResult::getStartStation,strStn)
                        .eq(OfflineResult::getEndStation,endStn)
                        .eq(OfflineResult::getCurrentStation,curStn)
                        .orderByDesc(OfflineResult::getwNum).last("limit 1");
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




        OfflineResult offlineResult = this.baseMapper.selectOne(wrapper);

        if(offlineResult != null){

            int wNum = offlineResult.getwNum();

            if(wNum != 0){
                double v = offlineResult.getwTlMinutes();
                if (v==0){
                    return offlineResult.getaTlMinutes();
                }
                return v;
            }else {
                wrapper1.lambda().eq(OfflineResult::getStartStation,strStn)
                        .eq(OfflineResult::getEndStation,endStn)
                        .eq(OfflineResult::getCurrentStation,curStn)
                        .orderByDesc(OfflineResult::getaNum).last("limit 1");
                OfflineResult offlineResult1 = this.baseMapper.selectOne(wrapper1);

                return offlineResult1.getaTlMinutes();
            }

        }
        return 0;
    }

}
