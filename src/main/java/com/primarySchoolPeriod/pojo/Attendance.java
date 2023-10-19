package com.primarySchoolPeriod.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 考勤管理表
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Getter
@Setter
@ApiModel(value = "考勤类")
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 请假类型，可变长度10，不可为空
     */
    @Length(max = 10,message = "请假类型长度不能超过{max}")
    @NotNull(message = "请假类型不能为空")
    @ApiModelProperty(value = "请假类型",required = true,dataType = "string")
    private String type;

    /**
     * 用户管理表ID，不可为空
     */
    @NotNull(message = "用户不能为空")
    @ApiModelProperty(value = "用户管理表ID",required = true,dataType = "int")
    private Integer user;

    /**
     * 备注，可变长度30
     */
    @Length(max = 30,message = "备注长度不能超过{max}")
    @ApiModelProperty(value = "备注",dataType = "string")
    private String name;

    /**
     * 0：未迟到，1：迟到，不可为空
     */
    @Min(value = 0,message = "0：未迟到，1：迟到")
    @Max(value = 1,message = "0：未迟到，1：迟到")
    @ApiModelProperty(value = "是否迟到，0：未迟到，1：迟到",required = true,dataType = "int")
    private Integer astatus;

    /**
     * 请假截止时间，不可为空
     */
    @NotNull(message = "请假截止时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'.000Z'")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'.000Z'", timezone = "GMT+8")
    @ApiModelProperty(value = "请假截止时间",required = true,dataType = "date")
    private Date abortTime;

    /**
     * 请假时间，不可为空
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

    @TableField(exist = false)
    private List<User> userList;
    @TableField(exist = false)
    private User userPojo;

}
