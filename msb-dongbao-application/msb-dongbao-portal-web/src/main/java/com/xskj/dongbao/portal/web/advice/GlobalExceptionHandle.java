package com.xskj.dongbao.portal.web.advice;

import com.xskj.dongbao.common.base.exception.TokenException;
import com.xskj.dongbao.common.base.result.ResultWrapper;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(ArithmeticException.class)
    public ResultWrapper customException(){
        return ResultWrapper.builder().code(301).message("统一异常").build();
    }

    @ExceptionHandler(TokenException.class)
    public ResultWrapper loginException(Exception e){
        return ResultWrapper.getFailBuilder().message(e.getMessage()).build();
    }
}
