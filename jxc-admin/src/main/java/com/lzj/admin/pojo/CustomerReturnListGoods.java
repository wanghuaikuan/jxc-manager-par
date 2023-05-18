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
 * 客户退货单商品表
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 10:23:33
 */
@Getter
@Setter
@TableName("t_customer_return_list_goods")
@ApiModel(value = "CustomerReturnListGoods对象", description = "客户退货单商品表")
public class CustomerReturnListGoods implements Serializable {

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

    @ApiModelProperty("价格")
    @TableField("price")
    private Double price;

    @ApiModelProperty("总价")
    @TableField("total")
    private Double total;

    @ApiModelProperty("单位")
    @TableField("unit")
    private String unit;

    @ApiModelProperty("客户退货id")
    @TableField("customer_return_list_id")
    private Integer customerReturnListId;

    @ApiModelProperty("商品类别")
    @TableField("type_id")
    private Integer typeId;

    @ApiModelProperty("商品id")
    @TableField("goods_id")
    private Integer goodsId;
}
