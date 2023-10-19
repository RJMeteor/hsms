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

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 药品管理表
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Getter
@Setter
@ApiModel(value = "药品类")
public class Drug implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 药品分类表ID,，不可为空
     */
    @NotNull(message = "药品分类不能为空")
    @ApiModelProperty(value = "药品分类表ID",required = true,dataType = "int")
    private Integer classification;

    /**
     * 药物名称，可变长度50，不可为空
     */
    @Length(max = 50,message = "药物名称长度不能超过{max}")
    @NotNull(message = "药物名称不能为空")
    @ApiModelProperty(value = "药物名称",required = true,dataType = "string")
    private String name;

    /**
     * 条形码，可变长度100，不可为空
     */
    @Length(max = 100,message = "条形码长度不能超过{max}")
    @NotNull(message = "条形码不能为空")
    @ApiModelProperty(value = "条形码",required = true,dataType = "string")
    private String barCode;

    /**
     * 型号，可变长度22，不可为空
     */
    @Length(max = 22,message = "型号长度不能超过{max}")
    @NotNull(message = "型号不能为空")
    @ApiModelProperty(value = "型号，",required = true,dataType = "string")
    private String model;

    /**
     * 单位，可变长度22，不可为空
     */
    @Length(max = 22,message = "单位长度不能超过{max}")
    @NotNull(message = "单位不能为空")
    @ApiModelProperty(value = "单位",required = true,dataType = "string")
    private String unit;

    /**
     * 数量，不可为空
     */
    @NotNull(message = "数量不能为空")
    @ApiModelProperty(value = "数量",required = true,dataType = "int")
    private Integer quantity;

    /**
     * 标签，可变长度20，不可为空
     */
    @Length(max = 20,message = "标签长度不能超过{max}")
    @NotNull(message = "标签不能为空")
    @ApiModelProperty(value = "标签",required = true,dataType = "string")
    private String label;

    /**
     * 供应商表ID，不可为空
     */
    @NotNull(message = "供应商不能为空")
    @ApiModelProperty(value = "供应商表ID",required = true,dataType = "int")
    private Integer vendor;

    /**
     * 客户单价，不可为空
     */
    @NotNull(message = "客户单价不能为空")
    @ApiModelProperty(value = "客户单价",required = true,dataType = "double")
    private Double customerPrice;

    /**
     * 成本价，不可为空
     */
    @NotNull(message = "成本价不能为空")
    @ApiModelProperty(value = "成本价",required = true,dataType = "double")
    private Double costPrice;

    /**
     * 特价，不可为空
     */
    @NotNull(message = "特价不能为空")
    @ApiModelProperty(value = "特价",required = true,dataType = "double")
    private Double special;

    /**
     * 推销，不可为空
     */
    @NotNull(message = "推销不能为空")
    @ApiModelProperty(value = "推销",required = true,dataType = "int")
    private Integer whether;

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

    @TableField(exist = false)
    private Classification classificationPojo;

    @TableField(exist = false)
    private Vendor vendorPojo;

    @TableField(exist = false)
    private List<Classification> classificationList;

    @TableField(exist = false)
    private List<Vendor> vendorList;
}
