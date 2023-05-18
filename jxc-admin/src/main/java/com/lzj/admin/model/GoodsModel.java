package com.lzj.admin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoodsModel {

    @ApiModelProperty(value = "商品id")
    private Integer id;

    @ApiModelProperty(value = "商品编码")
    private String code;

    @ApiModelProperty(value = "商品型号")
    private String model;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "数量")
    private Integer num;

    @ApiModelProperty(value = "单价")
    private Float price;

    @ApiModelProperty(value = "总价")
    private Float total;

    @ApiModelProperty(value = "单位")
    private String unit;



    @ApiModelProperty(value = "商品类别")
    private Integer typeId;

    @ApiModelProperty(value = "商品类别名称")
    private String typeName;

    @ApiModelProperty(value = "上次进价")
    private Double lastPurchasingPrice;

    @ApiModelProperty(value = "库存")
    private Integer inventoryQuantity;

}