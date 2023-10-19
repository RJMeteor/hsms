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

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 财务表
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Getter
@Setter
@ApiModel(value = "财务类")
public class Financing implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 财务类型,1：收入，0：消费，不可为空
     */
    @Min(value = 0,message = "0：收入，1：消费")
    @Max(value = 1,message = "0：收入，1：消费")
    @NotNull(message = "标签不能为空")
    @ApiModelProperty(value = "财务类型,1：收入，0：消费",required = true,dataType = "int")
    private Integer ftype;

    /**
     * 花费的说明，可变长度20，不可为空
     */
    @Length(max = 20,message = "花费的说明不能超过{max}")
    @NotNull(message = "花费的说明不能为空")
    @ApiModelProperty(value = "花费的说明",required = true,dataType = "string")
    private String name;

    /**
     * 添加时间，不可为空
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 部门ID，不可为空
     */
    @NotNull(message = "部门不能为空")
    @ApiModelProperty(value = "部门ID",required = true,dataType = "int")
    private Integer department;

    /**
     * 账户类型ID，不可为空
     */
    @NotNull(message = "账户类型不能为空")
    @ApiModelProperty(value = "账户类型ID",required = true,dataType = "int")
    private Integer account;

    /**
     * 金额，不可为空
     */
    @NotNull(message = "金额不能为空")
    @ApiModelProperty(value = "金额",required = true,dataType = "double")
    private Double money;

    /**
     * 0：失效，1：有效，不可为空
     */
    @NotNull(message = "状态不能为空")
    @Min(value = 0,message = "0：失效，1：有效")
    @Max(value = 1,message = "0：失效，1：有效")
    @ApiModelProperty(value = "0：失效，1：有效",required = true,dataType = "int")
    private Integer state;

    /**
     * 说明.可变长度100
     */
    @Length(max = 100,message = "说明长度不能超过{max}")
    @ApiModelProperty(value = "说明",dataType = "string")
    private String description;

    /**
     * 备注，可变长度100
     */
    @Length(max = 100,message = "备注长度不能超过{max}")
    @ApiModelProperty(value = "备注",dataType = "string")
    private String comment;

    /**
     * 更新时间，不可为空
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 办理人，可变长度20，不可为空
     */
    @Length(max = 20,message = "办理人名字长度不能超过{max}")
    private String person;

    /**
     * 0：未删除，1：已删除，不可为空
     */
    private Integer deleted;
    @TableField(exist = false)
    private Department departmentPojo;

    @TableField(exist = false)
    private  Account accountPojo;

    @TableField(exist = false)
    private  List<Department> departmentList;

    @TableField(exist = false)
    private  List<Account> accountList;
}
