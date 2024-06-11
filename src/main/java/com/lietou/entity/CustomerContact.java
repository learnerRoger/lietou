package com.lietou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 联系人信息表
 * </p>
 *
 * @author lietou
 * @since 2023-04-18
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("customer_contact")
@ApiModel(value = "CustomerContact对象", description = "联系人信息表")
public class CustomerContact implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("联系人ID(主键)")
    @TableId(value = "cc_id", type = IdType.AUTO)
    private Integer ccId;

    @ApiModelProperty("联系人姓名")
    @TableField("cc_name")
    private String ccName;

    @ApiModelProperty("联系人性别")
    @TableField("cc_sex")
    private String ccSex;

    @ApiModelProperty("联系人年龄")
    @TableField("cc_age")
    private String ccAge;

    @ApiModelProperty("联系人出生地")
    @TableField("cc_placeOfBirth")
    private String ccPlaceofbirth;

    @ApiModelProperty("候选人出生日期")
    @TableField("cc_birthday")
    private Date ccBirthday;

    @ApiModelProperty("候选人婚姻状况")
    @TableField("cc_maritalStatus")
    private String ccMaritalstatus;

    @ApiModelProperty("联系人职位")
    @TableField("cc_position")
    private String ccPosition;

    @ApiModelProperty("联系人办公电话号码")
    @TableField("cc_officePhone")
    private String ccOfficephone;

    @ApiModelProperty("联系人手机")
    @TableField("cc_phone")
    private String ccPhone;

    @ApiModelProperty("联系人电子邮件")
    @TableField("cc_email")
    private String ccEmail;

    @ApiModelProperty("联系人负责的工作")
    @TableField("cc_jobs")
    private String ccJobs;

    @ApiModelProperty("联系人关心的重点事项")
    @TableField("cc_careAbout")
    private String ccCareabout;

    @ApiModelProperty("联系人的利益诉求")
    @TableField("cc_interests")
    private String ccInterests;

    @ApiModelProperty("创建人")
    @TableField("cc_creator")
    private String ccCreator;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    @TableField("cc_createTime")
    private Timestamp ccCreatetime;

    @ApiModelProperty("更新人")
    @TableField("cc_updatePerson")
    private String ccUpdateperson;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")

    @ApiModelProperty("更新时间")
    @TableField("cc_updateTime")
    private Timestamp ccUpdatetime;


}
