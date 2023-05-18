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
 * 菜单表
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-12 09:49:13
 */
@Getter
@Setter
@TableName("t_menu")
@ApiModel(value = "Menu对象", description = "菜单表")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("菜单图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty("菜单名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("节点类型")
    @TableField("state")
    private Integer state;

    @ApiModelProperty("菜单url")
    @TableField("url")
    private String url;

    @ApiModelProperty("上级菜单id")
    @TableField("p_id")
    private Integer pId;

    @ApiModelProperty("权限码")
    @TableField("acl_value")
    private String aclValue;

    @ApiModelProperty("菜单层级")
    @TableField("grade")
    private Integer grade;

    @ApiModelProperty("是否删除")
    @TableField("is_del")
    private Integer isDel;
}
