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
 * 退货单商品表
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 07:23:35
 */
@Getter
@Setter
@TableName("t_return_list_goods")
@ApiModel(value = "ReturnListGoods对象", description = "退货单商品表")
public class ReturnListGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("商品编码")
    @TableField("code")
    private String code;

    @ApiModelProperty("商品型号")
    @TableField("model")
    private String model;

    @ApiModelProperty("商品名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("数量")
    @TableField("num")
    private Integer num;

    @ApiModelProperty("单价")
    @TableField("price")
    private Double price;

    @ApiModelProperty("总价")
    @TableField("total")
    private Double total;

    @ApiModelProperty("单位")
    @TableField("unit")
    private String unit;

    @ApiModelProperty("退货单id")
    @TableField("return_list_id")
    private Integer returnListId;

    @ApiModelProperty("商品类别")
    @TableField("type_id")
    private Integer typeId;

    @ApiModelProperty("商品id")
    @TableField("goods_id")
    private Integer goodsId;
}
