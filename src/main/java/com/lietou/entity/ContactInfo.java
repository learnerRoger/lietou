package com.lietou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * <p>
 * 联系信息表
 * </p>
 *
 * @author lietou
 * @since 2023-04-18
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("contact_info")
@ApiModel(value = "ContactInfo对象", description = "联系信息表")
public class ContactInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("联系信息ID(主键)")
    @TableId(value = "ci_id", type = IdType.AUTO)
    private Integer ciId;

    @ApiModelProperty("联系对象的姓名")
    @TableField("ci_name")
    private String ciName;

    @ApiModelProperty("联系日期")
    @TableField("ci_date")
    private Date ciDate;

    @ApiModelProperty("联系类型")
    @TableField("ci_type")
    private String ciType;

    @ApiModelProperty("人才状态")
    @TableField("ci_talentStatus")
    private String ciTalentstatus;

    @ApiModelProperty("下次联系日期")
    @TableField("ci_nextContactDate")
    private Date ciNextcontactdate;

    @ApiModelProperty("下次联系人")
    @TableField("ci_nextContactObject")
    private String ciNextcontactobject;

    @ApiModelProperty("所在项目ID")
    @TableField("ci_projectName")
    private String ciProjectname;

    @ApiModelProperty("评语")
    @TableField("ci_comment")
    private String ciComment;

    @ApiModelProperty("适合行业")
    @TableField("ci_suitableIndustry")
    private String ciSuitableindustry;

    @ApiModelProperty("适合职能1")
    @TableField("ci_suitablePosition")
    private String ciSuitableposition;

    @ApiModelProperty("适合职能2")
    @TableField("ci_suitablePosition2")
    private String ciSuitableposition2;

    @ApiModelProperty("期望工作地")
    @TableField("ci_expectedWorkPlace")
    private String ciExpectedworkplace;

    @ApiModelProperty("期望薪资")
    @TableField("ci_expectedSalary")
    private String ciExpectedsalary;

    @ApiModelProperty("报到日期")
    @TableField("ci_registerDate")
    private Date ciRegisterdate;

    @ApiModelProperty("创建人")
    @TableField("ci_creator")
    private String ciCreator;

    @ApiModelProperty("创建时间")
    @TableField("ci_createTime")
    private Timestamp ciCreatetime;

    @ApiModelProperty("更新人")
    @TableField("ci_updatePerson")
    private String ciUpdateperson;

    @ApiModelProperty("更新时间")
    @TableField("ci_updateTime")
    private Timestamp ciUpdatetime;


}
