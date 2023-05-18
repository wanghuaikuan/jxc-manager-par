package com.lzj.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 进货单
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-17 02:59:48
 */
@Getter
@Setter
@TableName("t_purchase_list")
@ApiModel(value = "PurchaseList对象", description = "进货单")
public class PurchaseList implements Serializable {

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

    @ApiModelProperty("进货日期")
    @TableField("purchase_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date purchaseDate;

    @ApiModelProperty("备注")
    @TableField("remarks")
    private String remarks;

    @ApiModelProperty("交易状态")
    @TableField("state")
    private Integer state;

    @ApiModelProperty("进货单号")
    @TableField("purchase_number")
    private String purchaseNumber;

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
