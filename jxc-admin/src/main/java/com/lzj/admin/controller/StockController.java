package com.lzj.admin.controller;

import com.lzj.admin.model.RespBean;
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
@RequestMapping("stock")
public class StockController {
   @Resource
    private IGoodsService goodsService;

    @RequestMapping("/index")
    public String index(){
        return "stock/stock";
    }

    /**
     * 库存量为0的商品查询接口
     *
     * @param goodsQuery
     * @return
     */
    @RequestMapping("listNoInventoryQuantity")
    @ResponseBody
    public Map<String, Object> listNoInventoryQuantity(GoodsQuery goodsQuery) {
        goodsQuery.setType(1);
        return goodsService.goodsList(goodsQuery);
    }
    /**
     * 库存量为0的商品查询接口
     *
     * @param goodsQuery
     * @return
     */
    @RequestMapping("listHasInventoryQuantity")
    @ResponseBody
    public Map<String, Object> listHasInventoryQuantity(GoodsQuery goodsQuery) {
        goodsQuery.setType(2);
        return goodsService.goodsList(goodsQuery);
    }

    @RequestMapping("toUpdateGoodsInfoPage")
    public String toUpdateGoodsInfoPage(Integer gid, Model model){
        model.addAttribute("goods",goodsService.getById(gid));
        return "stock/goods_update";
    }


    @RequestMapping("updateStock")
    @ResponseBody
    public RespBean updateStock(Goods goods){
        goodsService.updateStock(goods);
        return RespBean.success("商品记录更新成功！");
    }

    @RequestMapping("deleteStock")
    @ResponseBody
    public RespBean deleteStock(Integer id){
        goodsService.deleteStock(id);
        return RespBean.success("商品删除成功！");
    }



}
