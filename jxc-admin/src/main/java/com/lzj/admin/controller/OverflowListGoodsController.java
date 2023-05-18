package com.lzj.admin.controller;

import com.lzj.admin.query.OverflowListGoodQuery;
import com.lzj.admin.service.IOverflowListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 报溢单商品表 前端控制器
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 10:52:20
 */
@Controller
@RequestMapping("/overflowListGoods")
public class OverflowListGoodsController {


    @Resource
    private IOverflowListGoodsService overflowListGoodsService;


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> overflowListGoods(OverflowListGoodQuery overflowListGoodQuery){
        return overflowListGoodsService.overflowListGoods(overflowListGoodQuery);

    }
}
