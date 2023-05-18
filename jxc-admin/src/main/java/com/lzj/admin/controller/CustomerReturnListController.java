package com.lzj.admin.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.pojo.CustomerReturnList;
import com.lzj.admin.pojo.CustomerReturnListGoods;
import com.lzj.admin.query.CustomerReturnListQuery;
import com.lzj.admin.service.ICustomerReturnListService;
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
 * 客户退货单表 前端控制器
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 10:21:40
 */
@Controller
@RequestMapping("/customerReturn")
public class CustomerReturnListController {

    @Resource
    private ICustomerReturnListService customerReturnListService;

    @Resource
    private IUserService userService;
    /**
     * 客户退货主页
     */
    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("customerReturnNumber",customerReturnListService.getNextCustomerReturnNumber()) ;
        return "customerReturn/customer_return";
    }
    @RequestMapping("save")
    @ResponseBody
    public RespBean save(CustomerReturnList customerReturnList, String goodsJson, Principal principal){
        String userName=principal.getName();
        customerReturnList.setUserId(userService.findUserByUserName(userName).getId());
        List<CustomerReturnListGoods> plgList=new Gson().fromJson(goodsJson,new TypeToken<List<CustomerReturnListGoods>>(){}.getType());
        customerReturnListService.saveCustomerReturnList(customerReturnList,plgList);
        return RespBean.success("商品退货入库成功！");
    }

    @RequestMapping("searchPage")
    public String searchPage(){

        return "customerReturn/customer_return_search";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> customerReturnList(CustomerReturnListQuery customerReturnListQuery){
        return customerReturnListService.customerReturnList(customerReturnListQuery);

    }
    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer id){
        customerReturnListService.deleteCustomerReturnList(id);
        return RespBean.success("删除成功！");
    }

}
