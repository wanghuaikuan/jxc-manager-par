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
 * 角色菜单表
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-14 07:05:35
 */
@Getter
@Setter
@TableName("t_role_menu")
@ApiModel(value = "RoleMenu对象", description = "角色菜单表")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("菜单id")
    @TableField("menu_id")
    private Integer menuId;

    @ApiModelProperty("角色id")
    @TableField("role_id")
    private Integer roleId;
}
