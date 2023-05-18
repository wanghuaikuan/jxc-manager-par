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
 * 报损单表
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 08:27:56
 */
@Getter
@Setter
@TableName("t_damage_list")
@ApiModel(value = "DamageList对象", description = "报损单表")
public class DamageList implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("报损日期")
    @TableField("damage_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date damageDate;

    @ApiModelProperty("报损单号")
    @TableField("damage_number")
    private String damageNumber;

    @ApiModelProperty("备注")
    @TableField("remarks")
    private String remarks;

    @ApiModelProperty("操作用户id")
    @TableField("user_id")
    private Integer userId;

    @TableField(exist = false)
    private String userName;

}
