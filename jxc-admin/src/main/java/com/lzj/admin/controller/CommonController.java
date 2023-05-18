package com.lzj.admin.controller;


import com.lzj.admin.model.GoodsModel;
import com.lzj.admin.pojo.Goods;
import com.lzj.admin.query.GoodsQuery;
import com.lzj.admin.service.IGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("common")
public class CommonController {

    @Resource
    private IGoodsService goodsService;

    /**
     * 添加商品-选择商品页
     * @return
     */
    @RequestMapping("toSelectGoodsPage")
    public String toSelectGoodsPage(){
        return "common/goods";
    }

    @RequestMapping("toAddGoodsInfoPage")
    public String toGoodsInfoPage(Integer gid, Model model){
        model.addAttribute("goods",goodsService.getGoodsInfoById(gid));
        return "common/goods_add_update";
    }
    @RequestMapping("toUpdateGoodsInfoPage")
    public String toUpdateGoodsInfoPage(GoodsModel goodsmodel, Model model){
        Goods goods=goodsService.getGoodsInfoById(goodsmodel.getId());
        goodsmodel.setCode(goods.getCode());
        goodsmodel.setModel(goods.getModel());
        goodsmodel.setUnit(goods.getUnit());
        goodsmodel.setName(goods.getName());
        goodsmodel.setTypeName(goods.getTypeName());
        goodsmodel.setLastPurchasingPrice(goods.getLastPurchasingPrice());
        goodsmodel.setInventoryQuantity(goods.getInventoryQuantity());
        model.addAttribute("goods",goodsmodel);
        model.addAttribute("flag",1);

        return "common/goods_add_update";
    }

    /**
     * 當前庫存頁面
     */
    @RequestMapping("toGoodsStockPage")
    public String toGoodsStockPage(){
        return "common/stock_search";
    }


    @RequestMapping("stockList")
    @ResponseBody
    public Map<String,Object> stockList(GoodsQuery goodsQuery){
        return goodsService.stockList(goodsQuery);
    }


    /**
     * 商品报损|报溢查询页
     * @return
     */
    @RequestMapping("toDamageOverflowSearchPage")
    public String toDamageOverflowSearchPage(){
        return "common/damage_overflow_search";
    }

    /**
     * 商品报警
     */
    @RequestMapping("alarmPage")
    public String alarm(){
        return "common/alarm";
    }


    @RequestMapping("listAlarm")
    @ResponseBody
    public Map<String,Object> listAlarm(GoodsQuery goodsQuery){
        goodsQuery.setType(3);
        return goodsService.goodsList(goodsQuery);
    }



}
