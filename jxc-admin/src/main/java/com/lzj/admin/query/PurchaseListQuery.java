package com.lzj.admin.query;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

@Data
public class PurchaseListQuery extends BaseQuery {

    private String purchaseNumber;
    private Integer supplierId;
    private Integer state;
    private String startDate;
    private String endDate;

   private String goodsName;
   private Integer typeId;
   private List<Integer> typeIds;
   private Integer index;
}
