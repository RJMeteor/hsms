package com.primarySchoolPeriod.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.primarySchoolPeriod.util.validators.annotation.Phone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 供应商表
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Getter
@Setter
@ApiModel(value = "供应商表类")
public class Vendor implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应商名，可变长度10，不可为空
     */
    @Length(max = 10, message = "供应商名长度不能超过{max}")
    @NotNull(message = "供应商名不能为空")
    @ApiModelProperty(value = "供应商名",required = true,dataType = "string")
    private String name;

    /**
     * 供应商级别
     */
    @ApiModelProperty(value = "供应商级别",dataType = "int")
    private Integer vendorLevel;

    /**
     * 供应商来源，可变长度20，不可为空
     */
    @Length(max = 20, message = "供应商来源长度不能超过{max}")
    @NotNull(message = "供应商来源不能为空")
    @ApiModelProperty(value = "供应商来源",required = true,dataType = "string")
    private String vendorSource;

    /**
     * 电话号码，定长度11，不可为空
     */
    @Phone
    @Length(max = 11,min = 11, message = "电话号码长度不能超过{max}，不小于{min}")
    @NotNull(message = "电话号码不能为空")
    @ApiModelProperty(value = "电话号码",required = true,dataType = "string")
    private String phone;

    /**
     * 微信，可变长度40，不可为空
     */
    @Length(max = 40, message = "微信长度不能超过{max}")
    @NotNull(message = "微信不能为空")
    @ApiModelProperty(value = "微信",required = true,dataType = "string")
    private String weixin;

    /**
     * 邮编，可变长度15，不可为空
     */
    @Length(max = 30, message = "邮编长度不能超过{max}")
    @NotNull(message = "邮编不能为空")
    @Email(message = "邮箱格式错误")
    @ApiModelProperty(value = "邮编",required = true,dataType = "string")
    private String postcode;

    /**
     * 地址，可变长度50，不可为空
     */
    @Length(max = 50, message = "地址长度不能超过{max}")
    @NotNull(message = "地址不能为空")
    @ApiModelProperty(value = "地址",required = true,dataType = "string")
    private String address;

    /**
     * 营业执照，可变长度50，不可为空
     */
    @Length(max = 50, message = "营业执照长度不能超过{max}")
    @NotNull(message = "营业执照不能为空")
    @ApiModelProperty(value = "营业执照",required = true,dataType = "string")
    private String businessLicense;

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
     * 0：未删除，1：已删除，不可为空
     */
    private Integer deleted;


}
