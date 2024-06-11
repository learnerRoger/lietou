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
 * 候选人表
 * </p>
 *
 * @author lietou
 * @since 2023-04-18
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("candidate")
@ApiModel(value = "Candidate对象", description = "候选人表")
public class Candidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("候选人ID")
    @TableId(value = "t_id", type = IdType.AUTO)
    private Integer tId;

    @ApiModelProperty("候选人姓名")
    @TableField("t_name")
    private String tName;

    @ApiModelProperty("候选人性别")
    @TableField("t_sex")
    private String tSex;

    @ApiModelProperty("候选人人才类别")
    @TableField("t_type")
    private String tType;

    @ApiModelProperty("候选人人才级别")
    @TableField("t_degree")
    private String tDegree;

    @ApiModelProperty("候选人出生日期")
    @TableField("t_birthday")
    private Date tBirthday;

    @ApiModelProperty("候选人年龄")
    @TableField("t_age")
    private String tAge;

    @ApiModelProperty("候选人工作年限")
    @TableField("t_experience")
    private Integer tExperience;

    @ApiModelProperty("候选人毕业日期")
    @TableField("t_graduateDate")
    private Date tGraduatedate;

    @ApiModelProperty("候选人毕业院校")
    @TableField("t_graduateSchool")
    private String tGraduateschool;

    @ApiModelProperty("候选人专业")
    @TableField("t_major")
    private String tMajor;

    @ApiModelProperty("候选人学历")
    @TableField("t_education")
    private String tEducation;

    @ApiModelProperty("候选人电子邮件")
    @TableField("t_email")
    private String tEmail;

    @ApiModelProperty("候选人即时通信号码")
    @TableField("t_IM")
    private String tIm;

    @ApiModelProperty("候选人手机号码")
    @TableField("t_telephone")
    private String tTelephone;

    @ApiModelProperty("候选人办公电话号码")
    @TableField("t_officePhone")
    private String tOfficephone;

    @ApiModelProperty("候选人家庭号码")
    @TableField("t_homePhone")
    private String tHomephone;

    @ApiModelProperty("候选人身份证号码")
    @TableField("t_IDNumber")
    private String tIdnumber;

    @ApiModelProperty("候选人国籍")
    @TableField("t_nationality")
    private String tNationality;

    @ApiModelProperty("候选人民族")
    @TableField("t_nation")
    private String tNation;

    @ApiModelProperty("候选人身高(cm)")
    @TableField("t_height")
    private Integer tHeight;

    @ApiModelProperty("候选人体重(kg)")
    @TableField("t_weight")
    private Integer tWeight;

    @ApiModelProperty("候选人户口所在地")
    @TableField("t_residence")
    private String tResidence;

    @ApiModelProperty("候选人婚姻状况")
    @TableField("t_maritalStatus")
    private String tMaritalstatus;

    @ApiModelProperty("候选人籍贯")
    @TableField("t_nativePlace")
    private String tNativeplace;

    @ApiModelProperty("候选人现居住地")
    @TableField("t_address")
    private String tAddress;

    @ApiModelProperty("候选人现任职公司")
    @TableField("t_company")
    private String tCompany;

    @ApiModelProperty("候选人职位")
    @TableField("t_position")
    private String tPosition;

    @ApiModelProperty("候选人目前薪资")
    @TableField("t_salary")
    private String tSalary;

    @ApiModelProperty("候选人自我评价")
    @TableField("t_selfAssessment")
    private String tSelfassessment;

    @ApiModelProperty("候选人工作经历")
    @TableField("t_workExperience")
    private String tWorkexperience;

    @ApiModelProperty("候选人项目经验")
    @TableField("t_projectExperience")
    private String tProjectexperience;

    @ApiModelProperty("候选人培训经历")
    @TableField("t_trainingExperience")
    private String tTrainingexperience;

    @ApiModelProperty("候选人语言能力")
    @TableField("t_languageAbility")
    private String tLanguageability;

    @ApiModelProperty("候选人计算机能力")
    @TableField("t_computerAbility")
    private String tComputerability;

    @ApiModelProperty("候选人相关技能")
    @TableField("t_otherAbility")
    private String tOtherability;

    @ApiModelProperty("候选人备注")
    @TableField("t_comment")
    private String tComment;

    @ApiModelProperty("创建人")
    @TableField("t_creator")
    private String tCreator;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    @TableField("t_createTime")
    private Timestamp tCreatetime;

    @ApiModelProperty("更新人")
    @TableField("t_updatePerson")
    private String tUpdateperson;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("更新时间")
    @TableField("t_updateTime")
    private Timestamp tUpdatetime;


}
