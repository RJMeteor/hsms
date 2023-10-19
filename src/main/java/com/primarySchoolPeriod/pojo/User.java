package com.primarySchoolPeriod.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.primarySchoolPeriod.util.validators.annotation.Phone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户管理
 * </p>
 *
 * @author 作者
 * @since 2023-07-16
 */
@Getter
@Setter
@ApiModel(value = "用户管理类")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名，可变长度20
     */
    @Length(max = 20, message = "用户名长度不能超过{max}")
    @NotNull(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", dataType = "string")
    private String name;

    /**
     * 真实名字，可变长度20，不可为空
     */
    @Length(max = 20, message = "真实名字长度不能超过{max}")
    @NotNull(message = "真实名字不能为空")
    @ApiModelProperty(value = "真实名字", required = true, dataType = "string")
    private String rreal;

    /**
     * 电话，定长度11，不可为空
     */
    @Phone
    @Length(max = 11, min = 11, message = "电话长度不能超过{max},不小于{min}")
    @NotNull(message = "电话不能为空")
    @ApiModelProperty(value = "电话", required = true, dataType = "string")
    private String phone;

    /**
     * 密码，长度200，不可为空
     */
    @Length(max = 200, message = "电话长度不能超过{max},不小于{min}")
    @NotNull(message = "电话不能为空")
    @ApiModelProperty(value = "电话", required = true, dataType = "string")
    private String password;
    /**
     * 邮箱，可变长度30
     */
    @Length(max = 30, message = "邮箱长度不能超过{max}")
    @Email(message = "邮箱格式错误")
    @ApiModelProperty(value = "邮箱", dataType = "string")
    private String email;

    /**
     * 家庭地址，可变长度100
     */
    @Length(max = 100, message = "家庭地址长度不能超过{max}")
    @ApiModelProperty(value = "家庭地址", dataType = "string")
    private String homeAddress;

    /**
     * 学历，可变长度10，不可为空
     */
    @Length(max = 10, message = "学历长度不能超过{max}")
    @NotNull(message = "学历不能为空")
    @ApiModelProperty(value = "学历", required = true, dataType = "string")
    private String educationBackground;

    /**
     * 毕业院校，可变长度20，不可为空
     */
    @Length(max = 20, message = "毕业院校长度不能超过{max}")
    @NotNull(message = "毕业院校不能为空")
    @ApiModelProperty(value = "毕业院校", required = true, dataType = "string")
    private String school;

    /**
     * 身份证号，定长度18，不可为空
     */
    @Length(max = 18, min = 18, message = "身份证号长度不能超过{max},不小于{min}")
    @NotNull(message = "身份证号不能为空")
    @ApiModelProperty(value = "身份证号", required = true, dataType = "string")
    private String identityCard;

    /**
     * 银行账号，可变长度19，不可为空
     */
    @Length(max = 19, message = "银行账号长度不能超过{max}")
    @NotNull(message = "银行账号不能为空")
    @ApiModelProperty(value = "银行账号", required = true, dataType = "string")
    private String bankAccount;

    /**
     * 部门管理ID，不可为空
     */
    @NotNull(message = "部门不能为空")
    @ApiModelProperty(value = "部门管理ID", required = true, dataType = "int")
    private Integer department;

    /**
     * 性别，0：男，1：女，不可为空
     */
    @Min(value = 0, message = "0：男，1：女")
    @Max(value = 1, message = "0：男，1：女")
    @NotNull(message = "性别不能为空")
    @ApiModelProperty(value = "性别，0：男，1：女", required = true, dataType = "int")
    private Integer sex;

    /**
     * 职位管理ID，不可为空
     */
    @NotNull(message = "职位不能为空")
    @ApiModelProperty(value = "职位管理ID", required = true, dataType = "int")
    private Integer job;

    /**
     * 角色管理ID，不可为空
     */
    @NotNull(message = "角色不能为空")
    @ApiModelProperty(value = "角色管理ID", required = true, dataType = "int")
    private Integer role;

    /**
     * 工资，不可为空
     */
    @NotNull(message = "工资不能为空")
    @ApiModelProperty(value = "工资", required = true, dataType = "double")
    private Double salary;

    /**
     * 入职时间，不可为空
     */
    @NotNull(message = "入职时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'.000Z'")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'.000Z'", timezone = "GMT+8")
    @ApiModelProperty(value = "入职时间", required = true, dataType = "date")
    private Date entryTime;

    /**
     * 生日时间
     */
    @NotNull(message = "生日时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'.000Z'")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'.000Z'", timezone = "GMT+8")
    @ApiModelProperty(value = "生日时间", dataType = "date")
    private Date birthdayTime;

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

    @TableField(exist = false)
    private String token;


    @TableField(exist = false)
    private String userId;


    /**
     * 0：未删除，1：已删除，不可为空
     */
    private Integer deleted;

    @TableField(exist = false)
    private Department departmentPojo;

    @TableField(exist = false)
    private Role rolePojo;

    @TableField(exist = false)
    private Job jobPojo;

    @TableField(exist = false)
    private List<Department> departmentList;

    @TableField(exist = false)
    private List<Role> roleList;

    @TableField(exist = false)
    private List<Job> jobList;

    @TableField(exist = false)
    private List<Interface> interfaceo;
}
