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
 * 客户退货单表
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 10:21:40
 */
@Getter
@Setter
@TableName("t_customer_return_list")
@ApiModel(value = "CustomerReturnList对象", description = "客户退货单表")
public class CustomerReturnList implements Serializable {

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

    @ApiModelProperty("退货日期")
    @TableField("customer_return_date")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date customerReturnDate;

    @ApiModelProperty("退货单号")
    @TableField("customer_return_number")
    private String customerReturnNumber;

    @ApiModelProperty("备注")
    @TableField("remarks")
    private String remarks;

    @ApiModelProperty("交易状态")
    @TableField("state")
    private Integer state;

    @ApiModelProperty("操作用户")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("客户id")
    @TableField("customer_id")
    private Integer customerId;

    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String customerName;
}
