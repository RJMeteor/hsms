package com.primarySchoolPeriod.util;


import org.springframework.web.bind.MethodArgumentNotValidException;

public enum Metadata {
    HeadException(5001,"请求头错误"),
    SignatureException(5002,"token被篡改"),
    ExpiredJwtException(5003,"token过期了"),
    MalformedJwtException(5004,"token数据格式有误"),
    LogingException(5005,"登录失败"),
    GrantException(5007,"请重新登录"),
    NoLogin(4005,"免登录成功"),
    MethodArgumentNotValidException(5006,"字段错误");

    private Integer code;
    private String  message;

    Metadata(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
