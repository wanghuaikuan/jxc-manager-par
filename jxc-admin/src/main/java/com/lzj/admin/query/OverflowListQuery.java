package com.lzj.admin.query;

import lombok.Data;

@Data
public class OverflowListQuery extends BaseQuery{
    private String startDate;
    private String endDate;
}
