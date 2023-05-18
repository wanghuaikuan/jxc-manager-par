package com.lzj.admin.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.pojo.ReturnList;
import com.lzj.admin.pojo.ReturnListGoods;
import com.lzj.admin.query.ReturnListQuery;
import com.lzj.admin.service.IReturnListService;
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
 * 退货单表 前端控制器
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 07:02:37
 */
@Controller
@RequestMapping("/return")
public class ReturnListController {
  @Resource
  private IReturnListService returnListService;
    @Resource
    private IUserService userService;
    /**
     * 退回单页面
     * @return
     */
    @RequestMapping("/index")

    public String index(Model model){
        model.addAttribute("returnNumber",returnListService.getNextReturnNumber());
        return "return/return";
    }

    @RequestMapping("save")
    @ResponseBody
    public RespBean save(ReturnList returnList, String goodsJson, Principal principal){
        String userName=principal.getName();
        returnList.setUserId(userService.findUserByUserName(userName).getId());
        List<ReturnListGoods> rlgList=new Gson().fromJson(goodsJson,new TypeToken<List<ReturnListGoods>>(){}.getType());
        returnListService.saveReturnList(returnList,rlgList);
        return RespBean.success("商品退货成功！");
    }

    /**
     * 进货单查询也
     */
    @RequestMapping("searchPage")
    public String searchPage(){
        return "return/return_search";
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> ReturnList(ReturnListQuery returnListQuery){
        return returnListService.returnList(returnListQuery);

    }


    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer id){
        returnListService.deleteReturnList(id);
        return RespBean.success("删除成功！");
    }

}
