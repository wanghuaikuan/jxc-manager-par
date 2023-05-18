package com.lzj.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.pojo.Supplier;
import com.lzj.admin.query.SupplierQuery;
import com.lzj.admin.query.UserQuery;
import com.lzj.admin.service.ISupplierService;
import com.lzj.admin.service.impl.SupplierServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 供应商表 前端控制器
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-15 08:19:57
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Resource
    private ISupplierService supplierService;

    /**
     * 供应商管理主页
     */
    @RequestMapping("/index")
    public String index(){
        return "supplier/supplier";
    }

    /**
     * 供应商列表查询接口
     *
     * @param supplierQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody

    public Map<String, Object> supplierList(SupplierQuery supplierQuery) {
        return supplierService.supplierList(supplierQuery);
    }


    @RequestMapping("addOrUpdateSupplierPage")
    public String addOrUpdateSupplierPage(Integer id, Model model){
        if(null!=id){
            model.addAttribute("supplier",supplierService.getById(id));
        }
        return "supplier/add_update";
    }

    /**
     *
     * @param supplier
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
   public RespBean saveSupplier(Supplier supplier){
        supplierService.saveSupplier(supplier);
        return RespBean.success("供应商添加成功！");
   }
    @RequestMapping("update")
    @ResponseBody
    public RespBean updateSupplier(Supplier supplier){
        supplierService.updateSupplier(supplier);
        return RespBean.success("供应商更新成功！");
    }
    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteSupplier(Integer[] ids){
        supplierService.deleteSupplier(ids);
        return RespBean.success("供应商删除成功！");
    }

    /**
     * 在进货页面展示出所有的供货商
     * @return
     */
    @RequestMapping("allGoodsSuppliers")
    @ResponseBody
    public List<Supplier> allGoodsSuppliers(){
        return supplierService.list(new QueryWrapper<Supplier>().eq("is_del",0));
    }


}
