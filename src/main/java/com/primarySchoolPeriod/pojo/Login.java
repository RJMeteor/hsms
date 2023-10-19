package com.primarySchoolPeriod.pojo;

import com.primarySchoolPeriod.util.validators.annotation.Phone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel(value = "登录类")
public class Login {
    /**
     * 电话，定长度11，不可为空
     */
    @Phone
    @Length(max = 11, min = 11, message = "电话长度不能超过{max},不小于{min}")
    @NotNull(message = "电话不能为空")
    @ApiModelProperty(value = "电话", required = true, dataType = "string")
    private String phone;

    /**
     * 密码，长度200，不可为空
     */
    @Length(max = 200, message = "电话长度不能超过{max}")
    @NotNull(message = "电话不能为空")
    @ApiModelProperty(value = "电话", required = true, dataType = "string")
    private String password;
}
