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
 * 商品单位表
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-16 10:14:44
 */
@Getter
@Setter
@TableName("t_goods_unit")
@ApiModel(value = "GoodsUnit对象", description = "商品单位表")
public class GoodsUnit implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("单位名")
    @TableField("name")
    private String name;
}
