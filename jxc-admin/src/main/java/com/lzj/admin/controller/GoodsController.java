package com.lzj.admin.controller;

import com.lzj.admin.model.RespBean;
import com.lzj.admin.pojo.Goods;
import com.lzj.admin.query.GoodsQuery;
import com.lzj.admin.service.IGoodsService;
import com.lzj.admin.service.IGoodsTypeService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-16 10:14:04
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private IGoodsService goodsService;
    @Resource
    private IGoodsTypeService goodsTypeService;
    /**
     * 商品管理主页
     */
    @RequestMapping("/index")
    public String index(){
        return "goods/goods";
    }

    /**
     * 商品列表查询接口
     *
     * @param goodsQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> goodsList(GoodsQuery goodsQuery) {
        return goodsService.goodsList(goodsQuery);
    }

    /**
     * 商品添加与更新
     * @param id
     * @param typeId
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateGoodsPage")
    public String addOrUpdateGoodsPage(Integer id, Integer typeId,Model model){
        if(null!=id){
            Goods goods=goodsService.getById(id);
            model.addAttribute("goods",goodsService.getById(id));
            model.addAttribute("goodsType",goodsTypeService.getById(goods.getTypeId()));
        }else{
            if(null!=typeId){
                model.addAttribute("goodsType",goodsTypeService.getById(typeId));
            }
        }
        return "goods/add_update";
    }
    @RequestMapping("toGoodsTypePage")
    public String toGoodsTypePage(Integer typeId,Model model){
        if(null!=typeId){
            model.addAttribute("typeId",typeId);
        }
        return "goods/goods_type";
    }

    @RequestMapping("save")
    @ResponseBody
    public RespBean saveGoods(Goods goods){
        goodsService.saveGoods(goods);
        return RespBean.success("商品添加成功！");
    }
    @RequestMapping("update")
    @ResponseBody
    public RespBean updateGoods(Goods goods){
        goodsService.updateGoods(goods);
        return RespBean.success("商品更新成功！");
    }
    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteGoods(Integer id){
        goodsService.deleteGoods(id);
        return RespBean.success("商品删除成功！");
    }

}
