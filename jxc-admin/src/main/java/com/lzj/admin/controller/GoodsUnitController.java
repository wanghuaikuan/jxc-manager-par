package com.lzj.admin.controller;

import com.lzj.admin.pojo.GoodsUnit;
import com.lzj.admin.service.IGoodsUnitService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品单位表 前端控制器
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-16 10:14:44
 */
@Controller
@RequestMapping("/goodsUnit")
public class GoodsUnitController {
@Resource
    private IGoodsUnitService goodsUnitService;

@RequestMapping("allGoodsUnits")
@ResponseBody
   public List<GoodsUnit> allGoodsUnit(){
       return goodsUnitService.list();
   }
}
