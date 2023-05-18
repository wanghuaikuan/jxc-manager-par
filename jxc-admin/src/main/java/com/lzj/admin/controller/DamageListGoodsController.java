package com.lzj.admin.controller;

import com.lzj.admin.query.DamageListGoodQuery;
import com.lzj.admin.service.IDamageListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 报损单商品表 前端控制器
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 08:27:56
 */
@Controller
@RequestMapping("/damageListGoods")
public class DamageListGoodsController {

    @Resource
    private IDamageListGoodsService damageListGoodsService;


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> damageListGoods(DamageListGoodQuery damageListGoodQuery){
        return damageListGoodsService.damageListGoods(damageListGoodQuery);

    }

}
