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
 * 商品类别表
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-16 10:14:43
 */
@Getter
@Setter
@TableName("t_goods_type")
@ApiModel(value = "GoodsType对象", description = "商品类别表")
public class GoodsType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("类别名")
    @TableField("name")
    private String name;

    @ApiModelProperty("父级类别id")
    @TableField("p_id")
    private Integer pId;

    @ApiModelProperty("节点类型")
    @TableField("state")
    private Integer state;

    @ApiModelProperty("节点图标")
    @TableField("icon")
    private String icon;
}
