package com.lzj.admin.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.pojo.OverflowList;
import com.lzj.admin.pojo.OverflowListGoods;
import com.lzj.admin.query.OverflowListQuery;
import com.lzj.admin.service.IOverflowListService;
import com.lzj.admin.service.IUserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报溢单表 前端控制器
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 10:52:20
 */
@Controller
@RequestMapping("/overflow")
public class OverflowListController {


    @Resource
    private IUserService userService;

    @Resource
    private IOverflowListService overflowListService;
    /**
     * 商品报损主页
     */
    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("overflowNumber",overflowListService.getNextOverflowNumber());
        return "overflow/overflow";
    }
    @RequestMapping("save")
    @ResponseBody
    public RespBean save(OverflowList overflowList, String goodsJson, Principal principal){
        String userName=principal.getName();
        overflowList.setUserId(userService.findUserByUserName(userName).getId());
        List<OverflowListGoods> plgList=new Gson().fromJson(goodsJson,new TypeToken<List<OverflowListGoods>>(){}.getType());
        overflowListService.saveOverflowList(overflowList,plgList);
        return RespBean.success("商品报溢成功！");
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> overflowList(OverflowListQuery overflowListQuery){
        return overflowListService.overflowList(overflowListQuery);

    }
}
