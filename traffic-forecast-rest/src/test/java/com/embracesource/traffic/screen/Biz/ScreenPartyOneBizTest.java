package com.embracesource.traffic.screen.Biz;

import com.embracesource.traffic.base.utils.Result;
import com.embracesource.traffic.screen.business.ScreenPartyOneBiz;
import com.embracesource.traffic.screen.dto.TrainsRunDayDataDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：wangshimin
 * @date ：Created in 2021-01-25 下午 03:45
 * @description：
 * @version:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScreenPartyOneBizTest {
    @Autowired
    ScreenPartyOneBiz screenPartyOneBiz;

    @Test
    public void testGetSize() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = null;
        try {
            parse = simpleDateFormat.parse("2021-01-19");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Result<TrainsRunDayDataDTO> screenSize = screenPartyOneBiz.getTrainsRunDayData();
        System.out.println(screenSize.getData());
    }
}
