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
 * 角色表
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-12 09:49:27
 */
@Getter
@Setter
@TableName("t_role")
@ApiModel(value = "Role对象", description = "角色表")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("备注")
    @TableField("bz")
    private String bz;

    @ApiModelProperty("角色名")
    @TableField("name")
    private String name;

    @ApiModelProperty("描述")
    @TableField("remarks")
    private String remarks;

    @ApiModelProperty("是否删除")
    @TableField("is_del")
    private Integer isDel;
}
