package com.lietou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * 
 * </p>
 *
 * @author lietou
 * @since 2023-04-18
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("contracts")
@ApiModel(value = "Contracts对象", description = "")
public class Contracts implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("合同ID(主键)")
    @TableId(value = "con_id", type = IdType.AUTO)
    private Integer conId;

    @ApiModelProperty("合同名称")
    @TableField("con_name")
    private String conName;

    @ApiModelProperty("客户名称")
    @TableField("con_customName")
    private String conCustomname;

    @ApiModelProperty("关联项目ID")
    @TableField("con_projectID")
    private String conProjectid;

    @ApiModelProperty("关联项目名称")
    @TableField("con_projectName")
    private String conProjectname;

    @ApiModelProperty("项目开始时间")
    @TableField("con_startTime")
    private Date conStarttime;

    @ApiModelProperty("项目结束时间")
    @TableField("con_endTime")
    private Date conEndtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")

    @ApiModelProperty("开票时间")
    @TableField("con_billingTime")
    private Timestamp conBillingtime;

    @ApiModelProperty("支付方式")
    @TableField("con_payWay")
    private String conPayway;

    @ApiModelProperty("公司签约人")
    @TableField("con_companyContractor")
    private String conCompanycontractor;

    @ApiModelProperty("客户签约人")
    @TableField("con_customerContractor")
    private String conCustomercontractor;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")

    @ApiModelProperty("签约时间")
    @TableField("con_signDate")
    private Timestamp conSigndate;

    @ApiModelProperty("签约地点")
    @TableField("con_signAddress")
    private String conSignaddress;

    @ApiModelProperty("金额")
    @TableField("con_amount")
    private Double conAmount;

    @ApiModelProperty("执行状态")
    @TableField("con_status")
    private String conStatus;

    @ApiModelProperty("备注")
    @TableField("con_comment")
    private String conComment;

    @ApiModelProperty("创建人")
    @TableField("con_creator")
    private String conCreator;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    @TableField("con_createTime")
    private Timestamp conCreatetime;

    @ApiModelProperty("更新人")
    @TableField("con_updatePerson")
    private String conUpdateperson;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("更新时间")
    @TableField("con_updateTime")
    private Timestamp conUpdatetime;


}
