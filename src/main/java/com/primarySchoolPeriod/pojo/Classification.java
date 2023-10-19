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

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 药品分类表
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Getter
@Setter
@ApiModel(value = "药品分类类")
public class Classification implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称，可变长度10，不可为空
     */
    @Length(max = 10,message = "药品名称长度不能超过{max}")
    @NotNull(message = "药品名称不能为空")
    @ApiModelProperty(value = "药品名称",required = true,dataType = "string")
    private String name;

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
