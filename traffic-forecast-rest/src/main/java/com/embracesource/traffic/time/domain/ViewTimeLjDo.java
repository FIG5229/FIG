package com.embracesource.traffic.time.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value ="view_time_lj" )
public class ViewTimeLjDo implements Serializable {

    @TableField(value = "id" )
    private String id;

    @TableField(value = "station" )
    private String station;

    @TableField(value = "station_name" )
    private String stationName;

    @TableField(value = "lj" )
    private String lj;

    @TableField(value = "lj_name" )
    private String ljName;

    @TableField(value = "j_accrate" )
    private String jAccrate;

    @TableField(value = "create_time" )
    private Date createTime;

    public ViewTimeLjDo(String id){
        this.id = id;
    }

    public ViewTimeLjDo(String lj,String ljName){
        this.lj = lj;
        this.ljName = ljName;
    }

    public ViewTimeLjDo(String lj,String ljName,String station){
        this.lj = lj;
        this.ljName = ljName;
        this.station = station;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof ViewTimeLjDo){
            ViewTimeLjDo  timeDTO =  (ViewTimeLjDo)obj;
            return this.station.trim().equals(timeDTO.station.trim()) && this.lj.trim().equals(timeDTO.lj.trim());
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        return lj.hashCode();
    }
}

