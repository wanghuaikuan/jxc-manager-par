package com.lzj.admin.controller;

import com.lzj.admin.query.CustomerReturnListGoodsQuery;
import com.lzj.admin.query.SaleListGoodsQuery;
import com.lzj.admin.service.ICustomerReturnListGoodsService;
import com.lzj.admin.service.ISaleListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 客户退货单商品表 前端控制器
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 10:23:33
 */
@Controller
@RequestMapping("/customerReturnListGoods")
public class CustomerReturnListGoodsController {

    @Resource
    private ICustomerReturnListGoodsService customerReturnListGoodsService;
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> SaleList(CustomerReturnListGoodsQuery customerReturnListGoodsQuery){
        return customerReturnListGoodsService.customerReturnGoodsList(customerReturnListGoodsQuery);

    }
}
