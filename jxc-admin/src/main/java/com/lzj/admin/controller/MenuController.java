package com.lzj.admin.controller;

import com.lzj.admin.dto.TreeDto;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.pojo.Menu;
import com.lzj.admin.service.IMenuService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-12 09:49:13
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private IMenuService menuService;

    /**
     * 返回所有菜单数据
     * @return
     */
    @ResponseBody
    @RequestMapping("queryAllMenus")
    public List<TreeDto>queryAllMenus(Integer roleId){
         return menuService.queryAllMenus(roleId);
    }
    /**
     * 菜单主页
     */
    @RequestMapping("index")
    public String index(){
        return "menu/menu";
    }

    /**
     * 菜单列表查询接口
     * @return
     */
    @ResponseBody
    @RequestMapping("list")
    public Map<String,Object> menuList(){
        return menuService.menuList();
    }

    @RequestMapping("addMenuPage")
    public String addMenuPage(Integer grade, Integer pId, Model model){
        model.addAttribute("grade",grade);
        model.addAttribute("pId",pId);
        return "menu/add";
    }

    @PostMapping("save")
    @ResponseBody
    public RespBean saveMenu(Menu menu){
        menuService.saveMenu(menu);
        return RespBean.success("菜单添加成功！");
    }
    @RequestMapping("updateMenuPage")
    public String updateMenuPage( Integer id, Model model){
        model.addAttribute("menu",menuService.getById(id));
        return "menu/update";
    }
    @ResponseBody
    @RequestMapping("update")
   public RespBean updateMenu(Menu menu){
        menuService.updateMenu(menu);
        return RespBean.success("菜单记录更新成功！");
   }
    @ResponseBody
    @RequestMapping("delete")
    public RespBean deleteMenu(Integer id){
        menuService.deleteMenuById(id);
        return RespBean.success("菜单删除成功！");
    }
}
