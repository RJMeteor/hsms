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
 * 接口管理
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Getter
@Setter
@ApiModel(value = "接口类")
public class Interface implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色管理ID，不可为空
     */
    @NotNull(message = "角色不能为空")
    @ApiModelProperty(value = "角色管理ID",required = true,dataType = "int")
    private Integer role;

    /**
     * 标题，可变长度40，不可为空
     */
    @Length(max = 40,message = "标题长度不能超过{max}")
    @NotNull(message = "标题不能为空")
    @ApiModelProperty(value = "标题",required = true,dataType = "string")
    private String name;

    /**
     * 请求地址，可变长度255，不可为空
     */
    @Length(max = 255,message = "请求地址长度不能超过{max}")
    @NotNull(message = "请求地址不能为空")
    @ApiModelProperty(value = "请求地址",required = true,dataType = "string")
    private String requestAddress;

    @Length(max = 20,message = "请求英文名长度不能超过{max}")
    @NotNull(message = "请求英文名不能为空")
    @ApiModelProperty(value = "请求名",required = true,dataType = "string")
    private String pathName;

    @Length(max = 20,message = "请求中文名长度不能超过{max}")
    @NotNull(message = "请求中文名不能为空")
    @ApiModelProperty(value = "请求中文名",required = true,dataType = "string")
    private String metaName;


    @NotNull(message = "层级不能为空")
    @ApiModelProperty(value = "层级",required = true,dataType = "int")
    private Integer metaSort;



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
    private  Role rolePojo;

    @TableField(exist = false)
    private  List<Role> roleList;

}
