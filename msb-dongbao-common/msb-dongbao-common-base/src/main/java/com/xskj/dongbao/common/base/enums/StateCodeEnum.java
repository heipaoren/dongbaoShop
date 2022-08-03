package com.xskj.dongbao.common.base.enums;

public enum StateCodeEnum {

    SUCCESS(200,"请求成功"),
    FAIL(500,"请求失败");
    private int code;
    private String msg;
    public int getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    StateCodeEnum(int code, String msg){
        this.code=code;
        this.msg=msg;
    }
}
