package com.lietou.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.lietou.annotation.ExcelColumn;
import com.lietou.enums.GenderEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class CustomerDetailVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("客户id")
    private Integer cId;

    @ExcelColumn("公司名称")
    @ApiModelProperty("客户名称")
    private String cName;

    @ExcelColumn("公司性质")
    @ApiModelProperty("公司性质")
    private String cCompanyType;

    @ExcelColumn("公司地址")
    @ApiModelProperty("公司地址")
    private String cAddress;

    @ApiModelProperty("联系人姓名")
    private String ccName;

    @ExcelColumn("联系人手机号码")
    @ApiModelProperty("联系人手机号码")
    private String ccPhone;

    @ApiModelProperty("联系人Email")
    private String ccEmail;

    @ExcelColumn("签约状态")
    @ApiModelProperty("签约状态")
    private String conStatus;

}
