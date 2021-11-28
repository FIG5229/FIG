package com.embracesource.traffic.datamanage.business;

import com.embracesource.traffic.base.pageHelper.PageInfo;
import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.screen.service.HitResultService;
import com.embracesource.traffic.datamanage.dto.HitResultRequestDTO;
import com.embracesource.traffic.datamanage.dto.HitResultResponseDTO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: traffic-forecast
 * @description: 命中数据业务处理类
 * @author: kevin.jia
 * @create: 2021-05-24 09:25
 **/

@Service
public class HitResultBiz {
    @Autowired
    HitResultService hitResultService;

    public Result<PageInfo<HitResultResponseDTO>> getHitResultList(HitResultRequestDTO hitResultRequestDTO) {
        hitResultRequestDTO.defaultParam();
        int pageNum = hitResultRequestDTO.getPageNum();
        int pageSize = hitResultRequestDTO.getPageSize();
        int startIndex = (pageNum-1)*pageSize;
        /*导致查询时间过长，临时删除排序  hit_id*/
        //PageHelper.startPage(hitResultRequestDTO.getPageNum(),hitResultRequestDTO.getPageSize());
        List<HitResultResponseDTO> list = hitResultService.getHitResultList(hitResultRequestDTO,startIndex,pageSize);
        int count = hitResultService.getHitResultListCount(hitResultRequestDTO);
        PageInfo<HitResultResponseDTO> pageInfo= new PageInfo();
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setSize(count);
        pageInfo.setTotal(count);
        int pages = 0;//页数
        pages = count/pageSize;
        if(count%pageSize!=0){
            pages+=1;
        }
        pageInfo.setPages(pages);
        pageInfo.setList(list);
        return Result.ok(pageInfo);
    }
}
