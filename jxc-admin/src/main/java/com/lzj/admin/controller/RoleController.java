package com.lzj.admin.controller;

import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.pojo.Role;
import com.lzj.admin.query.RoleQuery;
import com.lzj.admin.service.IRoleService;
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
 * 角色表 前端控制器
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-12 09:49:27
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    /**
     * 角色管理主页
     */
    @Resource
    private IRoleService IRoleService;
    @RequestMapping("index")
   public String index(){
       return"role/role";
   }

    /**
     * 角色列表接口
     * @param roleQuery
     * @return
     */
   @RequestMapping("list")
   @ResponseBody

    public Map<String,Object> roleList(RoleQuery roleQuery) {
        return IRoleService.roleList(roleQuery);
    }
     /** 用户记录更新接口
     * @param role
     * @return
             */
    @RequestMapping("update")
    @ResponseBody
    public RespBean updateRole(Role role) {
        IRoleService.updateRole(role);
        return RespBean.success("角色记录更新成功!");
    }

    /**
     * 更新角色
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateRolePage")
    public String addOrUpdatePage(Integer id, Model model){
       if(null!=id){
           model.addAttribute("role",IRoleService.getById(id));
       }
       return "role/add_update";
    }


    /**
     * 用户信息更新
     * @param role
     * @return
     */
    @RequestMapping("updateRoleInfo")
    @ResponseBody
    public RespBean updateRoleInfo(Role role) {
        IRoleService.updateRoleInfo(role);
        return RespBean.success("角色信息更新成功");
    }
    /**
     * 角色记录添加接口
     * @param role
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
//    @PreAuthorize("102002")
    public RespBean saveUser(Role role) throws ParamsException {
        IRoleService.saveRole(role);
        return RespBean.success("角色记录添加成功!");
    }

    /**
     * 角色记录删除接口
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
//    @PreAuthorize("102004")
    public RespBean deleteRole(Integer id) throws ParamsException {
        IRoleService.deleteRole(id);
        return RespBean.success("角色记录删除成功!");
    }


    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String,Object>> queryAllRoles(Integer userId){

        return  IRoleService.queryAllRoles(userId);
    }


    /**
     * 权限添加页面
     */
    @RequestMapping("toAddGrantPage")
    public String toAddGrantPage(Integer roleId,Model model){
        model.addAttribute("roleId",roleId);
        return "role/grant";
    }
    /**
     * 用户授权
     */
    @RequestMapping("addGrant")
    @ResponseBody
    public RespBean addGrant(Integer roleId,Integer[] mids)  {
        IRoleService.addGrant(roleId,mids);
        return RespBean.success("角色记录授权成功!");
    }

}
