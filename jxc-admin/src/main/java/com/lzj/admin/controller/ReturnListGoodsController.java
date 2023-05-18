package com.lzj.admin.controller;

import com.lzj.admin.query.ReturnListGoodQuery;
import com.lzj.admin.service.IReturnListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 退货单商品表 前端控制器
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 07:23:35
 */
@Controller
@RequestMapping("/returnListGoods")
public class ReturnListGoodsController {


    @Resource
    private IReturnListGoodsService returnListGoodsService;


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> returnListGoods(ReturnListGoodQuery returnListGoodQuery){
        return returnListGoodsService.returnListGoods(returnListGoodQuery);

    }


    
}
