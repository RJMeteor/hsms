package com.primarySchoolPeriod.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

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
 * 住院床表
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Getter
@Setter
@ApiModel(value = "住院床类")
public class Bad implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /*
    * 床位，不可为空,可变长度15
    * */
    @NotNull(message = "床位，不可为空")
    @Length(max = 15,message = "床位最大长度不能超过{max}")
    @ApiModelProperty(value = "床位",required = true,dataType = "string")
    private String name;
    /**
     * 0：未占用，1：占用，不可为空
     */
    @Min(value = 0,message = "0：未占用，1：占用")
    @Max(value = 1,message = "0：未占用，1：占用")
    @ApiModelProperty(value = "是否正在使用，0：未占用，1：占用",required = true,dataType = "int")
    private Integer status;

    /**
     * 创建时间,不可为空
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间,不可为空
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 0：未删除，1：已删除,不可为空
     */
    private Integer deleted;


}
