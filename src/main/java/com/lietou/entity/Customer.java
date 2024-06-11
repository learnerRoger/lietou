package com.lietou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  lietou
 * @Date 2023-04-18 
 */

@Getter
@Setter
@TableName("customer")
@ApiModel(value = "客户", description = "客户")
public class Customer implements Serializable {

	private static final long serialVersionUID =  1L;

	/**
	 * 客户ID（主键）
	 */
	@TableId(value = "c_id", type = IdType.AUTO)
	private Integer cId;

	/**
	 * 客户姓名
	 */
   	@TableField("c_name" )
	private String cName;

	/**
	 * 客户电话
	 */
   	@TableField("c_phone" )
	private String cPhone;

	/**
	 * 客户所在地区
	 */
   	@TableField("c_address" )
	private String cAddress;

	/**
	 * 客户来源
	 */
   	@TableField("c_source" )
	private String cSource;

	/**
	 * 客户行业
	 */
   	@TableField("c_industry" )
	private String cIndustry;

	/**
	 * 客户企业性质
	 */
   	@TableField("c_companyType" )
	private String cCompanyType;

	/**
	 * 客户重要程度
	 */
   	@TableField("c_importance" )
	private String cImportance;

	/**
	 * 客户的上级公司
	 */
   	@TableField("c_superiorCompany" )
	private String cSuperiorCompany;

	/**
	 * 客户公司简介
	 */
   	@TableField("c_comment" )
	private String cComment;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@TableField("c_createTime" )
	private Date cCreateTime;

	/**
	 * 更新人
	 */
   	@TableField("c_updatePerson" )
	private String cUpdatePerson;

	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@TableField("c_updateTime" )
	private Date cUpdateTime;

	/**
	 * 客户联系人ID
	 */
   	@TableField("c_contactID" )
	private int cContactId;
}
