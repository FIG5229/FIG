package com.embracesource.traffic.screen.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

@Data
@TableName(value = "ajb_dispose_all")
public class AjbDisposeAllDO implements Serializable {

    private static final long serialVersionUID = -7462227515870792294L;

    @TableField(value = "year_ajb")
    private Integer yearAjb;

    @TableField(value = "ajrq")
    private String ajrq;

    @TableField(value = "ljsbsj")
    private String ljsbsj;

    @TableField(value = "jubie")
    private String jubie;

    @TableField(value = "xianbie")
    private String xianbie;

    @TableField(value = "shigufenlei")
    private String shigufenlei;

    @TableField(value = "fssj")
    private String fssj;

    @TableField(value = "jssj")
    private String jssj;

    @TableField(value = "ktsj")
    private String ktsj;

    @TableField(value = "dwhccs")
    private String dwhccs;

    @TableField(value = "dwkccs")
    private String dwkccs;

    @TableField(value = "cc")
    private String cc;

    @TableField(value = "sggk")
    private String sggk;

    @TableField(value = "ssd")
    private String ssd;

    @TableField(value = "fsdd")
    private String fsdd;

    @TableField(value = "wait_chouChe")
    private String waitChouche;

    @TableField(value = "cc_model")
    private String ccModel;

    @TableField(value = "gz_type_model")
    private String gzTypeModel;

    @TableField(value = "influence_cc_gt")
    private String influenceCcGt;

    @TableField(value = "influence_cc_ps")
    private String influenceCcPs;

    @TableField(value = "wd_time")
    private String wdTime;

    @TableField(value = "gz_type_model_2")
    private String gzTypeModel2;

    @TableField(value = "is_fengSuo")
    private Integer isFengsuo;

    @TableField(value = "isAffectRun")
    private Integer isaffectrun;

}