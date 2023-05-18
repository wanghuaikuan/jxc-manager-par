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
 * 供应商表
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-15 08:19:57
 */
@Getter
@Setter
@TableName("t_supplier")
@ApiModel(value = "Supplier对象", description = "供应商表")
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("联系地址")
    @TableField("address")
    private String address;

    @ApiModelProperty("联系人")
    @TableField("contact")
    private String contact;

    @ApiModelProperty("供应商名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("联系电话")
    @TableField("number")
    private String number;

    @ApiModelProperty("备注")
    @TableField("remarks")
    private String remarks;

    @ApiModelProperty("是否删除")
    @TableField("is_del")
    private Integer isDel;
}
