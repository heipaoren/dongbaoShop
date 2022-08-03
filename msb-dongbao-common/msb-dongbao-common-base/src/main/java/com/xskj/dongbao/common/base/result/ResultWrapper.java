package com.xskj.dongbao.common.base.result;

import com.xskj.dongbao.common.base.enums.StateCodeEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ResultWrapper<T> implements Serializable {
    private int code;
    private String message;
    private T data;

    public static ResultWrapper.ResultWrapperBuilder getSuccessBuilder(){
        return ResultWrapper.builder().code(StateCodeEnum.SUCCESS.getCode()).message(StateCodeEnum.SUCCESS.getMsg());
    }
    public static ResultWrapper.ResultWrapperBuilder getFailBuilder(){
        return ResultWrapper.builder().code(StateCodeEnum.FAIL.getCode()).message(StateCodeEnum.FAIL.getMsg());
    }
}
