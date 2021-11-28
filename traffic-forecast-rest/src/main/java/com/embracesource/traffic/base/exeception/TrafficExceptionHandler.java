package com.embracesource.traffic.base.exeception;


import com.embracesource.traffic.base.utils.Result;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@ResponseBody
@ControllerAdvice
public class TrafficExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<?> defaultExceptionHandler(HttpServletRequest request, Exception e) {
        printlnStackTrace(e);
        Integer code;
        String message;
        if (e instanceof BindException || e instanceof MissingServletRequestParameterException) {
            message = "入参不能为空";
            return Result.deny(message);
        } else if (e instanceof NoHandlerFoundException) {
            code = ErrorEnums.SYSTEM_ERROR.getCode();
            message = ErrorEnums.SYSTEM_ERROR.getMsg() + "(" + request.getServletPath() + ")";
        } else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException msrpe = (MissingServletRequestParameterException) e;
            code = ErrorEnums.SYSTEM_ERROR.getCode();
            message = ErrorEnums.SYSTEM_ERROR.getMsg() + "(" + msrpe.getParameterName() + ")";
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException matme = (MethodArgumentTypeMismatchException) e;
            code = ErrorEnums.SYSTEM_ERROR.getCode();
            message = ErrorEnums.SYSTEM_ERROR.getMsg() + "(" + matme.getParameter().getParameterName() + ")";
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException manve = (MethodArgumentNotValidException) e;
            code = ErrorEnums.SYSTEM_ERROR.getCode();
            FieldError error = (FieldError) manve.getBindingResult().getAllErrors().get(0);
            StringBuilder buffer = new StringBuilder();
            message = buffer.append(ErrorEnums.SYSTEM_ERROR.getMsg()).append("(").append("参数名: ").append(error.getField())
                    .append(", 错误信息: ").append(error.getDefaultMessage()).append(")").toString();
        } else if (e instanceof NotFoundException) {
            code = ErrorEnums.SYSTEM_ERROR.getCode();
            message = ErrorEnums.SYSTEM_ERROR.getMsg() + "(" + e.getMessage() + ")";
        } else if (e instanceof IllegalStateException) {
            code = ErrorEnums.SYSTEM_ERROR.getCode();
            message = ErrorEnums.SYSTEM_ERROR.getMsg() + "(" + e.getMessage() + ")";
        } else if (e instanceof KeyAlreadyExistsException) {
            code = ErrorEnums.SYSTEM_ERROR.getCode();
            message = ErrorEnums.SYSTEM_ERROR.getMsg() + "(" + e.getMessage() + ")";
        } else {
            code = ErrorEnums.SYSTEM_ERROR.getCode();
            message = ErrorEnums.SYSTEM_ERROR + "(" + e.getMessage() + ")";
        }
        return Result.error(message, code);
    }


    /**
     * 打印异常堆栈信息
     *
     * @param e
     */
    private void printlnStackTrace(Exception e) {
        String logError = "\n********************************" +
                "\n异常类型: " + e.getClass() +
                "\n异常信息: " + e.getMessage();
        e.printStackTrace();
        logError += "\n" + "********************************";
        log.error(logError);
    }
}
