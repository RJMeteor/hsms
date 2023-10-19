package com.primarySchoolPeriod.util;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class R<T> {
    private Integer code;
    private T data;

    public void success(T data){
        this.code = 200;
        this.data = data;
    }
    public void error(T data){
        this.code = 500;
        this.data = data;
    }
}
