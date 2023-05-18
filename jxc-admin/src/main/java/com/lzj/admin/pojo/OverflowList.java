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
 * 报溢单表
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 10:52:20
 */
@Getter
@Setter
@TableName("t_overflow_list")
@ApiModel(value = "OverflowList对象", description = "报溢单表")
public class OverflowList implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("报溢日期")
    @TableField("overflow_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date overflowDate;

    @ApiModelProperty("报溢单号")
    @TableField("overflow_number")
    private String overflowNumber;

    @ApiModelProperty("备注")
    @TableField("remarks")
    private String remarks;

    @ApiModelProperty("操作用户id")
    @TableField("user_id")
    private Integer userId;

    @TableField(exist = false)
    private String userName;
}
