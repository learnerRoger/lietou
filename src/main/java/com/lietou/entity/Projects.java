package com.lietou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * <p>
 * 项目表
 * </p>
 *
 * @author lietou
 * @since 2023-04-18
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("projects")
@ApiModel(value = "Projects对象", description = "项目表")
public class Projects implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("项目ID(主键)")
    @TableId(value = "p_id", type = IdType.AUTO)
    private Integer pId;

    @ApiModelProperty("项目名称")
    @TableField("p_name")
    private String pName;

    @ApiModelProperty("项目开始日期")
    @TableField("p_startTime")
    private Date pStarttime;

    @ApiModelProperty("项目计划完成日期")
    @TableField("p_expectedCompleteTime")
    private Date pExpectedcompletetime;

    @ApiModelProperty("项目状态")
    @TableField("p_status")
    private String pStatus;

    @ApiModelProperty("优先级别")
    @TableField("p_priority")
    private String pPriority;

    @ApiModelProperty("客户名称")
    @TableField("p_customerName")
    private String pCustomername;

    @ApiModelProperty("收款状态")
    @TableField("p_receiptStatus")
    private String pReceiptstatus;

    @ApiModelProperty("客户经理")
    @TableField("p_customerManager")
    private String pCustomermanager;

    @ApiModelProperty("业务人员")
    @TableField("p_consultant")
    private String pConsultant;

    @ApiModelProperty("项目主管")
    @TableField("p_manager")
    private String pManager;

    @ApiModelProperty("客户联系人")
    @TableField("p_customerContact")
    private String pCustomercontact;

    @ApiModelProperty("公司简介")
    @TableField("p_companyIntroduction")
    private String pCompanyintroduction;

    @ApiModelProperty("汇报对象")
    @TableField("p_reportTo")
    private String pReportto;

    @ApiModelProperty("下属人数")
    @TableField("p_subordinatesCnt")
    private Integer pSubordinatescnt;

    @ApiModelProperty("工作地点")
    @TableField("p_workplace")
    private String pWorkplace;

    @ApiModelProperty("行业")
    @TableField("p_industry")
    private String pIndustry;

    @ApiModelProperty("适合职能")
    @TableField("p_suitableposition")
    private String pSuitableposition;

    @ApiModelProperty("外语要求")
    @TableField("p_languageRequirement")
    private String pLanguagerequirement;

    @ApiModelProperty("性别要求")
    @TableField("p_sexRequirement")
    private String pSexrequirement;

    @ApiModelProperty("学历要求")
    @TableField("p_educationRequirement")
    private String pEducationrequirement;

    @ApiModelProperty("职位级别")
    @TableField("p_positoinLevel")
    private String pPositoinlevel;

    @ApiModelProperty("职位类型")
    @TableField("p_positionType")
    private String pPositiontype;

    @ApiModelProperty("年龄要求")
    @TableField("p_ageRequirement")
    private String pAgerequirement;

    @ApiModelProperty("职位描述")
    @TableField("p_positionDescription")
    private String pPositiondescription;

    @ApiModelProperty("职位开放原因")
    @TableField("p_positionOpenReason")
    private String pPositionopenreason;

    @ApiModelProperty("薪金范围")
    @TableField("p_salaryRange")
    private String pSalaryrange;

    @ApiModelProperty("奖金")
    @TableField("p_bonus")
    private String pBonus;

    @ApiModelProperty("福利")
    @TableField("p_welfare")
    private String pWelfare;

    @ApiModelProperty("委托前的进展")
    @TableField("p_progressBeforeCommission")
    private String pProgressbeforecommission;


}
