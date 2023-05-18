package com.lzj.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-13 10:31:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user_role")
@ApiModel(value = "UserRole对象", description = "用户角色表")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("角色id")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Integer userId;
}
