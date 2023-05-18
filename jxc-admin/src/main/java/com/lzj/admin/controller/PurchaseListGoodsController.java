package com.lzj.admin.controller;

import com.lzj.admin.query.PurchaseListGoodQuery;
import com.lzj.admin.query.PurchaseListQuery;
import com.lzj.admin.service.IPurchaseListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 进货单商品表 前端控制器
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-17 06:40:05
 */
@Controller
@RequestMapping("/purchaseListGoods")
public class PurchaseListGoodsController {
    @Resource
    private IPurchaseListGoodsService purchaseListGoodsService;


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> purchaseListGoods(PurchaseListGoodQuery purchaseListGoodQuery){
        return purchaseListGoodsService.purchaseListGoods(purchaseListGoodQuery);

    }
}
