package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.lzj.admin.dto.TreeDto;
import com.lzj.admin.pojo.Menu;
import com.lzj.admin.mapper.MenuMapper;
import com.lzj.admin.pojo.RoleMenu;
import com.lzj.admin.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.service.IRoleMenuService;
import com.lzj.admin.utils.AssertUtil;
import com.lzj.admin.utils.PageResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sun.reflect.generics.tree.Tree;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-12 09:49:13
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    private IRoleMenuService roleMenuService;

    @Override
    public List<TreeDto> queryAllMenus(Integer roleId) {
        List<TreeDto> treeDtos =this.baseMapper.queryAllMenus();
        List<Integer> roleHasMenuIds= roleMenuService.queryRoleHasAllMenusByRoleId(roleId);
        if(CollectionUtils.isNotEmpty(roleHasMenuIds)){
            treeDtos.forEach(treeDto -> {
                if(roleHasMenuIds.contains(treeDto.getId())){
                    treeDto.setChecked(true);
                }
            });
        }
        return  treeDtos;
    }


    @Override
    public Map<String, Object> menuList() {
        List<Menu> menus = this.list(new QueryWrapper<Menu>().eq("is_del",0));
        return PageResultUtils.getResult((long) menus.size(),menus);
    }

    @Override
    public Menu findMenuByNameAndGradeAndPId(String menuName,Integer grade,Integer pId) {
        return this.getOne(new QueryWrapper<Menu>().eq("is_del",0).eq("name",menuName).eq("grade",grade).eq("p_id",pId));
    }

    @Override
    public Menu findMenuByAclValue(String aclValue) {
        return this.getOne(new QueryWrapper<Menu>().eq("is_del",0).eq("acl_value",aclValue));
    }

    @Override
    public Menu findMenuById(Integer id) {
        return this.getOne(new QueryWrapper<Menu>().eq("is_del",0).eq("id",id));
    }

    @Override
    public Menu findMenuByGradeAndUrl(String url, Integer grade) {
        return this.getOne(new QueryWrapper<Menu>().eq("is_del",0).eq("url",url).eq("grade",grade));
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =Exception.class)
    public void saveMenu(Menu menu) {
        /**
         * 1.参数校验
         *    菜单名 不能为空
         *    菜单层级 grade (0,1,2) 只支持三级菜单
         * 2.同一层级下
         *     菜单名不可重复
         * 3.权限码
         *    非空
         *    全局唯一
         * 4.上级菜单
         *    上级菜单 必须存在
         * 5.url 判断
         *     菜单属于二级菜单  url 不可重复的
         * 6.设置参数
         *    是否删除 isDel  state
         */
        AssertUtil.isTrue(StringUtils.isBlank(menu.getName()),"请输入菜单名!");
        Integer grade = menu.getGrade();
        AssertUtil.isTrue(null == grade || !(grade==0||grade==1||grade==2),"菜单层级不合法!");
        AssertUtil.isTrue(null !=this.findMenuByNameAndGradeAndPId(menu.getName(),menu.getGrade(),menu.getPId()),"该层级下菜单已存在!");
        AssertUtil.isTrue(null !=this.findMenuByAclValue(menu.getAclValue()),"权限码已存在!");
        AssertUtil.isTrue(null == menu.getPId() ||
                null == this.findMenuById(menu.getPId()),"请指定上级菜单!");
        if(grade==2){
            // 三级菜单
            AssertUtil.isTrue(null !=this.findMenuByGradeAndUrl(menu.getUrl(),menu.getGrade()),"该层级下url 不可重复!");
        }
        menu.setIsDel(0);
        menu.setState(0);
        AssertUtil.isTrue(!(this.save(menu)),"菜单添加失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateMenu(Menu menu) {
        /**
         * 1.参数校验
         * 待更新记录必须存在
         *   菜单名 不能为空
         *   菜单等级grade(0,1,2)只支持三级菜单
         *  2.同一层级下
         *    菜单名不可重复
         *   3.权限码 非空且全局唯一
         *   4. 上级菜单必须存在
         *   5.url判断
         *       菜单属于二级菜单，url不可重复
         *   6.设置参数  是否删除  isDel state
         */
        AssertUtil.isTrue(null==menu.getId()||null==this.findMenuById(menu.getId()),"记录不存在！");
        AssertUtil.isTrue(StringUtils.isBlank(menu.getName()),"菜单名不能为空！");
        Integer grade= menu.getGrade();
        AssertUtil.isTrue(null==grade||!(grade==0||grade==1||grade==2),"菜单层级不合法！");
        Menu temp=this.findMenuByNameAndGradeAndPId(menu.getName(),menu.getGrade(),menu.getPId());
        Menu partentTemp=this.findMenuById(temp.getPId());
        AssertUtil.isTrue(null!=temp&&!(temp.getId().equals(menu.getId()))&&(temp.getName().equals(partentTemp)),"该层级下菜单已存在!");
        temp = this.findMenuByAclValue(menu.getAclValue());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(menu.getId())) ,"权限码已存在!");
        AssertUtil.isTrue(null == menu.getPId() ||
                null == this.findMenuById(menu.getPId()),"请指定上级菜单!");
        if(grade==1){
            // 二级菜单
           temp =this.findMenuByGradeAndUrl(menu.getUrl(),menu.getGrade());
            AssertUtil.isTrue(null != temp && !(temp.getId().equals(menu.getId())),"该层级下url 不可重复!");
        }
        AssertUtil.isTrue(!(this.updateById(menu)),"菜单记录更新失败!");
    }

    @Override
    public void deleteMenuById(Integer id) {
        /**
         * 1.参数校验
         *    记录必须存在
         * 2.子菜单
         *    如果存在子菜单，不允许直接删除上级菜单
         * 3.角色菜单表关联
         *    将关联的菜单角色记录一并删除
         * 4.执行菜单删除记录
         */
        Menu menu=this.findMenuById(id);
        AssertUtil.isTrue(null==id||null==this.findMenuById(id),"记录不存在！");
        long count=  this.count(new QueryWrapper<Menu>().eq("is_del",0).eq("p_id",id));
        AssertUtil.isTrue(count>0,"存在子菜单，不允许直接删除！");
        count= roleMenuService.count(new QueryWrapper<RoleMenu>().eq("menu_id",id));
        if(count>0){
            AssertUtil.isTrue(!(roleMenuService.remove(new QueryWrapper<RoleMenu>().eq("menu_id",id))),"菜单删除失败！");
        }
        menu.setIsDel(1);
        AssertUtil.isTrue(!(this.updateById(menu)),"菜单删除失败！");
    }
}
