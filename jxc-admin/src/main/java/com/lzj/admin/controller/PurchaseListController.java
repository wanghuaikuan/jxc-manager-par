package com.lzj.admin.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.pojo.PurchaseList;
import com.lzj.admin.pojo.PurchaseListGoods;
import com.lzj.admin.query.PurchaseListQuery;
import com.lzj.admin.service.IPurchaseListService;
import com.lzj.admin.service.IUserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.rmi.MarshalledObject;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 进货单 前端控制器
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-17 02:59:48
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseListController {
    @Resource
    private IPurchaseListService purchaseListService;

    @Resource
    private IUserService userService;

    @RequestMapping("/index")
//    获取进货单号
    public String index(Model model) throws Exception {
        String purchaseNumber=purchaseListService.getNextPurchaseNumber();
        model.addAttribute("purchaseNumber",purchaseNumber);
        return "purchase/purchase";
    }

    @RequestMapping("save")
    @ResponseBody
    public RespBean save(PurchaseList purchaseList, String goodsJson, Principal principal){
        String userName=principal.getName();
        purchaseList.setUserId(userService.findUserByUserName(userName).getId());
        List<PurchaseListGoods> plgList=new Gson().fromJson(goodsJson,new TypeToken<List<PurchaseListGoods>>(){}.getType());
        purchaseListService.savePurchaseList(purchaseList,plgList);
        return RespBean.success("商品进货成功！");
    }

    /**
     * 进货单查询也
     */
    @RequestMapping("searchPage")
    public String searchPage(){
        return "purchase/purchase_search";
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> purchaseList(PurchaseListQuery purchaseListQuery){
        return purchaseListService.purchaseList(purchaseListQuery);

    }




    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer id){
        purchaseListService.deletePurchaseList(id);
        return RespBean.success("删除成功！");
    }

    @RequestMapping("update")
    @ResponseBody
    public RespBean update(Integer pid){
        purchaseListService.updatePurchaseListStates(pid);
        return RespBean.success("结算成功！");
    }
    @RequestMapping("countPurchase")
    @ResponseBody
    public Map<String,Object> countPurchase(PurchaseListQuery purchaseListQuery){
        return purchaseListService.countPurchase(purchaseListQuery);
    }

}
