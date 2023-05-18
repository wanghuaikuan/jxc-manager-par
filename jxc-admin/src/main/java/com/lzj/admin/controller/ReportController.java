package com.lzj.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("report")
public class ReportController {


    @RequestMapping("countSupplier")
    public String countSupplier(){
          return "count/supplier";
    }

    @RequestMapping("countCustomer")
    public String countCustomer(){
        return "count/customer";
    }

    /**
     * 商品采购统计
     * @return
     */
    @RequestMapping("countPurchase")
    public String countPurchase(){
        return "count/purchase";
    }


    /**
     * 商品销售统计
     * @return
     */
    @RequestMapping("countSale")
    public String countSale(){
        return "count/sale";
    }
    /**
     * 商品日销售统计
     * @return
     */
    @RequestMapping("countDaySale")
    public String countDaySale(){
        return "count/day_sale";
    }
    /**
     * 商品日销售统计
     * @return
     */
    @RequestMapping("countMonthSale")
    public String countMonthSale(){
        return "count/month_sale";
    }

}
