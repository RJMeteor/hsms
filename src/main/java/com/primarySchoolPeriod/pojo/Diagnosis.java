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
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 诊断信息表
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Getter
@Setter
@ApiModel(value = "诊断信息类")
public class Diagnosis implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 什么病，可变长度30，不可为空
     */
    @Length(max = 10,message = "病长度不能超过{max}")
    @NotNull(message = "什么病不能为空")
    @ApiModelProperty(value = "什么病",required = true,dataType = "string")
    private String name;

    /**
     * 患者表ID，不可为空
     */
    @NotNull(message = "患者不能为空")
    @ApiModelProperty(value = "患者表ID",required = true,dataType = "int")
    private Integer patient;

    /**
     * 会诊科室ID，不可为空
     */
    @NotNull(message = "会诊科室不能为空")
    @ApiModelProperty(value = "会诊科室ID",required = true,dataType = "int")
    private Integer office;

    /**
     * 住院床ID，不可为空
     */

    @NotNull(message = "住院床不能为空")
    @ApiModelProperty(value = "住院床ID",required = true,dataType = "int")
    private Integer bad;

    /**
     * 金额，不可为空
     */

    @NotNull(message = "金额不能为空")
    @ApiModelProperty(value = "金额",required = true,dataType = "double")
    private Double money;

    /**
     * 药品表ID，不可为空
     */
    @NotNull(message = "药品表不能为空")
    @ApiModelProperty(value = "药品表ID",required = true,dataType = "int")
    private Integer drug;

    /**
     * 药品数量，不可为空
     */
    @NotNull(message = "药品数量不能为空")
    @ApiModelProperty(value = "药品数量",required = true,dataType = "int")
    private Integer drugCount;

    /**
     * 是否付款,0：未付款，1：已付款，不可为空
     */
    @NotNull(message = "是否付款不能为空")
    @Min(value = 0,message = "0：未付款，1：已付款")
    @Max(value = 1,message = "0：未付款，1：已付款")
    @ApiModelProperty(value = "是否付款,0：未付款，1：已付款",required = true,dataType = "int")
    private Integer payment;

    /**
     * 诊断内容，可变长度100
     */
    @Length(max = 100,message = "诊断内容长度不能超过{max}")
    @ApiModelProperty(value = "诊断内容",dataType = "string")
    private String diagnosis;

    /**
     * 诊断时间，不可为空
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
    private Bad badPojo;

    @TableField(exist = false)
    private Patient patientPojo;

    @TableField(exist = false)
    private Office officePojo;

    @TableField(exist = false)
    private Drug drugPojo;

    @TableField(exist = false)
    private List<Bad> badList;

    @TableField(exist = false)
    private List<Patient> patientList;

    @TableField(exist = false)
    private List<Office> officeList;

    @TableField(exist = false)
    private List<Drug> drugList;
}
