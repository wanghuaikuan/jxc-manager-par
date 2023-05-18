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
 * 商品表
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-16 10:14:04
 */
@Getter
@Setter
@TableName("t_goods")
@ApiModel(value = "Goods对象", description = "商品表")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("商品编码")
    @TableField("code")
    private String code;

    @ApiModelProperty("库存数量")
    @TableField("inventory_quantity")
    private Integer inventoryQuantity;

    @ApiModelProperty("库存下限")
    @TableField("min_num")
    private Integer minNum;

    @ApiModelProperty("商品型号")
    @TableField("model")
    private String model;

    @ApiModelProperty("商品名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("生产产商")
    @TableField("producer")
    private String producer;

    @ApiModelProperty("采购价格")
    @TableField("purchasing_price")
    private Double purchasingPrice;

    @ApiModelProperty("备注")
    @TableField("remarks")
    private String remarks;

    @ApiModelProperty("出售价格")
    @TableField("selling_price")
    private Double sellingPrice;

    @ApiModelProperty("商品单位")
    @TableField("unit")
    private String unit;

    @ApiModelProperty("商品类别")
    @TableField("type_id")
    private Integer typeId;

    @ApiModelProperty("商品状态")
    @TableField("state")
    private Integer state;

    @ApiModelProperty("上次采购价格")
    @TableField("last_purchasing_price")
    private Double lastPurchasingPrice;

    @ApiModelProperty("是否删除")
    @TableField("is_del")
    private Integer isDel;

    @ApiModelProperty("级别")
    @TableField(exist = false)
    private String unitName;

    @ApiModelProperty("种类")
    @TableField(exist = false)
    private String typeName;

    @ApiModelProperty("销售总数")
    @TableField(exist = false)
    private Integer saleTotal;

}
