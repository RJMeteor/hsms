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
 * 部门管理
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Getter
@Setter
@ApiModel(value = "部门类")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 部门名称，可变长度20，不可为空
     */
    @Length(max = 10,message = "部门名称长度不能超过{max}")
    @NotNull(message = "部门名称不能为空")
    @ApiModelProperty(value = "部门名称",required = true,dataType = "string")
    private String name;

    /**
     * 电话，不可为空
     */
    @Phone
    @NotNull(message = "电话号码不能为空")
    @ApiModelProperty(value = "电话",required = true,dataType = "string")
    private String phone;

    /**
     * 邮箱，可变长度60，不可为空
     */
    @Email(message = "邮箱格式错误")
    @Length(max = 30,message = "邮箱长度不能超过{max}")
    @NotNull(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱",required = true,dataType = "string")
    private String email;

    /**
     * 地址，可变长度255
     */
    @Length(max = 255,message = "地址长度长度不能超过{max}")
    @ApiModelProperty(value = "地址",dataType = "string")
    private String address;

    /**
     * 上下班时间，可变长50
     */
    @Length(max = 50,message = "上下班时间长度不能超过{max}")
    @ApiModelProperty(value = "上下班时间",dataType = "string")
    private String commuting;

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
