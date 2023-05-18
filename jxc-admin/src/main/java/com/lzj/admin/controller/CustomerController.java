package com.lzj.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.pojo.Customer;
import com.lzj.admin.pojo.Customer;
import com.lzj.admin.pojo.Supplier;
import com.lzj.admin.query.CustomerQuery;
import com.lzj.admin.query.CustomerQuery;
import com.lzj.admin.service.ICustomerService;
import com.lzj.admin.service.ICustomerService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-16 09:51:53
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private ICustomerService customerService;

    /**
     * 客户管理主页
     */
    @RequestMapping("/index")
    public String index(){
        return "customer/customer";
    }

    /**
     * 供应商列表查询接口
     *
     * @param customerQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody

    public Map<String, Object> customerList(CustomerQuery customerQuery) {
        return customerService.customerList(customerQuery);
    }


    @RequestMapping("addOrUpdateCustomerPage")
    public String addOrUpdateCustomerPage(Integer id, Model model){
        if(null!=id){
            model.addAttribute("customer",customerService.getById(id));
        }
        return "customer/add_update";
    }

    /**
     *
     * @param customer
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean saveCustomer(Customer customer){
        customerService.saveCustomer(customer);
        return RespBean.success("供应商添加成功！");
    }
    @RequestMapping("update")
    @ResponseBody
    public RespBean updateCustomer(Customer customer){
        customerService.updateCustomer(customer);
        return RespBean.success("供应商更新成功！");
    }
    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteCustomer(Integer[] ids){
        customerService.deleteCustomer(ids);
        return RespBean.success("供应商删除成功！");
    }

    /**
     * 在进货页面展示出所有的供货商
     * @return
     */
    @RequestMapping("allCustomers")
    @ResponseBody
    public List<Customer> allCustomers(){
        return customerService.list(new QueryWrapper<Customer>().eq("is_del",0));
    }
}
