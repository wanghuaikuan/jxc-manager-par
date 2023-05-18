package com.lzj.admin.controller;

import com.lzj.admin.dto.TreeDto;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.pojo.GoodsType;
import com.lzj.admin.query.GoodsQuery;
import com.lzj.admin.service.IGoodsTypeService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品类别表 前端控制器
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-16 10:14:43
 */
@Controller
@RequestMapping("/goodsType")
public class GoodsTypeController {
    @Resource
    private IGoodsTypeService goodsTypeService;
   @RequestMapping("queryAllGoodsTypes")
   @ResponseBody
    public List<TreeDto> queryAllGoodsTypes(Integer typeId){
        return goodsTypeService.queryAllGoodsTypes(typeId);
    }

    /**
     * 商品类型管理主页
     */
    @RequestMapping("/index")
    public String index(){
        return "goodsType/goods_type";
    }

    /**
     * 商品类型列表查询接口
     *
     * @param
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> goodsList() {
        return goodsTypeService.goodsTypeList();
    }

    /**
     *
     */
    @RequestMapping("/addGoodsTypePage")
    public String addGoodsTypePage(Model model,Integer pId){
        model.addAttribute("pId",pId);
        return "goodsType/add";
    }

    @RequestMapping("save")
    @ResponseBody
    public RespBean save(GoodsType goodsType) {
        goodsTypeService.saveGoodsType(goodsType);
        return RespBean.success("商品类型添加成功！");
    }
    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer id) {
        goodsTypeService.deleteGoodsType(id);
        return RespBean.success("商品类型删除成功！");
    }

}
