package com.lzj.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 客户表
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-16 09:51:53
 */
@Getter
@Setter
@TableName("t_customer")
@ApiModel(value = "Customer对象", description = "客户表")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("客户地址")
    @TableField("address")
    private String address;

    @ApiModelProperty("联系人")
    @TableField("contact")
    private String contact;

    @ApiModelProperty("客户名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("客户联系电话")
    @TableField("number")
    private String number;

    @ApiModelProperty("备注")
    @TableField("remarks")
    private String remarks;

    @ApiModelProperty("是否删除")
    @TableField("is_del")
    private Integer isDel;
}
