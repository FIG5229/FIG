/*
 * Copyright 2018
 * All Right Reserved
 * Author：potter
 * Create Date：2018年1月15日
 */
package com.embracesource.traffic.base.utils;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private static final Integer SUCCESS_CODE = 200;
    private static final Integer ERROR_CODE = 202;
    private static final Integer DENY_CODE = 201;

    private static final String SUCCESS_MSG = "success";
    private static final String ERROR_MSG = "error";
    private static final String DENY_MSG = "deny";


    @ApiModelProperty(value = "响应码")
    private Integer code;

    @ApiModelProperty(value = "描述")
    private String message;

    @ApiModelProperty(value = "结果集")
    private T data;

    public Result() {
        super();
    }

    public static <T> Result<T> ok() {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS_CODE);
        return result;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = ok();
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static <T> Result<T> ok(String message, T data) {
        Result<T> r = new Result<>();
        r.setCode(SUCCESS_CODE);
        r.setData(data);
        r.setMessage(message != null ? message : SUCCESS_MSG);
        return r;
    }

    public static <T> Result<T> deny(String message) {
        Result<T> r = new Result<>();
        r.setCode(DENY_CODE);
        r.setMessage(message != null ? message : DENY_MSG);
        return r;
    }

    public static <T> Result<T> error() {
        Result<T> r = new Result<>();
        r.setCode(ERROR_CODE);
        r.setMessage(ERROR_MSG);
        return r;
    }

    public static <T> Result<T> error(T data) {
        Result<T> r = error();
        if (data != null) {
            r.setData(data);
        }
        return r;
    }

    public static <T> Result<T> error(String message, Integer code) {
        Result<T> r = new Result<>();
        r.setCode(code != null ? code : ERROR_CODE);
        r.setData(null);
        r.setMessage(message != null ? message : ERROR_MSG);
        return r;
    }
}
