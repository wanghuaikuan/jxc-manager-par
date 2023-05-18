package com.lzj.admin.controller;

import com.lzj.admin.query.SaleListGoodsQuery;
import com.lzj.admin.service.ISaleListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 销售单商品表 前端控制器
 * </p>
 * @author 王怀宽
 * @since 2023-04-18 10:59:14
 */
@Controller
@RequestMapping("/saleListGoods")
public class SaleListGoodsController {
    @Resource
    private ISaleListGoodsService saleListGoodsService;
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> SaleList(SaleListGoodsQuery saleListGoodsQuery){
        return saleListGoodsService.saleGoodsList(saleListGoodsQuery);

    }


}
