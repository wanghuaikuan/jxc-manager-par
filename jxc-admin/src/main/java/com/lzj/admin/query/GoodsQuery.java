package com.lzj.admin.query;

import lombok.Data;

import java.util.List;

@Data
public class GoodsQuery extends BaseQuery{
   private String goodsName;
   private Integer typeId;
   private List<Integer> typeIds;

   /**
    * 1:库存量=0
    * 2.库存量>0
    * 3.库存量<库存下限
    */
   private Integer type;
}
