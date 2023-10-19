package com.primarySchoolPeriod.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.primarySchoolPeriod.util.validators.annotation.Phone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 患者管理
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Getter
@Setter
@ApiModel(value = "患者类")
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 患者名称，可变长度32，不可为空
     */
    @Length(max = 32,message = "患者名称长度不能超过{max}")
    @NotNull(message = "患者名称不能为空")
    @ApiModelProperty(value = "患者名称",required = true,dataType = "string")
    private String name;

    /**
     * 性别，性别，0：男，1：女，不可为空
     */
    @NotNull(message = "性别不能为空")
    @Min(value = 0,message = "0：男，1：女")
    @Max(value = 1,message = "0：男，1：女")
    @ApiModelProperty(value = "性别，性别，0：男，1：女",required = true,dataType = "int")
    private Integer sex;

    /**
     * 年龄，不可为空
     */
    @NotNull(message = "年龄不能为空")
    @ApiModelProperty(value = "年龄",required = true,dataType = "int")
    private Integer age;

    /**
     * 手机号码，定长度11，不可为空
     */
    @Phone
    @Length(max = 11,min = 11,message = "手机号码长度不能超过{max},小于{min}")
    @NotNull(message = "手机号码不能为空")
    @ApiModelProperty(value = "手机号码",required = true,dataType = "string")
    private String phoneNumber;

    /**
     * 身份证号，定长度18，不可为空
     */
    @Length(max = 18,min = 18,message = "身份证号长度不能超过{max},小于{min}")
    @NotNull(message = "身份证号不能为空")
    @ApiModelProperty(value = "身份证号",required = true,dataType = "string")
    private String identityNumber;

    /**
     * 医保卡号，可变长度19，不可为空
     */
    @Length(max = 19, message = "医保卡号长度不能超过{max}")
    @NotNull(message = "医保卡号不能为空")
    @ApiModelProperty(value = "医保卡号",required = true,dataType = "string")
    private String cardNumber;

    /**
     * 用户表ID，不可为空
     */
    @NotNull(message = "用户不能为空")
    @ApiModelProperty(value = "用户表ID",required = true,dataType = "int")
    private Integer user;

    /**
     * 创建时间，不可为空
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间，不可为空
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 病历，可变长度50，不可为空
     */
    @Length(max = 50,message = "病历长度不能超过{max}")
    @NotNull(message = "病历不能为空")
    @ApiModelProperty(value = "病历",required = true,dataType = "string")
    private String caseHistory;

    /**
     * 0：未删除，1：已删除，不可为空
     */
    private Integer deleted;

    @TableField(exist = false)
    private User userPojo;

    @TableField(exist = false)
    private  List<User> userList;

}
