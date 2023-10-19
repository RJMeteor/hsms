package com.primarySchoolPeriod.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 * 账号表
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Getter
@Setter
@ApiModel(value = "账号类")
public class Account {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账户名，可变长度50，不可为空
     */
    @Length(max = 50,message = "账户名长度不能超过{max}")
    @NotNull(message = "账户名不能为空")
    @ApiModelProperty(value = "账户名",required = true,dataType = "string")
    private String name;

    /**
     * 账户人,可变长度20，不可为空
     */
    @Length(max = 20,message = "账户人长度不能超过{max}")
    @NotNull(message = "账户人不能为空")
    @ApiModelProperty(value = "账户人",required = true,dataType = "string")
    private String person;

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
}
