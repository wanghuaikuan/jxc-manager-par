package com.lzj.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 退货单表
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 07:02:37
 */
@Getter
@Setter
@TableName("t_return_list")
@ApiModel(value = "ReturnList对象", description = "退货单表")
public class ReturnList implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("实付金额")
    @TableField("amount_paid")
    private Double amountPaid;

    @ApiModelProperty("应付金额")
    @TableField("amount_payable")
    private Double amountPayable;

    @ApiModelProperty("备注")
    @TableField("remarks")
    private String remarks;

    @ApiModelProperty("退货日期")
    @TableField("return_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;

    @ApiModelProperty("退货单号")
    @TableField("return_number")
    private String returnNumber;

    @ApiModelProperty("交易状态")
    @TableField("state")
    private Integer state;

    @ApiModelProperty("供应商")
    @TableField("supplier_id")
    private Integer supplierId;

    @ApiModelProperty("操作用户")
    @TableField("user_id")
    private Integer userId;


    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String supplierName;
}
