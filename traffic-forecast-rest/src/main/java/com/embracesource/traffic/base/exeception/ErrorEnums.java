package com.embracesource.traffic.base.exeception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnums {
    /**
     *
     */
    /*CANNOT_FOUND_ERROR(404, "资源无法找到"),
    MISSING_PARAMETER(400, "缺少参数"),
    ARG_TYPE_MISMATCH(607, "参数类型错误"),
    DATA_NOT_EXISTS(404, "数据不存在"),
    DATA_IS_EXISTS(406, "数据已存在"),
    INTERNAL_ERROR(500, "服务器内部错误"),

    METHOD_NOT_SUPPORTED(605, "请求方式错误"),
    CHECK_FAIL_MSG(606, "请求参数异常"),*/
    SYSTEM_ERROR(202, "系统异常"),
    BUSINESS_ERROR(201, "业务异常"),
    ;

    private final Integer code;
    private final String msg;
}
